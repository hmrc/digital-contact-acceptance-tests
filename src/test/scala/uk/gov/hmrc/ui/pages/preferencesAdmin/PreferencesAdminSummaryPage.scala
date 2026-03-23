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
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.optOutReasonText
import uk.gov.hmrc.ui.pages.BasePage
import PreferencesAdminUserSummaryPage.{fluentWait, getTitle}

object PreferencesAdminSummaryPage extends BasePage {

  var searchPageTitle: String = "User Summary"
  val optUserOutLinkSelector: String = optUserOutLinkCssSelector
  val optOutUserReasonId: String = optOutUserReasonTextId
  val optUserOutReason: String = optOutReasonText

  def pageTitle(): Unit = {
    getTitle
  }

  def clickOnOptUserOutLink(): Unit = {
    click(By.cssSelector(optUserOutLinkSelector))
    fluentWait
  }

  def fillReasonToOptOut(): Unit = {
    val optOutUserReasonTextArea: By = By.id(optOutUserReasonId)
    sendKeys(optOutUserReasonTextArea, optUserOutReason)
    click(By.cssSelector(yesButtonOnSummaryPage))
    fluentWait
  }
}
