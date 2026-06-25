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

package uk.gov.hmrc.ui.specs.owsmTests.ema

import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{createV4Message, deleteMongoRecordsFromCollection, pta, verifyEmail}
import uk.gov.hmrc.ui.pages.messages.SecureMessagesPage
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.OwsmTests
import uk.gov.hmrc.ui.utils.GeneratedTestData

class EmaGmcMessageErrorDetailsTestSpec extends BaseSpec {

  def setUpPreferences(): Unit = {
    LoginUsingAuthWizardPage.pageLoad()
    LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, GeneratedTestData.ninoNumber)
    PaperlessInterruptPage.pageTitle()
    And("I am unverified for paperless")
    PaperlessInterruptPage.fillInterruptPageForOptin()
    PaperlessEmailPage.fillEmailPage(GeneratedTestData.email)
    PaperlessVerifyEmailPage.pageTitle()
    And("I verify the email address")
    verifyEmail()
  }

  Feature("GMC EMA error messages from Quadient new endpoint") {

    Scenario("Validating the message response with invalid alertQueue", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      setUpPreferences()
      When("A GMC message is created via EMA using invalidAlertQueue")
      createV4Message("invalidAlertQueue")
      Then("The response must contains: Invalid alert queue submitted")
      SecureMessagesPage.pageContains("Invalid alert queue submitted")
    }

    Scenario("Validating the message response with empty alertQueue", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      setUpPreferences()
      When("A GMC message is created via EMA using emptyAlertQueue")
      createV4Message("emptyAlertQueue")
      Then("The response must contains: invalid alert queue provided")
      SecureMessagesPage.pageContains("invalid alert queue provided")
    }

    Scenario("Validating the message response with invalid source data", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      setUpPreferences()
      When("A GMC message is created via EMA using invalidSourceData")
      createV4Message("invalidSourceData")
      Then("The response must contains: invalid source data provided")
      SecureMessagesPage.pageContains("invalid source data provided")
    }

    Scenario("Validating the message response with unknown tax identifier", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      setUpPreferences()
      When("A GMC message is created via EMA using unknownTaxIdentifier")
      createV4Message("unknownTaxIdentifier")
      Then("The response must contains: The backend has rejected the message due to an unknown tax identifier")
      SecureMessagesPage.pageContains("The backend has rejected the message due to an unknown tax identifier")
    }

    Scenario("Validating the message response with missing tax identifier", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      setUpPreferences()
      When("A GMC message is created via EMA using missingTaxIdentifier")
      createV4Message("missingTaxIdentifier")
      Then("The response must contains: Missing mandatory fields: {$.recipient.taxIdentifier.'value'}")
      SecureMessagesPage.pageContains("Missing mandatory fields: {$.recipient.taxIdentifier.'value'}")
    }

    Scenario("Validating the message response with missing details", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      setUpPreferences()
      When("A GMC message is created via EMA using missingDetails")
      createV4Message("missingDetails")
      Then(
        "The response must contains: Missing mandatory fields: {$.details.issueDate: does not match the date pattern"
      )
      SecureMessagesPage.pageContains("Missing mandatory fields: {$.details.issueDate: does not match the date pattern")
    }

    Scenario("Validating the message response with invalid email", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      setUpPreferences()
      When("A GMC message is created via EMA using invalidEmail")
      createV4Message("invalidEmail")
      Then("The response must contains: invalid email address provided")
      SecureMessagesPage.pageContains("invalid email address provided")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
    deleteMongoRecordsFromCollection("secure message")
  }
}
