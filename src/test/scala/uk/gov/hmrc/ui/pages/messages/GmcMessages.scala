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
import uk.gov.hmrc.ui.utils.MessageFormData
import uk.gov.hmrc.ui.utils.MessageFormData.*


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
  val validFrom: String = validFromId
  val formId: String = formIdId
  val issueDate: String = issueDateId
  val batchId: String = batchIdId
  val sourceData: String = sourceDataId


  def createV4Message(gmcMessageType: String = ""): Unit = {
    logIntoDemoFrontendForV4()
    val gmcMessage: Unit = gmcMessageType.toLowerCase match {
      case "invalid formid" => fillFormWithInvalidFormId()
      case _ => fillMessageForm(MessageFormData.default)
    }
    click(By.id("submit-button"))
    waitForText("#main-content > div > div > div.data > p > span", "The message response is")
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
    sendKeys(englishSubjectInputField, formData.subject)
    sendKeys(englishContentInputField, formData.content)
    sendKeys(validFromInputField, formData.validFrom)
    sendKeys(formIdInputField, formData.details.formId)
    sendKeys(issueDateInputField, formData.details.issueDate)
    sendKeys(batchIdInputField, formData.details.batchId)
    sendKeys(sourceDataIdInputField, formData.details.sourceData)

  }

  def fillFormWithInvalidFormId(): Unit = {
    val updated: MessageFormData = MessageFormData.update(MessageFormData.default,
      identifierName = Some(taxIdentifierNameValue),
      identifierValue = Some(ninoNumber),
      formId = Some(invalidFormIdValue),
      regime = Some(regimeValue)
    )
    fillMessageForm(updated)
  }
}
