/*
 * Copyright 2024 HM Revenue & Customs
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

trait ApiPayLoad {

  val email: String  = GeneratedTestData.email
  val email2: String = GeneratedTestData.email2

  val payloadBounceEmail1: String =
    s"""{
            "emailAddress": "$email"
    }""".stripMargin

  val payloadBounceEmail2: String =
    s"""{
            "emailAddress": "$email2"
    }""".stripMargin

  val digitalSuppressionDataToNpsThruHip: String =
    s"""
       |{
       |"nationalInsuranceNumber": "YY000200A",
       |"bouncedFlag": false,
       |"currentOptimisticLock": 4,
       |"printPreferences": [{
       |"outputFormType": "P2",
       |"printStatus": "DIGITAL",
       |"lastUpdatedDate": "2025-04-11"}]
       |}""".stripMargin

  val paperSuppressionDataToNpsThruHip: String =
    s"""
       |{
       |"nationalInsuranceNumber": "YY000200A",
       |"bouncedFlag": false,
       |"currentOptimisticLock": 4,
       |"printPreferences": [{
       |"outputFormType": "P2",
       |"printStatus": "PAPER",
       |"lastUpdatedDate": "2025-04-11"}]
       |}""".stripMargin

  val bouncedSuppressionDataToNpsThruHip: String =
    s"""
       |{
       |"nationalInsuranceNumber": "YY000200A",
       |"bouncedFlag": true,
       |"currentOptimisticLock": 4,
       |"printPreferences": [{
       |"outputFormType": "P2",
       |"printStatus": "PAPER",
       |"lastUpdatedDate": "2025-04-11"}]
       |}""".stripMargin

}
