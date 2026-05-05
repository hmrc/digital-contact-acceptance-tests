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

package uk.gov.hmrc.ui.specs.preferencesAdminTests

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.pages.preferencesAdmin.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PreferencesAdminTests
import uk.gov.hmrc.ui.utils.TestData

class PaperlessMessageBrakeSpec extends BaseSpec with TestData {

  Feature("Message Brake test via Preferences Admin Frontend") {

    Scenario("Preferences Admin can reject the batch via message break", PreferencesAdminTests) {
      Given("I am logged into PTA account with nino enrolment and verify the email")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginPTAUsingAuthWizardByNinoOnly()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      And("I create a GMC message is created via EMA using invalid form id")
      deleteMongoRecordsFromCollection("Secure Message")
      createV4Message("Invalid FormId")
      When("Admin log into the preferences admin")
      PreferencesAdminPage.loadPage()
      PreferencesAdminPage.adminLogin()
      And("When click on the message brake link")
      PreferencesAdminPage.clickOnMessageBrake()
      And("I select the relevant formId checkbox and click on reject button")
      PreferencesMessageBrakePage.selectFormIdAndClickOnRejectButton()
      And("I enter the reason for rejecting the message")
      PreferencesMessageBrakePage.enterReasonForReject()
      Then("I should see that no more that formId exits in message brake")
      PreferencesMessageBrakePage.messageBrakeListEmpty()
    }

    Scenario("Preferences Admin can approve the batch via message break", PreferencesAdminTests) {
      Given("I am logged into PTA account with nino enrolment and verify the email")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginPTAUsingAuthWizardByNinoOnly()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      And("I create a GMC message is created via EMA using invalid form id")
      deleteMongoRecordsFromCollection("Secure Message")
      createV4Message("Invalid FormId")
      When("Admin log into the preferences admin")
      PreferencesAdminPage.loadPage()
      PreferencesAdminPage.adminLogin()
      And("When click on the message brake link")
      PreferencesAdminPage.clickOnMessageBrake()
      And("I select the relevant formId checkbox and click on reject button")
      PreferencesMessageBrakePage.selectFormIdAndClickOnApproveButton()
      And("I enter the reason for rejecting the message")
      PreferencesMessageBrakePage.enterReasonForApprove()
      Then("I should see that no more that formId exits in message brake")
      PreferencesMessageBrakePage.messageBrakeListEmpty()
    }
  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }
}
