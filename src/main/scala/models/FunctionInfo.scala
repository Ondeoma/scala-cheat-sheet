package models

import cats.syntax.all.*
import enums.FunctionGroup

case class FunctionInfo(group: FunctionGroup,
                        name: String,
                        typArgs: List[String],
                        args: List[ArgsInfo],
                        rtn: String,
                        image: String,
                        tags: List[Tag],
                        description: String)

object FunctionInfo {

  def noArg(group: FunctionGroup,
            name: String,
            typArgs: List[String],
            rtn: String,
            image: String,
            tags: List[Tag],
            description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, Nil, rtn, image, tags, description)
  }

  def oneArg(group: FunctionGroup,
             name: String,
             typArgs: List[String],
             arg: TypeInfo,
             rtn: String,
             image: String,
             tags: List[Tag],
             description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, List(ArgsInfo(List(arg), false)), rtn, image, tags, description)
  }

  def nArg(group: FunctionGroup,
           name: String,
           typArgs: List[String],
           args: List[TypeInfo],
           rtn: String,
           image: String,
           tags: List[Tag],
           description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, List(ArgsInfo(args, false)), rtn, image, tags, description)
  }

  def impArg(group: FunctionGroup,
             name: String,
             typArgs: List[String],
             arg: TypeInfo,
             rtn: String,
             image: String,
             tags: List[Tag],
             description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, List(ArgsInfo(List(arg), true)), rtn, image, tags, description)
  }

  def impNArg(group: FunctionGroup,
              name: String,
              typArgs: List[String],
              args: List[TypeInfo],
              rtn: String,
              image: String,
              tags: List[Tag],
              description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, List(ArgsInfo(args, true)), rtn, image, tags, description)
  }

}
