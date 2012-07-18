package org.apache.gora.tutorial.log;

import java.io.File;
import java.io.IOException;

import org.apache.gora.compiler.GoraCompiler;

/**
 * A simple driver class, meant to assist development within eclipse. Compiles
 * the json classes for the examples, in the absence of an actual gora compiler
 * maven plugin.
 */
public class CompileLogTypes {

  public static void main(String[] args) throws IOException {
    GoraCompiler.compileSchema(new File[] {
        new File("src/main/avro/metricdatum.json"),
        new File("src/main/avro/pageview.json"), }, new File("src/main/java"));
  }

}
