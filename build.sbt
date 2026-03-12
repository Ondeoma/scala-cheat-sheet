import org.scalajs.linker.interface.ModuleSplitStyle

lazy val scs: Project = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaVersion := "3.5.2",
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(
          ModuleSplitStyle.SmallModulesFor(List("scs")))
    },
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.8.1",
  )
