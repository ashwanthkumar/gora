package org.apache.gora.examples;

import java.io.File;
import java.io.IOException;

import org.apache.gora.compiler.GoraCompiler;

/**
 * Simple compiler driver, meant to aid development of gora within an IDE that
 * can incrementally build the project (in the absense of a gora compiler
 * plugin). Compiles the example json files.
 */
public class CompileExampleTypes {

  public static void main(String[] args) throws IOException {
    System.out.println("Beginning compilation...");
    GoraCompiler.compileSchema(new File[] {
        new File("src/examples/avro/employee.json"),
        new File("src/examples/avro/tokendatum.json"),
        new File("src/examples/avro/immutable_fields.json"),
        new File("src/examples/avro/webpage.json") }, new File(
        "src/examples/java"));
    GoraCompiler.compileSchema(new File[] {
        new File("../gora-tutorial/src/main/avro/metricdatum.json"),
        new File("../gora-tutorial/src/main/avro/pageview.json"), }, new File("../gora-tutorial/src/main/java"));
    System.out.println("Compilation finished.");
  }

}
