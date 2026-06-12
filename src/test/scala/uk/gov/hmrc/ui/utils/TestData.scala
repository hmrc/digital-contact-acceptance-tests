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

package uk.gov.hmrc.ui.utils

import uk.gov.hmrc.configuration.TestEnvironment

case class User(userType: String, nino: Option[String] = None)

trait TestData {

  def getUser(userType: String): User = {
    val taxIdentifier = userType.toLowerCase()

    taxIdentifier match {
      case "nino" =>
        User(userType, Some(GeneratedTestData.ninoNumber))
    }
  }

  val digitalContactDemoFrontend: String           = TestEnvironment.url("demo-frontend")
  val saApiProxy: String                           = TestEnvironment.url("sa-api-proxy")
  val preferenceFrontend: String                   = TestEnvironment.url("preferences-frontend")
  val preferences: String                          = TestEnvironment.url("preferences")
  val secureMessage: String                        = TestEnvironment.url("secure-message")
  val externalMessageAdapter: String               = TestEnvironment.url("external-message-adapter")
  val customerAdvisorFrontend: String              = TestEnvironment.url("customer-advisors-frontend")
  val secureMessagingBaseUrl: String               = TestEnvironment.url("secure-message-stub")
  val messageFrontend: String = TestEnvironment.url("message-frontend")
  val message: String = TestEnvironment.url("message")

  val credentialStrength: String                   = "strong"
  val confidenceLevel: String                      = "200"
  val enrolmentKey: String                         = "IR-SA"
  val identifierName: String                       = "UTR"
  val pta: String                                  = "/personal-account"
  val bta: String                                  = "/business-account"
  val itsa: String                                 = "/itsa"
  val optOutReasonText: String                     = "for testing purpose"
  val sourceValue: String                          = "gmc"
  val sourceMdtpValue: String                      = "mdtp"
  val taxIdentifierNameValue: String               = "nino"
  val regimeValue: String                          = "paye"
  val taxIdentifierNameSautrValue: String          = "sautr"
  val regimeSaValue: String                        = "sa"
  val taxIdentifierNameItsaValue: String           = "MTDITID"
  val regimeItsaValue: String                      = "itsa"
  val enrolmentKeyItsa: String                     = "HMRC-MTD-IT"
  val taxIdentifierNameVatValue                    = "HMRC-MTD-VAT.VRN"
  val regimeVatValue: String                       = "vat"
  val enrolmentKeyVat: String                      = "HMRC-MTD-VAT"
  val enrolmentKeyIoss: String                     = "HMRC-IOSS-ORG"
  val taxIdentifierNameIossValue: String           = "IOSSNumber"
  val regimeIossValue: String                      = "ioss"
  val enrolmentKeyIossInter: String                = "HMRC-IOSS-INT"
  val taxIdentifierNameIossInterValue: String      = "IntNumber"
  val enrolmentKeyIossNetp: String                 = "HMRC-IOSS-NETP"
  val taxIdentifierNameIossNetpValue: String       = "IOSSNumber"
  val enrolmentKeyOss: String                      = "HMRC-OSS-ORG"
  val taxIdentifierNameOssValue: String            = "VRN"
  val regimeOssValue: String                       = "oss"
  val enrolmentKeyAd: String                       = "HMRC-AD-ORG"
  val taxIdentifierNameAdValue: String             = "APPAID"
  val regimeAdValue: String                        = "ad"
  
  val enrolmentKeyObtds: String                    = "HMRC-OBTDS-ORG"
  val taxIdentifierNameObtdsValue: String          = "EtmpRegistrationNumber"
  val regimeSdilValue: String                      = "sdil"
  val regimeFhddsValue: String                     = "fhdds"
 
  
  val enrolmentKeyEpaye: String                    = "IR-PAYE"
  val enrolmentKeytaxIdentifierEpaye: String       = "EMPREF"
  val taxIdentifierNameEpayeValueUc: String        = "IR-PAYE.EMPREF"
  val taxIdentifierNameEpayeValueLc: String        = "IR-PAYE.empref"
  val taxIdentifierNameEpayeValueCc: String        = "IR-PAYE.empRef"
  val regimeEpayeValue: String                     = "epaye"
  val enrolmentKeyPpt: String                      = "HMRC-PPT-ORG"
  val enrolmentKeytaxIdentifierPpt: String         = "ETMPREGISTRATIONNUMBER"
  val taxIdentifierNamePptValueUc: String          = "HMRC-PPT-ORG.ETMPREGISTRATIONNUMBER"
  val taxIdentifierNamePptValueLc: String          = "HMRC-PPT-ORG.etmpregistrationnumber"
  val taxIdentifierNamePptValueCc: String          = "HMRC-PPT-ORG.EtmpRegistrationNumber"
  val regimePptValue: String                       = "ppt"
  val enrolmentKeyCds: String                      = "HMRC-CUS-ORG"
  val taxIdentifierNameCds: String                 = "EORINumber"
  val regimeCdsValue: String                       = "cds"

  val messageTypeValue: String                     = "mailout-batch"
  val alertQueueValue: String                      = "DEFAULT"
  val englishSubjectValue: String                  = "Reminder to file Self Assessment return"
  val englishContentValue: String                  =
    "PGgxPlRlc3QgTWVzc2FnZTxoMT4NCg0KPHNjcmlwdD53aW5kb3cuYWxlcnQoIkhlbGxvIik8L3NjcmlwdD4NCg0KPGgyPk5vIHBvcCB1cCBzaG91bGQgYXBwZWFyPC9oMj4NCg0KPHAgc3R5bGU9ImZvbnQtc2l6ZTogMTlweDtsaW5lLWhlaWdodDogMS4zMTU3ODk0NzQ7bWFyZ2luOiAwIDAgMzBweCAwOyI+DQogICAgRGVhciBDdXN0b21lcg0KPC9wPg0KDQo8cD5UaGlzIGlzIHlvdXIgbWVzc2FnZTwvcD4="
  val validFromValue: String                       = "2026-03-09"
  val invalidFormIdValue: String                   = "Test312"
  val batchIdValue: String                         = "1234567"
  val sourceDataValue: String                      = "ew0KICAgIm5hbWUiOiAiRGFuaWVsIiwNCiAgICJzZWF0IiA6ICJ5ZXMiDQp9"
  val reasonTextForAdding: String                  = "Adding a formId for testing"
  val reasonTextForDeleting: String                = "Deleting a formId for testing"
  val rejectReasonText: String                     = "Not a valid formId"
  val addReasonText: String                        = "Approved valid formId for testing"
  val contentValue: String                         =
    """<img src="https://www.qa.tax.service.gov.uk/assets/4.8.0/images/direct-debit-logo.png" alt="Direct Debit logo">"""
  val subjectValue: String                         = "Direct debit logo test"
  val identifierNameValue: String                  = "HMRC-OBTDS-ORG"
  val nameValue: String                            = "Test User"
                                                   
  val subject_p800: String                         = "Tax calculation for the year 6 April 2020 to 5 April 2021"
  val messageContent: String                       = "Actual message content should be here"
  val atsmessageContent: String                    = "DQoNCjxwPlRoaXMgc2hvd3MgaG93IGdvdmVybm1lbnQgc3BlbmRzIHlvdXIgdGF4IGFuZCBOYXRpb25hbCBJbnN1cmFuY2UgY29udHJpYnV0aW9ucy48L3A+DQoNCjxwPlNlZSB5b3VyIDxhIGhyZWY9Imh0dHBzOi8vd3d3LnRheC5zZXJ2aWNlLmdvdi51ay9hbm51YWwtdGF4LXN1bW1hcnkiPkFubnVhbCBUYXggU3VtbWFyeTwvYT4uPC9wPg0KDQo8cD5UYXggc3VtbWFyaWVzIGFyZSBmb3IgaW5mb3JtYXRpb24gb25seSwgc28geW91IGRvIG5vdCBuZWVkIHRvIGNvbnRhY3QgSE1SQy48L3A+DQoNCg0KDQoNCg=="

  val secureMessageStub: String                    = "secure-message-stub"
  val conversationList: String                     = "/messages"
  val caseSubject: String                          = "MRN20210219105505513 Case D-89019"
  val caseworkerMessage: String                    = "RGVhciBza3kgdHJhZGVyLAoKQXMgeW91IGltcG9ydGluZyB3aXRoIHJlZmVyZW5jZSBudW1iZXIgMTIzNDU2NiwgeW91IG5lZWQgdG8gcHJvdmlkZSBzb21lIGV4dHJhIGluZm9ybWF0aW9uIHRvIHJlbGVhc2UgZm9ybSB0aGUgY3VzdG9tLgpXZSBuZWVkIGltcG9ydCBsaWNlbmNlLCBpbnN1cmFuY2UgZGV0aWFscyBhbmQgc2FsZSBhZ3JlZW1lbnQgYmV0d2VlbiB5b3UgYW5kIGNvbXBhbnkuCgpGcm9tCkhNUkMgQ3VzdG9tcw=="
  val tagMessageFiltering: String                  = "?tag=notificationType~Direct%20Debit"
  val tagsMessageFiltering: String                 = "?tag=notificationType~Direct%20Debit&tag=notificationType~Direct%20Credit"
  val enrolmentKeyMessageFiltering: String         = "?enrolmentKey=HMRC-CUS-ORG"
  val enrolmentKeyAndTagMessageFiltering: String   = "?enrolmentKey=HMRC-CUS-ORG&tag=notificationType~Direct%20Credit"
  val enrolmentKeyAndVatdecMessageFiltering: String = "?enrolmentKey=HMCE-VATDEC-ORG"

  val name: String                                 = "CDCM"
  val conversationId: String                       = "D-89019-20210219"
  val conversationId2: String                      = "D-89019-20210220"
  val displayName: String                          = "National Clearance Hub"
  val displayName2: String                         = "Border Force Hub"
  val customerName: String                         = "Sky trader"
  val customerEmail: String                        = "customerName@gmail.com"
  val emailTemplate: String                        = "newMessageAlert_CDS_exp"
  val tagsKey1Name: String                         = "notificationType"
  val tagsKey2Name: String                         = "mrn"
  val tagsKey3Name: String                         = "queryId"
  val tagsKey1Value: String                        = "CDS-EXPORTS"
  val tagsKey2Value: String                        = "DMS7324874993"
  val tagsKey3Value: String                        = "D-89019-20210219"
  val unreadStatus: String                         = "Unread message. "
  val readStatus: String                           = "Previously viewed message. "
}
