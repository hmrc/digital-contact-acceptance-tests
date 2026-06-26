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

import org.openqa.selenium.{By, WebElement}
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.ElementLocators.{cysPageChangeEmailAddressLink, cysPageChangeEmailAddressVerifiedLink, cysPageContinueButton, cysPageEmailSentInChangeLink, cysPageEmailSentsIn, cysPageEmailSentsInVerified, cysPageFixthisLink, cysPageHeader, cysPageTaxDocument, cysPageTaxDocumentsChangeLink}
import uk.gov.hmrc.ui.pages.BasePage

object PaperlessCheckYourSettingsPage extends BasePage {

  // This is Check your settings page for paperless journey using digital-contact-demo-frontend
  var paperlessPTACheckYourSettingsPageTitle: String      = "Check your settings"
  var paperlessPTACheckYourSettingsPageTitleWelsh: String = "Gwirio’ch gosodiadau"

  def waitUntilPageLoad(isWelsh: Boolean = false): Unit           =
    if (isWelsh) {
      waitForText(cysPageHeader, paperlessPTACheckYourSettingsPageTitleWelsh)
    } else {
      waitForText(cysPageHeader, paperlessPTACheckYourSettingsPageTitle)
    }
  def contentVerification(section: String, content: String): Unit = {
    var contentElement: WebElement = null
    section match {
      case "Tax documents"               => contentElement = Driver.instance.findElement(By.cssSelector(cysPageTaxDocument))
      case "Emails sent in for verified" =>
        contentElement = Driver.instance.findElement(By.cssSelector(cysPageEmailSentsInVerified))
      case "Emails sent in"              => contentElement = Driver.instance.findElement(By.cssSelector(cysPageEmailSentsIn))
      case _                             => throw new IllegalArgumentException(s"Unknown Section")
    }
    assert(contentElement.getText == content)
  }

  def clickOnChangeEmailAddressLink(): Unit =
    click(By.cssSelector(cysPageChangeEmailAddressLink))

  def clickOnChangeEmailAddressLinkVerified(): Unit =
    click(By.cssSelector(cysPageChangeEmailAddressVerifiedLink))

  def clickOnContinueButton(): Unit =
    click(By.cssSelector(cysPageContinueButton))

  def clickOnEmailsSentInChangeLink(): Unit =
    click(By.cssSelector(cysPageEmailSentInChangeLink))

  def clickOnFixthisLink(): Unit =
    click(By.cssSelector(cysPageFixthisLink))

  def clickOnChangeTaxDocumentsLink(): Unit =
    click(By.cssSelector(cysPageTaxDocumentsChangeLink))

}
