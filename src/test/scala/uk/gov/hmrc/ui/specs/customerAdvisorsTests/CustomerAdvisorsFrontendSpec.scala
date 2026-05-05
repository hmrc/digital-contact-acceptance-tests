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

package uk.gov.hmrc.ui.specs.customerAdvisorsTests

import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.{lonIntoCustomerAdvisorMessageInboxPage, lonIntoCustomerAdvisorMessageSautrPage}
import uk.gov.hmrc.ui.pages.customerAdvisorsFrontend.customerAdvisorsMessagePage
import uk.gov.hmrc.ui.pages.customerAdvisorsFrontend.customerAdvisorsMessagePage.responseMessage
import uk.gov.hmrc.ui.pages.messages.MdtpMessages.*
import uk.gov.hmrc.ui.pages.paperless.{PaperlessBTAHomePage, PaperlessEmailPage, PaperlessInterruptPage}
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.OwsmTests

class CustomerAdvisorsFrontendSpec extends BaseSpec {

  Feature("Customer advisors to send messages to relevant customer") {

    Scenario("Customer advisors can submit a valid message to a particular customer", OwsmTests) {
      Given("I navigate to customer advisors page")
      lonIntoCustomerAdvisorMessageInboxPage()
      When("I fill the form for FHDDS message and click send")
      createMDTPMessage("FHDDS", "valid")
      Then("I should see that message is created")
      waitUntilHeader2("Success")
    }

    Scenario("Customer advisors can't submit a invalid message to a particular customer", OwsmTests) {
      Given("I navigate to customer advisors page")
      lonIntoCustomerAdvisorMessageInboxPage()
      When("I fill the form for FHDDS message and click send")
      createMDTPMessage("FHDDS", "invalid")
      Then("I should see that message is created")
      waitUntilHeader2("Failed")
    }

    Scenario("Customer advisors can't submit a valid duplicate message to a particular customer", OwsmTests) {
      Given("I navigate to customer advisors page")
      lonIntoCustomerAdvisorMessageInboxPage()
      When("I fill the form for FHDDS message and click send")
      createMDTPMessage("FHDDS", "valid")
      And("I resubmit the same form once again with same data")
      lonIntoCustomerAdvisorMessageInboxPage()
      createMDTPMessage("FHDDS", "valid")
      Then("I should see that message is created")
      responseMessage("Duplicate")
    }

    Scenario("Customer advisors can submit a valid message to a sautr customer", OwsmTests) {
      Given("I am logged into BTA account with nino & sautr enrolment and verify the emai")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      PaperlessBTAHomePage.btaPageTitle()
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("I navigate to customer advisors page")
      lonIntoCustomerAdvisorMessageSautrPage("correct")
      And("I fill the form to reply and click send")
      customerAdvisorsMessagePage.fillFormToReply()
      Then("I should see that message is submitted successfully")
      responseMessage(
        "Thanks. Your reply has been successfully received by the customer's Tax Account secure message Inbox."
      )
    }

    Scenario("Customer advisors can't submit a valid message to a wrong sautr", OwsmTests) {
      Given("I am logged into BTA account with nino & sautr enrolment and verify the emai")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      PaperlessBTAHomePage.btaPageTitle()
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("I navigate to customer advisors page")
      lonIntoCustomerAdvisorMessageSautrPage("wrong")
      And("I fill the form to reply and click send")
      customerAdvisorsMessagePage.fillFormToReply()
      Then("I should see the message after submission")
      responseMessage("The SA-UTR provided is not recognised by the Digital Tax Platform.")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("secure message")
    deleteMongoRecordsFromCollection("preferences")
  }
}
