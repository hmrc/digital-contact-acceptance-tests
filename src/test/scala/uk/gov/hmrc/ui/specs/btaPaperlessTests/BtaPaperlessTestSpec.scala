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

package uk.gov.hmrc.ui.specs.btaPaperlessTests

import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.specs.tags.BtaPaperlessTests

class BtaPaperlessTestSpec extends BaseSpec with BasePage {

  Feature("BTA - Paperless Journeys") {

    Scenario("BTA customer opted-in and email verified journey", BtaPaperlessTests) {
      Given("I am logged into BTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I verify the email address")
      verifyEmail()
      And("I navigate to BTA account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      Then("I see the status Online – we will let you know by email and the link Check your settings")
      PaperlessBTAHomePage.checkContactPreferenceText("Online – we will let you know by email")
      PaperlessBTAHomePage.checkDisplayedLink("Check your settings")
      PaperlessBTAHomePage.clickOnCheckYourSettingsLink()
    }

    Scenario("BTA customer opted-in and email-re-verify-journey (send the link again)", BtaPaperlessTests) {
      Given("I am logged into BTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessEmailPage.clickOnCloseButton()
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I click Fix this link")
      PaperlessBTAHomePage.clickOnFixthisLink()
      And("And I see the page: Verify your email address")
      PaperlessVerifyEmailPage.verifyEmailAddressPageTitle()
      PaperlessVerifyEmailPage.sendTheLinkAgain()
      And("I verify the email address")
      verifyEmail()
      And("I navigate to BTA account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
    }

    Scenario("BTA customer opted-in and email unverified -> email changed journey", BtaPaperlessTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessEmailPage.clickOnCloseButton()
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I click Fix this link")
      PaperlessBTAHomePage.clickOnFixthisLink()
      And("And I see the page: Verify your email address")
      PaperlessVerifyEmailPage.verifyEmailAddressPageTitle()
      When("I click Use a different email address button")
      PaperlessVerifyEmailPage.useDifferentEmailAddress()
      And("I see the page: Enter your email address")
      PaperlessEmailPage.paperlessEmailAddressPageTitle()
      When("I enter the Email address details")
      PaperlessEmailPage.fillEmailPage()
      And("I click Close button")
      PaperlessEmailPage.clickOnCloseButton()
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
    }

    Scenario("BTA customer opted-in and email-bounce-journey (new email address entered)", BtaPaperlessTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("The email is bounced")
      bounceVerifyEmail()
      And("I click close button")
      PaperlessEmailPage.clickOnCloseButton()
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I see the status By post – we cannot contact you on your email address and the link Fix this")
      PaperlessBTAHomePage.checkContactPreferenceText("By post – we cannot contact you on your email address")
      PaperlessBTAHomePage.checkDisplayedLink("Fix this")
      And("I click Fix this link")
      PaperlessBTAHomePage.clickOnFixthisLink()
      And("I see the page: We are having trouble sending you emails")
      PaperlessTroubleSendingEmailPage.PaperlessTroubleSendingEmailPageTitle()
      When("I click Enter email address button")
      PaperlessTroubleSendingEmailPage.enterYourEmailAddress()
      And("I see the page: Enter your email address")
      PaperlessEmailPage.paperlessEmailAddressPageTitle()
      And("I enter the Email address details")
      PaperlessEmailPage.fillEmailPage()
      And("I click close button")
      PaperlessEmailPage.clickOnCloseButton()
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
    }

    Scenario("BTA customer opted-in and email-bounce then opt-out journey", BtaPaperlessTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("The email is bounced")
      bounceVerifyEmail()
      And("I click close button")
      PaperlessEmailPage.clickOnCloseButton()
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I see the status By post – we cannot contact you on your email address and the link Fix this")
      PaperlessBTAHomePage.checkContactPreferenceText("By post – we cannot contact you on your email address")
      PaperlessBTAHomePage.checkDisplayedLink("Fix this")
      And("I click Fix this link")
      PaperlessBTAHomePage.clickOnFixthisLink()
      And("I see the page: We are having trouble sending you emails")
      PaperlessTroubleSendingEmailPage.PaperlessTroubleSendingEmailPageTitle()
      And("I click I do not want to get online tax letters link")
      PaperlessTroubleSendingEmailPage.doNotWantToGetOnlineTaxLetters()
      And("I see the page: Choose how to get your tax letters")
      PaperlessInterruptPage.chooseHowToGetYourTaxLettersTitle()
      When("I opt-out from the Standard paperless interrupt page")
      PaperlessInterruptPage.fillInterruptPageForOptout()
      And("I see the page: You now get tax letters by post")
      PaperlessOptoutPage.youNowGetTaxLettersByPostTitle()
      PaperlessOptoutPage.clickOnContinueButton()
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      Then("I see the status By Post and the link Get tax letters online")
      PaperlessBTAHomePage.checkContactPreferenceText("By post")
      PaperlessBTAHomePage.checkDisplayedLink("Get tax letters online")
    }

    Scenario("BTA customer reoptin journey", BtaPaperlessTests) {
      Given("I logged into BTA account with nino with sautr enrolment for verified customer")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into account ")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Keep getting your tax letters online")
      PaperlessReOptInPage.PaperlessReOptInPageTitle()
      PaperlessBTAHomePage.clickOnReviewUpdatedTermsLink()
      PaperlessReOptInPage.reOptIn(false)
      And("I see the page: Which email do you want to use for your tax letters?")
      PaperlessReOptInChooseEmailPage.chooseEmailPageTitle()
      And("I click the already opted in email address and continue")
      PaperlessReOptInPage.reOptInWithVerifiedEmail()
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
    }

    Scenario("BTA customer reoptin modified journey", BtaPaperlessTests) {
      Given("I logged into BTA account with nino with sautr enrolment for verified -> bounced customer")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into account ")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Keep getting your tax letters online")
      PaperlessReOptInPage.PaperlessReOptInPageTitle()
      PaperlessBTAHomePage.clickOnReviewUpdatedTermsLink()
      PaperlessReOptInPage.reOptIn()
      PaperlessReOptInPage.reOptInWithNewEmail()
      And("I see the page: Enter your email address")
      PaperlessEmailPage.paperlessEmailAddressPageTitle()
      PaperlessVerifyEmailPage.clickOnCloseButton()
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
