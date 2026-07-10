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

package uk.gov.hmrc.ui.pages.preferencesAdmin

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.ui.ElementLocators
import uk.gov.hmrc.ui.ElementLocators.*
import uk.gov.hmrc.ui.pages.BasePage

object PreferencesAdminPage extends BasePage {
  private val url: String   = TestEnvironment.url("preferences-admin-frontend")
  var authPageTitle: String = "Home"
  var homePage: String      = "home"

  def loadPage(): Unit = {
    get(url)
    fluentWait.until(ExpectedConditions.urlContains(url))
  }

  def pageTitle(): Unit =
    getTitle

  def adminLogin(): Unit = {
    val adminUsername: By = By.id("username")
    val adminPassword: By = By.id("password")
    sendKeys(adminUsername, "admin")
    sendKeys(adminPassword, "pwd")
    click(By.ByCssSelector(signIn))
    fluentWait
  }

  def userLogin(): Unit = {
    val adminUsername: By = By.id("username")
    val adminPassword: By = By.id("password")
    sendKeys(adminUsername, "user")
    sendKeys(adminPassword, "pwd")
    click(By.ByCssSelector(signIn))
    fluentWait
  }

  def solsUserLogin(): Unit = {
    val adminUsername: By = By.id("username")
    val adminPassword: By = By.id("password")
    sendKeys(adminUsername, "solsUser")
    sendKeys(adminPassword, "pwd")
    click(By.ByCssSelector(signIn))
    fluentWait
  }

  def adminHomePage(): Unit = {
    val currentLocation: String = getCurrentUrl
    assert(currentLocation.equals(url + homePage))
    assert(getPageSource.contains("Paperless Admin"))
    assert(getPageSource.contains("Message Brake"))
    assert(getPageSource.contains("Message Brake Allowlist"))
    assert(getPageSource.contains("Message Decode"))
    assert(getPageSource.contains("Multi Search"))
    fluentWait
  }

  def userHomePage(): Unit = {
    val currentLocation: String = getCurrentUrl
    assert(currentLocation.equals(url + homePage))
    assert(getPageSource.contains("Paperless Admin"))
    fluentWait
  }

  def clickOnPaperlessAdmin(): Unit = {
    click(By.ByCssSelector(clickOnPaperlessAdminLink))
    fluentWait
  }

  def clickOnMessageBrake(): Unit = {
    click(By.ByCssSelector(clickOnMessageBrakeLink))
    fluentWait
  }

  def clickOnMessageBrakeAllowlist(): Unit = {
    click(By.ByCssSelector(clickOnMessageBrakeAllowlistLink))
    fluentWait
  }

  def clickOnBulkOptOut(): Unit = {
    click(By.ByCssSelector(clickOnBulkOptOutLink))
    fluentWait
  }
  
}
