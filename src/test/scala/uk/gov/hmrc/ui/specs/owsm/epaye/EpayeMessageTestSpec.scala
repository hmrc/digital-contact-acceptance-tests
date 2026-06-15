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

package uk.gov.hmrc.ui.specs.owsm.epaye

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


class EpayeMessageTestSpec extends BaseSpec with TestData {

  Feature("Allow users to view their EPAYE messages in inbox") {

    Scenario("Customer can view their EPAYE messages in inbox using regime", OwsmTests) {
      Given("a message for EPAYE with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("epayeUpperCase", "valid")
      When("I open my messages for epaye using regime")
      logIntoMessage("epaye", "regime", regimeEpayeValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Direct debit logo test")
    }

    Scenario("Customer can view their EPAYE messages in inbox using enrolment key & regime", OwsmTests) {
      Given("a message for EPAYE with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("epayeUpperCase", "valid")
      When("I open my messages with EMPREF enrolment key & EPAYE regime for EPAYE")
      logIntoMessage("epaye", "regimeAndEnrolmentKey", regimeEpayeValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Direct debit logo test")
    }

    Scenario("Customer can view their messages count using enrolment key & regime", OwsmTests) {
      Given("a message for EPAYE with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("epayeUpperCase", "valid")
      When("I navigate with IR-PAYE enrolment key & EPAYE regime for EPAYE messages count")
      logIntoMessage("epaye", "regimeAndEnrolmentKeyMessageCount", regimeEpayeValue)
      Then("I see the message count: 1")
      SecureMessagesPage.pageContains("1")
    }

    Scenario("Customer can view their messages using enrolment key", OwsmTests) {
      Given("a message for EPAYE with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("epayeUpperCase", "valid")
      When("I open my messages with EMPREF enrolment for EPAYE")
      logIntoMessage("epayeTaxIdentifier", "enrolmentKey")
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Direct debit logo test")
    }

    Scenario("HMRC can create message with lowercase using enrolment name", OwsmTests) {
      Given("a message for EPAYE with lowercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("epayeLowerCase", "valid")
      When("I open my messages for epaye using regime")
      logIntoMessage("epaye", "regime", regimeEpayeValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Direct debit logo test")
    }

    Scenario("HMRC can create message with camelcase using enrolment name", OwsmTests) {
      Given("a message for EPAYE with camelcase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("epayeCamelCase", "valid")
      When("I open my messages for epaye using regime")
      logIntoMessage("epaye", "regime", regimeEpayeValue)
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
