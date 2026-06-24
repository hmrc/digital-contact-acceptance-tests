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
import uk.gov.hmrc.ui.ElementLocators.*
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.logIntoMessage
import uk.gov.hmrc.ui.pages.messages.CdsMessages.{CreateCDSFutureMessageWithTag, CreateCDSMessageWithTag, submitFormWithCustomerName}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.pages.messages.SecureMessagesPage.clickOnUnreadSubjectCds
import uk.gov.hmrc.ui.pages.messages.{CdsMessages, SecureMessagesPage, ViewConversationPage}
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.OwsmTests
import uk.gov.hmrc.ui.utils.DBTestSupport.deleteDatabase
import uk.gov.hmrc.ui.utils.TestData


class CdsViewMessagesTestSpec extends BaseSpec with TestData {

  Feature("Allow customers to view their messages") {

    Scenario("Customer can view list of messages", OwsmTests) {
      Given("A message and conversation created")
      CreateCDSMessageWithTag()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see National Clearance Hub text on the page")
      SecureMessagesPage.pageContains("National Clearance Hub")
      And("I can see Direct debit test text on the page")
      SecureMessagesPage.pageContains("Direct debit test")
      And("I can see 2 count in inbox list")
      waitForText(cdsMessageUnreadCount, "2")
    }

    Scenario("Customer navigate to message page and see message detail", OwsmTests) {
      Given("A message for CDS with tag created")
      CreateCDSMessageWithTag()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      When("I click conversation link")
      clickOnUnreadSubjectCds()
      Then("I can see Direct debit test text on the page")
      ViewConversationPage.pageTitle()
      SecureMessagesPage.pageContains("Direct debit test")
      And("I can see Dear sky trader text on the page")
      SecureMessagesPage.pageContains("Dear sky trader")
    }

    Scenario("Customer can navigate back to messages list and message should be read status from message detail", OwsmTests) {
      Given("A message for CDS with tag created")
      CreateCDSMessageWithTag()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      When("I click conversation link")
      SecureMessagesPage.clickOnUnreadSubjectCds()
      Then("I can see Dear sky trader text on the page")
      ViewConversationPage.pageTitle()
      SecureMessagesPage.pageContains("Dear sky trader")
      When("I click conversation Back link")
      ViewConversationPage.clickOnConversationBackLink()
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see the message with read status")
      CdsMessages.messageReadStatus()
    }

    Scenario("Customer navigate to conversation page and see conversation detail", OwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      When("I click conversation link")
      clickOnUnreadSubjectCds()
      Then("I can see National Clearance Hub text on the page")
      ViewConversationPage.pageTitle()
      SecureMessagesPage.pageContains("National Clearance Hub")
      And("I can see MRN20210219105505513 Case D-89019 text on the page")
      SecureMessagesPage.pageContains("MRN20210219105505513 Case D-89019")
    }

    Scenario("Customer can see unread, read status of messages when message unread and read", OwsmTests) {
      Given("A message for CDS with tag created")
      CreateCDSMessageWithTag()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see the message with unread status")
      CdsMessages.messageUnReadStatus()
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
      When("I click conversation link")
      SecureMessagesPage.clickOnUnreadSubjectCds()
      Then("I can see Dear sky trader text on the page")
      ViewConversationPage.pageTitle()
      SecureMessagesPage.pageContains("Dear sky trader")
      When("I click conversation Back link")
      ViewConversationPage.clickOnConversationBackLink()
      Then("I can see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }

    Scenario("Customer can see unread, read status of messages when conversation unread and read", OwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I can see the message with unread status")
      CdsMessages.messageUnReadStatus()
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
      When("I click conversation link")
      SecureMessagesPage.clickOnUnreadSubjectCds()
      Then("I can see National Clearance Hub text on the page")
      ViewConversationPage.pageTitle()
      SecureMessagesPage.pageContains("National Clearance Hub")
      When("I click conversation Back link")
      ViewConversationPage.clickOnConversationBackLink()
      Then("I can see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }

    Scenario("Customer can see read status of message second time when they navigate to messages list", OwsmTests) {
      Given("A message for CDS with tag created")
      CreateCDSMessageWithTag()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see the message with unread status")
      CdsMessages.messageUnReadStatus()
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
      When("I click conversation link")
      SecureMessagesPage.clickOnUnreadSubjectCds()
      Then("I can see Dear sky trader text on the page")
      ViewConversationPage.pageTitle()
      SecureMessagesPage.pageContains("Dear sky trader")
      When("I click conversation Back link")
      ViewConversationPage.clickOnConversationBackLink()
      Then("I can see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }

    Scenario("Customer can see read status of conversation second time when they navigate to messages list", OwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
      When("I click conversation link")
      SecureMessagesPage.clickOnUnreadSubjectCds()
      Then("I can see National Clearance Hub text on the page")
      ViewConversationPage.pageTitle()
      SecureMessagesPage.pageContains("National Clearance Hub")
      When("I click conversation Back link")
      ViewConversationPage.clickOnConversationBackLink()
      Then("I can see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }

    Scenario("customer shouldn't able to see future messages in inbox", OwsmTests) {
      Given("A message for Future CDS with tag created")
      CreateCDSFutureMessageWithTag()
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see the message inbox with empty")
      CdsMessages.checkInboxIsEmpty()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }

    Scenario("customer shouldn't able to see future messages", OwsmTests) {
      Given("A message for CDS with tag and future with tag created")
      CreateCDSMessageWithTag()
      CreateCDSFutureMessageWithTag()
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Customer can toggle language from English to Welsh and vice versa", OwsmTests) {
      Given("A message for CDS with tag created")
      CreateCDSMessageWithTag()
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I can see Negeseuon rhyngoch chi a CThEM text on the page")
      waitForText(cdsMessagePageHeader, "Negeseuon rhyngoch chi a CThEM")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
    deleteMongoRecordsFromCollection("secure message")
    deleteDatabase("conversation")
  }
}
