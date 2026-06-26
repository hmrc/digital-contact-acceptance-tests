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

  def messagesUsingRegimeRedirectUrl(regimeType: String): String                                        =
    digitalContactDemoFrontend.concat(s"/messages?regime=$regimeType")
  def messagesUsingEnrolmentKeyRedirectUrl(enrolmentKey: String): String                                =
    messageFrontend.concat(s"/messages?taxIdentifiers=$enrolmentKey")
  def messagesUsingEnrolmentKeyAndRegimeRedirectUrl(enrolmentKey: String, regimeType: String): String   =
    digitalContactDemoFrontend.concat(s"/messages?regime=$regimeType&taxIdentifiers=$enrolmentKey")
  def messageCountUsingEnrolmentKeyAndRegimeRedirectUrl(taxIdentifiers: String, regime: String): String =
    messageFrontend.concat(s"/messages/count?regimes=$regime&taxIdentifiers=$taxIdentifiers")

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

  def loginIntoAccountByAuthWizard(
    enrolmentType: String,
    account: String = bta,
    nino: String = GeneratedTestData.ninoNumber
  ): Unit = {
    val getRedirectUrl: By        = By.id(getRedirectUrlId)
    val getCredentialStrength: By = By.id(getCredentialStrengthId)
    val getConfidenceLevel: By    = By.id(getConfidenceLevelId)
    val getNinoNumber: By         = By.id(getNinoId)
    val enrolmentKeyId: By        = By.id("enrolment[0].name")
    val enrolmentNameId: By       = By.id("input-0-0-name")
    val enrolmentValueId: By      = By.id("input-0-0-value")

    sendKeys(getRedirectUrl, digitalContactDemoFrontend + account)
    selectByValue(getCredentialStrength, credentialStrength)
    selectByValue(getConfidenceLevel, confidenceLevel)
    sendKeys(getNinoNumber, nino)

    if (enrolmentType != "NoSautr") {
      sendKeys(enrolmentKeyId, enrolmentKey)
      sendKeys(enrolmentNameId, identifierName)
      enrolmentType match {
        case "sautr"  => sendKeys(enrolmentValueId, GeneratedTestData.identifierValue)
        case "sautr2" => sendKeys(enrolmentValueId, GeneratedTestData.identifierValue2)
        case _        => throw new IllegalArgumentException(s"Unknown UTR Value")
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

  def logIntoMessage(
    enrolmentType: String,
    redirectType: String,
    regime: String = "sautr",
    nino: String = GeneratedTestData.ninoNumber
  ): Unit = {
    val getRedirectUrl: By        = By.id(getRedirectUrlId)
    val getCredentialStrength: By = By.id(getCredentialStrengthId)
    val getConfidenceLevel: By    = By.id(getConfidenceLevelId)
    val getNinoNumber: By         = By.id(getNinoId)
    val enrolmentKeyId: By        = By.id("enrolment[0].name")
    val enrolmentNameId: By       = By.id("input-0-0-name")
    val enrolmentValueId: By      = By.id("input-0-0-value")
    val enrolmentListNino         = List("pta", "sautr", "itsa")
    val multipleEoriList          = List(
      "secure-message-stub-tags",
      "secure-message-stub-multipleEnrolment",
      "secure-message-stub-enrolment-tag",
      "secure-message-stub-enrolmentKey-tag",
      "secure-message-stub-enrolmentKey-enrolment",
      "secure-message-stub-enrolmentKey-enrolment-tag",
      "twsm-enrolmentKey-and-tag",
      "twsm-enrolmentKey-enrolment-tag"
    )

    val selectedEnrolmentKey: String =
      enrolmentType match {
        case "sdil" | "fhdds"     => enrolmentKeyObtds
        case "epaye"              => enrolmentKeyEpaye
        case "epayeTaxIdentifier" => enrolmentKeytaxIdentifierEpaye
        case "pptTaxIdentifier"   => enrolmentKeytaxIdentifierPpt
        case _                    => ""
      }

    val redirectUrl =
      redirectType match {
        case "secure-message-stub"                            =>
          secureMessagingBaseUrl + secureMessageStub + conversationList
        case "secure-message-stub-tag"                        =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + tagMessageFiltering
        case "secure-message-stub-tags"                       =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + tagsMessageFiltering
        case "secure-message-stub-enrolment"                  =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + s"?enrolment=HMRC-CUS-ORG~EORINumber~${GeneratedTestData.identifierValueEori}"
        case "secure-message-stub-enrolmentKey"               =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + enrolmentKeyMessageFiltering
        case "secure-message-stub-multipleEnrolment"          =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + s"?enrolment=HMRC-CUS-ORG~EORINumber~${GeneratedTestData.identifierValueEori}&enrolment=HMRC-CUS-ORG~EORINumber~${GeneratedTestData.identifierValueEori2}"
        case "secure-message-stub-enrolment-tag"              =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + s"?enrolment=HMRC-CUS-ORG~EORINumber~${GeneratedTestData.identifierValueEori}" + "&tag=notificationType~Direct%20Debit"
        case "secure-message-stub-enrolmentKey-tag"           =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + enrolmentKeyAndTagMessageFiltering
        case "secure-message-stub-enrolmentKey-enrolment"     =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + "?enrolmentKey=HMRC-CUS-ORG&enrolment=HMRC-CUS-ORG~EORINumber~" + s"${GeneratedTestData.identifierValueEori}"
        case "secure-message-stub-enrolmentKey-enrolment-tag" =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + "?enrolmentKey=HMCE-VATDEC-ORG&enrolment=HMRC-CUS-ORG~EORINumber~" + s"${GeneratedTestData.identifierValueEori}" + "&tag=notificationType~Direct%20Debit"
        case "secure-message-stub-vat-dec-enrolment"          =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + enrolmentKeyAndVatdecMessageFiltering
        case "secure-message-conversation"                    =>
          secureMessagingBaseUrl + secureMessageStub
        case "twsm-tag"                                       => secureMessagingBaseUrl + secureMessageStub + conversationList + tagCdsMessageFiltering
        case "twsm-multiple-tag"                              =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + tagCdsMessageFiltering
        case "twsm-enrolment"                                 => secureMessagingBaseUrl + secureMessageStub + conversationList + enrolmentKeyFiltering
        case "twsm-enrolment-and-tag"                         =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + s"?enrolment=HMRC-CUS-ORG~EORINumber~${GeneratedTestData.identifierValueEori}" + "&tag=notificationType~CDS-EXPORTS"
        case "twsm-enrolmentKey-and-tag"                      =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + enrolmentKeyAndTagFiltering
        case "twsm-enrolmentKey-enrolment-tag"                =>
          secureMessagingBaseUrl + secureMessageStub + conversationList + "?enrolmentKey=HMCE-VATDEC-ORG&enrolment=HMRC-CUS-ORG~EORINumber~" + s"${GeneratedTestData.identifierValueEori2}" + "&tag=notificationType~CDS-EXPORTS"

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

        case "regimeAndEnrolmentKeyMessageCount" =>
          messageCountUsingEnrolmentKeyAndRegimeRedirectUrl(
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
    if (enrolmentListNino.contains(enrolmentType)) {
      selectByValue(getCredentialStrength, credentialStrength)
      selectByValue(getConfidenceLevel, confidenceLevel)
      sendKeys(getNinoNumber, nino)
    }
    if (enrolmentType != "pta" & redirectType != "secure-message-conversation") {
      val enrolmentKeyMap: Map[String, (String, String, String)] = Map(
        "itsa"               -> (enrolmentKeyItsa, taxIdentifierNameItsaValue, GeneratedTestData.itsaIdentifierValue),
        "vat"                -> (enrolmentKeyVat, taxIdentifierNameVatValue, GeneratedTestData.vatVrnIdentifierValue),
        "ioss"               -> (enrolmentKeyIoss, taxIdentifierNameIossValue, GeneratedTestData.iossIdentifierValue),
        "ioss inter"         -> (enrolmentKeyIossInter, taxIdentifierNameIossInterValue, GeneratedTestData.iossInterIdentifierValue),
        "oss"                -> (enrolmentKeyOss, taxIdentifierNameOssValue, GeneratedTestData.ossIdentifierValue),
        "ad"                 -> (enrolmentKeyAd, taxIdentifierNameAdValue, GeneratedTestData.adIdentifierValue),
        "ioss netp"          -> (enrolmentKeyIossNetp, taxIdentifierNameIossNetpValue, GeneratedTestData.iossNetpIdentifierValue),
        "sdil"               -> (enrolmentKeyObtds, taxIdentifierNameObtdsValue, GeneratedTestData.identifierSdilValidValue),
        "fhdds"              -> (enrolmentKeyObtds, taxIdentifierNameObtdsValue, GeneratedTestData.identifierObtdsValidValue),
        "epaye"              -> (enrolmentKeyEpaye, taxIdentifierNameEpayeValueUc, GeneratedTestData.epayeTaxOfficeNumberAndReferenceValue),
        "epayeTaxIdentifier" -> (enrolmentKeyEpaye, taxIdentifierNameEpayeValueUc, GeneratedTestData.epayeTaxOfficeNumberAndReferenceValue),
        "ppt"                -> (enrolmentKeyPpt, taxIdentifierNamePptValueUc, GeneratedTestData.identifierValuePpt),
        "pptTaxIdentifier"   -> (enrolmentKeyPpt, taxIdentifierNamePptValueUc, GeneratedTestData.identifierValuePpt),
        "cds"                -> (enrolmentKeyCds, taxIdentifierNameCds, GeneratedTestData.identifierValueEori)
      ).withDefaultValue(enrolmentKey, identifierName, GeneratedTestData.identifierValue)

      val (key, name, value) = enrolmentKeyMap(enrolmentType)
      sendKeys(enrolmentKeyId, key)
      sendKeys(enrolmentNameId, name)
      sendKeys(enrolmentValueId, value)
    }
    if (multipleEoriList.contains(redirectType)) {
      val enrolmentKeyId1: By   = By.id("enrolment[1].name")
      val enrolmentNameId1: By  = By.id("input-1-0-name")
      val enrolmentValueId1: By = By.id("input-1-0-value")
      sendKeys(enrolmentKeyId1, enrolmentKeyCds)
      sendKeys(enrolmentNameId1, taxIdentifierNameCds)
      sendKeys(enrolmentValueId1, GeneratedTestData.identifierValueEori2)
    }
    click(By.id("submit"))
  }
}
