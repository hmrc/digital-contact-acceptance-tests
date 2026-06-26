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

object EmaMessageHtmlContent {
  val EmaHtmlContent =
    s"""{
           |<section lang="en" Subject="Late Payment Interest Due"
           |<div class="govuk-grid-row grid-row">
           |<div class="govuk-grid-column-two-thirds column-two-thirds"><h2 class="govuk-heading-l govuk-!-margin-top-9">You owe late payment interest on your VAT</h2>
           |<p class="govuk-body">Your VAT payment for the period 01 May 2023 to 31 May 2023 was due on 07 July 2023.</p>
           |<p class="govuk-body">You need to pay late payment interest because we did not receive your VAT payment in full until 14 September 2023.</p>
           |<p class="govuk-body">The amount you owe in late payment interest is &#163;132.32.</p>
           |<p class="govuk-body"><a href="https://www.gov.uk/" target="_blank">Read the guidance about late payment interest (Opens in a new tab)</a></p><h2 class="govuk-heading-l govuk-!-margin-top-9">If you need to submit your VAT
           |Return</h2>
           |<p class="govuk-body">Submit your VAT Return if you have not already. It might change the amount you owe.</p>
           |<p class="govuk-body">Go to GOV.UK for <a href="http://www.gov.uk/vat-returns/fill-in-your-return" target="_blank">help with filling in your VAT Return (Opens in a new tab)</a></p><h2 class="govuk-heading-l govuk-!-margin-top-9">How to
           |pay</h2>
           |<h3 class="govuk-heading-m heading-medium">Pay online</h3>
           |<p class="govuk-body">You can <a href="www.gov.uk/' + '" target="_blank">check what you owe and pay online (Opens in a new tab)</a> if you have not paid yet.</p>
           |<h3 class="govuk-heading-m heading-medium">Other ways to pay</h3>
           |<p class="govuk-body">If you pay using one of these payment methods, you’ll need to quote the charge reference number XS002920013825.</p>
           |<h3 class="govuk-heading-m heading-medium">Online or telephone bank transfer</h3>
           |<p class="govuk-body">You can pay HM Revenue and Customs (HMRC) by Faster Payments, CHAPS or Bacs.</p>
           |<p class="govuk-body">Sort code: 08 32 00<br>
           |Account number: 11963155<br>
           |Account name: HMRC VAT</p>
           |<h3 class="govuk-heading-m heading-medium">Overseas payments</h3>
           |<p class="govuk-body">Account number (IBAN): GB36BARC20051773152391<br>
           |Bank identifier code (BIC): BARCGB22<br>
           |Account name: HMRC VAT</p>
           |<h3 class="govuk-heading-m heading-medium">Debit or corporate credit card</h3>
           |<p class="govuk-body"><a href="https://www.gov.uk/pay-tax-debit-credit-card" target="_blank">Follow the instructions on GOV.UK (Opens in a new tab)</a></p>
           |<h2 class="govuk-heading-l govuk-!-margin-top-9">If you cannot pay now</h2>
           |<p class="govuk-body">Call 0300 200 3835 to discuss your payment options with one of our customer support advisers. You might be able to set up a payment plan so you can pay in instalments.</p>
           |<p class="govuk-body">You’ll need details of your income and expenditure when you call, so we can make sure your payments are affordable.</p><h2 class="govuk-heading-l govuk-!-margin-top-9">More about late payment interest</h2>
           |<p class="govuk-body">We calculate late payment interest from the day after your payment was due until you pay in full.</p>
           |<p class="govuk-body">Interest calculations are shown on your <a href="www.gov.uk/' + '" target="_blank">check what you owe and pay online (Opens in a new tab)</a> pages.</p>
           |<h3 class="govuk-heading-m heading-medium">If you think the interest amount is wrong</h3>
           |<p class="govuk-body">We must charge interest on all late payments, by law. This means you cannot appeal against this interest charge.</p>
           |<p class="govuk-body">However, if you think we have applied your late payment interest incorrectly, you can <a href="https://www.gov.uk/" target="_blank">ask us to look at it again (Opens in a new tab)</a>.</p><h2 class="govuk-heading-l
           |govuk-!-margin-top-9">Need more support?</h2>
           |<p class="govuk-body">Tell us if you have health or personal circumstances that make it difficult for you to deal with HMRC. There are ways we can help.</p>
           |<p class="govuk-body">Go to GOV.UK for <a href="https://www.gov.uk/get-help-hmrc-extra-support" target="_blank">more information about the support we can offer (Opens in a new tab)</a>.</p>
           |<p class="govuk-body">If you’ve told us you have an authorised agent or representative for VAT, we’ve sent them this information.</p>
           |<p class="message_time faded-text--small govuk-body-s">Message reference&#58; LPI1 05/22 1.0</p>
           |</div>
           |</div>
           |</section>
           |<section lang="cy" Subject="Y llog sy’n ddyledus am dalu’n hwyr"
           |<div class="govuk-grid-row grid-row">
           |<div class="govuk-grid-column-two-thirds column-two-thirds"><h2 class="govuk-heading-l govuk-!-margin-top-9">Mae arnoch log am dalu’ch TAW yn hwyr</h2>
           |<p class="govuk-body">Roedd eich taliad TAW ar gyfer y cyfnod 22 Medi 2022 i 22 Medi 2023 yn ddyledus ar 16 Medi 2021.</p>
           |<p class="govuk-body">Mae angen i chi dalu llog am dalu’n hwyr oherwydd ni chawsom eich taliad TAW yn llawn tan 26 Medi 2021.</p>
           |<p class="govuk-body">Y swm o log sydd arnoch am dalu’n hwyr yw &#163;99999999.99.</p>
           |<p class="govuk-body"><a href="https://www.gov.uk/" target="_blank">Darllenwch yr arweiniad am log am dalu’n hwyr (Yn agor tab newydd)</a></p><h2 class="govuk-heading-l govuk-!-margin-top-9">Os oes angen i chi gyflwyno’ch Ffurflen
           |TAW</h2>
           |<p class="govuk-body">Ewch ati i gyflwyno’ch Ffurflen TAW os nad ydych wedi gwneud hynny’n barod. Mae’n bosibl y bydd yn newid y swm sydd arnoch.</p>
           |<p class="govuk-body">Ewch i GOV.UK i gael <a href="https://www.gov.uk/anfon-ffurflen-taw" target="_blank">help i lenwi’ch Ffurflen TAW (Yn agor tab newydd)</a></p><h2 class="govuk-heading-l govuk-!-margin-top-9">Sut i dalu</h2>
           |<h3 class="govuk-heading-m heading-medium">Talu ar-lein</h3>
           |<p class="govuk-body">Gallwch <a href="https://www.gov.uk/" target="_blank">wirio beth sydd arnoch a thalu ar-lein (Yn agor tab newydd)</a> os nad ydych wedi talu eto.</p>
           |<h3 class="govuk-heading-m heading-medium">Dulliau eraill o dalu</h3>
           |<p class="govuk-body">Os ydych yn talu drwy ddefnyddio un o’r dulliau talu hyn, bydd angen i chi roi cyfeirnod y tâl, sef XX999999999999.</p>
           |<h4 class="govuk-heading-s heading-small">Trosglwyddiad banc ar-lein neu dros y ffôn</h4>
           |<p class="govuk-body">Gallwch dalu Cyllid a Thollau EF (CThEF) drwy ddefnyddio Taliadau Cyflymach, CHAPS neu Bacs.</p>
           |<p class="govuk-body">Cod didoli: 08 32 00<br>
           |Rhif y cyfrif: 11963155<br>
           |Enw’r cyfrif: HMRC VAT</p>
           |<h4 class="govuk-heading-s heading-small">Taliadau tramor</h4>
           |<p class="govuk-body">Rhif y cyfrif (IBAN): GB36BARC20051773152391<br>
           |Cod adnabod y banc (BIC): BARCGB22<br>
           |Enw’r cyfrif: HMRC VAT</p>
           |<h4 class="govuk-heading-s heading-small">Cerdyn debyd neu gerdyn credyd corfforaethol</h4>
           |<p class="govuk-body"><a href="https://www.gov.uk/taluch-treth-cerdyn-debyd-credyd" target="_blank">Dilynwch y cyfarwyddiadau ar GOV.UK (Yn agor tab newydd)</a></p>
           |<h2 class="govuk-heading-l govuk-!-margin-top-9">Os na allwch dalu nawr</h2>
           |<p class="govuk-body">Ffoniwch 0300 200 3705 i drafod eich opsiynau talu gydag un o’n hymgynghorwyr cymorth cwsmeriaid. Mae’n bosibl y byddwch yn gallu trefnu cynllun talu er mwyn talu fesul rhandaliad.</p>
           |<p class="govuk-body">Bydd angen manylion eich incwm a’ch gwariant wrth law pan fyddwch yn ein ffonio er mwyn i ni allu gwneud yn siŵr bod eich taliadau’n fforddiadwy.</p><h2 class="govuk-heading-l govuk-!-margin-top-9">Rhagor o
           |wybodaeth am log am dalu’n hwyr</h2>
           |<p class="govuk-body">Rydym yn cyfrifo’r llog am dalu’n hwyr o’r diwrnod ar ôl y dyddiad dyledus tan y diwrnod y byddwch yn talu’n llawn.</p>
           |<p class="govuk-body">Mae cyfrifiadau llog wedi’u nodi ar eich tudalennau <a href="https://www.gov.uk/" target="_blank">wirio beth sydd arnoch a thalu ar-lein (Yn agor tab newydd)</a>.</p>
           |<h3 class="govuk-heading-m heading-medium">Os ydych yn meddwl bod swm y llog yn anghywir</h3>
           |<p class="govuk-body">Yn ôl y gyfraith, mae’n rhaid i ni godi llog ar bob taliad hwyr. Mae hyn yn golygu na allwch apelio yn erbyn y tâl llog hwn.</p>
           |<p class="govuk-body">Fodd bynnag, os ydych chi o’r farn ein bod wedi codi llog arnoch am dalu’n hwyr mewn camgymeriad, gallwch <a href="https://www.gov.uk/" target="_blank">ofyn i ni edrych ar y penderfyniad eto (Yn agor tab
           |newydd)</a>.</p><h2 class="govuk-heading-l govuk-!-margin-top-9">Angen rhagor o gymorth?</h2>
           |<p class="govuk-body">Rhowch wybod i ni os oes amgylchiadau personol neu os yw’ch iechyd yn ei gwneud yn anodd i chi ddelio â CThEF. Gallwn helpu mewn sawl ffordd.</p>
           |<p class="govuk-body">Ewch i GOV.UK i gael <a href="https://www.gov.uk/cael-help-cthem-cymorth-ychwanegol" target="_blank">rhagor o wybodaeth am y cymorth y gallwn ei gynnig (Yn agor tab newydd)</a>.</p>
           |<p class="govuk-body">Os ydych wedi rhoi gwybod i ni fod gennych asiant awdurdodedig neu gynrychiolydd ar gyfer TAW, rydym wedi anfon copi o’r wybodaeth hon atynt.</p>
           |<p class="message_time faded-text--small govuk-body-s">Cyfeirnod neges&#58; LPI1_CY 05/22 1.0</p>
           |</div>
           |</div>
           |</section>
    }""".stripMargin

}
