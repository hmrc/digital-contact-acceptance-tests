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

package uk.gov.hmrc.ui.pages.paperless

import org.openqa.selenium.By
import uk.gov.hmrc.ui.ElementLocators.{emailPageHeader, reOptInPageHeader, reOptInPageSubmitEmailFormHeader, spsReOptIn2Id, spsReOptInId, spsReOptInIdEmailId, submitEmailButtonId}
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.sendKeys
import uk.gov.hmrc.ui.pages.paperless.PaperlessBTAHomePage.fluentWait

object PaperlessReOptInPage extends BasePage {
  var PaperlessReOptInPageTitleEnglish: String = "Keep getting your tax letters online"
  var PaperlessReOptInPageTitleWelsh: String = "Parhau i gael eich llythyrau treth ar-lein"

  def waitUntilPageLoad(isWelsh: Boolean=false): Unit = {
    if (isWelsh) {
      waitForText(reOptInPageHeader, PaperlessReOptInPageTitleWelsh)
    } else {
      waitForText(reOptInPageHeader, PaperlessReOptInPageTitleEnglish)
    }
  }

  def reOptIn(emailBounced: Boolean = false): Unit = {
    click(By.id(spsReOptInId))
    click(By.id(submitEmailButtonId))

    if(! emailBounced)
      fluentWait.until(driver => driver.findElement(By.cssSelector(reOptInPageSubmitEmailFormHeader)).getText.equals("Which email do you want to use for your tax letters?"))
    else
      fluentWait.until(driver => driver.findElement(By.cssSelector(emailPageHeader)).getText.equals("Enter your email address"))
  }

  def reOptInWithVerifiedEmail(): Unit = {
    click(By.id(spsReOptInId))
    click(By.id(submitEmailButtonId))
  }

  def reOptinEnterNewEmail(): Unit = {
    sendKeys(By.id(spsReOptInIdEmailId), email2)
    click(By.id(submitEmailButtonId))

  }

  def reOptInWithNewEmail(): Unit = {
    click(By.id(spsReOptIn2Id))
    reOptinEnterNewEmail()
  }

  def reOptOut(): Unit = {
    click(By.id(spsReOptIn2Id))
    click(By.id(submitEmailButtonId))
  }

  def reOptInWithNewEmailPta(): Unit = {
    click(By.id("sps-re-opt-in-2"))
    reOptinEnterNewEmail()
  }

  def PaperlessReOptInPageTitle(): Unit = {
    getTitle.contains(PaperlessReOptInPageTitleEnglish)
    fluentWait
  }

  def clickCloseButton(): Unit = {
    click(By.cssSelector("#main-content > div > div > div:nth-child(5) > a"))
  }

}
