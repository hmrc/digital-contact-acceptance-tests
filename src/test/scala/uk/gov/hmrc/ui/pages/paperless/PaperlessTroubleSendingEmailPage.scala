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
import uk.gov.hmrc.ui.ElementLocators.{toubleSendingEmailPageEnterAddress, toubleSendingEmailPageNotWantOnlineLetters}
import uk.gov.hmrc.ui.pages.BasePage

object PaperlessTroubleSendingEmailPage extends BasePage {

  var PaperlessTroubleSendingEmailPageHeader: String = "We are having trouble sending you emails"

  def enterYourEmailAddress(): Unit = {
    click(By.cssSelector(toubleSendingEmailPageEnterAddress))
  }

  def doNotWantToGetOnlineTaxLetters(): Unit = {
    click(By.cssSelector(toubleSendingEmailPageNotWantOnlineLetters))
  }

  def enterEmailButtonExists(): Unit = {
    assert(Driver.instance.findElements(By.cssSelector(toubleSendingEmailPageEnterAddress)).size() > 0)
  }
  def doNotWantOnlineLettersLinkExists(): Unit = {
    assert(Driver.instance.findElements(By.cssSelector(toubleSendingEmailPageNotWantOnlineLetters)).size() > 0)
  }
}
