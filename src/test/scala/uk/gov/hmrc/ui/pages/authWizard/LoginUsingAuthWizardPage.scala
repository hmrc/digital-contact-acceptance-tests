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

package uk.gov.hmrc.ui.pages.authWizard

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.ui.pages.BasePage
import uk.gov.hmrc.ui.pages.preferencesAdmin.PreferencesAdminPage.{click, sendKeys}
import uk.gov.hmrc.ui.utils.ElementLocators.{getConfidenceLevelId, getCredentialStrengthId, getNinoId, getRedirectUrlId}
import uk.gov.hmrc.ui.utils.TestData

object LoginUsingAuthWizardPage extends BasePage with TestData {

  private val authWizardBaseUrl: String = TestEnvironment.url("auth-wizard")
  val saApiProxyBaseUrl: String = TestEnvironment.url("sa-api-proxy")
  var authPageTitle: String = "Authority Wizard"

  def pageLoad(): Unit = {
    get(authWizardBaseUrl)
    fluentWait.until(ExpectedConditions.urlContains(authWizardBaseUrl))
  }

  def pageTitle(): Unit =
    getTitle
  
  def loginPTAUsingAuthWizardByNinoOnly(): Unit = {
    val getRedirectUrl: By = By.id(getRedirectUrlId)
    val getCredentialStrength: By = By.id(getCredentialStrengthId)
    val getConfidenceLevel: By = By.id(getConfidenceLevelId)
    val getNinoNumber: By = By.id(getNinoId)

    sendKeys(getRedirectUrl, digitalContactDemoFrontend+pta)
    selectByValue(getCredentialStrength, credentialStrength)
    selectByValue(getConfidenceLevel, confidenceLevel)
    sendKeys(getNinoNumber, ninoNumber)

    click(By.id("submit"))
    fluentWait
  }

  def loginIntoAccountByAuthWizard(enrolmentType: String): Unit = {
    val getRedirectUrl: By = By.id(getRedirectUrlId)
    val getCredentialStrength: By = By.id(getCredentialStrengthId)
    val getConfidenceLevel: By = By.id(getConfidenceLevelId)
    val getNinoNumber: By = By.id(getNinoId)
    val enrolmentKeyId: By = By.id("enrolment[0].name")
    val enrolmentNameId: By = By.id("input-0-0-name")
    val enrolmentValueId: By = By.id("input-0-0-value")

      sendKeys(getRedirectUrl, digitalContactDemoFrontend + bta)
      selectByValue(getCredentialStrength, credentialStrength)
      selectByValue(getConfidenceLevel, confidenceLevel)
      sendKeys(getNinoNumber, ninoNumber)
      sendKeys(enrolmentKeyId, enrolmentKey)
      sendKeys(enrolmentNameId, identifierName)

      val enrolmentValue = enrolmentType match {
        case "sautr" => sendKeys(enrolmentValueId, identifierValue)
        case "sautr2" => sendKeys(enrolmentValueId, identifierValue2)
        case _ => throw new IllegalArgumentException(s"Unknown UTR Value")
      }
    click(By.id("submit"))
    fluentWait
  }
  
}
