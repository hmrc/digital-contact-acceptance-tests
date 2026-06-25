/*
 * Copyright 2025 HM Revenue & Customs
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

package uk.gov.hmrc.ui.utils.html

object EnMessageHtmlContentItsa {
  val HtmlContentEnItsa_SubjectEnglish = "New ITSA Secure Message In English"
  val HtmlContentEnItsa                =
    s"""{
           <section lang="en" Subject="New ITSA Secure Message In English" From="HMRC">
           <div class="govuk-grid-row grid-row">
           <div class="govuk-grid-column-two-thirds column-two-thirds">
           <h2 class="govuk-heading-l govuk-!-margin-top-9">What you need to send us for the 2024 to 2025 tax year </h2>
           <p class="govuk-body">We are writing to you to confirm that for the 2024 to 2025 tax year, you need to use software compatible with Making Tax Digital for Income Tax to&#58;</p>
           <ul class="list list-bullet govuk-list govuk-list--bullet">
           <li>create and store digital records of your business income and expenses (from self&#8208;employment and property)</li>
           <li>send us quarterly updates (summaries of your digital records)</li>
           <li>send us a tax return for the 2024 to 2025 tax year (we will send a notice for this after 5 April 2025).</li>
           </ul>
           </div>
           </div>
           </section>
           }""".stripMargin

}
