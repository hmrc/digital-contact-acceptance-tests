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

package uk.gov.hmrc.ui.pages.messages

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.*
import uk.gov.hmrc.ui.utils.{GeneratedTestData, MessageFormData}
import org.apache.commons.codec.binary.Base64
import uk.gov.hmrc.ui.ElementLocators.sendMessageResponse
import uk.gov.hmrc.ui.utils.html.{EnMessageHtmlContentItsa, MessageHtmlContent, NIREF1EnMessageHtmlContent, V4MessageHtmlContentItsa, V4MessageHtmlContentP800, V4MessageHtmlContentSa300, V4MessageHtmlContentVat}

object GmcMessages extends BasePage {

  val referenceId: String = uniqueReferenceId
  val sourceType: String = messageSourceId
  val taxIdentifierName: String = taxIdentifierNameId
  val taxIdentifierValue: String = taxIdentifierValueId
  val regime: String = regimeId
  val userEmail: String = emailId
  val messageType: String = messageTypeId
  val alertQueue: String = alertQueueId
  val englishSubject: String = englishSubjectId
  val englishContent: String = englishMessageId
  val welshSubject: String = welshSubjectId
  val welshContent: String = welshMessageId
  val validFrom: String = validFromId
  val formId: String = formIdId
  val issueDate: String = issueDateId
  val batchId: String = batchIdId
  val sourceData: String = sourceDataId


  def createV4Message(gmcMessageType: String = ""): Unit = {

    val base64_encoded_content_atssautr = Base64.encodeBase64String(atsmessageContent.getBytes("UTF-8"))
    val base64_encoded_content_p800 = Base64.encodeBase64String(MessageHtmlContent.p800HtmlContent.getBytes("UTF-8"))
    val base64_encoded_content_p800_v4 = Base64.encodeBase64String(V4MessageHtmlContentP800.HtmlContentP800.getBytes("UTF-8"))
    val base64_encoded_content_itsa_v4 = Base64.encodeBase64String(V4MessageHtmlContentItsa.HtmlContentSaItsa.getBytes("UTF-8"))
    val base64_encoded_content_sautr_v4 = Base64.encodeBase64String(V4MessageHtmlContentSa300.HtmlContentSa300.getBytes("UTF-8"))
    val base64_encoded_content_vat_v4 = Base64.encodeBase64String(V4MessageHtmlContentVat.HtmlContentVat.getBytes("UTF-8"))
    val base64_encoded_content_itsa_en = Base64.encodeBase64String(EnMessageHtmlContentItsa.HtmlContentEnItsa.getBytes("UTF-8"))
    val base64_encoded_content_niref1_en = Base64.encodeBase64String(NIREF1EnMessageHtmlContent.HtmlContentNiref1.getBytes("UTF-8"))

    logIntoDemoFrontendForV4()
    gmcMessageType.toLowerCase match {
      case "invalid formid" => fillFormWithInvalidFormId()
      case "nino" => fillFormNino(subject_p800, base64_encoded_content_p800)
      case "sautr for ats" => fillFormSautrAts("Your Annual Tax Summary for 2019 to 2020 is now", base64_encoded_content_atssautr)
      case "nino for v4" => fillFormNinoEnglishAndWelsh(V4MessageHtmlContentP800.htmlContentP800_SubjectEnglish, base64_encoded_content_p800_v4, V4MessageHtmlContentP800.htmlContentP800_SubjectWelsh,base64_encoded_content_p800_v4)
      case "sautr for v4" => fillFormSautrEnglishAndWelsh(V4MessageHtmlContentSa300.HtmlContentSa300_SubjectEnglish, base64_encoded_content_sautr_v4, V4MessageHtmlContentSa300.HtmlContentSa300_SubjectWelsh, base64_encoded_content_sautr_v4)
      case "itsaid for v4" => fillFormItsaEnglishAndWelsh(V4MessageHtmlContentItsa.HtmlContentSaItsa_SubjectEnglish,base64_encoded_content_itsa_v4, V4MessageHtmlContentItsa.HtmlContentSaItsa_SubjectWelsh,base64_encoded_content_itsa_v4)
      case "vat for v4" => fillFormVatEnglishAndWelsh(V4MessageHtmlContentVat.htmlContentVat_SubjectEnglish,base64_encoded_content_vat_v4, V4MessageHtmlContentVat.htmlContentVat_SubjectWelsh,base64_encoded_content_vat_v4)
      case "ioss" => fillFormIoss(V4MessageHtmlContentVat.htmlContentVat_SubjectEnglish, base64_encoded_content_vat_v4)
      case "ioss inter" => fillFormIossInter(V4MessageHtmlContentVat.htmlContentVat_SubjectEnglish, base64_encoded_content_vat_v4)
      case "oss" => fillFormOss(V4MessageHtmlContentVat.htmlContentVat_SubjectEnglish, base64_encoded_content_vat_v4)
      case "ad" => fillFormAd(V4MessageHtmlContentVat.htmlContentVat_SubjectEnglish, base64_encoded_content_vat_v4)
      case "itsaid for en" => fillFormItsaEn(EnMessageHtmlContentItsa.HtmlContentEnItsa_SubjectEnglish, base64_encoded_content_itsa_en)
      case "itsamig1 for en" => fillFormItsaMig1En(EnMessageHtmlContentItsa.HtmlContentEnItsa_SubjectEnglish, base64_encoded_content_itsa_en)
      case "lpp1aitsa" => fillFormItsaLpp1aEn(EnMessageHtmlContentItsa.HtmlContentEnItsa_SubjectEnglish, base64_encoded_content_itsa_en)
      case "lpp2itsa" => fillFormItsaLpp2En(EnMessageHtmlContentItsa.HtmlContentEnItsa_SubjectEnglish, base64_encoded_content_itsa_en)
      case "par1itsa" => fillFormItsaPar1En(EnMessageHtmlContentItsa.HtmlContentEnItsa_SubjectEnglish, base64_encoded_content_itsa_en)
      case "ioss netp" => fillFormIossNetp(V4MessageHtmlContentVat.htmlContentVat_SubjectEnglish, base64_encoded_content_vat_v4)
      case "niref1 en" => fillFormNiref1En(NIREF1EnMessageHtmlContent.HtmlContentNiref1_SubjectEnglish, base64_encoded_content_niref1_en)
      case "niref4 en" => fillFormNiref4En(NIREF1EnMessageHtmlContent.HtmlContentNiref1_SubjectEnglish, base64_encoded_content_niref1_en)
      case "invalidalertqueue" => fillFormInvalidAlertQueue(subject_p800, base64_encoded_content_p800)
      case "emptyalertqueue" => fillFormEmptyAlertQueue(subject_p800, base64_encoded_content_p800)
      case "invalidsourcedata" => fillFormInvalidSourceData(subject_p800, base64_encoded_content_p800)
      case "unknowntaxidentifier" => fillFormUnknownTaxIdentifier(subject_p800, base64_encoded_content_p800)
      case "missingtaxidentifier" => fillFormMissingTaxIdentifier(subject_p800, base64_encoded_content_p800)
      case "missingdetails" => fillFormMissingDetails(subject_p800, base64_encoded_content_p800)
      case "invalidemail" => fillFormInvalidEmail(subject_p800, base64_encoded_content_p800)
      case _ => throw new IllegalArgumentException(s"Unknown Message Type")
    }
    click(By.id("submit-button"))
    waitForText(sendMessageResponse, "The message response is")
  }
  def fillMessageForm(formData: MessageFormData): Unit = {
    val referenceIdInputField: By = By.id(referenceId)
    val sourceInputField: By = By.id(sourceType)
    val identifierNameInputField: By = By.id(taxIdentifierName)
    val identifierValueInputField: By = By.id(taxIdentifierValue)
    val regimeInputField: By = By.id(regime)
    val emailInputField: By = By.id(userEmail)
    val messageTypeInputField: By = By.id(messageType)
    val alertQueueInputField: By = By.id(alertQueue)
    val englishSubjectInputField: By = By.id(englishSubject)
    val englishContentInputField: By = By.id(englishContent)
    val welshSubjectInputField: By = By.id(welshSubject)
    val welshContentInputField: By = By.id(welshContent)
    val validFromInputField: By = By.id(validFrom)
    val formIdInputField: By = By.id(formId)
    val issueDateInputField: By = By.id(issueDate)
    val batchIdInputField: By = By.id(batchId)
    val sourceDataIdInputField: By = By.id(sourceData)

    sendKeys(referenceIdInputField, formData.externalRef.id)
    sendKeys(sourceInputField, formData.externalRef.source)
    sendKeys(identifierNameInputField, formData.recipient.taxIdentifier.name)
    sendKeys(identifierValueInputField, formData.recipient.taxIdentifier.value)
    sendKeys(emailInputField, formData.recipient.email)
    sendKeys(regimeInputField, formData.regime)
    sendKeys(messageTypeInputField, formData.messageType)
    sendKeys(alertQueueInputField, formData.alertQueue)
    sendKeys(englishSubjectInputField, formData.subjectEnglish)
    sendKeys(englishContentInputField, formData.contentEnglish)
    sendKeys(welshSubjectInputField, formData.subjectWelsh)
    sendKeys(welshContentInputField, formData.contentWelsh)
    sendKeys(validFromInputField, formData.validFrom)
    sendKeys(formIdInputField, formData.details.formId)
    sendKeys(issueDateInputField, formData.details.issueDate)
    sendKeys(batchIdInputField, formData.details.batchId)
    sendKeys(sourceDataIdInputField, formData.details.sourceData)
  }

  def fillFormWithInvalidFormId(): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      formId = Some(invalidFormIdValue),
      regime = Some(regimeValue)
    )
    fillMessageForm(updated)
  }

  def fillFormNino(subject: String, content: String):Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormSautrAts(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormNinoEnglishAndWelsh(subjectEnglish: String, contentEnglish: String, subjectWelsh: String, contentWelsh: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
      subjectWelsh = Some(subjectWelsh),
      contentWelsh = Some(contentWelsh)
    )
    fillMessageForm(updated)
  }

  def fillFormSautrEnglishAndWelsh(subjectEnglish: String, contentEnglish: String, subjectWelsh: String, contentWelsh: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
      subjectWelsh = Some(subjectWelsh),
      contentWelsh = Some(contentWelsh)
    )
    fillMessageForm(updated)
  }

  def fillFormItsaEnglishAndWelsh(subjectEnglish: String, contentEnglish: String, subjectWelsh: String, contentWelsh: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameItsaValue),
      identifierValue = Some(GeneratedTestData.itsaIdentifierValue),
      regime = Some(regimeItsaValue),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
      subjectWelsh = Some(subjectWelsh),
      contentWelsh = Some(contentWelsh)
    )
    fillMessageForm(updated)
  }

  def fillFormVatEnglishAndWelsh(subjectEnglish: String, contentEnglish: String, subjectWelsh: String, contentWelsh: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameVatValue),
      identifierValue = Some(GeneratedTestData.vatVrnIdentifierValue),
      regime = Some(regimeVatValue),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
      subjectWelsh = Some(subjectWelsh),
      contentWelsh = Some(contentWelsh)
    )
    fillMessageForm(updated)
  }

  def fillFormIoss(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(enrolmentKeyIoss),
      identifierValue = Some(GeneratedTestData.iossIdentifierValue),
      regime = Some(regimeIossValue),
      formId = Some("M01IOSS"),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormIossInter(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(enrolmentKeyIossInter),
      identifierValue = Some(GeneratedTestData.iossInterIdentifierValue),
      regime = Some(regimeIossValue),
      formId = Some("M07GIOSS"),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormOss(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(enrolmentKeyOss),
      identifierValue = Some(GeneratedTestData.ossIdentifierValue),
      regime = Some(regimeOssValue),
      formId = Some("M01IOSS"),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormAd(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(enrolmentKeyAd),
      identifierValue = Some(GeneratedTestData.adIdentifierValue),
      regime = Some(regimeAdValue),
      formId = Some("AD2"),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormItsaEn(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameItsaValue),
      identifierValue = Some(GeneratedTestData.itsaIdentifierValue),
      formId = Some("LSP1_ITSA"),
      regime = Some(regimeItsaValue),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormItsaMig1En(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameItsaValue),
      identifierValue = Some(GeneratedTestData.itsaIdentifierValue),
      formId = Some("ITSAMIG1"),
      regime = Some(regimeItsaValue),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormItsaLpp1aEn(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameItsaValue),
      identifierValue = Some(GeneratedTestData.itsaIdentifierValue),
      formId = Some("LPP1A_ITSA"),
      regime = Some(regimeItsaValue),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormItsaLpp2En(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameItsaValue),
      identifierValue = Some(GeneratedTestData.itsaIdentifierValue),
      formId = Some("LPP2_ITSA"),
      regime = Some(regimeItsaValue),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormItsaPar1En(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameItsaValue),
      identifierValue = Some(GeneratedTestData.itsaIdentifierValue),
      formId = Some("PAR1_ITSA"),
      regime = Some(regimeItsaValue),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormIossNetp(subjectEnglish: String, contentEnglish: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(enrolmentKeyIossNetp),
      identifierValue = Some(GeneratedTestData.iossNetpIdentifierValue),
      regime = Some(regimeIossValue),
      formId = Some("M05AGIOSS"),
      subjectEnglish = Some(subjectEnglish),
      contentEnglish = Some(contentEnglish),
    )
    fillMessageForm(updated)
  }

  def fillFormNiref1En(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      formId = Some("NIREF1"),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormNiref4En(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      formId = Some("NIREF4"),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormInvalidAlertQueue(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      formId = Some("SA300"),
      alertQueue = Some("Test"),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormEmptyAlertQueue(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      formId = Some("SA300"),
      alertQueue = Some(""),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormInvalidSourceData(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      formId = Some("SA300"),
      sourceData = Some(""),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormUnknownTaxIdentifier(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some("nino1"),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      formId = Some("SA300"),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormMissingTaxIdentifier(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(""),
      regime = Some(regimeVatValue),
      formId = Some("SA300"),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormMissingDetails(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(GeneratedTestData.ninoNumber),
      regime = Some(regimeValue),
      formId = Some("SA300"),
      issueDate = Some(""),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content)
    )
    fillMessageForm(updated)
  }

  def fillFormInvalidEmail(subject: String, content: String): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(enrolmentKeyAd),
      identifierValue = Some(GeneratedTestData.adIdentifierValue),
      regime = Some(regimeAdValue),
      formId = Some("AD2"),
      email = Some("testuser123"),
      subjectEnglish = Some(subject),
      contentEnglish = Some(content),
    )
    fillMessageForm(updated)
  }
}
