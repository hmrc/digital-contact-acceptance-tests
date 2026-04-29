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

import org.openqa.selenium.{By, WebDriver}
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.pages.BasePage

object PaperlessPTAHomePage extends BasePage {

  // This is PTA home page for paperless journey using digital-contact-demo-frontend
  var paperlessPtaHomePageTitle: String = "Personal Tax Account"

  def waitUntilPageLoad(): Unit = {
    fluentWait.until(driver => driver.findElement(By.cssSelector("#main-content > div > div.govuk-grid-column-two-thirds > h1")).getText.equals(paperlessPtaHomePageTitle))
  }

  def checkUnreadNotification(notification: String): Boolean = {
    Driver.instance.findElement(By.cssSelector("#main-content > div > div.govuk-grid-column-one-third > p")).getText == notification
  }

  def clickGoToYourMessages(): Unit = {
    click(By.cssSelector("#main-content > div > div.govuk-grid-column-one-third > a"))
  }

  def messageVerification():Unit = {
    if(checkUnreadNotification("1 unread Notification")) {
      clickGoToYourMessages()
    }
  }
  
  def navigateToCheckYourSettings(): Unit = {
    click(By.cssSelector("#main-content > div > div.govuk-grid-column-two-thirds > div > a"))
  }
}
