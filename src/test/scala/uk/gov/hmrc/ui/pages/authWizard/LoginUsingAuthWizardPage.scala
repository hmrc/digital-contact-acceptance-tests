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

package uk.gov.hmrc.ui.pages.authWizard

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.preferencesAdmin.PreferencesAdminPage.{click, sendKeys}
import uk.gov.hmrc.ui.utils.GeneratedTestData

object LoginUsingAuthWizardPage extends BasePage {

  private val authWizardBaseUrl: String = TestEnvironment.url("auth-wizard")
  val saApiProxyBaseUrl: String         = TestEnvironment.url("sa-api-proxy")
  var authPageTitle: String             = "Authority Wizard"
  val v4Message: String                 = digitalContactDemoFrontend + "/v4-message"
  val mdtpMessageInbox: String          = customerAdvisorFrontend + "/customer-advisors-frontend/inbox"
  val mdtpMessageSautr: String          = customerAdvisorFrontend + "/inbox/"

  def messagesUsingRegimeRedirectUrl(regimeType: String): String = digitalContactDemoFrontend.concat(s"/messages?regime=$regimeType")
  def messagesUsingEnrolmentKeyRedirectUrl(enrolmentKey: String): String = messageFrontend.concat(s"/messages?taxIdentifiers=$enrolmentKey")
  def messagesUsingEnrolmentKeyAndRegimeRedirectUrl(enrolmentKey: String, regimeType: String): String = digitalContactDemoFrontend.concat(s"/messages?regime=$regimeType&taxIdentifiers=$enrolmentKey")

  def pageLoad(): Unit = {
    get(authWizardBaseUrl)
    fluentWait.until(ExpectedConditions.urlContains(authWizardBaseUrl))
  }

  def pageTitle(): Unit =
    getTitle

  def loginPTAUsingAuthWizardByNinoOnly(): Unit = {
    val getRedirectUrl: By        = By.id(getRedirectUrlId)
    val getCredentialStrength: By = By.id(getCredentialStrengthId)
    val getConfidenceLevel: By    = By.id(getConfidenceLevelId)
    val getNinoNumber: By         = By.id(getNinoId)

    sendKeys(getRedirectUrl, digitalContactDemoFrontend + pta)
    selectByValue(getCredentialStrength, credentialStrength)
    selectByValue(getConfidenceLevel, confidenceLevel)
    sendKeys(getNinoNumber, GeneratedTestData.ninoNumber)
    click(By.id("submit"))
    fluentWait
  }

  def loginIntoAccountByAuthWizard(enrolmentType: String, account: String=bta, nino: String=GeneratedTestData.ninoNumber): Unit = {
    val getRedirectUrl: By = By.id(getRedirectUrlId)
    val getCredentialStrength: By = By.id(getCredentialStrengthId)
    val getConfidenceLevel: By = By.id(getConfidenceLevelId)
    val getNinoNumber: By = By.id(getNinoId)
    val enrolmentKeyId: By = By.id("enrolment[0].name")
    val enrolmentNameId: By = By.id("input-0-0-name")
    val enrolmentValueId: By = By.id("input-0-0-value")

    sendKeys(getRedirectUrl, digitalContactDemoFrontend + account)
    selectByValue(getCredentialStrength, credentialStrength)
    selectByValue(getConfidenceLevel, confidenceLevel)
    sendKeys(getNinoNumber, nino)

    if(enrolmentType != "NoSautr") {
      sendKeys(enrolmentKeyId, enrolmentKey)
      sendKeys(enrolmentNameId, identifierName)
      enrolmentType match {
        case "sautr" => sendKeys(enrolmentValueId, GeneratedTestData.identifierValue)
        case "sautr2" => sendKeys(enrolmentValueId, GeneratedTestData.identifierValue2)
        case _ => throw new IllegalArgumentException(s"Unknown UTR Value")
      }
    }
    click(By.id("submit"))
  }

  def logIntoDemoFrontendForV4(): Unit = {
    pageLoad()
    val getRedirectUrl: By = By.id(getRedirectUrlId)
    sendKeys(getRedirectUrl, v4Message)
    click(By.id("submit"))
    fluentWait
  }

  def logIntoCustomerAdvisorMessageInboxPage(): Unit = {
    pageLoad()
    val getRedirectUrl: By = By.id(getRedirectUrlId)
    sendKeys(getRedirectUrl, mdtpMessageInbox)
    click(By.id("submit"))
    fluentWait
  }

  def logIntoCustomerAdvisorMessageSautrPage(sautr: String): Unit = {
    pageLoad()
    val getRedirectUrl: By = By.id(getRedirectUrlId)
    sendKeys(getRedirectUrl, mdtpMessageSautr + GeneratedTestData.identifierValue)
    sautr match {
      case "correct" => sendKeys(getRedirectUrl, mdtpMessageSautr + GeneratedTestData.identifierValue)
      case "wrong"   => sendKeys(getRedirectUrl, mdtpMessageSautr + GeneratedTestData.identifierValue2)
    }
    click(By.id("submit"))
    fluentWait
  }

  def logIntoMessage(enrolmentType: String, redirectType: String, regime: String = "sautr", nino: String=GeneratedTestData.ninoNumber): Unit = {
    val getRedirectUrl: By = By.id(getRedirectUrlId)
    val getCredentialStrength: By = By.id(getCredentialStrengthId)
    val getConfidenceLevel: By = By.id(getConfidenceLevelId)
    val getNinoNumber: By = By.id(getNinoId)
    val enrolmentKeyId: By = By.id("enrolment[0].name")
    val enrolmentNameId: By = By.id("input-0-0-name")
    val enrolmentValueId: By = By.id("input-0-0-value")
    
    val selectedEnrolmentKey: String =
      enrolmentType match {
        case "sdil" => enrolmentKeySdil
        case "fhdds" => enrolmentKeyFhdds
        case _       => ""
      }
    
    val redirectUrl =
      redirectType match {

        case "regime" =>
          messagesUsingRegimeRedirectUrl(
            regime.toLowerCase()
          )

        case "enrolmentKey" =>
          messagesUsingEnrolmentKeyRedirectUrl(selectedEnrolmentKey)

        case "regimeAndEnrolmentKey" =>
          messagesUsingEnrolmentKeyAndRegimeRedirectUrl(
            selectedEnrolmentKey,
            regime.toLowerCase()
          )
        case _ =>
          throw new IllegalArgumentException(
            s"Unknown redirect type: $redirectType"
          )
      }

    pageLoad()
    sendKeys(getRedirectUrl, redirectUrl)
    selectByValue(getCredentialStrength, credentialStrength)
    selectByValue(getConfidenceLevel, confidenceLevel)
    sendKeys(getNinoNumber, nino)
    
    if(enrolmentType == "itsa") {
      sendKeys(enrolmentKeyId, enrolmentKeyItsa)
      sendKeys(enrolmentNameId, taxIdentifierNameItsaValue)
      sendKeys(enrolmentValueId, GeneratedTestData.itsaIdentifierValue)
    }
    else if(enrolmentType == "vat"){
      sendKeys(enrolmentKeyId, enrolmentKeyVat)
      sendKeys(enrolmentNameId, taxIdentifierNameVatValue)
      sendKeys(enrolmentValueId, GeneratedTestData.vatVrnIdentifierValue)
    }
    else if(enrolmentType == "ioss"){
      sendKeys(enrolmentKeyId, enrolmentKeyIoss)
      sendKeys(enrolmentNameId, taxIdentifierNameIossValue)
      sendKeys(enrolmentValueId, GeneratedTestData.iossIdentifierValue)
    }
    else if(enrolmentType == "ioss inter"){
      sendKeys(enrolmentKeyId, enrolmentKeyIossInter)
      sendKeys(enrolmentNameId, taxIdentifierNameIossInterValue)
      sendKeys(enrolmentValueId, GeneratedTestData.iossInterIdentifierValue)
    }
    else if(enrolmentType == "oss"){
      sendKeys(enrolmentKeyId, enrolmentKeyOss)
      sendKeys(enrolmentNameId, taxIdentifierNameOssValue)
      sendKeys(enrolmentValueId, GeneratedTestData.ossIdentifierValue)
    }
    else if(enrolmentType == "ad"){
      sendKeys(enrolmentKeyId, enrolmentKeyAd)
      sendKeys(enrolmentNameId, taxIdentifierNameAdValue)
      sendKeys(enrolmentValueId, GeneratedTestData.adIdentifierValue)
    }
    else if(enrolmentType == "ioss netp"){
      sendKeys(enrolmentKeyId, enrolmentKeyIossNetp)
      sendKeys(enrolmentNameId, taxIdentifierNameIossNetpValue)
      sendKeys(enrolmentValueId, GeneratedTestData.iossNetpIdentifierValue)
    }
    else if(enrolmentType == "sdil"){
      sendKeys(enrolmentKeyId, enrolmentKeySdil)
      sendKeys(enrolmentNameId, taxIdentifierNameSdilValue)
      sendKeys(enrolmentValueId, GeneratedTestData.identifierSDILValidValue)
    }
    else if(enrolmentType == "fhdds"){
      sendKeys(enrolmentKeyId, enrolmentKeyFhdds)
      sendKeys(enrolmentNameId, taxIdentifierNameFhddsValue)
      sendKeys(enrolmentValueId, GeneratedTestData.identifierFHDDSValidValue)
    }
    else if (enrolmentType != "NoSautr") {
      sendKeys(enrolmentKeyId, enrolmentKey)
      sendKeys(enrolmentNameId, identifierName)
      enrolmentType match {
        case "sautr" => sendKeys(enrolmentValueId, GeneratedTestData.identifierValue)
        case "sautr2" => sendKeys(enrolmentValueId, GeneratedTestData.identifierValue2)
        case _ => throw new IllegalArgumentException(s"Unknown UTR Value")
      }
    }
    click(By.id("submit"))
  }
}
