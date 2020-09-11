/**
  * This is basic language questions so don't use external library or build in function
  */
object BasicScala {

  /**
    * Encode parameter in url format
    *
    * Example:
    *
    * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
    * output : "?sort_by=name&order_by=asc&user_id=12"
    *
    * input  : Map()
    * output : ""
    */
  def encodeParamsInUrl(params: Map[String, String]): String =
    if (params.isEmpty) ""
    else
      params
        .map {
          case (k, v) =>
            s"$k=$v"
        }
        .mkString("?", "&", "")

  // RFC-5322 email validation regex
  val regex =
    "(?im)^(?=.{1,64}@)(?:(\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\"@)|([0-9a-z](?:\\.(?!\\.)|[-!#\\$%&'\\*\\+/=\\?\\^`\\{\\}\\|~\\w])*@))(?=.{1,255}$)(?:(\\[(?:\\d{1,3}\\.){3}\\d{1,3}\\])|((?:(?=.{1,63}\\.)[0-9a-z][-\\w]*[0-9a-z]*\\.)+[a-z0-9][\\-a-z0-9]{0,22}[a-z0-9])|((?=.{1,63}$)[0-9a-z][-\\w]*))$".r

  /**
    * Test if a String is an email
    */
  def isEmail(maybeEmail: String): Boolean =
    regex.pattern.matcher(maybeEmail).matches()

  /**
    * Compute i ^ n
    *
    * Example:
    *
    * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
    * output : 8
    *
    * input : (i = 99, n = 38997)
    * output : 1723793299 <br>
    *  99^38997 :
    *  is far greater than int max value,
    *  for this case the int overflows and we have non-sense number.
    */
  def power(i: Int, n: Int): Int = n match {
    case 0          => 1
    case x if x < 0 => 0 // since the return type is Int, we cant return (1 / power(i,-x)) :: Double
    case x if (x % 2 == 0) =>
      val p = power(i, x / 2)
      p * p
    case x =>
      val p = power(i, x / 2)
      x * p * p
  }
}
