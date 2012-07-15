package org.apache.gora.compiler;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestCompiler {

  @Test
  public void canCompileClasses() throws IOException {
    GoraCompiler.compileSchema(new File[] {
        new File("src/examples/avro/employee.json"),
        new File("src/examples/avro/tokendatum.json"),
        new File("src/examples/avro/webpage.json") }, new File(
        "src/examples/java"));
    GoraCompiler.compileSchema(new File[] {
        new File("../gora-tutorial/src/main/avro/metricdatum.json"),
        new File("../gora-tutorial/src/main/avro/pageview.json"), }, new File(
        "../gora-tutorial/src/main/java"));
  }

}
