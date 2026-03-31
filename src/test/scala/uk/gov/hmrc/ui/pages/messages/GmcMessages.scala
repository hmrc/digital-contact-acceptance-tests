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

object GmcMessages extends BasePage  {

  val referenceId: String        = uniqueReferenceId
  val sourceType: String         = messageSourceId
  val taxIdentifierName: String  = taxIdentifierNameId
  val taxIdentifierValue: String = taxIdentifierValueId
  val regime: String             = regimeId
  val userEmail: String              = emailId
  val messageType: String        = messageTypeId
  val alertQueue: String         = alertQueueId
  val englishSubject: String     = englishSubjectId
  val englishContent: String     = englishMessageId
  val validFrom: String          = validFromId
  val formId: String             = formIdId
  val issueDate: String          = issueDateId
  val batchId: String            = batchIdId
  val sourceData: String         = sourceDataId

  def createV4Message(messageType: String): Unit = {
    logIntoDemoFrontendForV4()
    val message: Unit     = messageType.toLowerCase match {
      case "invalid formid" => fillFormWithInvalidFormId()
    }
    click(By.id("submit-button"))
  }

  def fillFormWithInvalidFormId(): Unit           = {
    val referenceIdInputField: By     = By.id(referenceId)
    val sourceInputField: By          = By.id(sourceType)
    val identifierNameInputField: By  = By.id(taxIdentifierName)
    val identifierValueInputField: By = By.id(taxIdentifierValue)
    val regimeInputField: By          = By.id(regime)
    val emailInputField: By           = By.id(userEmail)
    val messageTypeInputField: By     = By.id(messageType)
    val alertQueueInputField: By      = By.id(alertQueue)
    val englishSubjectInputField: By  = By.id(englishSubject)
    val englishContentInputField: By  = By.id(englishContent)
    val validFromInputField: By       = By.id(validFrom)
    val formIdInputField: By          = By.id(formId)
    val issueDateInputField: By       = By.id(issueDate)
    val batchIdInputField: By         = By.id(batchId)
    val sourceDataIdInputField: By    = By.id(sourceData)

    sendKeys(referenceIdInputField, referenceIdValue)
    sendKeys(sourceInputField, sourceValue)
    sendKeys(identifierNameInputField, taxIdentifierNameValue)
    sendKeys(identifierValueInputField, ninoNumber)
    sendKeys(regimeInputField, regimeValue)
    sendKeys(emailInputField, email)
    sendKeys(messageTypeInputField, messageTypeValue)
    sendKeys(alertQueueInputField, alertQueueValue)
    sendKeys(englishSubjectInputField, englishSubjectValue)
    sendKeys(englishContentInputField, englishContentValue)
    sendKeys(validFromInputField, validFromValue)
    sendKeys(formIdInputField, invalidFormIdValue)
    sendKeys(issueDateInputField, validFromValue)
    sendKeys(batchIdInputField, batchIdValue)
    sendKeys(sourceDataIdInputField, sourceDataValue)
    
  }

}