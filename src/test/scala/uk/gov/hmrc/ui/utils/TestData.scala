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

  val credentialStrength: String = "strong"
  val confidenceLevel: String = "200"
  val enrolmentKey: String = "IR-SA"
  val identifierName: String = "UTR"
  val ninoNumber: String = "YY000200A"
  val email: String = "testuser@gmail.com"
  
  def redirectUrlForPTA: String =TestEnvironment.url("demo-frontend")
  def deleteAllPreferencesCollection(): String = TestEnvironment.url("preferences")
  def saApiProxy(): String = TestEnvironment.url("sa-api-proxy")

}
