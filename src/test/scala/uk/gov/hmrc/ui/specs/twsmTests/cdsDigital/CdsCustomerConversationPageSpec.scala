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

import uk.gov.hmrc.ui.ElementLocators.{cdsMessageDetailHeader, errorMessage, errorSummary}
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.logIntoMessage
import uk.gov.hmrc.ui.pages.messages.CdsMessages.{deleteMongoRecordsFromCollection, submitFormWithCustomerName}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.waitForText
import uk.gov.hmrc.ui.pages.messages.ReplyToConversationPage.{clickOnSend, enterMessage}
import uk.gov.hmrc.ui.pages.messages.SecureMessagesPage.clickOnUnreadSubjectCds
import uk.gov.hmrc.ui.pages.messages.ViewConversationPage.clickOnReplyToThisMessageLink
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.TwsmTests
import uk.gov.hmrc.ui.utils.DBTestSupport.deleteDatabase
import uk.gov.hmrc.ui.utils.TestData

class CdsCustomerConversationPageSpec extends BaseSpec with TestData {

  Feature("Error Validations on Conversation Page") {

    Scenario("Customer see an error validation when an empty message field is entered", TwsmTests) {
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
      And("I click on the send button")
      clickOnSend()
      Then("I should error summary on the page")
      waitForText(errorSummary, "There is a problem")
      And("I should error message on the page")
      waitForText(errorMessage, "You must write a message to reply")
    }

    Scenario("Customer see a character limit validation on reply message field", TwsmTests) {
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
      And("I entered more than limit words and click on send button")
      enterMessage("error")
      clickOnSend()
      Then("I should error summary on the page")
      waitForText(errorSummary, "There is a problem")
      And("I should error message on the page")
      waitForText(errorMessage, "The message must be 4,000 characters or fewer")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("secure message")
    deleteDatabase("conversation")
  }
}
