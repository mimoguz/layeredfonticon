# Layered Font Icon Support For Java Swing / FlatLaf

This is a very basic layered font icon utility that I extracted from another project of mine, for Java 11+ and Scala 3. It lets you use your own font icons in Swing applications, optionally with multiple layers in one icon. Below screenshot shows several examples using three layers.

![Screenshot](./.github/images/screenshot-2023-01-29.png)

To see how to use, please check java and scala demos, and documentation comments in the [core/.../Layer.scala](./core/src/main/scala/io/github/mimoguz/layeredfonticon/Layer.scala) file.

The 'basic' project offers Java Swing support without any other dependencies. The 'flat' project adds a little bit better support for the magnificent [FlatLaf](https://www.formdev.com/flatlaf/) look and feel.

## Building

You will need JDK 11+ and [sbt](https://www.scala-sbt.org/index.html). Then, running

    sbt publishLocal

in the project root will publish the artefacts to your local ivy repository and list what's published.
