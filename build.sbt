name := "scalamuc"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.1"
)
