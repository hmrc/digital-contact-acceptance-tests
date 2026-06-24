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

package uk.gov.hmrc.ui.specs.owsm.cdsFinancials

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.ElementLocators.{cdsMessagePageFirstMessageSubject, cdsMessagePageHeader, cdsMessagePageSecondMessageSubject, cdsMessageReadCount, cdsMessageUnreadCount}
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.logIntoMessage
import uk.gov.hmrc.ui.pages.messages.CdsMessages.{CreateCDSMessageWithMultipleTag, CreateCDSMessageWithTag, checkInboxIsEmpty}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.OwsmTests
import uk.gov.hmrc.ui.utils.DBTestSupport.deleteDatabase
import uk.gov.hmrc.ui.utils.TestData


class CdsMessageApiFilteringTestSpec extends BaseSpec with TestData {

  Feature("Allow messages to be filter by API") {

    Scenario("Messages can be filter by a tag", OwsmTests) {
      Given("Given a message for CDS with tag created")
      CreateCDSMessageWithTag()
      When("I navigate to messages list page using eori enrollment with tag filter")
      logIntoMessage("cds", "secure-message-stub-tag")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit test")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Messages can be filter by multiple tag", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using multiple-eori enrollment with tags filter")
      logIntoMessage("cds", "secure-message-stub-tags")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit test")
      And("I can see Direct debit logo test text on the page")
      waitForText(cdsMessagePageSecondMessageSubject, "Direct debit logo test")
      And("I can see 2 count in inbox list")
      waitForText(cdsMessageUnreadCount, "2")
    }

    Scenario("Messages can be filter by enrolment", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using eori enrollment with enrolment filter")
      logIntoMessage("cds", "secure-message-stub-enrolment")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit logo test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit logo test")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Messages can be filter by enrolmentKey", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using eori enrollment with enrolmentKey filter")
      logIntoMessage("cds", "secure-message-stub-enrolmentKey")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit logo test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit logo test")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Messages can be filter by multiple enrolment", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using multiple-eori enrollment with multipleEnrolment filter")
      logIntoMessage("cds", "secure-message-stub-multipleEnrolment")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit test")
      And("I can see Direct debit logo test text on the page")
      waitForText(cdsMessagePageSecondMessageSubject, "Direct debit logo test")
      And("I can see 2 count in inbox list")
      waitForText(cdsMessageUnreadCount, "2")
    }

    Scenario("Messages can be filter by enrolment and tag", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using multiple-eori enrollment with enrolment and tag filter")
      logIntoMessage("cds", "secure-message-stub-enrolment-tag")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit logo test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit logo test")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Messages can be filter by enrolmentKey and tag", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using multiple-eori enrollment with enrolmentKey and tag filter")
      logIntoMessage("cds", "secure-message-stub-enrolmentKey-tag")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit test")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }
    
    Scenario("Messages can be filter by enrolmentKey and enrolment", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using multiple-eori enrollment with enrolmentKey and enrolment filter")
      logIntoMessage("cds", "secure-message-stub-enrolmentKey-enrolment")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit test")
      And("I can see Direct debit logo test text on the page")
      waitForText(cdsMessagePageSecondMessageSubject, "Direct debit logo test")
      And("I can see 2 count in inbox list")
      waitForText(cdsMessageUnreadCount, "2")
    }
    
    Scenario("Messages can be filter by enrolmentKey, enrolment and tag", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using multiple-eori enrollment with enrolmentKey, enrolment and tag filter")
      logIntoMessage("cds", "secure-message-stub-enrolmentKey-enrolment-tag")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see Direct debit logo test text on the page")
      waitForText(cdsMessagePageFirstMessageSubject, "Direct debit logo test")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Messages can be filter by wrong enrolment with empty list", OwsmTests) {
      Given("Given a message for Multiple CDS with tag created")
      CreateCDSMessageWithMultipleTag()
      When("I navigate to messages list page using eori enrollment with vat dec enrolment filter")
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
    deleteMongoRecordsFromCollection("preferences")
    deleteMongoRecordsFromCollection("secure message")
    deleteDatabase("conversation")
  }
}
