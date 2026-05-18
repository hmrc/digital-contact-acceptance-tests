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
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{bta, deleteMongoRecordsFromCollection, navigateToAccount, ninoNumber1, pta, verifyEmail, waitUntilHeader}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests


class PtaPaperlessOptinTestSpec extends BaseSpec {


  Feature("Opt-in PTA/BTA Welcome Secure Message") {

    Scenario("Opt-in Welcome Secure Message with salutation for PTA customers", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, ninoNumber1)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail(ninoNumber1)
      And("I navigate to PTA account")
      navigateToAccount(pta)
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      And("I click Go to your messages link")
      PaperlessPTAHomePage.messageVerification()
      Then("opt-in secure message is displayed in the inbox")
      waitUntilHeader("Messages")
      PaperlessPTAInboxPage.checkMessageSubject("Your online tax letters")
    }
  }

  Scenario("Opt-in Welcome Secure Message with salutation for BTA customers", PtaPaperlessTests) {
    Given("I am logged into BTA account with nino and sautr enrolment")
    LoginUsingAuthWizardPage.pageLoad()
    LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr2")
    PaperlessBTAHomePage.waitUntilPageLoad()
    PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
    And("I am unverified for paperless")
    PaperlessInterruptPage.fillInterruptPageForOptin()
    PaperlessEmailPage.fillEmailPage()
    PaperlessVerifyEmailPage.pageTitle()
    And("I verify the email address")
    verifyEmail()
    And("I navigate to BTA account")
    navigateToAccount(bta)
    And("I see the page: Business Tax Account")
    PaperlessBTAHomePage.waitUntilPageLoad()
    And("I click Go to your messages link")
    PaperlessPTAHomePage.messageVerification()
    Then("opt-in secure message is displayed in the inbox")
    PaperlessPTAInboxPage.checkMessageSubject("Your online tax letters")
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
    deleteMongoRecordsFromCollection("secure message")
  }

}
