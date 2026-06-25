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
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.logIntoMessage
import uk.gov.hmrc.ui.pages.messages.CdsMessages
import uk.gov.hmrc.ui.pages.messages.CdsMessages.submitFormWithCustomerName
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.pages.messages.ReplyToConversationPage.{clickOnBackToMessagesButton, clickOnSend, enterMessage}
import uk.gov.hmrc.ui.pages.messages.SecureMessagesPage.{backLink, clickOnReadSubjectCds, clickOnUnreadSubjectCds}
import uk.gov.hmrc.ui.pages.messages.ViewConversationPage.clickOnReplyToThisMessageLink
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.TwsmTests
import uk.gov.hmrc.ui.utils.DBTestSupport.deleteDatabase
import uk.gov.hmrc.ui.utils.TestData

class CdsCustomerMessagesSpec extends BaseSpec with TestData {

  Feature("Allow customers to view their messages") {

    Scenario("Customer can view list of messages", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the display name")
      waitForText(cdsMessageDisplayName, "National Clearance Hub")
      And("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
    }

    Scenario("Customer navigate to a conversation page and see conversation detail") {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      When("I click on the subject link")
      clickOnUnreadSubjectCds()
      Then("I see below subject as")
      waitForText(cdsMessageDetailHeader, "MRN20210219105505513 Case D-89019")
    }

    Scenario("Second time customer navigate to a conversation page and see conversation detail", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
      When("I click on the subject link")
      clickOnUnreadSubjectCds()
      Then("I see below subject as")
      waitForText(cdsMessageDetailHeader, "MRN20210219105505513 Case D-89019")
      When("I click conversation back link")
      backLink()
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
      When("I click on the subject link")
      clickOnReadSubjectCds()
      Then("I see below subject as")
      waitForText(cdsMessageDetailHeader, "MRN20210219105505513 Case D-89019")
      And("I see the first read text on the page")
      waitForText(firstReadMessageText, "First read")
    }

    Scenario("Customer navigate back to messages page from conversation detail", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      When("I click on the subject link")
      clickOnUnreadSubjectCds()
      Then("I see below subject as")
      waitForText(cdsMessageDetailHeader, "MRN20210219105505513 Case D-89019")
      When("I click conversation back link")
      backLink()
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }

    Scenario("Customer can reply to a conversation", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      When("I click on the subject link")
      clickOnUnreadSubjectCds()
      Then("I see below subject as")
      waitForText(cdsMessageDetailHeader, "MRN20210219105505513 Case D-89019")
      When("I click on Reply to this message link")
      clickOnReplyToThisMessageLink()
      And("I entered valid message and click on send button")
      enterMessage("valid")
      clickOnSend()
      Then("I should see the text Message sent on page")
      waitForText(cdsMessageDetailHeader, "Message sent")
    }

    Scenario("Customer can navigate back to list of messages from reply success page", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see 1 count in inbox list")
      waitForText(cdsMessageUnreadCount, "1")
      When("I click on the subject link")
      clickOnUnreadSubjectCds()
      Then("I see below subject as")
      waitForText(cdsMessageDetailHeader, "MRN20210219105505513 Case D-89019")
      When("I click on Reply to this message link")
      clickOnReplyToThisMessageLink()
      And("I entered valid message and click on send button")
      enterMessage("valid")
      clickOnSend()
      Then("I should see the text Message sent on page")
      waitForText(cdsMessageDetailHeader, "Message sent")
      When("I click back on Back to your messages button")
      clickOnBackToMessagesButton()
      Then("I see the message with read status")
      CdsMessages.messageReadStatus()
      And("I can see message thread count")
      waitForText(cdsMessageThreadCount, "2")
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }

    Scenario("Customer can view the replied message from caseworker and count", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I click on the subject link")
      clickOnUnreadSubjectCds()
      And("I click on Reply to this message link")
      clickOnReplyToThisMessageLink()
      And("I entered valid message and click on send button")
      enterMessage("valid")
      clickOnSend()
      And("caseworker reply to the message")
      caseWorkerReply()
      When("I click back on Back to your messages button")
      clickOnBackToMessagesButton()
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I see the message with unread status")
      CdsMessages.messageUnReadStatus()
      And("I can see message thread count")
      waitForText(cdsMessageThreadCount, "3")
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "1")
    }

    Scenario("Customer can view & reply once again after caseworker replied and count", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      And("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I click on the subject link")
      clickOnUnreadSubjectCds()
      And("I click on Reply to this message link")
      clickOnReplyToThisMessageLink()
      And("I entered valid message and click on send button")
      enterMessage("valid")
      clickOnSend()
      And("caseworker reply to the message")
      caseWorkerReply()
      And("I click back on Back to your messages button")
      clickOnBackToMessagesButton()
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      And("I click on the subject link")
      clickOnUnreadSubjectCds()
      Then("I can see caseworker message Thanks for your response")
      waitForText(caseWorkerRepliedMessageText, "Thanks for your response")
      When("I click on Reply to this message link")
      clickOnReplyToThisMessageLink()
      And("I entered valid message and click on send button")
      enterMessage("valid")
      clickOnSend()
      Then("I should see the text Message sent on page")
      waitForText(cdsMessageDetailHeader, "Message sent")
      When("I click back on Back to your messages button")
      clickOnBackToMessagesButton()
      Then("I can see message thread count")
      waitForText(cdsMessageThreadCount, "4")
      And("I can see 0 count in inbox list")
      waitForText(cdsMessageReadCount, "0")
    }

    Scenario("Customer can toggle language from English to Welsh and vice versa", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      When("I toggle the language to Welsh")
      selectLanguageWelsh()
      Then("I can see Negeseuon rhyngoch chi a CThEM on the page")
      waitForText(cdsMessagePageHeader, "Negeseuon rhyngoch chi a CThEM")
      When("I toggle the language to English")
      selectLanguageEnglish()
      Then("I can see Messages between you and HMRC text on the page")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("secure message")
    deleteDatabase("conversation")
  }
}
