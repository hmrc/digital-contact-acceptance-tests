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

object V4MessageHtmlContentVat {
  val htmlContentVat_SubjectEnglish = "Late Payment Interest Due"
  val htmlContentVat_SubjectWelsh   = "Y llog sy’n ddyledus am dalu’n hwyr"
  val HtmlContentVat                =
    s"""{
  <section lang="en" Subject="Late Payment Interest Due"
        <div class="govuk-grid-row grid-row">
        <div class="govuk-grid-column-two-thirds column-two-thirds"><h2 class="govuk-heading-l govuk-!-margin-top-9">You owe late payment interest on your VAT</h2>
        <p class="govuk-body">Your VAT payment for the period 01 May 2023 to 31 May 2023 was due on 07 July 2023.</p>
        <p class="govuk-body">You need to pay late payment interest because we did not receive your VAT payment in full until 14 September 2023.</p>
        <p class="govuk-body">The amount you owe in late payment interest is &#163;132.32.</p>
        <p class="govuk-body">Sort code: 08 32 00<br>
        <p class="govuk-body">Tell us if you have health or personal circumstances that make it difficult for you to deal with HMRC. There are ways we can help.</p>
        <p class="govuk-body">Go to GOV.UK for <a href="https://www.gov.uk/get-help-hmrc-extra-support" target="_blank">more information about the support we can offer (Opens in a new tab)</a>.</p>
        <p class="govuk-body">If you’ve told us you have an authorised agent or representative for VAT, we’ve sent them this information.</p>
        <p class="message_time faded-text--small govuk-body-s">Message reference&#58; LPI1 05/22 1.0</p>
        </div>
        </div>
        </section>
        <section lang="cy" Subject="Y llog sy’n ddyledus am dalu’n hwyr"
        <div class="govuk-grid-row grid-row">
        <div class="govuk-grid-column-two-thirds column-two-thirds"><h2 class="govuk-heading-l govuk-!-margin-top-9">Mae arnoch log am dalu’ch TAW yn hwyr</h2>
        <p class="govuk-body">Roedd eich taliad TAW ar gyfer y cyfnod 22 Medi 2022 i 22 Medi 2023 yn ddyledus ar 16 Medi 2021.</p>
        <p class="govuk-body">Mae angen i chi dalu llog am dalu’n hwyr oherwydd ni chawsom eich taliad TAW yn llawn tan 26 Medi 2021.</p>
        <p class="govuk-body">Y swm o log sydd arnoch am dalu’n hwyr yw &#163;99999999.99.</p>
        <p class="govuk-body"><a href="https://www.gov.uk/" target="_blank">Darllenwch yr arweiniad am log am dalu’n hwyr (Yn agor tab newydd)</a></p><h2 class="govuk-heading-l govuk-!-margin-top-9">Os oes angen i chi gyflwyno’ch Ffurflen
        TAW</h2>
        <p class="govuk-body">Ewch ati i gyflwyno’ch Ffurflen TAW os nad ydych wedi gwneud hynny’n barod. Mae’n bosibl y bydd yn newid y swm sydd arnoch.</p>
        <p class="govuk-body">Ewch i GOV.UK i gael <a href="https://www.gov.uk/anfon-ffurflen-taw" target="_blank">help i lenwi’ch Ffurflen TAW (Yn agor tab newydd)</a></p><h2 class="govuk-heading-l govuk-!-margin-top-9">Sut i dalu</h2>
        <p class="govuk-body">Rydym yn cyfrifo’r llog am dalu’n hwyr o’r diwrnod ar ôl y dyddiad dyledus tan y diwrnod y byddwch yn talu’n llawn.</p>
        <p class="govuk-body">Mae cyfrifiadau llog wedi’u nodi ar eich tudalennau <a href="https://www.gov.uk/" target="_blank">wirio beth sydd arnoch a thalu ar-lein (Yn agor tab newydd)</a>.</p>
        <p class="message_time faded-text--small govuk-body-s">Cyfeirnod neges&#58; LPI1_CY 05/22 1.0</p>
        </div>
        </div>
        </section>
    }""".stripMargin

}
