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
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{clickOnBackLink, deleteMongoRecordsFromCollection, pta, selectLanguageEnglish, selectLanguageWelsh, waitUntilHeader}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests


class PtaPaperlessConfirmEmailAddressPageTestSpec extends BaseSpec {


  Feature("Email address confirmation") {

    Scenario("PTA customer can resend the verification email", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate from PTA to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I click Fix this link")
      PaperlessCheckYourSettingsPage.clickOnFixthisLink()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I see the link Use a different email address")
      PaperlessVerifyEmailPage.useDifferentEmailAddressLinkExist()
      And("I click Send the link again")
      PaperlessVerifyEmailPage.sendTheLinkAgain()
      Then("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
    }

    Scenario("Customer can change the email address", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate from PTA to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      And("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I click Fix this link")
      PaperlessCheckYourSettingsPage.clickOnFixthisLink()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I see the link Use a different email address")
      PaperlessVerifyEmailPage.useDifferentEmailAddressLinkExist()
      And("I click Use a different email address link")
      PaperlessVerifyEmailPage.useDifferentEmailAddress()
      Then("I see the page: Enter your email address")
      PaperlessEmailPage.waitUntilPageLoad()
      And("I enter Email address details")
      PaperlessEmailPage.fillEmailPage()
    }

    Scenario("Customer can navigate to the previous page", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      Then("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I click Fix this link")
      PaperlessCheckYourSettingsPage.clickOnFixthisLink()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I click Back link")
      clickOnBackLink()
      Then("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
    }

    Scenario("Customer can change the language from English to Welsh", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      Then("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I click Fix this link")
      PaperlessCheckYourSettingsPage.clickOnFixthisLink()
      And("I click toggle link to Welsh")
      selectLanguageWelsh()
      Then("I see the page: Cadarnhau’ch cyfeiriad e-bost")
      waitUntilHeader("Cadarnhau’ch cyfeiriad e-bost")
    }

    Scenario("Customer can change the language from Welsh to English", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      When("I navigate to Check Your Settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      Then("I see the page: Check Your Settings")
      PaperlessCheckYourSettingsPage.waitUntilPageLoad()
      And("I click Fix this link")
      PaperlessCheckYourSettingsPage.clickOnFixthisLink()
      And("I click toggle link to Welsh")
      selectLanguageWelsh()
      And("I click toggle link to English")
      selectLanguageEnglish()
      Then("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
    }

  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
