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
import org.openqa.selenium.support.ui.FluentWait
import uk.gov.hmrc.ui.pages.BasePage

object PaperlessBTAHomePage extends BasePage {

  //This is BTA home page for paperless journey using digital-contact-demo-frontend
  var paperlessBtaHomePageTitle: String = "Business Tax Account"
  
  def btaPageTitle(): Unit = {
    getTitle.contains(paperlessBtaHomePageTitle)
  }

  def clickOnGetTaxLettersOnlineLink(): Unit = {
    val getTaxLettersOnlineLink: By = By.id("Gettaxlettersonline")
    click(getTaxLettersOnlineLink)
    fluentWait
  }

}
