package models

case class FunctionInfo(group: String,
                        name: String,
                        typArgs: List[String],
                        args: List[TypeInfo],
                        rtn: String,
                        image: String,
                        tags: List[Tag],
                        description: String)
