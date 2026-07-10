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

package uk.gov.hmrc.ui.specs.preferencesAdminTests

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.messages.GmcMessages.{deleteMongoRecordsFromCollection, pta, verifyEmail}
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.pages.preferencesAdmin.*
import uk.gov.hmrc.ui.pages.preferencesAdmin.PreferencesAdminBulkOptOutPage.bulkOptOutFile
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PreferencesAdminTests
import uk.gov.hmrc.ui.utils.GeneratedTestData.ninosForBulkOptOut

class PaperlessBulkOptOutSpec extends BaseSpec {

  Feature("Paperless Admin Bulk Optout functionality") {

    Scenario("Admin can opt-out more than one opted in customers using the Bulk Optout file upload functionality", PreferencesAdminTests) {
      Given("Opt-in 5 NINOs for the paperless admin bulk optout testing")
       ninosForBulkOptOut.foreach { nino =>
          LoginUsingAuthWizardPage.pageLoad()
          LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, nino)
          PaperlessInterruptPage.fillInterruptPageForOptin()
          PaperlessEmailPage.fillEmailPage()
          verifyEmail(nino)
       }
      When("Admin logs into the preferences admin")
      PreferencesAdminPage.loadPage()
      PreferencesAdminPage.adminLogin()
      And("Admin clicks on the Bulk Opt Out link")
      PreferencesAdminPage.clickOnBulkOptOut()
      And("the Bulk Opt Out page is displayed")
      PreferencesAdminBulkOptOutPage.pageTitle()
      And("Admin chooses the CSV file with the nino details and clicks on the Upload and Process button")
      PreferencesAdminBulkOptOutPage.csvFileUploadAndProcess(bulkOptOutFile)
      And("the File Upload Confirmation page is displayed")
      PreferencesAdminFileUploadConfirmationPage.pageTitle()
      Then("The successfully opted record count and nino details are displayed")
      PreferencesAdminFileUploadConfirmationPage.fileUploadSuccessMessage()
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
  }
}
