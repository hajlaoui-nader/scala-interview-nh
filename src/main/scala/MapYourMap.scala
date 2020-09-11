/**
  * Tell developer names by the department code
  * Expected result:
  * Map(frontend -> List(Remy, Alexandre), analytics -> List(Pierre), api -> List(Noe))
  */
object MapYourMap {

  val devNames       = Map("dev1" -> "Pierre", "dev2" -> "Remy", "dev3" -> "Noe", "dev4" -> "Alexandre")
  val devDepartments = Map("dev1" -> "analytics", "dev2" -> "frontend", "dev3" -> "api", "dev4" -> "frontend")

  val namesInDepartments: Map[String, List[String]] =
    devNames
      .map {
        case (id, name) => (name, devDepartments.getOrElse(id, "no department assigned"))
      }
      .groupBy(_._2)
      .view
      .mapValues(_.keys.toList)
      .toMap

}
