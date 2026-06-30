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
import uk.gov.hmrc.ui.pages.messages.CdsMessages.submitFormWithCustomerName
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.pages.messages.ReplyToConversationPage.{clickOnSend, enterMessage}
import uk.gov.hmrc.ui.pages.messages.SecureMessagesPage.{backLinkCdsQuery, clickOnUnreadSubjectCds}
import uk.gov.hmrc.ui.pages.messages.ViewConversationPage.clickOnReplyToThisMessageLink
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.TwsmTests
import uk.gov.hmrc.ui.utils.DBTestSupport.deleteDatabase
import uk.gov.hmrc.ui.utils.TestData

class CdsCaseworkerSpec extends BaseSpec with TestData {

  Feature("Allow caseworker to raise a CDS query") {

    Scenario("Caseworker can raise a cds query using secure message stub", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      Then("I can see Query creation complete")
      waitForText(cdsQueryCreatedPageHeader, "Query creation complete")
    }

    Scenario("Caseworker can navigate to back from query created page", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      Then("I can see Query creation complete")
      waitForText(cdsQueryCreatedPageHeader, "Query creation complete")
      When("I click back link on the page")
      backLinkCdsQuery()
      Then("I should be on the secure message stub page")
      waitGetUrlResult("http://localhost:9202/secure-message-stub")
    }

    Scenario("Caseworker cannot create a duplicate query using the same Sender name and Conversation Id", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      Then("I can see Query creation complete")
      waitForText(cdsQueryCreatedPageHeader, "Query creation complete")
      When("I click back link on the page")
      backLinkCdsQuery()
      And("I resubmit the same query with same Name and Id")
      submitFormWithCustomerName()
      Then("I can see Query creation unsuccessfull text on the page")
      waitForText(cdsQueryCreatedPageHeader, "Query creation unsuccessfull")
    }

    Scenario("Messages can be filter by enrolmentKey", TwsmTests) {
      Given("I navigate to secure message page and submitted With Customer Name")
      logIntoMessage("cds", "secure-message-conversation")
      submitFormWithCustomerName()
      When("I navigate to messages list page using eori enrollment")
      logIntoMessage("cds", "secure-message-stub")
      And("I click on the subject link")
      clickOnUnreadSubjectCds()
      And("I click on Reply to this message link")
      clickOnReplyToThisMessageLink()
      And("I entered valid message and click on send button")
      enterMessage("valid")
      clickOnSend()
      Then("I reply as a caseworker and see status 201")
      caseWorkerReply()
      And("I should see the text message sent")
      waitForText(cdsMessageDetailHeader, "Message sent")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("secure message")
    deleteDatabase("conversation")
  }
}
