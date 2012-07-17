package org.apache.gora.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.Schema.Type;
import org.apache.avro.compiler.specific.SpecificCompiler;
import org.apache.gora.persistency.Persistent;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;

public class GoraCompiler extends SpecificCompiler {

  private static final Set<String> GORA_RESERVED_NAMES = new HashSet<String>();
  static {
    GORA_RESERVED_NAMES.addAll(Arrays
        .asList(new String[] { Persistent.DIRTY_BYTES_FIELD_NAME }));
  }

  private static final Set<String> GORA_HIDDEN_FIELD_NAMES = new HashSet<String>();
  static {
    GORA_HIDDEN_FIELD_NAMES.add(Persistent.DIRTY_BYTES_FIELD_NAME);
  }

  public static void compileSchema(File[] srcFiles, File dest)
      throws IOException {
    Schema.Parser parser = new Schema.Parser();

    for (File src : srcFiles) {
      Schema originalSchema = parser.parse(src);
      Schema newSchema = getSchemaWithDirtySupport(originalSchema);
      GoraCompiler compiler = new GoraCompiler(newSchema);
      compiler.setTemplateDir("/org/apache/gora/compiler/templates/");
      compiler.compileToDestination(src, dest);
    }
  }

  public static String generateAppropriateWrapperOrValue(Schema schema) {
    switch (schema.getType()) {
    case MAP:
      return "(value instanceof org.apache.gora.persistency.Dirtyable) ? "
          + "value : new org.apache.gora.persistency.impl.DirtyMapWrapper(value)";
    case ARRAY:
      return "(value instanceof org.apache.gora.persistency.Dirtyable) ? "
          + "value : new org.apache.gora.persistency.impl.DirtyListWrapper(value)";
    default:
      return "value";
    }
  }

  private static int getNumberOfBytesNeededForDirtyBits(Schema originalSchema) {
    return (int) Math.ceil((originalSchema.getFields().size() + 1) * 0.125);
  }

  public static String generateDirtyMethod(Schema schema, Field field) {
    /*
     * TODO: this is dirty. We need to file a bug in avro to get them to open
     * the API so other compilers can use their utility methods
     */
    String getMethod = generateGetMethod(schema, field);
    String dirtyMethod = "is" + getMethod.substring(3) + "Dirty";
    return dirtyMethod;
  }

  public static String generateDefaultValueString(Schema schema,
      String fieldName) {
    if (fieldName == "__g__dirty") {
      return "java.nio.ByteBuffer.wrap(new byte["
          + getNumberOfBytesNeededForDirtyBits(schema) + "])";
    } else {
      throw new IllegalArgumentException(fieldName
          + " is not a gora managed field.");
    }
  }

  public static boolean isNotHiddenField(String fieldName) {
    return !GORA_HIDDEN_FIELD_NAMES.contains(fieldName);
  }

  GoraCompiler(Schema schema) {
    super(schema);
  }

  private static Schema getSchemaWithDirtySupport(Schema originalSchema)
      throws IOException {
    if (originalSchema.getType() != Type.RECORD) {
      throw new IOException("Gora only supports record schemas.");
    }
    List<Field> originalFields = originalSchema.getFields();
    /* make sure the schema doesn't contain the field __g__dirty */
    for (Field field : originalFields) {
      if (GORA_RESERVED_NAMES.contains(field.name())) {
        throw new IOException("Gora schemas cannot contain the field name "
            + field.name());
      }
    }
    Schema newSchema = Schema.createRecord(originalSchema.getName(),
        originalSchema.getDoc(), originalSchema.getNamespace(),
        originalSchema.isError());
    List<Field> newFields = new ArrayList<Schema.Field>();
    byte[] defaultDirtyBytesValue = new byte[getNumberOfBytesNeededForDirtyBits(originalSchema)];
    Arrays.fill(defaultDirtyBytesValue, (byte) 0);
    JsonNode defaultDirtyJsonValue = JsonNodeFactory.instance
        .binaryNode(defaultDirtyBytesValue);
    Field dirtyBits = new Field(Persistent.DIRTY_BYTES_FIELD_NAME,
        Schema.create(Type.BYTES),
        "Bytes used to represent weather or not a field is dirty.",
        defaultDirtyJsonValue);
    newFields.add(dirtyBits);
    for (Field originalField : originalFields) {
      Field newField;
      if (originalField.schema().getType() != Type.RECORD) {
        newField = new Field(originalField.name(), originalField.schema(),
            originalField.doc(), originalField.defaultValue(),
            originalField.order());
      } else {
        // recursively add dirty support
        newField = new Field(originalField.name(),
            getSchemaWithDirtySupport(originalField.schema()),
            originalField.doc(), originalField.defaultValue(),
            originalField.order());
      }
      newFields.add(newField);
    }
    newSchema.setFields(newFields);
    return newSchema;
  }

}
