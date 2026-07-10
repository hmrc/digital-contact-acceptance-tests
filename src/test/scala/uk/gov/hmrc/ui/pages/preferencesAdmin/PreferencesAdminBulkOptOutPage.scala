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

import org.openqa.selenium.{By, WebElement}
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.ui.ElementLocators.chooseFileUpload
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.preferencesAdmin.PreferencesMessageDecodePage.click

object PreferencesAdminBulkOptOutPage extends BasePage {

  var BulkOptOutTitle: String = "Bulk Opt Out"
  val bulkOptOutFile: String = "bulkoptout.csv"
  
  def pageTitle(): Unit =
    getTitle

  def csvFileUploadAndProcess(file: String): Unit = {
    val directory = System.getProperty("user.dir")
    val fileInput: WebElement = fluentWait.until(
      ExpectedConditions.elementToBeClickable(By.id(chooseFileUpload))
    )
    fileInput.sendKeys(s"$directory/src/test/scala/uk/gov/hmrc/ui/utils/$file")
    fluentWait.until(
      ExpectedConditions.elementToBeClickable(By.id(uploadAndProcess))
    )
    click(By.id(uploadAndProcess))
  }
}
