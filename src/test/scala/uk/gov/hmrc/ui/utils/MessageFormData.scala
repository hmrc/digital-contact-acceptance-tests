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

import uk.gov.hmrc.ui.pages.messages.GmcMessages.{alertQueueValue, batchIdValue, email, englishContentValue, englishSubjectValue, identifierValue, messageTypeValue, ninoNumber1, referenceIdValue, regimeSaValue, sourceDataValue, sourceValue, taxIdentifierNameSautrValue, taxIdentifierNameValue, validFromValue}

case class MessageFormData(
                            externalRef: MessageFormData.ExternalRef,
                            recipient: MessageFormData.Recipient,
                            regime: String,
                            messageType: String,
                            subject: String,
                            content: String,
                            validFrom: String,
                            alertQueue: String,
                            details: MessageFormData.Details
                          )

object MessageFormData {
  case class ExternalRef(
                          id: String,
                          source: String
                        )

  case class TaxIdentifier(
                            name: String,
                            value: String
                          )

  case class RecipientName(
                            line1: String
                          )

  case class Recipient(
                        taxIdentifier: TaxIdentifier,
                        name: RecipientName,
                        email: String
                      )

  case class Details(
                      formId: String,
                      issueDate: String,
                      batchId: String,
                      sourceData: String
                    )

  // Update and return a new FormData
  def update(
              formData: MessageFormData,
              externalRefId: Option[String] = None,
              externalRefSource: Option[String] = None,
              identifierName: Option[String] = None,
              identifierValue: Option[String] = None,
              nameLine1: Option[String] = None,
              email: Option[String] = None,
              formId: Option[String] = None,
              issueDate: Option[String] = None,
              batchId: Option[String] = None,
              sourceData: Option[String] = None,
              subject: Option[String] = None,
              content: Option[String] = None,
              regime: Option[String] = None,
              validFrom: Option[String] = None,
              alertQueue: Option[String] = None
            ): MessageFormData = {
    formData.copy(
      externalRef = formData.externalRef.copy(
        id = externalRefId.getOrElse(formData.externalRef.id),
        source = externalRefSource.getOrElse(formData.externalRef.source)
      ),
      recipient = formData.recipient.copy(
        taxIdentifier = formData.recipient.taxIdentifier.copy(
          name = identifierName.getOrElse(formData.recipient.taxIdentifier.name),
          value = identifierValue.getOrElse(formData.recipient.taxIdentifier.value)
        ),
        name = formData.recipient.name.copy(
          line1 = nameLine1.getOrElse(formData.recipient.name.line1)
        ),
        email = email.getOrElse(formData.recipient.email)
      ),
      regime = regime.getOrElse(formData.regime),
      messageType = formData.messageType,
      subject = subject.getOrElse(formData.subject),
      content = content.getOrElse(formData.content),
      validFrom = validFrom.getOrElse(formData.validFrom),
      alertQueue = validFrom.getOrElse(formData.alertQueue),
      details = formData.details.copy(
        formId = formId.getOrElse(formData.details.formId),
        issueDate = issueDate.getOrElse(formData.details.issueDate),
        batchId = batchId.getOrElse(formData.details.batchId),
        sourceData = sourceData.getOrElse(formData.details.sourceData)
      )
    )
  }

  val default: MessageFormData = MessageFormData(
    externalRef = ExternalRef(
      id = referenceIdValue,
      source = sourceValue
    ),
    recipient = Recipient(
      taxIdentifier = TaxIdentifier(
        name = taxIdentifierNameSautrValue,
        value = identifierValue
      ),
      name = RecipientName(
        line1 = "P800"
      ),
      email = email),
    regime = regimeSaValue,
    messageType = messageTypeValue,
    subject = englishSubjectValue,
    content = englishContentValue,
    validFrom = validFromValue,
    alertQueue = alertQueueValue,
    details = Details(
      formId = "P800 2021",
      issueDate = validFromValue,
      batchId = batchIdValue,
      sourceData = sourceDataValue
    )
  )
}