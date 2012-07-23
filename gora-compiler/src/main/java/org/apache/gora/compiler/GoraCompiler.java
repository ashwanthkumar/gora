package org.apache.gora.compiler;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.Schema.Type;
import org.apache.avro.compiler.specific.SpecificCompiler;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;

public class GoraCompiler extends SpecificCompiler {

  public static String DIRTY_BYTES_FIELD_NAME = "__g__dirty";
  public static final int FIRST_UNMANAGED_FIELD_INDEX = 1;

  private static final Set<String> GORA_RESERVED_NAMES = new HashSet<String>();
  static {
    GORA_RESERVED_NAMES.addAll(Arrays
        .asList(new String[] { DIRTY_BYTES_FIELD_NAME }));
  }

  private static final Set<String> GORA_HIDDEN_FIELD_NAMES = new HashSet<String>();
  static {
    GORA_HIDDEN_FIELD_NAMES.add(DIRTY_BYTES_FIELD_NAME);
  }

  public static void compileSchema(File[] srcFiles, File dest)
      throws IOException {
    for (File src : srcFiles) {
      Schema.Parser parser = new Schema.Parser();
      Schema originalSchema = parser.parse(src);
      Schema newSchema = getSchemaWithDirtySupport(originalSchema);
      GoraCompiler compiler = new GoraCompiler(newSchema);
      compiler.setTemplateDir("/org/apache/gora/compiler/templates/");
      compiler.compileToDestination(src, dest);
    }
  }

  public static String generateAppropriateImmutabilityModifier(Schema schema) {
    switch (schema.getType()) {
    case STRING:
      return ".toString()";
    case BYTES:
      return ".asReadOnlyBuffer()";
    default:
      return "";
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
    case STRING:
      return "value.toString()";
    case BYTES:
      return "deepCopyToReadOnlyBuffer(value)";
    default:
      return "value";
    }
  }

  private static int getNumberOfBytesNeededForDirtyBits(Schema originalSchema) {
    return (int) Math.ceil((originalSchema.getFields().size() + 1) * 0.125);
  }

  public static String generateDirtyMethod(Schema schema, Field field) {
    /*
     * TODO: See AVRO-1127. This is dirty. We need to file a bug in avro to get
     * them to open the API so other compilers can use their utility methods
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
    switch (originalSchema.getType()) {
    case RECORD:
      return getRecordSchemaWithDirtySupport(originalSchema);
    case UNION:
      return getUnionSchemaWithDirtySupport(originalSchema);
    case MAP:
      return getMapSchemaWithDirtySupport(originalSchema);
    case ARRAY:
      return getArraySchemaWithDirtySupport(originalSchema);
    default:
      return originalSchema;
    }
  }

  private static Schema getArraySchemaWithDirtySupport(Schema originalSchema)
      throws IOException {
    return Schema.createArray(getSchemaWithDirtySupport(originalSchema
        .getElementType()));
  }

  private static Schema getMapSchemaWithDirtySupport(Schema originalSchema)
      throws IOException {
    return Schema.createMap(getSchemaWithDirtySupport(originalSchema
        .getValueType()));
  }

  private static Schema getUnionSchemaWithDirtySupport(Schema originalSchema)
      throws IOException {
    List<Schema> schemaTypes = originalSchema.getTypes();
    List<Schema> newTypeSchemas = new ArrayList<Schema>();
    for (int i = 0; i < schemaTypes.size(); i++) {
      Schema currentTypeSchema = schemaTypes.get(i);
      newTypeSchemas.add(getSchemaWithDirtySupport(currentTypeSchema));
    }
    return Schema.createUnion(newTypeSchemas);
  }

  private static Schema getRecordSchemaWithDirtySupport(Schema originalSchema)
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
    Field dirtyBits = new Field(DIRTY_BYTES_FIELD_NAME,
        Schema.create(Type.BYTES),
        "Bytes used to represent weather or not a field is dirty.",
        defaultDirtyJsonValue);
    newFields.add(dirtyBits);
    for (Field originalField : originalFields) {
      // recursively add dirty support
      Field newField = new Field(originalField.name(),
          getSchemaWithDirtySupport(originalField.schema()),
          originalField.doc(), originalField.defaultValue(),
          originalField.order());
      newFields.add(newField);
    }
    newSchema.setFields(newFields);
    return newSchema;
  }

  public static String generateNullCheckIfAppropriate(Field field, String mangledName) {
    switch (field.schema().getType()) {
    case UNION:
    case MAP:
    case ARRAY:
    case BYTES:
    case STRING:
      return mangledName + " == null ? null : ";
    default:
      return "";
    }
  }

}
