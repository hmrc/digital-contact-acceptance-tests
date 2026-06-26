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
import uk.gov.hmrc.ui.ElementLocators.{clickOnAddNewFormButtonId, clickOnDeleteFormButtonId}
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.preferencesAdmin.PreferencesMessageBrakePage.getText

object PreferencesMessageBrakeAllowlistPage extends BasePage {

  var searchPageTitle: String = "Message Form IDs Allowlist"

  def pageTitle(): Unit =
    getTitle

  def clickOnAddNewFormButton(): Unit =
    click(By.cssSelector(clickOnAddNewFormButtonId))

  def clickOnDeleteFormId(): Unit = {
    click(By.cssSelector(clickOnDeleteFormButtonId))
    fluentWait
  }

  def fillReasonToDeleteFormId(): Unit = {
    val reasonText: By = By.id(reasonTextId)
    sendKeys(reasonText, reasonTextForDeleting)
    click(By.name("confirm"))
    fluentWait
  }

  def formIdAdded(): Unit =
    getText(By.cssSelector("#main-content > div > div")).contains("SA316")

  def addNewFormId(id: String): Unit = {
    val formId: By     = By.id(newFormIdId)
    val reasonText: By = By.id(reasonTextId)
    sendKeys(formId, id)
    sendKeys(reasonText, reasonTextForAdding)
    click(By.name("confirm"))
  }

  def clickOnDeleteFormId(id: String): Unit = {
    val xpathForId = s"//button[contains(@onclick,'${id.replace(" ", "%20")}')]"
    click(By.xpath(xpathForId))
  }

  def confirmFormIdDeleted(id: String): Unit =
    assert(getPageSource.contains(id).equals(false))
}
