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

import org.openqa.selenium.{By, WebDriver, WebElement}
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.pages.BasePage

object PaperlessCheckYourSettingsPage extends BasePage {

  // This is Check your settings page for paperless journey using digital-contact-demo-frontend
  var paperlessPTACheckYourSettingsPageTitle: String = "Check your settings"
  var paperlessPTACheckYourSettingsPageTitleWelsh: String = "Gwirio’ch gosodiadau"

  def waitUntilPageLoad(isWelsh: Boolean=false): Unit = {
    if(isWelsh){
      fluentWait.until(driver => driver.findElement(By.cssSelector("#saCheckSettings")).getText.equals(paperlessPTACheckYourSettingsPageTitleWelsh))
    }else {
      fluentWait.until(driver => driver.findElement(By.cssSelector("#saCheckSettings")).getText.equals(paperlessPTACheckYourSettingsPageTitle))
    }
  }

  def contentVerification(section:String, content: String): Unit = {
    var contentElement: WebElement = null
    section match {
      case "Tax documents" => contentElement = Driver.instance.findElement(By.cssSelector("#main-content > div > div > dl > div:nth-child(1) > dd.govuk-summary-list__value"))
      case "Emails sent in for verified" =>  contentElement = Driver.instance.findElement(By.cssSelector("#main-content > div > div > dl:nth-child(3) > div:nth-child(2) > dd.govuk-summary-list__value"))
      case "Emails sent in" =>  contentElement = Driver.instance.findElement(By.cssSelector("#main-content > div > div > dl > div.govuk-summary-list__row.govuk-summary-list__row--no-actions > dd"))

      case _ => throw new IllegalArgumentException(s"Unknown Section")
    }
    assert (contentElement.getText == content)
  }

  def clickOnChangeEmailAddressLink(): Unit = {
    click(By.cssSelector("#main-content > div > div > dl > div:nth-child(2) > dd.govuk-summary-list__actions > a"))
  }

  def clickOnChangeEmailAddressLinkVerified(): Unit = {
    click(By.cssSelector("#main-content > div > div > dl:nth-child(5) > div > dd.govuk-summary-list__actions > a"))
  }

  def clickOnContinueButton(): Unit = {

    click(By.cssSelector("#main-content > div > div > a"))
  }

  def clickOnEmailsSentInChangeLink(): Unit = {
    click(By.cssSelector("#main-content > div > div > dl:nth-child(3) > div:nth-child(2) > dd.govuk-summary-list__actions > a"))
  }

  def clickOnFixthisLink(): Unit = {
    click(By.cssSelector("#main-content > div > div > dl > div:nth-child(2) > dd.govuk-summary-list__value > p > a"))
  }

  def clickOnChangeTaxDocumentsLink(): Unit = {
    click(By.cssSelector("#main-content > div > div > dl:nth-child(3) > div:nth-child(1) > dd.govuk-summary-list__actions > a"))
  }
  
}
