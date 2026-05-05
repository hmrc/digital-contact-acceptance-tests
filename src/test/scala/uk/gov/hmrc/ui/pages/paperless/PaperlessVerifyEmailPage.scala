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

object PaperlessVerifyEmailPage extends BasePage {
  var paperlessVerifyEmailPageTitle: String = "Verify your email address"

  def continueVerifyEmailAddressPage(): Unit =
    click(By.ByCssSelector("#main-content > div > div > div:nth-child(5) > a"))

  def sendTheLinkAgain(): Unit =
    click(By.cssSelector("#main-content > div > div > div > a"))

  def useDifferentEmailAddress(): Unit = {
    click(By.cssSelector("#main-content > div > div > div > p > a"))
    fluentWait.until(driver =>
      driver
        .findElement(By.cssSelector("#form-submit-email-address > fieldset > legend > h1"))
        .getText
        .equals("Enter your email address")
    )
  }
}
