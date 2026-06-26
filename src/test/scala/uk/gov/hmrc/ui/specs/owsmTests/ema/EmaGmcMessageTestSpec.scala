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

package uk.gov.hmrc.ui.specs.owsmTests.ema

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.ElementLocators.{demoFrontEndInboxFirstMessageSubject, demoFrontEndInboxFirstMessageSubject2}
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.logIntoMessage
import uk.gov.hmrc.ui.pages.messages.GmcMessages.*
import uk.gov.hmrc.ui.pages.messages.SecureMessagesPage
import uk.gov.hmrc.ui.pages.paperless.*
import uk.gov.hmrc.ui.pages.preferencesAdmin.{PreferencesAdminPage, PreferencesMessageBrakeAllowlistPage}
import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.specs.tags.OwsmTests
import uk.gov.hmrc.ui.utils.TestData

class EmaGmcMessageTestSpec extends BaseSpec with TestData {

  Feature("GMC messages from Quadient to EMA") {

    val formIds = Seq(
      "P800 2021",
      "ITSAQU1",
      "M01IOSS",
      "M07GIOSS",
      "LPI1",
      "AD2",
      "LSP1_ITSA",
      "M05AGIOSS",
      "ITSAMIG1",
      "LPP1A_ITSA",
      "LPP2_ITSA",
      "PAR1_ITSA",
      "NIREF1",
      "NIREF4"
    )

    formIds.foreach { formId =>
      Scenario(s"Add $formId form ID to the Message Brake Allowlist", OwsmTests) {
        Given("I log into the preferences admin as Admin")
        PreferencesAdminPage.loadPage()
        PreferencesAdminPage.adminLogin()
        When("When click on the message brake allowlist link")
        PreferencesAdminPage.clickOnMessageBrakeAllowlist()
        And("I click on Add new formId button")
        PreferencesMessageBrakeAllowlistPage.clickOnAddNewFormButton()
        And("I enter the formId, reason and click on confirm")
        PreferencesMessageBrakeAllowlistPage.addNewFormId(formId)
        Then("I should see that new formId added in the allowlist")
        PreferencesMessageBrakeAllowlistPage.formIdAdded()
      }
    }

    Scenario("Customer can view the P800 messages in PTA inbox", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using nino")
      createV4Message("nino")
      And("I open my messages for PTA using regime")
      logIntoMessage("pta", "regime", regimeValue)
      Then("I see the message: Tax calculation for the year 6 April 2020 to 5 April 2021")
      waitForText(demoFrontEndInboxFirstMessageSubject, subject_p800)
    }

    Scenario("Customer can view the ATS messages in BTA inbox", OwsmTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using sautr for ats")
      createV4Message("sautr for ats")
      And("I open my messages for BTA using regime")
      logIntoMessage("sautr", "regime", "regimeSaValue")
      Then("I see the message: Your Annual Tax Summary for 2019 to 2020 is now")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Your Annual Tax Summary for 2019 to 2020 is now")
    }

    Scenario("Customer can view the P800 v4 messages in PTA inbox", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using nino for v4")
      createV4Message("nino for v4")
      And("I open my messages for PTA using regime")
      logIntoMessage("pta", "regime", regimeValue)
      And("I see the message: Tax calculation for the year 6 April 2020 to 5 April 2021")
      waitForText(demoFrontEndInboxFirstMessageSubject, subject_p800)
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Cyfrifiad treth ar gyfer y flwyddyn 6 Ebrill 2020 i 5 Ebrill 2021")
      waitForText(
        demoFrontEndInboxFirstMessageSubject,
        "Cyfrifiad treth ar gyfer y flwyddyn 6 Ebrill 2020 i 5 Ebrill 2021"
      )
      And("I click the message: Cyfrifiad treth ar gyfer y flwyddyn 6 Ebrill 2020 i 5 Ebrill 2021")
      SecureMessagesPage.clickOnSubject()
      And("When I see the message: Cyfrifiad treth ar gyfer y flwyddyn 6 Ebrill 2020 i 5 Ebrill 2021")
      SecureMessagesPage.pageContains("Cyfrifiad treth ar gyfer y flwyddyn 6 Ebrill 2020 i 5 Ebrill 2021")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: Tax calculation for the year 6 April 2020 to 5 April 2021")
      SecureMessagesPage.pageContains(subject_p800)
    }

    Scenario("Customer can view the SA v4 messages in BTA inbox", OwsmTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using sautr for v4")
      createV4Message("sautr for v4")
      And("I open my messages for BTA using regime")
      logIntoMessage("sautr", "regime", regimeSaValue)
      Then("I see the message: File your Self Assessment return")
      waitForText(demoFrontEndInboxFirstMessageSubject, "File your Self Assessment return")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Ffeiliwch eich datganiad Hunanasesiad")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Ffeiliwch eich datganiad Hunanasesiad")
      And("I click the message: Ffeiliwch eich datganiad Hunanasesiad")
      SecureMessagesPage.clickOnSubject()
      When("I see the message: Ffeiliwch eich datganiad Hunanasesiad")
      SecureMessagesPage.pageContains("Ffeiliwch eich datganiad Hunanasesiad")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: File your Self Assessment return")
      SecureMessagesPage.pageContains("File your Self Assessment return")
    }

    Scenario("Customer can view the ITSA v4 messages in BTA inbox", OwsmTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using itsaid for v4")
      createV4Message("itsaid for v4")
      And("I open my messages for itsa using regime")
      logIntoMessage("itsa", "regime", regimeItsaValue)
      Then("I see the message: Send your quarterly Income Tax update")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Send your quarterly Income Tax update")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Anfonwch eich diweddariad Treth Incwm chwarterol")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Anfonwch eich diweddariad Treth Incwm chwarterol")
      And("I click the message: Anfonwch eich diweddariad Treth Incwm chwarterol")
      SecureMessagesPage.clickOnSubject()
      When("I see the message: Anfonwch eich diweddariad Treth Incwm chwarterol")
      SecureMessagesPage.pageContains("Anfonwch eich diweddariad Treth Incwm chwarterol")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: Send your quarterly Income Tax update")
      SecureMessagesPage.pageContains("Send your quarterly Income Tax update")
    }

    Scenario("Customer can view the VAT v4 messages in BTA inbox", OwsmTests) {
      Given("A GMC message is created via EMA using vat for v4")
      createV4Message("vat for v4")
      And("I open my messages for Vat using regime")
      logIntoMessage("vat", "regime", regimeVatValue)
      Then("I see the message: Late Payment Interest Due")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Late Payment Interest Due")
      And("I click Welsh language link")
      selectLanguageWelsh()
      Then("I see the message: Y llog sy’n ddyledus am dalu’n hwyr")
      waitForText(demoFrontEndInboxFirstMessageSubject, "Y llog sy’n ddyledus am dalu’n hwyr")
      And("I click the message: Y llog sy’n ddyledus am dalu’n hwyr")
      SecureMessagesPage.clickOnSubject()
      When("I see the message: Y llog sy’n ddyledus am dalu’n hwyr")
      SecureMessagesPage.pageContains("Y llog sy’n ddyledus am dalu’n hwyr")
      And("I click English language link")
      selectLanguageEnglish()
      Then("I see the message: Late Payment Interest Due")
      SecureMessagesPage.pageContains("Late Payment Interest Due")
    }

    Scenario("Customer can view the IOSS INTERMEDIARY messages in secure message inbox", OwsmTests) {
      Given("A GMC message is created via EMA using ioss inter")
      createV4Message("ioss inter")
      And("I open my messages for ioss using regime")
      logIntoMessage("ioss inter", "regime", regimeIossValue)
      Then("I see the message: Late Payment Interest Due")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Late Payment Interest Due")
    }

    Scenario("Customer can view the OSS messages in secure message inbox", OwsmTests) {
      Given("A GMC message is created via EMA using oss")
      createV4Message("oss")
      And("I open my messages for oss using regime")
      logIntoMessage("oss", "regime", regimeOssValue)
      Then("I see the message: Late Payment Interest Due")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Late Payment Interest Due")
    }

    Scenario("Customer can view the Alcohol Duty messages in secure message inbox", OwsmTests) {
      Given("A GMC message is created via EMA using ad")
      createV4Message("ad")
      And("I open my messages for ad using regime")
      logIntoMessage("ad", "regime", regimeAdValue)
      Then("I see the message: Late Payment Interest Due")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Late Payment Interest Due")
    }

    Scenario("Customer can view the ITSA new English messages in BTA inbox", OwsmTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using itsaid for en")
      createV4Message("itsaid for en")
      And("I open my messages for itsa using regime")
      logIntoMessage("itsa", "regime", regimeItsaValue)
      Then("I see the message: New ITSA Secure Message In English")
      waitForText(demoFrontEndInboxFirstMessageSubject, "New ITSA Secure Message In English")
    }

    Scenario("Customer can view the ITSA Mig1 new English messages in BTA inbox", OwsmTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using itsamig1 for en")
      createV4Message("itsamig1 for en")
      And("I open my messages for itsa using regime")
      logIntoMessage("itsa", "regime", regimeItsaValue)
      Then("I see the message: New ITSA Secure Message In English")
      waitForText(demoFrontEndInboxFirstMessageSubject, "New ITSA Secure Message In English")
    }

    Scenario("Customer can view the ITSA Lpp1a new English messages in BTA inbox", OwsmTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using lpp1aitsa")
      createV4Message("lpp1aitsa")
      And("I open my messages for itsa using regime")
      logIntoMessage("itsa", "regime", regimeItsaValue)
      Then("I see the message: New ITSA Secure Message In English")
      waitForText(demoFrontEndInboxFirstMessageSubject, "New ITSA Secure Message In English")
    }

    Scenario("Customer can view the ITSA Lpp2 new English messages in BTA inbox", OwsmTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using lpp2itsa")
      createV4Message("lpp2itsa")
      And("I open my messages for itsa using regime")
      logIntoMessage("itsa", "regime", regimeItsaValue)
      Then("I see the message: New ITSA Secure Message In English")
      waitForText(demoFrontEndInboxFirstMessageSubject, "New ITSA Secure Message In English")
    }

    Scenario("Customer can view the ITSA par1 new English messages in BTA inbox", OwsmTests) {
      Given("I logged into BTA account with nino with sautr enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("sautr")
      And("I see the page: Business Tax Account")
      PaperlessBTAHomePage.btaPageTitle()
      And("I am unverified for paperless - BTA")
      PaperlessBTAHomePage.clickOnGetTaxLettersOnlineLink()
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using par1itsa")
      createV4Message("par1itsa")
      And("I open my messages for itsa using regime")
      logIntoMessage("itsa", "regime", regimeItsaValue)
      Then("I see the message: New ITSA Secure Message In English")
      waitForText(demoFrontEndInboxFirstMessageSubject, "New ITSA Secure Message In English")
    }

    Scenario("Customer can view the IOSS NETP messages in secure message inbox", OwsmTests) {
      Given("A GMC message is created via EMA using ioss netp")
      createV4Message("ioss netp")
      And("I open my messages for ioss using regime")
      logIntoMessage("ioss netp", "regime", regimeIossValue)
      Then("I see the message: Late Payment Interest Due")
      waitForText(demoFrontEndInboxFirstMessageSubject2, "Late Payment Interest Due")
    }

    Scenario("Customer can view the NIREF1 En messages in PTA inbox", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using niref1 en")
      createV4Message("niref1 en")
      And("I open my messages for PTA using regime")
      logIntoMessage("pta", "regime", regimeValue)
      Then("I see the message: National Insurance contributions - we may owe you a refund")
      waitForText(demoFrontEndInboxFirstMessageSubject, "National Insurance contributions - we may owe you a refund")
    }

    Scenario("Customer can view the NIREF4 EN messages in PTA inbox", OwsmTests) {
      Given("I am logged into PTA account with nino enrolment")
      LoginUsingAuthWizardPage.pageLoad()
      LoginUsingAuthWizardPage.loginIntoAccountByAuthWizard("NoSautr", pta)
      PaperlessInterruptPage.pageTitle()
      And("I am unverified for paperless")
      PaperlessInterruptPage.fillInterruptPageForOptin()
      PaperlessEmailPage.fillEmailPage()
      PaperlessVerifyEmailPage.pageTitle()
      And("I verify the email address")
      verifyEmail()
      When("A GMC message is created via EMA using niref4 en")
      createV4Message("niref4 en")
      And("I open my messages for PTA using regime")
      logIntoMessage("pta", "regime", regimeValue)
      Then("I see the message: National Insurance contributions - we may owe you a refund")
      waitForText(demoFrontEndInboxFirstMessageSubject, "National Insurance contributions - we may owe you a refund")
    }

    formIds.foreach { formId =>
      Scenario(s"Delete $formId form ID from Message Brake Allowlist", OwsmTests) {
        Given("I log into the preferences admin as Admin")
        PreferencesAdminPage.loadPage()
        PreferencesAdminPage.adminLogin()
        When("When click on the message brake allowlist link")
        PreferencesAdminPage.clickOnMessageBrakeAllowlist()
        And(s"I select the Form ID $formId to delete")
        PreferencesMessageBrakeAllowlistPage.clickOnDeleteFormId(formId)
        PreferencesMessageBrakeAllowlistPage.fillReasonToDeleteFormId()
        Then(s"I confirm the deletion of $formId")
        PreferencesMessageBrakeAllowlistPage.confirmFormIdDeleted(formId)
      }
    }
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    deleteMongoRecordsFromCollection("preferences")
    deleteMongoRecordsFromCollection("secure message")
  }
}
