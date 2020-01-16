addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.7")

unmanagedJars in Compile += Attributed.blank(
  file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))

fork in run := true