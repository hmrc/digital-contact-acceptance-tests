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
import uk.gov.hmrc.ui.pages.preferencesAdmin.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.PreferencesAdminTests
import uk.gov.hmrc.ui.utils.TestData

class PaperlessMessageBrakeAllowlistSpec extends BaseSpec with TestData {

  Feature("Message Brake allowlist test via Preferences Admin Frontend") {

    Scenario("Preferences Admin can add formId in the message brake allowlist", PreferencesAdminTests) {
      Given("I log into the preferences admin as Admin")
      PreferencesAdminPage.loadPage()
      PreferencesAdminPage.adminLogin()
      When("When click on the message brake allowlist link")
      PreferencesAdminPage.clickOnMessageBrakeAllowlist()
      And("I click on Add new formId button")
      PreferencesMessageBrakeAllowlistPage.clickOnAddNewFormButton()
      And("I enter the formId, reason and click on confirm")
      PreferencesMessageBrakeAllowlistPage.addNewFormId("SA316")
      Then("I should see that new formId added in the allowlist")
      PreferencesMessageBrakeAllowlistPage.formIdAdded()
    }

    Scenario("Preferences Admin can delete formId from message brake allowlist", PreferencesAdminTests) {
      Given("I log into the preferences admin as Admin")
      PreferencesAdminPage.loadPage()
      PreferencesAdminPage.adminLogin()
      And("When click on the message brake allowlist link")
      PreferencesAdminPage.clickOnMessageBrakeAllowlist()
      And("I added a formId in the message brake allowlist")
      PreferencesMessageBrakeAllowlistPage.clickOnAddNewFormButton()
      PreferencesMessageBrakeAllowlistPage.addNewFormId("SA316")
      When("I click on Delete Form button and fill the reason")
      PreferencesMessageBrakeAllowlistPage.clickOnDeleteFormId()
      PreferencesMessageBrakeAllowlistPage.fillReasonToDeleteFormId()
      Then("I should see that deleted formId doesn't exist in allowlist")
    }
  }
}
