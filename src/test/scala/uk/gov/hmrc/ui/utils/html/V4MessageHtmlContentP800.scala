/*
 * Copyright 2024 HM Revenue & Customs
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

object V4MessageHtmlContentP800 {
  val htmlContentP800_SubjectEnglish = "Tax calculation for the year 6 April 2020 to 5 April 2021"
  val htmlContentP800_SubjectWelsh = "Cyfrifiad treth ar gyfer y flwyddyn 6 Ebrill 2020 i 5 Ebrill 2021"
  val HtmlContentP800: String =
    s"""{
        <section lang="en", subject="Tax calculation for the year 6 April 2020 to 5 April 2021">
        <p class="govuk-body">You must send us your income and expenses update for your foreign property income for the quarter ending 05 July 2020.</p>
        <p class="govuk-body">You need to send the updates to us on or before 05 August 2020.</p>
        <p class="govuk-body">We may award you a penalty if your quarterly updates are late.</p>
        <p class="govuk-body">It is your responsibility to make sure you send us all necessary submissions. If you have an agent, you may want to discuss this reminder with them.</p>
        </section>
        <section lang="cy", subject="Cyfrifiad treth ar gyfer y flwyddyn 6 Ebrill 2020 i 5 Ebrill 2021">
        <p class="govuk-body">Rhaid i chi anfon eich diweddariad incwm a threuliau ar gyfer eich incwm o eiddo tramor ar gyfer y chwarter yn diweddu 05 Gorffennaf 2020 atom.</p>
        <p class="govuk-body">Mae angen i chi anfon y diweddariadau atom ar neu cyn 05 Awst 2020.</p>
        <p class="govuk-body">Mae'n bosibl y byddwn yn rhoi cosb i chi os bydd eich diweddariadau chwarterol yn hwyr.</p>
        <p class="govuk-body">Eich cyfrifoldeb chi yw sicrhau eich bod yn anfon yr holl gyflwyniadau angenrheidiol atom. Os oes gennych asiant, efallai y byddwch am drafod y nodyn atgoffa hwn gyda nhw.</p>
        </section>
    }""".stripMargin

}