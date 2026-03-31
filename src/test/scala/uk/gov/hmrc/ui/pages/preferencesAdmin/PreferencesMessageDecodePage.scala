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
import uk.gov.hmrc.ui.ElementLocators.clickOnMessageDecode
import uk.gov.hmrc.ui.pages.BasePage

object PreferencesMessageDecodePage extends BasePage {

  var searchPageTitle: String = "Decode from Base64 encoded format"

  def pageTitle(): Unit =
    getTitle

  def clickOnMessageDecodeLink(): Unit = {
    click(By.cssSelector(clickOnMessageDecode))
    fluentWait
  }

  def enterBase64EncodedText(): Unit = {
    val base64EncodedTextId: By = By.id(encodedTextId)
    sendKeys(base64EncodedTextId, englishContentValue)
    click(By.id(decodeButtonId))
    fluentWait
  }

  def messageDecodedTextCheck(): Unit = {
    getText(By.cssSelector("#encoded-text")).contains("Test Message")
    fluentWait
  }
}
