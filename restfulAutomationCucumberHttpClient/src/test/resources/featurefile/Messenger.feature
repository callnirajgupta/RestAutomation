@message @Regression
Feature: verify the message

@message1
Scenario: get all message
Given the Rest API messenger is up an runing
When user call "messages"service with method "Get" and argument ""
Then verify that response contain statuscode as "200"
And verify that service response contain all messages

@abc
Scenario: post new message
Given the Rest API messenger is up an runing
When user call "messages"service with method "Post" and new massage
Then verify that response contain statuscode as "201"
And verify that service response contain newly added message
When user call "messages"service with method "Get" and argument ""
And verify that service response contain all messages





