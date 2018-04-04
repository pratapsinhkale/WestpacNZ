Feature: KiwiSaver Retirement Calculator 

  @Req1
  Scenario: To validate help icons are present for all input fields
    Given user launches browser "chrome"
    And user navigates to url "url"
    And user hovers on menu "KiwiSaver"
    And user clicks on the button "KiwiSaver calculators"
    And user clicks on the button "Click here to get started."
    And user validates information icon for "Current age" with info text "msg"

  @Req2
  Scenario: To check the kiwisaver projected balance at retirement
    Given user launches browser "chrome"
    And user navigates to url "url"
    And user hovers on menu "KiwiSaver"
    And user clicks on the button "KiwiSaver calculators"
    And user clicks on the button "Click here to get started."
    And user enters value "30" into "Current age"
    And user selects value "Employed" from dropdown "Employment status"
    And user enters value "82000" into "Salary or wages per year (before tax)"
    And user select radio option "4%" for "KiwiSaver member contribution"
    And user selects value "17.5%" from dropdown "Prescribed investor rate (PIR)"
    And user select radio option "High" for "Risk profile"
    And user clicks on the button "View your KiwiSaver retirement projections"
    Then user can see the projected amount at retirement
    
  @Req3
  Scenario: To check the kiwisaver projected balance at retirement
  	Given user launches browser "chrome"
    And user navigates to url "url"
    And user hovers on menu "KiwiSaver"
    And user clicks on the button "KiwiSaver calculators"
    And user clicks on the button "Click here to get started."
    And user enters value "45" into "Current age"
    And user selects value "Self-employed" from dropdown "Employment status"
    And user selects value "10.5%" from dropdown "Prescribed investor rate (PIR)"
    And user enters value "100000" into "Current KiwiSaver balance"
    And user enters value "90" into "Voluntary contributions"
    And user selects value "Fortnightly" from dropdown "Voluntary contributions"
  	And user select radio option "Medium" for "Risk profile"
  	And user enters value "290000" into "Savings goal at retirement"
  	And user clicks on the button "View your KiwiSaver retirement projections"
  	Then user can see the projected amount at retirement
  	
  @Req4
    Scenario Outline: To check the kiwisaver projected balance at retirement
  	Given user launches browser "chrome"
    And user navigates to url "url"
    And user hovers on menu "KiwiSaver"
    And user clicks on the button "KiwiSaver calculators"
    And user clicks on the button "Click here to get started."
    And user enters value "<Age>" into "Current age"
    And user selects value "<EmpStatus>" from dropdown "Employment status"
    And user selects value "<PIR>" from dropdown "Prescribed investor rate (PIR)"
    And user enters value "<Balance>" into "Current KiwiSaver balance"
    And user enters value "<VolContri>" into "Voluntary contributions"
    And user selects value "<VolContriPeriod>" from dropdown "Voluntary contributions"
  	And user select radio option "<Risk>" for "Risk profile"
  	And user enters value "<SavingsGoal>" into "Savings goal at retirement"
  	And user clicks on the button "View your KiwiSaver retirement projections"
  	Then user can see the projected amount at retirement
  	Examples:
  		|Age |  EmpStatus    |  PIR  |  Balance  | VolContri  | VolContriPeriod  |  Risk  |  SavingsGoal  |
  		 
  		|45  | Self-employed | 10.5% | 100000    | 90         | Fortnightly      | Medium | 290000        |
  				
  		|55  | Not employed  | 10.5% | 140000    | 10         | Annually         | Medium | 200000        |
    
    
    