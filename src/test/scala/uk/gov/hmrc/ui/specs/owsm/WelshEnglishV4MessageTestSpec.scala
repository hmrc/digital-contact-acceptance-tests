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
import uk.gov.hmrc.ui.ElementLocators.{cdsMessagePageFirstMessageSubject, cdsMessagePageHeader, demoFrontEndInboxFirstMessageSubject, demoFrontEndInboxFirstMessageSubject2}
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.{logIntoCDSMessagePage, logIntoMessageUsingRegime}
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.pages.messages.SecureMessagesPage
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.{OwsmTests, Wip}
import uk.gov.hmrc.ui.utils.{GeneratedTestData, TestData}


class WelshEnglishV4MessageTestSpec extends BaseSpec with TestData {

  Feature("Language toggler for V4 messages") {

    Scenario("Customer can view the SDDS v4 messages in BTA inbox", OwsmTests) {
      Given("A v4 message is created for sdil")
      createV4Message("sdil")
      When("I open my messages for sdil using regime")
      logIntoMessageUsingRegime("sdil", regimeSdilValue)
      Then("I see the message: Soft Drinks Industry Levy Direct Debit cancelled")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Soft Drinks Industry Levy Direct Debit cancelled")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Debyd Uniongyrchol Ardoll y Diwydiant Diodydd Meddal wedii ganslol")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Debyd Uniongyrchol Ardoll y Diwydiant Diodydd Meddal wedii ganslol")
      And("I click the message: Debyd Uniongyrchol Ardoll y Diwydiant Diodydd Meddal wedii ganslol")
      SecureMessagesPage.clickOnSubject()
      And("When I see the message: Debyd Uniongyrchol Ardoll y Diwydiant Diodydd Meddal wedii ganslol")
      SecureMessagesPage.pageContains("Debyd Uniongyrchol Ardoll y Diwydiant Diodydd Meddal wedii ganslol")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: Soft Drinks Industry Levy Direct Debit cancelled")
      SecureMessagesPage.pageContains("Soft Drinks Industry Levy Direct Debit cancelled")
    }

    Scenario("Customer can view the FHDDS v4 messages in BTA inbox", OwsmTests) {
      Given("A v4 message is created for fhdds")
      createV4Message("fhdds")
      When("I open my messages for fhdds using regime")
      logIntoMessageUsingRegime("fhdds", regimeFhddsValue)
      Then("I see the message: FHDDS messages for test")
      waitForText(demoFrontEndInboxFirstMessageSubject, "FHDDS messages for test")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Negeseuon FHDDS ar gyfer prawf")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Negeseuon FHDDS ar gyfer prawf")
      And("I click the message: Negeseuon FHDDS ar gyfer prawf")
      SecureMessagesPage.clickOnSubject()
      And("When I see the message: Negeseuon FHDDS ar gyfer prawf")
      SecureMessagesPage.pageContains("Negeseuon FHDDS ar gyfer prawf")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: FHDDS messages for test")
      SecureMessagesPage.pageContains("FHDDS messages for test")
    }

    Scenario("Customer can view the PPT v4 messages in BTA inbox", OwsmTests) {
      Given("A v4 message is created for ppt")
      createV4Message("ppt")
      When("I open my messages for ppt using regime")
      logIntoMessageUsingRegime("ppt", regimePptValue)
      Then("I see the message: PPT messages for test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "PPT messages for test")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Negeseuon PPT ar gyfer prawf")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Negeseuon PPT ar gyfer prawf")
      And("I click the message: Negeseuon PPT ar gyfer prawf")
      SecureMessagesPage.clickOnSubjectEpaye()
      And("When I see the message: Negeseuon PPT ar gyfer prawf")
      SecureMessagesPage.pageContains("Negeseuon PPT ar gyfer prawf")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: PPT messages for test")
      SecureMessagesPage.pageContains("PPT messages for test")
    }

    Scenario("Customer can view the EPAYE v4 messages in BTA inbox", OwsmTests) {
      Given("A v4 message is created for epaye")
      createV4Message("epaye")
      When("I open my messages for epaye using regime")
      logIntoMessageUsingRegime("epaye", regimeEpayeValue)
      Then("I see the message: EPAYE messages for test")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "EPAYE messages for test")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Negeseuon EPAYE ar gyfer prawf")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Negeseuon EPAYE ar gyfer prawf")
      And("I click the message: Negeseuon EPAYE ar gyfer prawf")
      SecureMessagesPage.clickOnSubjectEpaye()
      And("When I see the message: Negeseuon EPAYE ar gyfer prawf")
      SecureMessagesPage.pageContains("Negeseuon EPAYE ar gyfer prawf")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: EPAYE messages for test")
      SecureMessagesPage.pageContains("EPAYE messages for test")
    }

    Scenario("Customer can view the CDS v4 messages in BTA inbox", OwsmTests) {
      Given("A v4 message is created for cds")
      createV4Message("cds")
      When("I navigate to messages list page using eori enrollment")
      logIntoCDSMessagePage()
      Then("I see the text: Messages between you and HMRC")
      waitForText(cdsMessagePageHeader, "Messages between you and HMRC")
      Then("I see the Message: CDS messages for test")
      waitForText(cdsMessagePageFirstMessageSubject, "CDS messages for test")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Negeseuon CDS ar gyfer prawf")
      waitForText(cdsMessagePageFirstMessageSubject, "Negeseuon CDS ar gyfer prawf")
      And("I click the message: Negeseuon CDS ar gyfer prawf")
      SecureMessagesPage.clickOnSubjectCds()
      And("When I see the message: Negeseuon CDS ar gyfer prawf")
      SecureMessagesPage.pageContains("Negeseuon CDS ar gyfer prawf")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: CDS messages for test")
      SecureMessagesPage.pageContains("CDS messages for test")
    }

    Scenario("Customer can view the Optin v4 messages in BTA inbox", Wip) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta, GeneratedTestData.ninoNumber1)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail(GeneratedTestData.ninoNumber1)
      When("A v4 message is created for optin")
      createV4Message("optin")
      And("I open my messages for PTA using regime")
      logIntoMessageUsingRegime(enrolmentType="NoSautr", regime=regimeValue, nino = GeneratedTestData.ninoNumber1 )
      And("I see the message: Your online tax letters")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Your online tax letters")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Eich llythyrau treth ar-lein")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Eich llythyrau treth ar-lein")
      And("I click the message: Eich llythyrau treth ar-lein")
      SecureMessagesPage.clickOnSubject()
      And("When I see the message: Eich llythyrau treth ar-lein")
      SecureMessagesPage.pageContains("Eich llythyrau treth ar-lein")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: Your online tax letters")
      SecureMessagesPage.pageContains("Your online tax letters")
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
    deleteMongoRecordsFromCollection("secure message")
  }
}
