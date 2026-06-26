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
import uk.gov.hmrc.ui.ElementLocators.{chooseLanguagePageContinueButton, chooseLanguagePageEnglish, chooseLanguagePageHeader, chooseLanguagePageWelsh}
import uk.gov.hmrc.ui.pages.BasePage

object PaperlessChooseLanguagePage extends BasePage {

  var paperlessChooseLanguagePageTitle: String      = "Get your paperless email notification in Welsh"
  var paperlessChooseLanguagePageTitleWelsh: String = "Cael eich hysbysiadau di-bapur drwy e-bost yn Gymraeg"

  def waitUntilPageLoad(isWelsh: Boolean = false): Unit =
    if (isWelsh) {
      waitForText(chooseLanguagePageHeader, paperlessChooseLanguagePageTitleWelsh)
    } else
      waitForText(chooseLanguagePageHeader, paperlessChooseLanguagePageTitle)

  def chooseSendPaperlessLanguage(isWelsh: Boolean = true): Unit =
    if (isWelsh)
      click(By.cssSelector(chooseLanguagePageWelsh))
    else
      click(By.cssSelector(chooseLanguagePageEnglish))

  def clickOnContinueButton(): Unit =
    click(By.cssSelector(chooseLanguagePageContinueButton))

}
