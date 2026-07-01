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

package uk.gov.hmrc.ui.specs.eps

import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.messages.CdsMessages.{deleteMongoRecordsFromCollection, p2EmailAlerts}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{pta, suppressionDataToNpsThruHip, verifyEmail}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.EpsPaperlessTests
import uk.gov.hmrc.ui.utils.GeneratedTestData
import uk.gov.hmrc.ui.utils.GeneratedTestData.epsNinoNumber

class p2PaperlessEmailAlertsTestSpec extends BaseSpec {
  
  Feature("P2 Email flow from NPS to Customer via eps-message-renderer") {

    Scenario("The P2 email sent by the NPS user to the customer", EpsPaperlessTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, epsNinoNumber)
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      When("I verify the email address")
      verifyEmail(epsNinoNumber)
      And("the suppression data is sent to NPS for outputPreference as digital and bounced false through HIP")
      suppressionDataToNpsThruHip("digital")
      Then("the NPS user can send P2 email to the customer successfully")
      p2EmailAlerts(epsNinoNumber)
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }
}
