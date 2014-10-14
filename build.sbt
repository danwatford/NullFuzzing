name := "NullFuzzing"

version := "1.0"

scalaVersion := "2.11.2"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "com.pholser" % "junit-quickcheck-core" % "0.4-beta-2" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.6" % "test"

libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"

scalacOptions ++= Seq("-feature")
