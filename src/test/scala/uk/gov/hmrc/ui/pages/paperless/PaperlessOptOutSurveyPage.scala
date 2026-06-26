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
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.ElementLocators.{optOutSurveyContinueButton, optOutSurveyPageHeader, optOutSurveyReason, optOutSurveySkipButton}
import uk.gov.hmrc.ui.pages.BasePage

object PaperlessOptOutSurveyPage extends BasePage {

  // This is Opt out survey page for paperless journey using digital-contact-demo-frontend
  var paperlessOptOutSurveyPageTitle: String   = "Why did you choose paper tax letters?"
  var paperlessReOptOutSurveyPageTitle: String = "Why did you choose to go back to paper letters?"

  def waitUntilPageLoad(reOptout: Boolean = false): Unit =
    if (reOptout) {
      waitForText(optOutSurveyPageHeader, paperlessReOptOutSurveyPageTitle)
    } else {
      waitForText(optOutSurveyPageHeader, paperlessOptOutSurveyPageTitle)
    }

  def optOutSurveyQuestionsSelection(): Unit = {
    click(By.cssSelector("#choice-d210eccd-9ea1-48fd-a28e-25abbb7508fe"))
    click(By.cssSelector("#choice-717c2da0-4411-41ad-9a78-b335786e7107"))
    click(By.cssSelector("#choice-bf74f47f-e9ce-4c15-a9aa-1af80a594861"))
    click(By.cssSelector("#choice-ca31965c-dd40-4a2c-a606-fe961da485c0"))
    click(By.cssSelector("#choice-a6f84da8-9fd7-440d-915e-2a2f8a543c9b"))
  }

  def reOptOutSurveyQuestionsSelection(): Unit = {
    click(By.cssSelector("#choice-0305d33f-2e8d-4cb2-82d2-52132fc325fe"))
    click(By.cssSelector("#choice-ce34aa17-df2a-44fb-9d5c-4d930396483a"))
    click(By.cssSelector("#choice-d0edb491-6dcb-48a8-aeca-b16f01c541a5"))
    click(By.cssSelector("#choice-1e825e7d-6fc8-453f-8c20-1a7ed4d84ea5"))
    click(By.cssSelector("#choice-15d28c3f-9f33-4c44-aefa-165fc84b5e23"))
  }

  def enterSurveyReason(): Unit =
    sendKeys(By.cssSelector(optOutSurveyReason), "Testing the survey functionality")

  def clickOnContinueButton(): Unit =
    click(By.cssSelector(optOutSurveyContinueButton))

  def clickOnSkipButton(): Unit =
    click(By.cssSelector(optOutSurveySkipButton))

  def pageNotVisible(): Unit =
    assert(Driver.instance.findElements(By.cssSelector(optOutSurveyPageHeader)).size() == 0)

}
