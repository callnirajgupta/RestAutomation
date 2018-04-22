@CityTem @Regression
Feature: Mandatory field validation of AbstractAgreement page

Scenario Outline: get city temperature
Given the Rest API messenger is up an runing
When user call "messages"service with method "Get" and argument ""
Then verify that response contain statuscode as "200"
And verify that service response contain the following attribute


