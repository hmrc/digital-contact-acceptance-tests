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
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.ElementLocators.*
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.*
import uk.gov.hmrc.ui.utils.GeneratedTestData

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

object CdsMessages extends BasePage {

  val caseworkerReplyPayload: String =
    s"""{
                 "sender": {
                    "system": {
                      "identifier": {
                      "name": "$name",
                      "value": "$conversationId"
                    }
                 }
          },
          "content": "$caseworkerReplyMessage"
         }""".stripMargin

  def cdsRecipient(EORINumber: String): String =
    s"""  "recipient":{
            "taxIdentifier":{
              "name":"HMRC-CUS-ORG",
              "value":"$EORINumber"
            },
            "name":{
              "line1" : "Mr Smith"
            },
           "email":"${GeneratedTestData.email}"
          }"""

  def payloadNoTag(
    externalRefId: String,
    eoriNumber: String,
    subject: String,
    content: String,
    validFrom: String = "2017-02-14"
  ): String = {
    val recipientBlock: String = cdsRecipient(eoriNumber)
    s"""
               "externalRef": {
                 "id": "$externalRefId",
                 "source": "mdtp"
               },
                $recipientBlock,
               "messageType": "cds_ddi_setup_dcs_alert",
               "subject": "$subject",
               "content": "$content",
               "validFrom": "$validFrom",
               "alertQueue":"DEFAULT"
               """.stripMargin
  }

  def cdsPayloadNoTag(
    externalRefId: String,
    eoriNumber: String,
    subject: String,
    content: String,
    validFrom: String = "2017-02-14"
  ): String = {
    val payload = payloadNoTag(externalRefId, eoriNumber, subject, content, validFrom)
    s"""{
           $payload
         }"""
  }

  def cdsPayloadWithTag(
    externalRefId: String,
    eoriNumber: String,
    subject: String,
    content: String,
    notificationType: String,
    validFrom: String = "2017-02-14"
  ): String = {
    val payload = payloadNoTag(externalRefId, eoriNumber, subject, content, validFrom)
    s"""{
              $payload,
              "tags": {
                "notificationType": "$notificationType"
               }
        }""".stripMargin
  }

  def CreateCDSMessageWithTag(): Unit = {
    val subject: String = "Direct debit test"
    val payload: String = cdsPayloadWithTag(
      GeneratedTestData.referenceIdValue,
      GeneratedTestData.identifierValueEori,
      subject,
      caseworkerMessage,
      "Direct Debit"
    )
    postCDSMessage(payload)
  }

  def CreateCDSMessageWithMultipleTag(): Unit = {
    val subject: String  = "Direct debit logo test"
    val subject2: String = "Direct debit test"
    val payload: String  = cdsPayloadWithTag(
      GeneratedTestData.referenceIdValue,
      GeneratedTestData.identifierValueEori,
      subject,
      caseworkerMessage,
      "Direct Debit"
    )
    val payload2: String = cdsPayloadWithTag(
      GeneratedTestData.referenceIdValue + 1,
      GeneratedTestData.identifierValueEori2,
      subject2,
      caseworkerMessage,
      "Direct Credit"
    )

    postCDSMessage(payload)
    postCDSMessage(payload2)
  }

  def CreateCDSFutureMessageWithTag(): Unit = {
    val formatter       = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val laterDate       = LocalDateTime.now().plusDays(10).format(formatter)
    val subject: String = "Direct debit logo test"
    val payload: String = cdsPayloadWithTag(
      GeneratedTestData.referenceIdValue + 1,
      GeneratedTestData.identifierValueEori,
      subject,
      caseworkerMessage,
      "Direct Debit",
      laterDate
    )
    postCDSMessage(payload)
  }

  def checkInboxIsEmpty(): Unit =
    assert(Driver.instance.findElements(By.cssSelector(cdsMessagePageFirstMessageSubject)).size() == 0)

  def submitFormWithCustomerName(): Unit = {
    val cdsSubject: By                     = By.id(conversationSubject)
    val cdsMessage: By                     = By.id(conversationMessage)
    val cdsClientName: By                  = By.id(conversationClientName)
    val cdsConversationIdentifierName: By  = By.id(conversationConversationIdentifierName)
    val cdsConversationIdentifierValue: By = By.id(conversationConversationIdentifierValue)
    val cdsSenderId: By                    = By.id(conversationSenderId)
    val cdsDisplayName: By                 = By.id(conversationDisplayName)
    val cdsCustomerName: By                = By.id(conversationCustomerName)
    val cdsCustomerEmail: By               = By.id(conversationCustomerEmail)
    val cdsCustomerEnrolmentKey: By        = By.id(conversationCustomerEnrolmentKey)
    val cdsCustomerEnrolmentName: By       = By.id(conversationCustomerEnrolmentName)
    val cdsCustomerEnrolmentValue: By      = By.id(conversationCustomerEnrolmentValue)
    val cdsAlertTemplate: By               = By.id(conversationAlertTemplate)
    val cdsTagsKey1Id: By                  = By.id(tagsKey1Id)
    val cdsTagsKey1ValueId: By             = By.id(tagsKey1ValueId)
    val cdsTagsKey2Id: By                  = By.id(tagsKey2Id)
    val cdsTagsKey2ValueId: By             = By.id(tagsKey2ValueId)
    val cdsQueryButton: By                 = By.cssSelector(conversationQueryButton)

    sendKeys(cdsSubject, caseSubject)
    sendKeys(cdsMessage, caseworkerMessage)
    sendKeys(cdsClientName, name)
    sendKeys(cdsConversationIdentifierName, name)
    sendKeys(cdsConversationIdentifierValue, conversationId)
    sendKeys(cdsSenderId, conversationId)
    sendKeys(cdsDisplayName, displayName)
    sendKeys(cdsCustomerName, customerName)
    sendKeys(cdsCustomerEmail, customerEmail)
    sendKeys(cdsCustomerEnrolmentKey, enrolmentKeyCds)
    sendKeys(cdsCustomerEnrolmentName, taxIdentifierNameCds)
    sendKeys(cdsCustomerEnrolmentValue, GeneratedTestData.identifierValueEori)
    sendKeys(cdsAlertTemplate, emailTemplate)
    sendKeys(cdsTagsKey1Id, tagsKey1Name)
    sendKeys(cdsTagsKey1ValueId, tagsKey1Value)
    sendKeys(cdsTagsKey2Id, tagsKey2Name)
    sendKeys(cdsTagsKey2ValueId, tagsKey2Value)
    click(cdsQueryButton)
    fluentWait
  }

  def submitFormWithoutCustomerName(): Unit = {
    val cdsSubject: By                     = By.id(conversationSubject)
    val cdsMessage: By                     = By.id(conversationMessage)
    val cdsClientName: By                  = By.id(conversationClientName)
    val cdsConversationIdentifierName: By  = By.id(conversationConversationIdentifierName)
    val cdsConversationIdentifierValue: By = By.id(conversationConversationIdentifierValue)
    val cdsSenderId: By                    = By.id(conversationSenderId)
    val cdsDisplayName: By                 = By.id(conversationDisplayName)
    val cdsCustomerEmail: By               = By.id(conversationCustomerEmail)
    val cdsCustomerEnrolmentKey: By        = By.id(conversationCustomerEnrolmentKey)
    val cdsCustomerEnrolmentName: By       = By.id(conversationCustomerEnrolmentName)
    val cdsCustomerEnrolmentValue: By      = By.id(conversationCustomerEnrolmentValue)
    val cdsAlertTemplate: By               = By.id(conversationAlertTemplate)
    val cdsTagsKey1Id: By                  = By.id(tagsKey1Id)
    val cdsTagsKey1ValueId: By             = By.id(tagsKey1ValueId)
    val cdsTagsKey2Id: By                  = By.id(tagsKey2Id)
    val cdsTagsKey2ValueId: By             = By.id(tagsKey2ValueId)
    val cdsQueryButton: By                 = By.cssSelector(conversationQueryButton)

    sendKeys(cdsSubject, caseSubject)
    sendKeys(cdsMessage, caseworkerMessage)
    sendKeys(cdsClientName, name)
    sendKeys(cdsConversationIdentifierName, name)
    sendKeys(cdsConversationIdentifierValue, conversationId2)
    sendKeys(cdsSenderId, conversationId2)
    sendKeys(cdsDisplayName, displayName2)
    sendKeys(cdsCustomerEmail, customerEmail)
    sendKeys(cdsCustomerEnrolmentKey, enrolmentKeyCds)
    sendKeys(cdsCustomerEnrolmentName, taxIdentifierNameCds)
    sendKeys(cdsCustomerEnrolmentValue, GeneratedTestData.identifierValueEori2)
    sendKeys(cdsAlertTemplate, emailTemplate)
    sendKeys(cdsTagsKey1Id, tagsKey1Name)
    sendKeys(cdsTagsKey1ValueId, tagsKey1Value)
    sendKeys(cdsTagsKey2Id, tagsKey2Name)
    sendKeys(cdsTagsKey2ValueId, tagsKey2Value)
    click(cdsQueryButton)
    fluentWait
  }

  def messageReadStatus(): Unit =
    assert(getText(By.cssSelector(readConversationStatus)).equals(readStatus))

  def messageUnReadStatus(): Unit =
    assert(getText(By.cssSelector(unreadConversationStatus)).equals(unreadStatus))

}
