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

package uk.gov.hmrc.ui.pages

import org.apache.pekko.actor.ActorSystem
import org.mongodb.scala.model.{Filters, Updates}
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase, SingleObservableFuture}
import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait, Wait}
import org.scalatest.concurrent.Futures.PatienceConfig
import play.api.libs.ws.StandaloneWSRequest
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.selenium.webdriver.Driver
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
import org.scalatest.time.{Seconds, Span}
import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.preferences
import uk.gov.hmrc.ui.utils.TestData
import uk.gov.hmrc.ui.utils.data.ApiPayLoad
import play.api.libs.ws.DefaultBodyWritables.writeableOf_String

import java.time.Duration
import scala.concurrent.Await

trait BasePage extends PageObject with TestData with ApiPayLoad{

  implicit val patienceConfig: PatienceConfig =
    PatienceConfig(timeout = Span(10, Seconds), interval = Span(5, Seconds))

  implicit val system: ActorSystem = ActorSystem()

  val WsClient = StandaloneAhcWSClient()

  val entityIdRegex = "(\\bJsDefined\\b|\\(|\\)|\")"

  val getRedirectUrlId          = "redirectionUrl"
  val getCredentialStrengthId   = "credentialStrength"
  val getConfidenceLevelId      = "confidenceLevel"
  val getNinoId                 = "nino"
  val onlineRadioButtonId       = "sps-opt-in"
  val postRadioButtonId         = "sps-opt-in-2"
  val getEmailTextFieldId       = "sps-opt-in-email"
  val saUtrRadioButtonId        = "name"
  val ninoRadioButtonId         = "name-2"
  val ItsaIdRadioButtonId       = "name-3"
  val emailIdRadioButtonId      = "name-4"
  val identifierValueTextId     = "value"
  val optUserOutLinkCssSelector = "#main-content > div > div > details > summary > span"
  val optOutUserReasonTextId    = "reason"
  val yesButtonOnSummaryPage    = "#confirm > form > div.govuk-button-group > button"
  val searchButtonOnSearchPage  = "#main-content > div > div > form > button"
  val uniqueReferenceId         = "externalRef.id"
  val messageSourceId           = "externalRef.source"
  val taxIdentifierNameId       = "recipient.taxIdentifier.name"
  val taxIdentifierValueId      = "recipient.taxIdentifier.value"
  val regimeId                  = "recipient.regime"
  val emailId                   = "recipient.email"
  val messageTypeId             = "messageType"
  val alertQueueId              = "alertQueue"
  val englishSubjectId          = "english-subject"
  val welshSubjectId            = "english-subject"
  val englishMessageId          = "english-message-content"
  val welshMessageId            = "welsh-message-content"
  val validFromId               = "validFrom"
  val formIdId                  = "details.formId"
  val issueDateId               = "details.issueDate"
  val batchIdId                 = "details.batchId"
  val sourceDataId              = "details.sourceData"
  val newFormIdId               = "formId"
  val reasonTextId              = "reasonText"
  val encodedTextId             = "encoded-text"
  val rejectButtonId            = "reject"
  val confirmButtonId           = "confirm"
  val approveButtonId           = "approve"
  val decodeButtonId            = "decode"
  val checkYourSettings         = "Checkyoursettings"
  val close                     = "#main-content > div > div > div:nth-child(5) > a"
  val ContinueOptOut            = "submitEmailButton"

  private val PREFERENCESDATABASE = "preferences"
  private val COLLECTION2 = "saIndividualPreferences"

  def mongoClient: MongoClient = MongoClient()
  def preferencesDb: MongoDatabase = mongoClient.getDatabase(PREFERENCESDATABASE)
  def saIndividualPreferencesCollection: MongoCollection[Document] = preferencesDb.getCollection(COLLECTION2)
  
  def fluentWait: Wait[WebDriver] = new FluentWait[WebDriver](Driver.instance)
    .withTimeout(Duration.ofSeconds(3))
    .pollingEvery(Duration.ofSeconds(1))

  def deleteMongoRecordsFromCollection(serviceCollection: String): Unit = {
    val deletePreferencesRecords: String   = preferences + "test-only/preferences-admin/print-suppression"
    val deleteSecureMessageRecords: String = secureMessage + "test-only/delete/secure-messages"

    val collectionMatch = serviceCollection.toLowerCase() match {
      case "preferences"    => WsClient.url(deletePreferencesRecords).delete()
      case "secure message" => WsClient.url(deleteSecureMessageRecords).delete()
    }
    fluentWait
  }

  def verifyEmail(nino:String = ninoNumber): Unit = {

    // To get entity ID via sa-api proxy
    val entityIdUrl: String                                       = saApiProxy + ("/entity-resolver/entity-resolver/paye/" + nino)
    val entityIdUrlResponse: StandaloneWSRequest#Response         = waitGetUrlResult(entityIdUrl)
    val resultBody                                                = entityIdUrlResponse.body
    val bodyAsJson                                                = Json.parse(resultBody).\("_id").toString
    val extractedEntityId                                         = bodyAsJson.replaceAll(entityIdRegex, "")
    // To get verificaton token via sa-api proxy
    val verificationTokenUrl: String                              =
      saApiProxy + s"/preferences/test-only/preferences-admin/$extractedEntityId/verification-token"
    val getToken: StandaloneWSRequest#Response                    = waitGetUrlResult(verificationTokenUrl)
    val verificationToken                                         = getToken.body
    // To verify email address using token
    val emailVerificationUrl: String                              = preferenceFrontend + s"sa/print-preferences/verification/$verificationToken"
    waitGetUrlResult(emailVerificationUrl)
  }

  def bounceVerifyEmail(): Unit = {
    val bounceUrl = preferences + "test-only/preferences-admin/bounce-email"
      val response = Await.result(WsClient.url(bounceUrl)
        .addHttpHeaders("Content-Type" -> "application/json")
        .post(payloadBounceEmail1), 5.seconds)
      assert(response.status == 204)
  }

  def setVersionMajor(): Unit = {
    val json: JsValue = Json.parse(saIndividualPreferencesCollection.find().first().toFuture().futureValue.toJson())
    val entityId = (json \ "entityId").as[String]
    val query = Filters.equal("entityId", entityId)
    val update = Updates.set("termsAndConditions.generic.optInPage.version.major", 0)
    saIndividualPreferencesCollection.findOneAndUpdate(query, update).toFuture().futureValue
  }

  def waitUntilHeader(header: String): Unit = {
    fluentWait.until(driver => driver.findElement(By.cssSelector("#main-content > div > div > h1")).getText.equals(header))
  }

  def waitGetUrlResult(url: String): StandaloneWSRequest#Response = {
    val response: StandaloneWSRequest#Response = Await.result(WsClient.url(url).get(), 5.seconds)
    assert(response.status == 200)
    response
  }

  def navigateToAccount(account: String = bta): Unit = {
    val url = digitalContactDemoFrontend + account
    Driver.instance.navigate.to(url)
    fluentWait.until(ExpectedConditions.urlContains(url))
  }

  def selectLanguageWelsh(): Unit = {
    val welshLink: By = By.cssSelector("body > header > section > div > nav > ul > li:nth-child(2) > a")
    click(welshLink)
  }

  def selectLanguageEnglish(): Unit = {
    val englishLink: By = By.cssSelector("body > header > section > div > nav > ul > li:nth-child(1) > a")
    click(englishLink)
  }

  def clickOnBackLink(): Unit = {
    click(By.cssSelector("body > div > div > div:nth-child(1) > div > a"))
  }
  
  def navigateToUrl(url: String): Unit = {
    Driver.instance.navigate.to(url)
    fluentWait.until(ExpectedConditions.urlContains(url))
  }

  def bounceChangedEmail(): Unit = {
    val bounceUrl = preferences + "test-only/preferences-admin/bounce-email"
    val response = Await.result(WsClient.url(bounceUrl)
      .addHttpHeaders("Content-Type" -> "application/json")
      .post(payloadBounceEmail2), 5.seconds)
    assert(response.status == 204)
  }
  
  def waitForText(selector:String, text: String): Unit = {
    fluentWait.until(driver => driver.findElement(By.cssSelector(selector)).getText.equals(text))

  }
}

