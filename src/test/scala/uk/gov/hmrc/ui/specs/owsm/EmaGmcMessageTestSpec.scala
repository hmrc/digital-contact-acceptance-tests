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

package uk.gov.hmrc.ui.specs.owsm

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.logIntoMessageUsingRegime
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.pages.preferencesAdmin.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.{PreferencesAdminTests, Wip}
import uk.gov.hmrc.ui.utils.TestData

class EmaGmcMessageTestSpec extends BaseSpec with TestData {

  Feature("Generate email alerts for GMC message") {

    Scenario("The user should see email alerts for GMC messages", Wip) {
      Given("I logged into BTA account with nino with sautr1 enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using sautr1")
      createV4Message()
      logIntoMessageUsingRegime("sautr")
      println(Driver.instance.getPageSource)
      println(Driver.instance.getCurrentUrl)
    }

  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
    deleteMongoRecordsFromCollection("secure message")
  }
}
