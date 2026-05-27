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
import uk.gov.hmrc.ui.utils.GeneratedTestData

object MdtpMessages extends BasePage {

  val contentId: String         = messageContentId
  val subjectId: String         = messageSubjectId
  val identifierNameId: String  = recipientTaxIdentifierNameId
  val identifierValueId: String = recipientTaxIdentifierValueId
  val userEmailId: String       = recipientEmailId
  val userNameId: String        = recipientNameId

  def createMDTPMessage(mdtpMessageType: String, typeMessage: String): Unit = {
    val mdtpMessage: Unit = mdtpMessageType.toLowerCase match {
      case "fhdds" => fillFormForFHDDSMessage(typeMessage)
    }
    click(By.id("submit-advice"))
  }

  def fillFormForFHDDSMessage(typeMessage: String): Unit = {
    val contentInputField: By         = By.id(contentId)
    val subjectInputField: By         = By.id(subjectId)
    val identifierNameInputField: By  = By.id(identifierNameId)
    val identifierValueInputField: By = By.id(identifierValueId)
    val emailInputField: By           = By.id(userEmailId)
    val nameInputField: By            = By.id(userNameId)
    val messageTypeInputField: By     = By.id(messageTypeId)

    sendKeys(contentInputField, contentValue)
    sendKeys(subjectInputField, subjectValue)
    sendKeys(identifierNameInputField, identifierNameValue)
    sendKeys(emailInputField, GeneratedTestData.email)
    sendKeys(nameInputField, nameValue)
    sendKeys(messageTypeInputField, messageTypeValue)

    val indentifierValue = typeMessage match {
      case "valid"   => sendKeys(identifierValueInputField, GeneratedTestData.identifierFHDDSValidValue)
      case "invalid" => sendKeys(identifierValueInputField, GeneratedTestData.identifierFHDDSInvalidValue)
      case _         => throw new IllegalArgumentException(s"Unknown Value")
    }
  }
}
