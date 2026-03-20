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

package uk.gov.hmrc.ui.specs.PreferencesAdminTests

import uk.gov.hmrc.ui.specs.tags.PreferencesAdminTests
import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.pages.authWizard.AuthWizardPage.{deletePreferencesCollection, verifyEmail}
import uk.gov.hmrc.ui.pages.Paperless.{PaperlessEmailPage, PaperlessIntrruptPage}
import uk.gov.hmrc.ui.pages.authWizard.AuthWizardPage
import uk.gov.hmrc.ui.pages.preferencesAdmin.{PreferencesAdminPage, PreferencesAdminSearchPage, PreferencesAdminSummaryPage, PreferencesAdminUserSummaryPage}
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.utils.TestData

class PreferencesAdminSpec extends BaseSpec with TestData {



  Feature("Admin can opt-out the verified users") {

    Scenario("auth test") {
      Given("Navigate to auth page")
      AuthWizardPage.pageLoad()
    }

    Scenario("Admin can opt-out the user who has already opted-in and verified using nino", PreferencesAdminTests) {
      Given("I am logged into PTA account with nino enrolment and verify the email")
      deletePreferencesCollection()
      AuthWizardPage.pageLoad()
      AuthWizardPage.loginPTAUsingAuthWizard()
      PaperlessIntrruptPage.fillIntrruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("Admin log into the preferences admin")
      PreferencesAdminPage.loadPage()
      PreferencesAdminPage.adminLogin()
      And("When click on the paperless admin link")
      PreferencesAdminPage.clickOnPaperlessAdmin()
      And("I select search by nino option")
      PreferencesAdminSearchPage.selectNinoRadioOption()
      And("I enter the nino value and click search")
      PreferencesAdminSearchPage.fillIdentifierValueUsingNino()
      And("I click on opt out user link and fill the reason")
      PreferencesAdminSummaryPage.clickOnOptUserOutLink()
      PreferencesAdminSummaryPage.fillReasonToOptOut()
      Then("I should see the user summary page title and success message")
      PreferencesAdminUserSummaryPage.pageTitle()
      PreferencesAdminUserSummaryPage.userOptOutSuccessfullyMessage()
    }

    Scenario("Admin can opt-out the user who has already opted-in and verified using email", PreferencesAdminTests) {
      Given("I am logged into PTA account with nino enrolment and verify the email")
      deletePreferencesCollection()
      AuthWizardPage.pageLoad()
      AuthWizardPage.loginPTAUsingAuthWizard()
      PaperlessIntrruptPage.fillIntrruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      verifyEmail()
      When("Admin log into the preferences admin")
      PreferencesAdminPage.loadPage()
      PreferencesAdminPage.adminLogin()
      And("When click on the paperless admin link")
      PreferencesAdminPage.clickOnPaperlessAdmin()
      And("I select search by email option")
      PreferencesAdminSearchPage.selectEmailRadioOption()
      And("I enter the email value and click search")
      PreferencesAdminSearchPage.fillIdentifierValueUsingEmail()
      And("I click on opt out user link and fill the reason")
      PreferencesAdminSummaryPage.clickOnOptUserOutLink()
      PreferencesAdminSummaryPage.fillReasonToOptOut()
      Then("I should see the user summary page title and success message")
      PreferencesAdminUserSummaryPage.pageTitle()
      PreferencesAdminUserSummaryPage.userOptOutSuccessfullyMessage()
    }
  }
}
