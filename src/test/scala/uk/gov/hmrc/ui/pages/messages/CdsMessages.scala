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

import org.apache.commons.codec.binary.Base64
import org.openqa.selenium.By
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.ElementLocators.{cdsMessagePageFirstMessageSubject, sendMessageResponse}
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.*
import uk.gov.hmrc.ui.utils.html.*
import uk.gov.hmrc.ui.utils.{GeneratedTestData, MessageFormData}

import java.time.format.DateTimeFormatter
import java.time.{ZoneId, ZonedDateTime}



object CdsMessages extends BasePage {

  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  val today = ZonedDateTime.now(ZoneId.of("UTC"))
  val tomorrow = ZonedDateTime.now(ZoneId.of("UTC")).plusDays(1)
  val priorDate = ZonedDateTime.now(ZoneId.of("UTC")).minusDays(5)
  val laterDate = formatter format tomorrow
  val earlierDate = formatter format priorDate
  val todayDate = formatter format today
  
  
  def cdsRecipient(EORINumber: String): String = {
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
  }

//
  def payloadNoTag(externalRefId: String, eoriNumber: String, subject: String, content: String, validFrom: String = "2017-02-14"): String = {
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

  def cdsPayloadNoTag(externalRefId: String, eoriNumber: String, subject: String, content: String, validFrom: String = "2017-02-14"): String = {
    val payload= payloadNoTag(externalRefId, eoriNumber, subject, content)
    s"""{
           $payload
         }"""
  }
  
  def cdsPayloadWithTag(externalRefId: String, eoriNumber: String, subject: String, content: String, notificationType: String, validFrom: String = "2017-02-14"): String = {
    val payload = payloadNoTag(externalRefId, eoriNumber, subject, content)
    s"""{
              $payload,
              "tags": {
                "notificationType": "$notificationType"
               }
        }""".stripMargin
  }

  def CreateCDSMessageWithTag(): Unit = {
    val subject: String = "Direct debit test"
    val payload:String = cdsPayloadWithTag(GeneratedTestData.referenceIdValue, GeneratedTestData.identifierValueEori, subject, caseworkerMessage, "Direct Credit")
    postMessage(payload)
  }

  def CreateCDSMessageWithMultipleTag(): Unit = {
    val subject: String = "Direct debit logo test"
    val subject2: String = "Direct debit test"
    val payload: String = cdsPayloadWithTag(GeneratedTestData.referenceIdValue, GeneratedTestData.identifierValueEori, subject, caseworkerMessage, "Direct Debit")
    val payload2: String = cdsPayloadWithTag(GeneratedTestData.referenceIdValue + 1, GeneratedTestData.identifierValueEori2, subject2, caseworkerMessage, "Direct Credit")

    postMessage(payload)
    postMessage(payload2)
  }

  def emptyInbox(): Unit = {
    assert(Driver.instance.findElements(By.cssSelector(cdsMessagePageFirstMessageSubject)).size() == 0)
  }
}