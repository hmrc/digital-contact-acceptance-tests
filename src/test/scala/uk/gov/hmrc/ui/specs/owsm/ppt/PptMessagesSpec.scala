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

import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.{logIntoCustomerAdvisorMessageInboxPage, logIntoMessage}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{regimeFhddsValue, regimePptValue, regimeSdilValue}
import uk.gov.hmrc.ui.pages.messages.MdtpMessages.createMDTPMessage
import uk.gov.hmrc.ui.pages.messages.{MdtpMessages, SecureMessagesPage}
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.OwsmTests

class PptMessagesSpec extends BaseSpec {

  Feature("Allow customers to view Ppt messages") {

    Scenario("Customer can view their PPT messages in inbox using regime", OwsmTests) {
      Given("a message for PPT with uppercase enrolment name created")
      logIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("ppt", "valid")
      When("I open my messages for ppt using regime for PPT")
      logIntoMessage("ppt", "regime", regimePptValue)
      Then("I see the message: Direct debit test")
      SecureMessagesPage.pageContains("Direct debit logo test")
    }

    Scenario("Customer can view their PPT messages in inbox using enrolment key & regime") {
      Given("a message for PPT with uppercase enrolment name created")
      When("I open my messages with HMRC-PPT-ORG enrolment key & PPT regime for PPT")
      Then("I see the message: Direct debit test")
    }

    Scenario("Customer can view their messages count using enrolment key & regime") {

      Given("a message for PPT with uppercase enrolment name created")

      When("I navigate with HMRC-PPT-ORG enrolment key & PPT regime for PPT messages count")

      Then("I see the messages count: 1")
    }

    Scenario("Customer can view their messages using enrolment key") {

      Given("a message for PPT with uppercase enrolment name created")

      When("I open my messages with ETMPREGISTRATIONNUMBER enrolment for PPT")

      Then("I see the message: Direct debit test")
    }

    Scenario("HMRC can create message with lowercase using enrolment name") {

      Given("a message for PPT with lowercase enrolment name created")

      When("I open my messages for ppt using regime for PPT")

      Then("I see the message: Direct debit test")
    }

    Scenario("HMRC can create message with camelcase using enrolment name") {

      Given("a message for PPT with camelcase enrolment name created")

      When("I open my messages for ppt using regime for PPT")

      Then("I see the message: Direct debit test")
    }
  }
}
