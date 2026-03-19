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
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.pages.AuthWizardPage.{deleteAllPreferencesCollection, ninoNumber, saApiProxy}

import java.time.Duration

trait BasePage extends PageObject {

  implicit val system: ActorSystem = ActorSystem()

  val WsClient = StandaloneAhcWSClient()

  val getRedirectUrlId = "redirectionUrl"
  val getCredentialStrengthId = "credentialStrength"
  val getConfidenceLevelId = "confidenceLevel"
  val getNinoId = "nino"
  val onlineRadioButtonId = "sps-opt-in"
  val postRadioButtonId = "sps-opt-in-2"
  val getEmailTextFieldId = "sps-opt-in-email"

  def fluentWait: Wait[WebDriver] = new FluentWait[WebDriver](Driver.instance)
    .withTimeout(Duration.ofSeconds(3))
    .pollingEvery(Duration.ofSeconds(1))

  def deletePreferencesCollection(): Unit = {
    val deletePreferencesRecords: String = deleteAllPreferencesCollection() + "test-only/preferences-admin/print-suppression"
    WsClient.url(deletePreferencesRecords).delete()
  }
//
//  def emailVerification(): Unit = {
//    val emailVerificationId : String
//  }

  def getEntityId(): Unit = {
    val entityId: String = saApiProxy() + (("/entity-resolver/entity-resolver/paye/")+ninoNumber)
    println("EntityIdRequest============================" + entityId)
    val entityIdResponse = WsClient.url(entityId).get()
    println("Response============================" + entityIdResponse)
    entityIdResponse.value

  }

//  def emailVerificationUrl(): String = TestEnvironment.url("/sa/print-preferences/verification/" + pendingEmailVerificationToken())

}

