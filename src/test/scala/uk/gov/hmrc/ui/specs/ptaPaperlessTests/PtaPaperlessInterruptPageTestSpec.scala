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
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{deleteMongoRecordsFromCollection, navigateToAccount, pta, selectLanguageWelsh, verifyEmail, waitUntilHeader}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests


class PtaPaperlessInterruptPageTestSpec extends BaseSpec {


  Feature("PTA Paperless Interrupt page") {

    Scenario("Opt-in Interrupt page", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      Then("I see the page: Choose how to get your tax letters")
      waitUntilHeader("Choose how to get your tax letters")
    }

    Scenario("Welsh content on Opt-in page", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      When("I click toggle link to Welsh")
      selectLanguageWelsh()
      Then("I see the page: Dewis sut i gael eich llythyrau treth")
      waitUntilHeader("Dewis sut i gael eich llythyrau treth")
    }

    Scenario("Opt-in with email address verified", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      When("I click Online and continue")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      And("I enter the Email address details")
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I verify the email address")
      verifyEmail()
      And("I navigate to PTA account")
      navigateToAccount(pta)
      Then("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
    }

    Scenario("Opt-in without email address verified", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr2 enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr2", pta)
      waitUntilHeader("Choose how to get your tax letters")
      When("I click Online and continue")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      And("I enter the Email address details")
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      Then("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
    }

    Scenario("Non-SA user is not shown go paperless page", PtaPaperlessTests) {
      Given("I am logged into BTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr")
      PaperlessBTAHomePage.waitUntilPageLoad()
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.waitUntilPageLoad()
      And("No get letters online link")
      PaperlessBTAHomePage.noGetLettersOnlineLink()
    }

  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
//    deleteMongoRecordsFromCollection("Secure Message")
  }

}
