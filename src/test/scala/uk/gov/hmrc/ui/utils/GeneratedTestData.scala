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

object GeneratedTestData {
  private def randomDigits(n: Int): String =
    (1 to n).map(_ => scala.util.Random.nextInt(10)).mkString

  val identifierValue: String = randomDigits(10)

  val identifierValue2: String = randomDigits(10)

  val ninoNumber: String = s"YY${randomDigits(6)}A"

  val ninoNumber1: String = s"SP${randomDigits(6)}A"

  val itsaIdentifierValue: String = s"ITSA${randomDigits(11)}"

  val vatVrnIdentifierValue: String = randomDigits(9)

  val iossIdentifierValue: String = s"GB${randomDigits(10)}"

  val iossInterIdentifierValue: String = s"IN${randomDigits(10)}"
  
  val iossNetpIdentifierValue: String = s"IN${randomDigits(10)}"

  val ossIdentifierValue: String = randomDigits(9)

  val adIdentifierValue: String = s"XMADP${randomDigits(9)}"

  val identifierObdtsValidValue: String = s"XZFH${randomDigits(11)}"

  val identifierObdtsInvalidValue: String = s"XGER${randomDigits(11)}"

  val identifierPptValidValue: String = s"XMPPT${randomDigits(10)}"

  val identifierPptInvalidValue: String = s"ABCDE${randomDigits(10)}"
  
  val email: String = "testuser123@example.com"

  val email2: String = "testuser123@example.org"
}
