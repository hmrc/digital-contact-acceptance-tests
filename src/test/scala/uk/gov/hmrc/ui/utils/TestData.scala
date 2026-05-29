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
  val enrolmentKeyVatObtds: String                 = "HMRC-OBTDS-ORG"
  val taxIdentifierNameObtdsValue: String          = "EtmpRegistrationNumber"
  val regimeSdilValue: String                      = "sdil"
  val regimeFhddsValue: String                     = "fhdds"
  val enrolmentKeyEpaye: String                    = "IR-PAYE"
  val taxIdentifierNameEpayeValue: String          = "IR-PAYE.EMPREF"
  val regimeEpayeValue: String                     = "epaye"
  val enrolmentKeyPpt: String                      = "HMRC-PPT-ORG"
  val taxIdentifierNamePptValue: String            = "HMRC-PPT-ORG.ETMPREGISTRATIONNUMBER"
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
}
