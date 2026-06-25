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

import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.BtaPaperlessTests

class BtaPaperlessStatusSpec extends BaseSpec with BasePage {

  Feature("BTA - Paperless Status") {

    Scenario("BTA paperless status for a new customer", BtaPaperlessTests) {
      Given("I am logged into BTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      Then("I see the status By Post and the link Get tax letters online")
      PaperlessBTAHomePage.checkContactPreferenceText("By post")
      PaperlessBTAHomePage.checkDisplayedLink("Get tax letters online")
    }

    Scenario("BTA paperless status for unverified customer", BtaPaperlessTests) {
      Given("I am logged into BTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      When("I click Close button")
      PaperlessBTAHomePage.clickOnCloseButton()
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      Then("I see the status By post, until you verify your email address and the link Fix this")
      PaperlessBTAHomePage.checkContactPreferenceText("By post, until you verify your email address")
      PaperlessBTAHomePage.checkDisplayedLink("Fix this")
    }

    Scenario("BTA paperless status for bounced customer", BtaPaperlessTests) {
      Given("I am logged into BTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      bounceVerifyEmail()
      When("I am logged into the account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.checkContactPreferenceText("By post – we cannot contact you on your email address")
      PaperlessBTAHomePage.checkDisplayedLink("Fix this")
    }

    Scenario("BTA paperless status for existing customer", BtaPaperlessTests) {
      Given("I logged into BTA account with nino with sautr1 enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      And("I navigate to BTA account")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      Then("I see the status Online – we will let you know by email and the link Check your settings")
      PaperlessBTAHomePage.checkDisplayedLink("Check your settings")
    }

    Scenario("BTA paperless status for reoptin journey", BtaPaperlessTests) {
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("I verify email")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into account ")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the status Online — you need to agree to our updated terms and the link Review updated terms")
      PaperlessBTAHomePage.checkContactPreferenceText("Online — you need to agree to our updated terms")
      PaperlessBTAHomePage.checkDisplayedLink("Review updated terms")
      PaperlessBTAHomePage.clickOnReviewUpdatedTermsLink()
      PaperlessReOptInPage.reOptIn(false)
      PaperlessReOptInPage.reOptInWithVerifiedEmail()
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
    }

    Scenario("BTA paperless status for reoptin modified journey", BtaPaperlessTests) {
      Given("I logged into BTA account with nino with sautr1 enrolment for verified -> bounced customer")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      And("I verify email")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("I update major version")
      setVersionMajor()
      And("I am logged into account ")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the status Online — you need to agree to our updated terms and the link Review updated terms")
      PaperlessBTAHomePage.checkContactPreferenceText("Online — you need to agree to our updated terms")
      PaperlessBTAHomePage.checkDisplayedLink("Review updated terms")
      PaperlessBTAHomePage.clickOnReviewUpdatedTermsLink()
      PaperlessReOptInPage.reOptIn()
      PaperlessReOptInPage.reOptInWithNewEmail()
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      Then("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
    }

    Scenario("BTA paperless status for an opted-out customer", BtaPaperlessTests) {
      Given("Given I logged into BTA account with nino with sautr enrolment for opt-out customer")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      When("I continue opt out")
      PaperlessInterruptPage.fillInterruptPageForOptout()
      PaperlessOptoutPage.clickOnContinueButton()
      When("I see the page: Business Tax Account")
      Then("I see the status By Post and the link Get tax letters online")
      PaperlessBTAHomePage.checkContactPreferenceText("By post")
      PaperlessBTAHomePage.checkDisplayedLink("Get tax letters online")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
