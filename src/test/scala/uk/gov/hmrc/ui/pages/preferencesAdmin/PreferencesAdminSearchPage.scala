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
import uk.gov.hmrc.ui.pages.authWizard.LoginUsingAuthWizardPage.{email, identifierValue, ninoNumber}
import uk.gov.hmrc.ui.pages.BasePage
import PreferencesAdminUserSummaryPage.{fluentWait, getTitle}

object PreferencesAdminSearchPage extends BasePage {

  var searchPageTitle: String = "Customer Identification"
  val saUtrRadioButton: String = saUtrRadioButtonId
  val ninoRadioButton: String = ninoRadioButtonId
  val ItsaIdRadioButton: String = ItsaIdRadioButtonId
  val emailIdRadioButton: String = emailIdRadioButtonId
  val identifierValueText: String = identifierValueTextId

  def pageTitle(): Unit = {
    getTitle
  }

  def selectNinoRadioOption(): Unit = {
    val selectNino: By = By.id(ninoRadioButton)
    selectCheckbox(selectNino)
  }

  def selectEmailRadioOption(): Unit = {
    val selectEmail: By = By.id(emailIdRadioButton)
    selectCheckbox(selectEmail)
  }

  def selectSautrRadioOption(): Unit = {
    val selectSautr: By = By.id(saUtrRadioButton)
    selectCheckbox(selectSautr)
  }

  def fillIdentifierValueUsingNino(): Unit = {
    val identifierValueTextArea: By = By.id(identifierValueText)
    sendKeys(identifierValueTextArea, ninoNumber)
    click(By.cssSelector(searchButtonOnSearchPage))
    fluentWait
  }

  def fillIdentifierValueUsingEmail(): Unit = {
    val identifierValueTextArea: By = By.id(identifierValueText)
    sendKeys(identifierValueTextArea, email)
    click(By.cssSelector(searchButtonOnSearchPage))
    fluentWait
  }

  def fillIdentifierValueUsingSautr(): Unit = {
    val identifierValueTextArea: By = By.id(identifierValueText)
    sendKeys(identifierValueTextArea, identifierValue)
    click(By.cssSelector(searchButtonOnSearchPage))
    fluentWait
  }
}
