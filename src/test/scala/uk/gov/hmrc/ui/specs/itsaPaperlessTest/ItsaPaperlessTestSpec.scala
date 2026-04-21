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

package uk.gov.hmrc.ui.specs.itsaPaperlessTest
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{bounceVerifyEmail, deleteMongoRecordsFromCollection, itsa, setVersionMajor, verifyEmail, waitUntilHeader}
import uk.gov.hmrc.ui.pages.paperless.{PaperlessBTAHomePage, PaperlessEmailPage, PaperlessInterruptPage, PaperlessOptoutPage, PaperlessReOptInPage, PaperlessTroubleSendingEmailPage, PaperlessVerifyEmailPage}
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.{ItsaPaperlessTests, Wip}


class ItsaPaperlessTestSpec extends BaseSpec {


  Feature("ITSA Paperless Interrupt page") {

    Scenario("ITSA Opt-in from Interrupt page", ItsaPaperlessTests) {
      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      When("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      Then("I can see Contact preference: By post, until you verify your email address")
      PaperlessBTAHomePage.checkContactPreferenceText("By post, until you verify your email address")
    }

    Scenario("ITSA Opt-out from Interrupt page", ItsaPaperlessTests) {
      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      When("I click Continue Optout button")
      PaperlessInterruptPage.fillInterruptPageForOptout()
      Then("I am in Opt out confirmation page")
      PaperlessOptoutPage.inPaperlessOptoutConfirmPage()
    }

    Scenario("ITSA email unverified journey - Send the link again", ItsaPaperlessTests) {
      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      And("I see the page: Verify your email address")
      PaperlessBTAHomePage.clickOnFixthisLink()
      waitUntilHeader("Verify your email address")
      When("I click Send the link again")
      PaperlessVerifyEmailPage.sendTheLinkAgain()
      And("I verify email")
      verifyEmail()
      And("I am logged into account ")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      Then("I can see Contact preference: Online – we will let you know by email")
      PaperlessBTAHomePage.checkContactPreferenceText("Online – we will let you know by email")
    }

    Scenario("ITSA email unverified journey - Use a different email address", ItsaPaperlessTests) {

      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      And("I see the page: Verify your email address")
      PaperlessBTAHomePage.clickOnFixthisLink()
      waitUntilHeader("Verify your email address")
      When("I click Use a different email address")
      PaperlessVerifyEmailPage.useDifferentEmailAddress()
      And("I enter an email address")
      PaperlessEmailPage.fillEmailPage()
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      Then("I can see Contact preference: By post, until you verify your email address")
      PaperlessBTAHomePage.checkContactPreferenceText("By post, until you verify your email address")

    }

    Scenario("ITSA Opt-in with email address bounced page - enter email address", ItsaPaperlessTests) {

      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("email bounced")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      bounceVerifyEmail()
      When("I am logged into the account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      And("I see the page: We are having trouble sending you emails")
      PaperlessBTAHomePage.clickOnFixthisLink()
      waitUntilHeader("We are having trouble sending you emails")
      And("I enter email")
      PaperlessTroubleSendingEmailPage.enterYourEmailAddress()
      PaperlessEmailPage.fillEmailPage()
      And("I click Close button")
      PaperlessVerifyEmailPage.continueVerifyEmailAddressPage()
      Then("I can see Contact preference: By post, until you verify your email address")
      PaperlessBTAHomePage.checkContactPreferenceText("By post, until you verify your email address")
    }

    Scenario("ITSA Opt-out from email address bounced page", ItsaPaperlessTests) {

      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("email is bounced")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      bounceVerifyEmail()
      When("I am logged into account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      And("I see the page: We are having trouble sending you emails")
      PaperlessBTAHomePage.clickOnFixthisLink()
      waitUntilHeader("We are having trouble sending you emails")
      And(" I opt out")
      PaperlessTroubleSendingEmailPage.doNotWantToGetOnlineTaxLetters()
      PaperlessInterruptPage.fillInterruptPageForOptout()
      Then("I am in Opt out confirmation page")
      PaperlessOptoutPage.inPaperlessOptoutConfirmPage()
    }

    Scenario("ITSA Re-Opt-in journey - already verified email address", ItsaPaperlessTests) {

      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("I verify email")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into account ")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      And("I re-optin with the same email address")
      PaperlessBTAHomePage.clickOnReviewUpdatedTermsLink()
      PaperlessReOptInPage.reOptIn()
      PaperlessReOptInPage.reOptInWithVerifiedEmail()
      Then("I can see Contact preference: Online – we will let you know by email")
      PaperlessBTAHomePage.checkContactPreferenceText("Online – we will let you know by email")
    }


    Scenario("ITSA Re-Opt-in journey - new email address", ItsaPaperlessTests) {

      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("I verify email")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      And("I re-optin with new email address")
      PaperlessBTAHomePage.clickOnReviewUpdatedTermsLink()
      PaperlessReOptInPage.reOptIn()
      PaperlessReOptInPage.reOptInWithNewEmail()
      Then("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
    }

    Scenario("ITSA Re-Opt-in Modified journey", ItsaPaperlessTests) {

      Given("I am logged into ITSA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("I verify email")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      And("email is bounced")
      bounceVerifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", itsa)
      And("I optin with an new email")
      PaperlessBTAHomePage.clickOnReviewUpdatedTermsLink()
      PaperlessReOptInPage.reOptIn(true)
      PaperlessReOptInPage.reOptinEnterNewEmail()
      Then("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
    }
  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
