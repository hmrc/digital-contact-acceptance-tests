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

package uk.gov.hmrc.ui

object ElementLocators {

  val signIn                                                  = "#main-content > div > div > form > fieldset > button"
  val clickOnPaperlessAdminLink                               = "#main-content > div > div > div > ul > li > a"
  val clickOnMessageBrakeLink                                 = "#main-content > div > div > div > ul > li:nth-child(2) > a"
  val clickOnMessageBrakeAllowlistLink                        = "#main-content > div > div > div > ul > li:nth-child(3) > a"
  val clickOnAddNewFormButtonId                               = "#main-content > div > div > form > div > button"
  val clickOnDeleteFormButtonId                               = "#main-content > div > div > div:nth-child(8) > form > button"
  val clickOnMessageDecode                                    = "#main-content > div > div > div > ul > li:nth-child(4) > a"
  val reOptinPageHeader                                       = "#main-content > div > div > header > h1"
  val btaHomePageContactPreferenceText                        = "#main-content > div > div.govuk-grid-column-two-thirds > div > div:nth-child(2) > span"
  val btaHomePageHeader                                       = "#main-content > div > div.govuk-grid-column-two-thirds > h1"
  val changeEmailAddressPageHeader                            = "#main-content > div > div > header > h1"
  val cysPageHeader                                           = "#saCheckSettings"
  val cysPageTaxDocument                                      = "#main-content > div > div > dl > div:nth-child(1) > dd.govuk-summary-list__value"
  val cysPageEmailSentsIn                                     = "#main-content > div > div > dl > div.govuk-summary-list__row.govuk-summary-list__row--no-actions > dd"
  val cysPageEmailSentsInVerified                             = "#main-content > div > div > dl:nth-child(3) > div:nth-child(2) > dd.govuk-summary-list__value"
  val cysPageChangeEmailAddressLink                           = "#main-content > div > div > dl > div:nth-child(2) > dd.govuk-summary-list__actions > a"
  val cysPageChangeEmailAddressVerifiedLink                   = "#main-content > div > div > dl:nth-child(5) > div > dd.govuk-summary-list__actions > a"
  val cysPageContinueButton                                   = "#main-content > div > div > a"
  val cysPageEmailSentInChangeLink                            = "#main-content > div > div > dl:nth-child(3) > div:nth-child(2) > dd.govuk-summary-list__actions > a"
  val cysPageFixthisLink                                      = "#main-content > div > div > dl > div:nth-child(2) > dd.govuk-summary-list__value > p > a"
  val cysPageTaxDocumentsChangeLink                           = "#main-content > div > div > dl:nth-child(3) > div:nth-child(1) > dd.govuk-summary-list__actions > a"
  val chooseLanguagePageHeader                                = "#form-submit-language > div > fieldset > legend > h1"
  val chooseLanguagePageWelsh                                 = "#lang-2"
  val chooseLanguagePageEnglish                               = "#lang"
  val chooseLanguagePageContinueButton                        = "#form-submit-language > button"
  val confirmGettingTaxLettersPageHeader                      = "#main-content > div > div > header > h1"
  val confirmGettingTaxLettersPageByPost                      = "#confirm-opt-out"
  val confirmGettingTaxLettersPageBOnline                     = "#cancel-link"
  val emailPageHeader                                         = "#form-submit-email-address > div > label > h1"
  val emailPageHeader2                                        = "#form-submit-email-address > fieldset > legend > h1"
  val optOutPageHeader                                        = "#form-submit-email-address > div > h1"
  val optOutSurveyPageHeader                                  = "#main-content > div > div > header > h1"
  val optOutSurveyReason                                      = "#reason"
  val optOutSurveyContinueButton                              = "#submitSurveyButton"
  val optOutSurveySkipButton                                  = "#skipSurveyButton"
  val ptaHomePageHeader                                       = "#main-content > div > div.govuk-grid-column-two-thirds > h1"
  val ptaHomePageUnreadNotificationLink                       = "#main-content > div > div.govuk-grid-column-one-third > p"
  val ptaHomePageGotoMessageLink                              = "#main-content > div > div.govuk-grid-column-one-third > a"
  val ptaHomePageToCYSLink                                    = "#main-content > div > div.govuk-grid-column-two-thirds > div > a"
  val ptaInboxPageMessageSubject                              = "#sa-messages-table > tbody > tr > td:nth-child(2)"
  val reOptInPageHeader                                       = "#main-content > div > div > header > h1"
  val reOptInPageSubmitEmailFormHeader                        = "#form-submit-email-address > div > fieldset > legend > h1"
  val toubleSendingEmailPageEnterAddress                      = "#main-content > div > div > div.govuk-button-group > a"
  val toubleSendingEmailPageNotWantOnlineLetters              = "#main-content > div > div > p:nth-child(6) > a"
  val verifyEmailAddressPageContinue                          = "#main-content > div > div > div:nth-child(5) > a"
  val verifyEmailAddressPageSendLinkAgain                     = "#main-content > div > div > div > a"
  val verifyEmailAddressUseDifferentEmail                     = "#main-content > div > div > div > p > a"
  val verifyNewEmailPageContinueButton                        = "#return-to-dashboard-button"
  val GettaxlettersonlineId                                   = "Gettaxlettersonline"
  val FixthisId                                               = "Fixthis"
  val ReviewupdatedtermsId                                    = "Reviewupdatedterms"
  val submitEmailButtonId                                     = "submitEmailButton"
  val spsReOptInId                                            = "sps-re-opt-in"
  val spsReOptInIdEmailId                                     = "sps-re-opt-in-email"
  val spsReOptIn2Id                                           = "sps-re-opt-in-2"
  val emailMainId                                             = "email.main"
  val emailConfirmId                                          ="email.confirm"
  val submitChangedEmailButtonId                              = "submit-email-button"
  val demoFrontEndInboxFirstMessageSubject                    = "#sa-messages-table > tbody > tr > td:nth-child(2) > div > span > a > span > span"
  val demoFrontEndInboxIossMessageSubject                     = "#sa-messages-table > tbody > tr > td:nth-child(2) > a > span"
  val pageHeader1                                             = "#main-content > div > div > h1"
  val pageHeader2                                             = "#main-content > div > div > h2"
  val pageLanguageEnglish                                     = "body > header > section > div > nav > ul > li:nth-child(1) > a"
  val pageLanguageWelsh                                       = "body > header > section > div > nav > ul > li:nth-child(2) > a"
  val pageBackLink                                            = "body > div > div > div:nth-child(1) > div > a"
  val sendMessageResponse                                     = "#main-content > div > div > div.data > p > span"
  val cdsMessagePageHeader                                    = "#main-content > div > div > div > div > h1"
  val cdsMessagePageFirstMessageSubject                       = "#message-0 > span.govuk-\\!-font-weight-bold.black-text"

  

}