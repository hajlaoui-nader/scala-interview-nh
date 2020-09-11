object WhatsWrong1 {

  trait Interface {
    val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {

    override val city = "Paris"
  }

  /**
    * prints "Paris" as overriden in Supporter case object
    */
  Supporter.city //What does this print ?

  /**
    * prints "Ici c'est nul !" because support is evaluated before the override of city
    * (which is evaluated to null in the trait Interface)
    * To fix this we can change the support definition from val to def or lazy val
    */
  Supporter.support //What does this print and why ? How to fix it ?
}
