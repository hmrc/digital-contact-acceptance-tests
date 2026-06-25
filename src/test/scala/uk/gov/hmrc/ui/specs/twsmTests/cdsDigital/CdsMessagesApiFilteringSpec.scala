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

package uk.gov.hmrc.ui.specs.twsmTests.cdsDigital

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.ElementLocators.*
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.logIntoMessage
import uk.gov.hmrc.ui.pages.messages.CdsMessages.{checkInboxIsEmpty, submitFormWithCustomerName, submitFormWithoutCustomerName}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.TwsmTests
import uk.gov.hmrc.ui.utils.DBTestSupport.deleteDatabase
import uk.gov.hmrc.ui.utils.TestData

class CdsMessagesApiFilteringSpec extends BaseSpec with TestData {

  Feature("Allow messages to be filter by API") {

    Scenario("Conversations can be filter by a tag", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      When("I navigate to conversations list page using eori enrolment with tag filter")
      logIntoMessage("cds", "twsm-tag")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name")
      waitForText(cdsMessageDisplayName, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Conversations can be filter by multiple tag", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When("I navigate to conversations list page using eori enrolment with tag filter")
      logIntoMessage("cds", "twsm-multiple-tag")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name as National Clearance Hub")
      waitForText(cdsMessageDisplayName, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Conversations can be filter by enrolment", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When("I navigate to conversations list page using eori enrolment with enrolment filter")
      logIntoMessage("cds", "twsm-enrolment")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name as National Clearance Hub")
      waitForText(cdsMessageDisplayName, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Conversations can be filter by enrolmentKey", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When("I navigate to conversations list page using multiple eori enrolment with enrolmentKey filter")
      logIntoMessage("cds", "secure-message-stub-multipleEnrolment")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name as Border Force Hub")
      waitForText(cdsMessageDisplayName, "Border Force Hub")
      And("I see the display name as National Clearance Hub")
      waitForText(cdsMessageDisplayName2, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "2")
    }

    Scenario("Conversations can be filter by multiple enrolment", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When("I navigate to conversations list page using multiple eori enrolment with multipleEnrolment filter")
      logIntoMessage("cds", "secure-message-stub-multipleEnrolment")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name as Border Force Hub")
      waitForText(cdsMessageDisplayName, "Border Force Hub")
      And("I see the display name as National Clearance Hub")
      waitForText(cdsMessageDisplayName2, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "2")
    }

    Scenario("Conversations can be filter by enrolment and tag", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When("I navigate to conversations list page using multiple eori enrolment with enrolment and tag filter")
      logIntoMessage("cds", "twsm-enrolment-and-tag")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name as National Clearance Hub")
      waitForText(cdsMessageDisplayName, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Conversations can be filter by enrolmentKey and tag", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When("I navigate to conversations list page using multiple eori enrolment with enrolmentKey and tag filter")
      logIntoMessage("cds", "twsm-enrolmentKey-and-tag")
      Then("I can see Messages between you and HMRC text on the page")
      And("I see the display name as Border Force Hub")
      waitForText(cdsMessageDisplayName, "Border Force Hub")
      And("I see the display name as National Clearance Hub")
      waitForText(cdsMessageDisplayName2, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "2")
    }

    Scenario("Conversations can be filter by enrolmentKey and enrolment", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When("I navigate to conversations list page using multiple eori enrolment with enrolmentKey and enrolment filter")
      logIntoMessage("cds", "secure-message-stub-enrolmentKey-enrolment")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name as Border Force Hub")
      waitForText(cdsMessageDisplayName, "Border Force Hub")
      And("I see the display name as National Clearance Hub")
      waitForText(cdsMessageDisplayName2, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "2")
    }

    Scenario("Conversations can be filter by enrolmentKey, enrolment and tag", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When(
        "I navigate to conversations list page using multiple eori enrolment with enrolmentKey, enrolment and tag filter"
      )
      logIntoMessage("cds", "twsm-enrolmentKey-enrolment-tag")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name as Border Force Hub")
      waitForText(cdsMessageDisplayName, "Border Force Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Conversations can be filter by by wrong enrolment with empty list", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to secure message page and submitted Without Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithoutCustomerName()
      When("I navigate to conversations list page using eori enrollment with vat dec enrolment filter")
      logIntoMessage("cds", "secure-message-stub-vat-dec-enrolment")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see the message inbox with empty")
      checkInboxIsEmpty()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("secure message")
    deleteDatabase("conversation")
  }
}
