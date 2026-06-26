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
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{bounceVerifyEmail, deleteMongoRecordsFromCollection, pta, suppressionDataToNpsThruHip, verifyEmail}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests
import uk.gov.hmrc.ui.utils.GeneratedTestData
import uk.gov.hmrc.ui.utils.GeneratedTestData.epsNinoNumber

class p2PaperlessJourneyHipTestSpec extends BaseSpec {

  Feature("P2 preference status flow from DC to EPS to NPS via HIP") {

    Scenario("Paperless opt - in journey for the P2 customer", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, epsNinoNumber)
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      When("I verify the email address")
      verifyEmail(epsNinoNumber)
      Then("the suppression data is sent to NPS for outputPreference as digital and bounced false through HIP")
      suppressionDataToNpsThruHip("digital")
    }

    Scenario("Paperless opt - out journey for the P2 customer", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, epsNinoNumber)
      When("I opt-out from the Standard paperless interrupt page")
      PaperlessInterruptPage.fillInterruptPageForOptout()
      PaperlessOptoutPage.waitUntilPageLoad()
      And("I click Continue opt-out button")
      PaperlessOptoutPage.clickOnContinueButton()
      And("I see the page: Why did you choose paper tax letters")
      PaperlessOptOutSurveyPage.waitUntilPageLoad()
      And("I click Skip button")
      PaperlessOptOutSurveyPage.clickOnSkipButton()
      And("I see Post in the Check your settings page")
      PaperlessCheckYourSettingsPage.contentVerification("Tax documents", "Post")
      Then("the suppression data is sent to NPS for outputPreference as paper and bounced false through HIP")
      suppressionDataToNpsThruHip("paper")
    }

    Scenario("Paperless bounce journey for the P2 customer", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, epsNinoNumber)
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      When("the email is bounced")
      bounceVerifyEmail()
      Then("the suppression data is sent to NPS for outputPreference as paper and bounced true through HIP")
      suppressionDataToNpsThruHip("bounced")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }
}
