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
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.sendKeys

object PaperlessReOptInPage extends BasePage {
  var PaperlessReOptInPageHeader: String = "Keep getting your tax letters online"

  def reOptIn(emailBounced: Boolean = false): Unit = {
    click(By.id("sps-re-opt-in"))
    click(By.id("submitEmailButton"))
    if (!emailBounced)
      fluentWait.until(driver =>
        driver
          .findElement(By.cssSelector("#form-submit-email-address > div > fieldset > legend > h1"))
          .getText
          .equals("Which email do you want to use for your tax letters?")
      )
    else
      fluentWait.until(driver =>
        driver
          .findElement(By.cssSelector("#form-submit-email-address > fieldset > legend > h1"))
          .getText
          .equals("Enter your email address")
      )
  }

  def reOptInWithVerifiedEmail(): Unit = {
    click(By.id("sps-re-opt-in"))
    click(By.id("submitEmailButton"))
  }

  def reOptinEnterNewEmail(): Unit = {
    sendKeys(By.id("sps-re-opt-in-email"), email2)
    click(By.id("submitEmailButton"))
  }

  def reOptInWithNewEmail(): Unit = {
    click(By.id("sps-re-opt-in-2"))
    reOptinEnterNewEmail()
  }

}
