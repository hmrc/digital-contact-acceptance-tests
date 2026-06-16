package uk.gov.hmrc.ui.specs.twsm.cdsDigital

import uk.gov.hmrc.ui.specs.BaseSpec
import uk.gov.hmrc.ui.utils.TestData

class cdsCustomerConversationPageSpec extends BaseSpec with TestData {

  Feature("Error Validations on Conversation Page") {

    Scenario("Customer see an error validation when an empty message field is entered"){
      Given("I navigate to secure message page and submitted With Customer Name")
      And("I navigate to messages list page using eori enrollment")
      When("I click on the subject link")
      clickOnSubjectCds()
      Then("I see below subject as")
      When("I click on Reply to this message link")
    }
  }

}
