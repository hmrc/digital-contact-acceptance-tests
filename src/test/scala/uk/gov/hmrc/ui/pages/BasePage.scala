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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.{FluentWait, Wait}
import org.scalatest.concurrent.Futures.PatienceConfig
import play.api.libs.ws.StandaloneWSRequest
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.selenium.webdriver.Driver
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatest.time.{Seconds, Span}
import play.api.libs.json.Json
import uk.gov.hmrc.ui.utils.TestData

import java.time.Duration
import scala.concurrent.Future

trait BasePage extends PageObject with TestData {

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

  def verifyEmail(): Unit = {

    // To get entity ID via sa-api proxy
    val entityIdUrl: String                                       = saApiProxy + ("/entity-resolver/entity-resolver/paye/" + ninoNumber)
    val entityIdUrlResponse: Future[StandaloneWSRequest#Response] = WsClient.url(entityIdUrl).get()
    val resultBody                                                = entityIdUrlResponse.futureValue.body
    val bodyAsJson                                                = Json.parse(resultBody).\("_id").toString
    val extractedEntityId                                         = bodyAsJson.replaceAll(entityIdRegex, "")
    // To get verificaton token via sa-api proxy
    val verificationTokenUrl: String                              =
      saApiProxy + s"/preferences/test-only/preferences-admin/$extractedEntityId/verification-token"
    val getToken: Future[StandaloneWSRequest#Response]            = WsClient.url(verificationTokenUrl).get()
    val verificationToken                                         = getToken.futureValue.body
    // To verify email address using token
    val emailVerificationUrl: String                              = preferenceFrontend + s"sa/print-preferences/verification/$verificationToken"
    val emailVerification                                         = WsClient.url(emailVerificationUrl).get()
    fluentWait
  }
}
