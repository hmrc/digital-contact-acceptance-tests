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

package uk.gov.hmrc.ui.specs.owsm.ops

import uk.gov.hmrc.ui.ElementLocators.demoFrontEndInboxFirstMessageSubject
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.{logIntoCustomerAdvisorMessageInboxPage, logIntoMessage}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{deleteMongoRecordsFromCollection, regimeFhddsValue, regimeSdilValue, waitForText}
import uk.gov.hmrc.ui.pages.messages.MdtpMessages.createMDTPMessage
import uk.gov.hmrc.ui.pages.messages.MdtpMessages
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.OwsmTests

class OpsMessagesSpec extends BaseSpec {

  Feature("Allow customers to view Ops messages") {

    Scenario("Customer can view their SDIL messages in inbox", OwsmTests) {
      Given("a message for SDIL is created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("sdil", "valid")
      When("I open my messages for SDIL using regime for sdil")
      logIntoMessage("sdil", "regime",regimeSdilValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Direct debit logo test")
    }

    Scenario("Customer can view their FHDDS messages in inbox", OwsmTests) {
      Given("a message for FHDDS is created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("fhdds", "valid")
      When("I open my messages for FHDDS using regime for fhdds")
      logIntoMessage("fhdds", "regime", regimeFhddsValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Direct debit logo test")
    }

    Scenario("Customer can view their SDIL messages in inbox using enrolment key", OwsmTests) {
      Given("a message for SDIL is created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("sdil", "valid")
      When("I open my messages with HMRC-OBTDS-ORG enrolment for SDIL")
      logIntoMessage("sdil", "enrolmentKey")
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Direct debit logo test")
    }

    Scenario("Customer can view their FHDDS messages in inbox using enrolment key", OwsmTests) {
      Given("a message for FHDDS is created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("fhdds", "valid")
      When("I open my messages with HMRC-OBTDS-ORG enrolment for FHDDS")
      logIntoMessage("fhdds", "enrolmentKey")
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Direct debit logo test")
    }

    Scenario("Customer can view their SDIL messages in inbox using enrolment key & regime", OwsmTests) {
      Given("a message for SDIL is created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("sdil", "valid")
      When("I open my messages with HMRC-OBTDS-ORG enrolment key & SDIL regime for SDIL")
      logIntoMessage("sdil", "regimeAndEnrolmentKey", regimeSdilValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Direct debit logo test")
    }

    Scenario("Customer can view their FHDDS messages in inbox using enrolment key & regime", OwsmTests) {
      Given("a message for FHDDS is created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("fhdds", "valid")
      When("I open my messages with HMRC-OBTDS-ORG enrolment key & FHDDS regime for FHDDS")
      logIntoMessage("fhdds", "regimeAndEnrolmentKey", regimeFhddsValue)
      Then("I see the message: Direct debit logo test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Direct debit logo test")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("secure message")
  }
}