/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.utils

import org.mongodb.scala.model.Filters
import org.mongodb.scala.{MongoClient, MongoDatabase, SingleObservableFuture}
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.selenium.webdriver.Driver

object DBTestSupport {
  private val MESSAGEDATABASE  = "message"
  private val ENTITYDB         = "entity-resolver"
  private val PAYENOTIFICATIONDB = "paye-notification"

  def mongoClient: MongoClient = MongoClient()
  def messagedb: MongoDatabase = mongoClient.getDatabase(MESSAGEDATABASE)
  def entityDB: MongoDatabase  = mongoClient.getDatabase(ENTITYDB)
  def payeNotificationDB: MongoDatabase = mongoClient.getDatabase(PAYENOTIFICATIONDB)

  def deleteDatabase(database: String): Unit = {
    Driver.instance.manage().deleteAllCookies()
    database match {
      case "conversation" => messagedb.getCollection("conversation").deleteMany(Filters.empty()).toFuture().futureValue
      case "entity"       => entityDB.getCollection("entity").deleteMany(Filters.empty()).toFuture().futureValue
      case "printSuppressionAlerts" => entityDB.getCollection("printSuppressionAlerts").deleteMany(Filters.empty()).toFuture().futureValue
    }
  }

  def getPrintSuppressionAlertsMongoId: String = {
    val json = payeNotificationDB.getCollection("printSuppressionAlerts").find().first().toFuture().futureValue.toJson()
    val status: JsValue = Json.parse(json)
    val statusGot = (status \ "status").as[String]
    statusGot
  }
}
