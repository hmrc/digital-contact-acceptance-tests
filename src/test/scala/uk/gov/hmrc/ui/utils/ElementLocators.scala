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

package uk.gov.hmrc.ui.utils

object ElementLocators {

  val signIn                    = "#main-content > div > div > form > fieldset > button"
  val clickOnPaperlessAdminLink = "#main-content > div > div > div > ul > li > a"
  val clickOnMessageBrakeLink   = "#main-content > div > div > div > ul > li > b"
  val getRedirectUrlId          = "redirectionUrl"
  val getCredentialStrengthId   = "credentialStrength"
  val getConfidenceLevelId      = "confidenceLevel"
  val getNinoId                 = "nino"
  val onlineRadioButtonId       = "sps-opt-in"
  val postRadioButtonId         = "sps-opt-in-2"
  val getEmailTextFieldId       = "sps-opt-in-email"
  val saUtrRadioButtonId        = "name"
  val ninoRadioButtonId         = "name-2"
  val ItsaIdRadioButtonId       = "name-3"
  val emailIdRadioButtonId      = "name-4"
  val identifierValueTextId     = "value"
  val optUserOutLinkCssSelector = "#main-content > div > div > details > summary > span"
  val optOutUserReasonTextId    = "reason"
  val yesButtonOnSummaryPage    = "#confirm > form > div.govuk-button-group > button"
  val searchButtonOnSearchPage  = "#main-content > div > div > form > button"
  val uniqueReferenceId         = "externalRef.id"
  val messageSourceId           = "externalRef.source"
  val taxIdentifierNameId       = "recipient.taxIdentifier.name"
  val taxIdentifierValueId      = "recipient.taxIdentifier.value"
  val regimeId                  = "recipient.regime"
  val messageTypeId             = "messageType"
  val alertQueueId              = "alertQueue"
  val englishSubjectId          = "english-subject"
  val welshSubjectId            = "english-subject"
  val englishMessageId          = "english-message-content"
  val welshMessageId            = "welsh-message-content"
  val validFromId               = "validFrom"
  val formIdId                  = "details.formId"
  val issueDateId               = "details.issueDate"
  val batchIdId                 = "details.batchId"
  val sourceDataId              = "details.sourceData"

}
