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

object MessageHtmlContent {
  val p800HtmlContent =
    s"""{
       |
 |<div class="govuk-grid-row grid-row">
       |<div class="govuk-grid-column-two-thirds column-two-thirds">
       |
 |<!--
       |<h1 class="govuk-heading-xl heading-xlarge">Tax calculation for the year 6 April 2019 to 5 April 2020. You have paid too little tax. </h1>
       |<p class="message_time faded-text--small">This message was sent to you on 10 September 2020</p>
       |  <br>
       |  <p class="govuk-body">Dear Mr Sample</p>
       |
       |  <p class="govuk-body">National Insurance number J* ** ** 00 A</p>
       |-->
       |
 |
       |
 |  <h2 class="govuk-heading-l heading-large">You have paid too little tax.</h2>
       |
       |  <div class="alert alert--info alert--info__light">
       |    <p class="alert__message">You owe HMRC £176.80.</p>
       |</div>
       |
       |
 |  <h3 class="govuk-heading-m heading-medium">What happens next</h3>
       |  <p class="govuk-body">We will automatically collect the tax by increasing the tax deducted from your wages, salary or pension, usually in equal instalments for 12 months from next April. Or, you can <a href"http://www.gov.uk/check-income-tax-last-year">check the tax you owe and pay it now online.</a></p>
       |
 |
 |  <h3 class="govuk-heading-m heading-medium">Why you have paid too little tax</h3>
       |  <p class="govuk-body">Each year we check everyone’s tax position to see if the correct amount of Income Tax was paid. We have now looked at the latest information we hold for this year and have worked out that you have paid too little tax. You can find a full calculation and explanation in this letter.</p>
       |
 |  <h3 class="govuk-heading-m heading-medium">You should check this calculation</h3>
       |  <p class="govuk-body">You must tell us if you think the information we hold is wrong or about any changes to your taxable income. You can <a href="https://www.gov.uk/tax-overpayments-and-underpayments" target="_blank">use the notes to help.</a></p>
       |
 |
 |  <h3 class="govuk-heading-m heading-medium">You don’t need to call us</h3>
       |  <p class="govuk-body">You can manage all your tax affairs through the Personal Tax Account you are signed into now. Use it to view more information, make sure you’re paying the right amount of tax, and tell us about in-year changes.</p>
       |
 |  <p class="govuk-body">You can also choose to <a href="https://www.tax.service.gov.uk/paperless/check-settings" target="_blank">sign up for online tax letters</a>, instead of getting paper letters. We’ll send you an email whenever something changes, and you’ll find all your tax letters saved online, here in your Personal Tax Account.</p>
       |
 |  <p class="govuk-body"><a href="https://www.gov.uk/tax-overpayments-and-underpayments" target="_blank">Find out more about tax overpayments and underpayments on GOV.UK</a></p>
       |
 |  <p class="govuk-body">You should show this tax calculation to your tax adviser if you have one, though you must make any online payments yourself. Your tax advisor cannot do this for you.</p>
       |
 |
 |  <details class="govuk-details subsection" data-module="govuk-details">
       |    <summary class="govuk-details__summary">
       |      <span class="govuk-details__summary-text">
       |
 |        Full tax calculation for MR AB SAMPLE
       |
 |      </span>
       |    </summary>
       |    <div class="govuk-details__text panel panel-border-wide">
       |
 |      <br>
       |      National Insurance Number J* ** ** 00 A <br>For the tax year 2019-2020
       |
 | <p class="govuk-body">National Insurance Number J* ** ** 00 A <br>For the tax year 2019-2020</p>
       |
 |<h2 class="govuk-heading-m heading-medium">Income</h2>
       | <div class="govuk-body govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |   <h3 class="govuk-heading-s heading-small">DHL AVIATION UK LTD</h3>
       |
 |   <p class="govuk-table__cell--numeric text--right push">&#163;38,634.18</p>
       |   <p class="govuk-table__cell--numeric text--right push">Income tax:&emsp;&#163;5,355.00</p>
       | </div>
       | <dl class="govuk-body">
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt>&ensp;&ensp;Medical insurance</dt>
       |     <dd class="govuk-table__cell--numeric text--right">&#163;652.00</dd>
       |   </div>
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt class="govuk-!-font-weight-bold bold ">Benefits in kind</dt>
       |     <dd class="govuk-table__cell--numeric text--right">&#163;873.00</dd>
       |     <dd class="govuk-table__cell--numeric text--right">Income tax:&emsp;&#163;0.00</dd>
       |   </div>
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt class="govuk-!-font-weight-bold bold">Total</dt>
       |     <dd class="govuk-!-font-weight-bold bold govuk-table__cell--numeric text--right">&#163;4,0159.18</dd>
       |     <dd class="govuk-!-font-weight-bold bold govuk-table__cell--numeric text--right">Income tax:&emsp;&#163;5355.00</dd>
       |   </div>
       | </dl>
       |
 | <h2 class="govuk-heading-m heading-medium">Less your allowances</h2>
       |
 | <dl class="govuk-body">
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt>Personal allowance (tapered if appropriate)</dt>
       |     <dd class="govuk-table__cell--numeric text--right">&#163;12,500.00</dd>
       |   </div>
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt>Total tax free amount</dt>
       |     <dd class="govuk-table__cell--numeric text--right">&#163;12,400.00</dd>
       |   </div>
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt class="govuk-!-font-weight-bold bold ">Your total taxable income</dt>
       |     <dd class="govuk-table__cell--numeric text--right bold">&#163;27,659.18</dd>
       |   </div>
       | </dl>
       |
 |<h2 class="govuk-heading-m heading-medium">Income Tax rate(s)</h2>
       |
 | <dl class="govuk-body">
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt>Basic rate at 20% on</dt>
       |     <dd class="govuk-table__cell--numeric text--right">&#163;27,659.00</dd>
       |	 <dd class="govuk-table__cell--numeric text--right">Income tax:&emsp;&#163;5,531.80</dd>
       |   </div>
       |
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt>Total</dt>
       |     <dd class="govuk-table__cell--numeric text--right bold">&#163;27,659.00</dd>
       |	  <dd class="govuk-!-font-weight-bold bold govuk-table__cell--numeric text--right">Income tax:&emsp;&#163;5,531.80</dd>
       |   </div>
       |
       |
       |   <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt class="govuk-!-font-weight-bold bold ">Your total taxable income</dt>
       |     <dd class="govuk-table__cell--numeric text--right bold">&#163;27,659.18</dd>
       |   </div>
       | </dl>
       |
 |<h2 class="govuk-heading-m heading-medium">Result</h2>
       |
 |<dl class="govuk-body">
       |
       |  <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt>Total Tax Payable</dt>
       |	 <dd class="govuk-table__cell--numeric text--right">Income tax:&emsp;&#163;5,531.80</dd>
       |   </div>
       |
       |
       |    <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt>Tax you've already paid</dt>
       |	 <dd class="govuk-table__cell--numeric text--right">Income tax:&emsp;&#163;5,355.00</dd>
       |   </div>
       |
       |     <div class="govuk-section-break govuk-section-break--visible govuk-section-break--m divider--bottom section">
       |     <dt class="govuk-!-font-weight-bold bold">You owe HMRC</dt>
       |	 <dd class="govuk-!-font-weight-bold bold govuk-table__cell--numeric text--right">Income tax:&emsp;&#163;176.80</dd>
       |   </div>
       |
       |
       |
       |
       | </dl>
       |
 |
 |
       |
 |    </div>
       |  </details>
 |</div>
       |
 |
    }""".stripMargin

}
