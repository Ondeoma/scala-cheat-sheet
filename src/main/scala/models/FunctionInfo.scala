package models

import cats.syntax.all.*

case class FunctionInfo(group: String,
                        name: String,
                        typArgs: List[String],
                        args: Option[List[TypeInfo]],
                        rtn: String,
                        image: String,
                        tags: List[Tag],
                        description: String)

object FunctionInfo {

  def apply(group: String,
            name: String,
            typArgs: List[String],
            args: List[TypeInfo],
            rtn: String,
            image: String,
            tags: List[Tag],
            description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, args.some, rtn, image, tags, description)
  }

}
