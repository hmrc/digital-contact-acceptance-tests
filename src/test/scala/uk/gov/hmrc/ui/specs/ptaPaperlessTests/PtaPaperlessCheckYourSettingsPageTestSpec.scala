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
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{deleteMongoRecordsFromCollection, pta, selectLanguageEnglish, selectLanguageWelsh}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests

class PtaPaperlessCheckYourSettingsPageTestSpec extends BaseSpec {

  Feature("Check Your Settings Page") {

    Scenario("Content for the unverified user in Check Your Settings page", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      PaperlessVerifyEmailPage.pageTitle()
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      Then("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I see Tax documents: Post only until you verify your email address")
      PaperlessCheckYourSettingsPage.contentVerification(
        "Tax documents",
        "Post only until you verify your email address"
      )
      And("I see Emails sent in: Not available until you verify your email address")
      PaperlessCheckYourSettingsPage.contentVerification(
        "Emails sent in",
        "Not available until you verify your email address"
      )
    }

    Scenario("Change email address for unverified user under Contact Details", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      PaperlessVerifyEmailPage.pageTitle()
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I Click on Change link to change email address")
      PaperlessCheckYourSettingsPage.clickOnChangeEmailAddressLink()
      Then("I see the page: Change your email address")
      PaperlessChangeEmailAddressPage.waitUntilPageLoad()
    }

    Scenario("Unverified user navigates to PTA landing page from Check Your Settings page", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      PaperlessVerifyEmailPage.pageTitle()
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I Click Continue to your Personal Account button")
      PaperlessCheckYourSettingsPage.clickOnContinueButton()
      Then("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
    }

    Scenario(
      "Unverified user toggles the language on Check Your Settings Page from English to Welsh",
      PtaPaperlessTests
    ) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      PaperlessVerifyEmailPage.pageTitle()
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I Click the toggle link to Welsh")
      selectLanguageWelsh()
      Then("I see the page: Gwirio’ch gosodiadau")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad(true)
    }

    Scenario(
      "Unverified user toggles the language on Check Your Settings Page from Welsh to English",
      PtaPaperlessTests
    ) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      PaperlessVerifyEmailPage.pageTitle()
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I click the toggle link to Welsh")
      selectLanguageWelsh()
      PaperlessCheckYourSettingsPage.waitUntilPageLoad(true)
      And("I click the toggle link to English")
      selectLanguageEnglish()
      Then("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
