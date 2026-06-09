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

package uk.gov.hmrc.ui.specs.owsm.ppt

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.ElementLocators.{demoFrontEndInboxFirstMessageSubject, demoFrontEndInboxFirstMessageSubject2}
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.{logIntoCustomerAdvisorMessageInboxPage, logIntoMessage}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.pages.messages.MdtpMessages.createMDTPMessage
import uk.gov.hmrc.ui.pages.messages.SecureMessagesPage
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.OwsmTests
import uk.gov.hmrc.ui.utils.TestData


class PptMessageTestSpec extends BaseSpec with TestData {

  Feature("Allow users to view their PPT messages in inbox") {

    Scenario("Customer can view their PPT messages in inbox using regime", OwsmTests) {
      Given("a message for PPT with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("pptUpperCase", "valid")
      When("I open my messages for ppt using regime")
      logIntoMessage("ppt", "regime", regimePptValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Direct debit logo test")
    }

    Scenario("Customer can view their PPT messages in inbox using enrolment key & regime", OwsmTests) {
      Given("a message for PPT with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("pptUpperCase", "valid")
      When("I open my messages with HMRC-PPT-ORG enrolment key & PPT regime for ppt")
      logIntoMessage("ppt", "regimeAndEnrolmentKey", regimePptValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Direct debit logo test")
    }

    Scenario("Customer can view their messages count using enrolment key & regime", OwsmTests) {
      Given("a message for PPT with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("pptUpperCase", "valid")
      When("I navigate with HMRC-PPT-ORG enrolment key & PPT regime for PPT messages count")
      logIntoMessage("ppt", "regimeAndEnrolmentKeyMessageCount", regimePptValue)
      Then("I see the message count: 1")
      SecureMessagesPage.pageContains("1")
    }

    Scenario("Customer can view their messages using enrolment key", OwsmTests) {
      Given("a message for PPT with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("pptUpperCase", "valid")
      When("I open my messages with ETMPREGISTRATIONNUMBER enrolment for PPT")
      logIntoMessage("pptTaxIdentifier", "enrolmentKey")
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Direct debit logo test")
    }

    Scenario("HMRC can create message with lowercase using enrolment name", OwsmTests) {
      Given("a message for PPT with lowercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("pptLowerCase", "valid")
      When("I open my messages for ppt using regime")
      logIntoMessage("ppt", "regime", regimePptValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Direct debit logo test")
    }

    Scenario("HMRC can create message with camelcase using enrolment name", OwsmTests) {
      Given("a message for PPT with camelcase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("pptCamelCase", "valid")
      When("I open my messages for ppt using regime")
      logIntoMessage("ppt", "regime", regimePptValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Direct debit logo test")
    }
  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
    deleteMongoRecordsFromCollection("secure message")
  }
}
