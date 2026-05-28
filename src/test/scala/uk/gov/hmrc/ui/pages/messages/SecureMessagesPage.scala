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

object SecureMessagesPage extends BasePage{
  
  def clickOnSubject(): Unit = {
    val subjectLink: By = By.cssSelector(demoFrontEndInboxFirstMessageSubject)
    click(subjectLink)
  }

  def clickOnSubjectEpaye(): Unit = {
    val subjectLink: By = By.cssSelector(demoFrontEndInboxIossMessageSubject)
    click(subjectLink)
  }

  def clickOnSubjectCds(): Unit = {
    val subjectLink: By = By.cssSelector(cdsMessagePageFirstMessageSubject)
    click(subjectLink)
  }
  
  def pageContains(subject: String): Unit = {
    assert(getPageSource.contains(subject))
  }
}
