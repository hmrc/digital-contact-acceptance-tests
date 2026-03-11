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

package uk.gov.hmrc.ui.specs

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.pages.AuthWizardPage
import uk.gov.hmrc.ui.specs.tags.Wip


class AuthWizardPageSpec extends BaseSpec {

  Feature("To test Auth wizard page"){
    Scenario("Validate page title of auth wizard", Wip){
      Given("I am on the auth wizard page")
      AuthWizardPage.pageLoad()

      When("")

      Then("I should see the page title")
      AuthWizardPage.pageTitle()
    }
  }
}
