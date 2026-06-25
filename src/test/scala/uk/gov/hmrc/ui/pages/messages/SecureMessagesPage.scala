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

package uk.gov.hmrc.ui.pages.messages

import org.openqa.selenium.By
import uk.gov.hmrc.ui.ElementLocators.*
import uk.gov.hmrc.ui.pages.BasePage

object SecureMessagesPage extends BasePage {

  def clickOnSubject(): Unit = {
    val subjectLink: By = By.cssSelector(demoFrontEndInboxFirstMessageSubject)
    click(subjectLink)
    fluentWait
  }

  def clickOnSubjectEpaye(): Unit = {
    val subjectLink: By = By.cssSelector(demoFrontEndInboxFirstMessageSubject2)
    click(subjectLink)
    fluentWait
  }

  def clickOnUnreadSubjectCds(): Unit = {
    val subjectLink: By = By.cssSelector(cdsMessagePageFirstMessageSubject)
    click(subjectLink)
    fluentWait
  }

  def clickOnReadSubjectCds(): Unit = {
    val subjectLink: By = By.cssSelector(cdsMessagePageReadSubject)
    click(subjectLink)
    fluentWait
  }

  def pageContains(subject: String): Unit =
    assert(getPageSource.contains(subject))

  def backLink(): Unit = {
    val pageBackLink: By = By.cssSelector("#main-content > div > div > div > a")
    click(pageBackLink)
    fluentWait
  }
}
