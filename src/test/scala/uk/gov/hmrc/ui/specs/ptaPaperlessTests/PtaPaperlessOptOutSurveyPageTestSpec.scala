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

package uk.gov.hmrc.ui.specs.ptaPaperlessTests

import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{deleteMongoRecordsFromCollection, pta, waitUntilHeader}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests


class PtaPaperlessOptOutSurveyPageTestSpec extends BaseSpec {


  Feature("Opt-out Survey page") {

    Scenario("Survey submission with questions and reason during opt-out", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      And("I see the page: Choose how to get your tax letters")
      waitUntilHeader("Choose how to get your tax letters")
      When("I opt-out from the Standard paperless interrupt page")
      PaperlessInterruptPage.fillInterruptPageForOptout()
      And("I see the page: You now get tax letters by post")
      PaperlessOptoutPage.waitUntilPageLoad()
      And("I click Continue opt-out button")
      PaperlessOptoutPage.clickOnContinueButton()
      And("I see the page: Why did you choose paper tax letters")
      PaperlessOptOutSurveyPage.waitUntilPageLoad()
      And("I select the question for opt-out survey")
      PaperlessOptOutSurveyPage.optOutSurveyQuestionsSelection()
      And("I enter the reason")
      PaperlessOptOutSurveyPage.enterSurveyReason()
      And("I click Continue button")
      PaperlessOptOutSurveyPage.clickOnContinueButton()
      Then("I see Post in the Check your settings page")
      PaperlessCheckYourSettingsPage.contentVerification("Tax documents", "Post")
    }

    Scenario("Survey skip without questions and reasons during opt-out", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      And("I see the page: Choose how to get your tax letters")
      waitUntilHeader("Choose how to get your tax letters")
      When("I opt-out from the Standard paperless interrupt page")
      PaperlessInterruptPage.fillInterruptPageForOptout()
      And("I see the page: You now get tax letters by post")
      PaperlessOptoutPage.inPaperlessOptoutConfirmPage()
      And("I click Continue opt-out button")
      PaperlessOptoutPage.clickOnContinueButton()
      And("I see the page: Why did you choose paper tax letters")
      PaperlessOptOutSurveyPage.waitUntilPageLoad()
      And("I click Skip button")
      PaperlessOptOutSurveyPage.clickOnSkipButton()
      Then("I see Post in the Check your settings page")
      PaperlessCheckYourSettingsPage.contentVerification("Tax documents", "Post")
    }

    Scenario("No survey during opt-in", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      waitUntilHeader("Verify your email address")
      And("I do not see the survey page")
      PaperlessOptOutSurveyPage.pageNotVisible()
      When("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      Then("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
    }

  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
