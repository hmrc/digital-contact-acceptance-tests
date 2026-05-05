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
        User(userType, Some(ninoNumber))
    }
  }

  val digitalContactDemoFrontend: String = TestEnvironment.url("demo-frontend")
  val saApiProxy: String                 = TestEnvironment.url("sa-api-proxy")
  val preferenceFrontend: String         = TestEnvironment.url("preferences-frontend")
  val preferences: String                = TestEnvironment.url("preferences")
  val secureMessage: String              = TestEnvironment.url("secure-message")
  val externalMessageAdapter: String     = TestEnvironment.url("external-message-adapter")

  val credentialStrength: String     = "strong"
  val confidenceLevel: String        = "200"
  val enrolmentKey: String           = "IR-SA"
  val identifierName: String         = "UTR"
  val identifierValue: String        = "1234567890"
  val identifierValue2: String       = "1234567891"
  val ninoNumber: String             = "YY000200A"
  val ninoNumber1: String            = "SP222333A"
  val pta: String                    = "/personal-account"
  val bta: String                    = "/business-account"
  val itsa: String                   = "/itsa"
  val email: String                  = "testuser123@gmail.com"
  val email2: String                 = "testuser2@gmail.com"
  val optOutReasonText: String       = "for testing purpose"
  val referenceIdValue: String       = "1234243234242234"
  val sourceValue: String            = "gmc"
  val taxIdentifierNameValue: String = "nino"
  val regimeValue: String            = "paye"
  val messageTypeValue: String       = "mailout-batch"
  val alertQueueValue: String        = "DEFAULT"
  val englishSubjectValue: String    = "Reminder to file Self Assessment return"
  val englishContentValue: String    =
    "PGgxPlRlc3QgTWVzc2FnZTxoMT4NCg0KPHNjcmlwdD53aW5kb3cuYWxlcnQoIkhlbGxvIik8L3NjcmlwdD4NCg0KPGgyPk5vIHBvcCB1cCBzaG91bGQgYXBwZWFyPC9oMj4NCg0KPHAgc3R5bGU9ImZvbnQtc2l6ZTogMTlweDtsaW5lLWhlaWdodDogMS4zMTU3ODk0NzQ7bWFyZ2luOiAwIDAgMzBweCAwOyI+DQogICAgRGVhciBDdXN0b21lcg0KPC9wPg0KDQo8cD5UaGlzIGlzIHlvdXIgbWVzc2FnZTwvcD4="
  val validFromValue: String         = "2026-03-09"
  val invalidFormIdValue: String     = "Test312"
  val batchIdValue: String           = "1234567"
  val sourceDataValue: String        = "ew0KICAgIm5hbWUiOiAiRGFuaWVsIiwNCiAgICJzZWF0IiA6ICJ5ZXMiDQp9"
  val reasonTextForAdding: String    = "Adding a formId for testing"
  val reasonTextForDeleting: String  = "Deleting a formId for testing"
  val rejectReasonText: String       = "Not a valid formId"
  val addReasonText: String          = "Approved valid formId for testing"

}
