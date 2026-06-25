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

object NIREF1EnMessageHtmlContent {
  val HtmlContentNiref1_SubjectEnglish = "National Insurance contributions - we may owe you a refund"
  val HtmlContentNiref1                =
    s"""{
      <section lang="en" Subject="National Insurance contributions - we may owe you a refund"
      <div class="govuk-grid-row grid-row">
      <div class="govuk-grid-column-two-thirds column-two-thirds">
      <h2 class="govuk-heading-l">National Insurance contributions - we may owe you a refund</h2>
      <p class="govuk-body">We need to check details of your National Insurance contributions (NICs) for the tax year 6	&#160;April	&#160;2000 to 5	&#160;April	&#160;2001. You may have paid too much NICs. If so, you can
      claim a refund.</p>
      <p class="govuk-body">To check whether you&#39;re due a refund we need you to send us some information.</p>
      <h2 class="govuk-heading-m">What you need to do</h2>
      <p class="govuk-body">You’ll need to <a href="https://www.gov.uk/guidance/apply-for-refund-of-class-1-national-insurance-contributions" target="_blank">apply for a refund of Class 1 National Insurance contributions (opens in a new tab)</a>,
      telling us about your employment and NICs. You can ask a friend or family member to help you with this. You&#39;ll need:</p>
      <p class="govuk-body"><ul class="list list-bullet govuk-list govuk-list--bullet">
      <li>your HMRC sign in details  (if you don&#39;t have these, you can create them when you start your application)</li>
      <li> your claim reference number: Refunds 12</li>
      <li> details of the bank account you want the refund paid to.</li>
      </ul></p>
      <h2 class="govuk-heading-m">What will happen next</h2>
      <p class="govuk-body">We&#39;ll then check your account and post a message in your HMRC online account to let you know the outcome. If a refund is due, we&#39;ll pay it into your bank account provided or send you a cheque.</p>
      <p class="govuk-body">You&#39;ll also be able to see any messages in the &#39;Communication&#39; section of the HMRC app.</p>
      <p class="govuk-body">If you want to receive alerts about any new letters or messages, <a href="https://www.gov.uk/guidance/go-paperless-for-tax-code-updates" target="_blank">you can set your contact preferences in your HMRC account
      (opens in a new tab)</a>, or the &#39;notifications&#39; settings on your phone for the HMRC app.</p>
      <h2 class="govuk-heading-m">Watch out for scams</h2>
      <p class="govuk-body">Criminals use letters, emails, phone calls and texts to try to steal information and money from you. Before sharing your personal or financial details, use our website to check the sender or caller is genuine search -
      &#39;Check a list of genuine HMRC contacts&#39; on GOV.UK.</p>
      <p class="govuk-body">Never share your HMRC sign-in details. Someone could use them to steal from you or claim benefits or a refund in your name.</p>
      <p class="message_time faded-text--small govuk-body-s">&#58; CA4361 01/26 1.0</p>
      </div>
      </div>
      </section>
    }""".stripMargin

}
