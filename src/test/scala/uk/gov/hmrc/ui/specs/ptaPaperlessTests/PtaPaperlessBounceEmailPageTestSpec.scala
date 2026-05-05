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
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{bounceVerifyEmail, deleteMongoRecordsFromCollection, navigateToAccount, pta, waitUntilHeader}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PtaPaperlessTests


class PtaPaperlessBounceEmailPageTestSpec extends BaseSpec {


  Feature("Bounce Email page") {

    Scenario("Email bounce page, enter email address page and OptOut page display through Email Bounce page", PtaPaperlessTests) {
      Given("I am logged into PTA account with nino and sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr", pta)
      waitUntilHeader("Choose how to get your tax letters")
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      waitUntilHeader("Verify your email address")
      And("the email is bounced")
      bounceVerifyEmail()
      And("I navigate to PTA account")
      navigateToAccount(pta)
      And("I see the page: Personal Tax Account")
      PaperlessPTAHomePage.waitUntilPageLoad()
      And("I navigate to Check your settings page")
      PaperlessPTAHomePage.navigateToCheckYourSettings()
      When("I click fix this")
      PaperlessCheckYourSettingsPage.clickOnFixthisLink()
      And("I see the page: We are having trouble sending you emails")
      waitUntilHeader("We are having trouble sending you emails")
      And("I see the Enter email address button")
      PaperlessTroubleSendingEmailPage.enterEmailButtonExists()
      And("I see the link: I do not want to get online tax letters")
      PaperlessTroubleSendingEmailPage.doNotWantOnlineLettersLinkExists()
      And("I click Enter email address button")
      PaperlessTroubleSendingEmailPage.enterYourEmailAddress()
      And("I see the page: Enter your email address")
      PaperlessEmailPage.waitUntilPageLoad()
      And("I enter the Email address details")
      PaperlessEmailPage.fillEmailPage()
      Then("I see the page: Verify your email address")
      waitUntilHeader("Verify your email address")
    }
  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }

}
