import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future {
    ceos.find(_.id == id)
  }
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id: String): Future[Option[Enterprise]] = Future {
    enterprises.find(_.id == id)
  }
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future {
    enterprises.find(_.ceo_id == ceo_id)
  }
}

object WhatsWrong2 {

  //Review this code. What could be done better ? How would you do it ?
  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    val ceobyId        = CEODao.byId(ceo_id.get)
    val entrepriseById = EnterpriseDao.byCEOId(ceo_id.get)
    for {
      ceo <- ceobyId
      enterprise <- entrepriseById
    } yield {
      (ceo, enterprise)
    }
  }

  /**
    * I changed the "ceo_id" from Option[String] to String,
    * the caller should handle the case when ceo_id is None.
    * I renamed the variable to camelCase.
    *
    * Option.get is dangerous and can throw exception, prefer pattern matching to handle both cases
    *
    * The old implementation is done sequentially, prefer parallel execution
    */
  def getCEOAndEnterpriseParallelExecution(ceoId: String): Future[(Option[CEO], Option[Enterprise])] = {
    val ceobyId        = CEODao.byId(ceoId)
    val entrepriseById = EnterpriseDao.byCEOId(ceoId)
    for {
      ceo <- ceobyId
      enterprise <- entrepriseById
    } yield {
      (ceo, enterprise)
    }
  }

  /**
    * The previous implementation is parallel, but the futures are started right away in val definitions
    * To add a lazy behavior, we can add the lazy keyword
    *
    * please note that when using futures:
    * - The future is executed when defined
    * - Even when there's a timeout constraint, the future continues its execution after the timeout exception
    * - Futures are not Referential Transparent
    *
    * Prefer using a pure data structure to model side effectful expressions as values.
    * ZIO, Cats effect IO, Monix.Task offer this kind of structure and guarantee the RT.
    */
  def getCEOAndEnterpriseLazyParallelExecution(ceoId: String): Future[(Option[CEO], Option[Enterprise])] = {
    lazy val ceobyId        = CEODao.byId(ceoId)
    lazy val entrepriseById = EnterpriseDao.byCEOId(ceoId)
    ceobyId.zip(entrepriseById)
  }
}
