package models

import cats.syntax.all.*

case class FunctionInfo(group: String,
                        name: String,
                        typArgs: List[String],
                        args: List[ArgsInfo],
                        rtn: String,
                        image: String,
                        tags: List[Tag],
                        description: String)

object FunctionInfo {

  def noArg(group: String,
             name: String,
             typArgs: List[String],
             rtn: String,
             image: String,
             tags: List[Tag],
             description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, Nil, rtn, image, tags, description)
  }
  
  def oneArg(group: String,
             name: String,
             typArgs: List[String],
             arg: TypeInfo,
             rtn: String,
             image: String,
             tags: List[Tag],
             description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, List(ArgsInfo(List(arg), false)), rtn, image, tags, description)
  }

  def nArg(group: String,
           name: String,
           typArgs: List[String],
           args: List[TypeInfo],
           rtn: String,
           image: String,
           tags: List[Tag],
           description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, List(ArgsInfo(args, false)), rtn, image, tags, description)
  }

  def impArg(group: String,
             name: String,
             typArgs: List[String],
             arg: TypeInfo,
             rtn: String,
             image: String,
             tags: List[Tag],
             description: String): FunctionInfo = {
    FunctionInfo(group, name, typArgs, List(ArgsInfo(List(arg), true)), rtn, image, tags, description)
  }

  def impNArg(group: String,
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
