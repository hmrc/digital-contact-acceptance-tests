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

package uk.gov.hmrc.ui.specs.eps

import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.messages.CdsMessages.{deleteMongoRecordsFromCollection, matchEpsStatus, p2EmailAlerts, waitForSeconds}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{pta, verifyEmail}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.EpsPaperlessTests
import uk.gov.hmrc.ui.utils.DBTestSupport.deleteDatabase
import uk.gov.hmrc.ui.utils.GeneratedTestData
import uk.gov.hmrc.ui.utils.GeneratedTestData.{epsNinoNumber, epsNinoNumber1}

class p2WorkItemProcessingTestSpec extends BaseSpec {
  
  Feature("Eps pulls outstanding work from eps hods and processes it") {

    Scenario("Sets the alert to succeeded when an email is successfully sent", EpsPaperlessTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, epsNinoNumber)
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I verify the email address")
      verifyEmail(epsNinoNumber)
      And("the print suppressions alerts have been cleared")
      deleteDatabase("printSuppressionAlerts")
      And("work is added to the queue for nino YY000200A")
      p2EmailAlerts(epsNinoNumber)
      When("I wait 15 seconds for eps - message - renderer scheduler to pull and process the outstanding work")
      waitForSeconds(15)
      Then("the status for YY000200A should be succeeded")
      matchEpsStatus(epsNinoNumber, "succeeded")
    }

    Scenario("Sets the alert to permanently failed when an email is not successfully sent", EpsPaperlessTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, epsNinoNumber1)
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("the print suppressions alerts have been cleared")
      deleteDatabase("printSuppressionAlerts")
      And("work is added to the queue for nino")
      p2EmailAlerts(epsNinoNumber1)
      When("I wait 15 seconds for eps - message - renderer scheduler to pull and process the outstanding work")
      waitForSeconds(15)
      Then("the status for the nino should be permanently failed")
      matchEpsStatus(epsNinoNumber1, "permanently failed")
    }
  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")

  }
}
