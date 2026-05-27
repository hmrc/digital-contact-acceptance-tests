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
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{clickOnBackLink, deleteMongoRecordsFromCollection, navigateToAccount, pta, selectLanguageEnglish, selectLanguageWelsh, verifyEmail}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests


class PtaPaperlessChooseLanguagePageTestSpec extends BaseSpec {


  Feature("Choose language for email preference page") {

    Scenario("Customer can change language preference from english to welsh and vice versa", PtaPaperlessTests) {
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
      And("I navigate to PTA account")
      navigateToAccount(pta)
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      And("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      When("I click Emails sent in Change link")
      PaperlessCheckYourSettingsPage.clickOnEmailsSentInChangeLink()
      And("I choose Send paperless email notification in Welsh" )
      PaperlessChooseLanguagePage.chooseSendPaperlessLanguage()
      And("I click Continue button")
      PaperlessChooseLanguagePage.clickOnContinueButton()
      Then("I see the page: Check your settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I see Emails sent in: Welsh, if available" )
      PaperlessCheckYourSettingsPage.contentVerification("Emails sent in for verified", "Welsh, if available")
      When("I click Emails sent in Change link")
      PaperlessCheckYourSettingsPage.clickOnEmailsSentInChangeLink()
      PaperlessChooseLanguagePage.waitUntilPageLoad()
      And("I choose Send paperless email notification in English")
      PaperlessChooseLanguagePage.chooseSendPaperlessLanguage(false)
      And("I click Continue button")
      PaperlessChooseLanguagePage.clickOnContinueButton()
      Then("I see the page: Check your settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I see Emails sent in: English")
      PaperlessCheckYourSettingsPage.contentVerification("Emails sent in for verified", "English")
    }

    Scenario("Customer can navigate to the previous pages", PtaPaperlessTests) {
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
      And("I navigate to PTA account")
      navigateToAccount(pta)
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      And("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      When("I click Emails sent in Change link")
      PaperlessCheckYourSettingsPage.clickOnEmailsSentInChangeLink()
      And("I see the page: Get your paperless email notification in Welsh")
      PaperlessChooseLanguagePage.waitUntilPageLoad()
      And("I click Back link")
      clickOnBackLink()
      Then("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
    }

    Scenario("Customer can change the language from English to Welsh", PtaPaperlessTests) {
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
      And("I navigate to PTA account")
      navigateToAccount(pta)
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      And("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      When("I click Emails sent in Change link")
      PaperlessCheckYourSettingsPage.clickOnEmailsSentInChangeLink()
      And("I see the page: Get your paperless email notification in Welsh")
      PaperlessChooseLanguagePage.waitUntilPageLoad()
      And("I click toggle link to Welsh")
      selectLanguageWelsh()
      Then("I see the page: Cael eich hysbysiadau di-bapur drwy e-bost yn Gymraeg")
      PaperlessChooseLanguagePage.waitUntilPageLoad(true)
    }

    Scenario("Customer can change the language from Welsh to English", PtaPaperlessTests) {
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
      And("I navigate to PTA account")
      navigateToAccount(pta)
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      And("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      When("I click Emails sent in Change link")
      PaperlessCheckYourSettingsPage.clickOnEmailsSentInChangeLink()
      And("I see the page: Get your paperless email notification in Welsh")
      PaperlessChooseLanguagePage.waitUntilPageLoad()
      And("I click toggle link to Welsh")
      selectLanguageWelsh()
      And("I click toggle link to English")
      selectLanguageEnglish()
      Then("I see the page: Get your paperless email notification in Welsh")
      PaperlessChooseLanguagePage.waitUntilPageLoad()
    }

  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
