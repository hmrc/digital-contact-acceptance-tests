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
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{deleteMongoRecordsFromCollection, pta, setVersionMajor, verifyEmail}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests

class PtaPaperlessReOptOutSurveyPageTestSpec extends BaseSpec {

  Feature("Re-opt-out Survey page") {

    Scenario("Survey submission with questions and reason during re-opt-out", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into PTA account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      And("I see the page: Keep getting your tax letters online")
      PaperlessReOptInPage.waitUntilPageLoad()
      And("I re-opt-out from the Re-opt-in interrupt page")
      PaperlessReOptInPage.reOptOut()
      Then("I see the page: You now get tax letters by post")
      PaperlessOptoutPage.waitUntilPageLoad()
      When("I click Continue Opt out button")
      PaperlessOptoutPage.clickOnContinueButton()
      And("I see the page: Why did you choose to go back to paper letters?")
      PaperlessOptOutSurveyPage.waitUntilPageLoad(true)
      And("I select the questions for re-opt-out survey")
      PaperlessOptOutSurveyPage.reOptOutSurveyQuestionsSelection()
      And("I enter the reason")
      PaperlessOptOutSurveyPage.enterSurveyReason()
      And("I click Continue button")
      PaperlessOptOutSurveyPage.clickOnContinueButton()
      Then("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
    }

    Scenario("Survey skip without questions and reasons during re-opt-out", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into PTA account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      And("I see the page: Keep getting your tax letters online")
      PaperlessReOptInPage.waitUntilPageLoad()
      And("I re-opt-out from the Re-opt-in interrupt page")
      PaperlessReOptInPage.reOptOut()
      Then("I see the page: You now get tax letters by post")
      PaperlessOptoutPage.waitUntilPageLoad()
      When("I click Continue Opt out button")
      PaperlessOptoutPage.clickOnContinueButton()
      And("I see the page: Why did you choose to go back to paper letters?")
      PaperlessOptOutSurveyPage.waitUntilPageLoad(true)
      And("I Skip the survey")
      PaperlessOptOutSurveyPage.clickOnSkipButton()
      Then("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
    }

    Scenario("No survey during re-opt-in", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into PTA account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      And("I see the page: Keep getting your tax letters online")
      PaperlessReOptInPage.waitUntilPageLoad()
      And("I re-opt-in from the Re-opt-in interrupt page")
      PaperlessReOptInPage.reOptIn()
      And("I click the already opted in email address and continue")
      PaperlessReOptInPage.reOptInWithVerifiedEmail()
      Then("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      And("I do not see the survey page")
      PaperlessOptOutSurveyPage.pageNotVisible()
    }

  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
