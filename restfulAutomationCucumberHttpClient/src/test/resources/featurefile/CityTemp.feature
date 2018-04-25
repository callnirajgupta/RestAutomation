@CityTem @Regression
Feature:verify city temperature

Scenario Outline: get city temperature
Given the Rest API City temperature is up an runing
When user call the service with argument city as "<City>"
Then verify that response contain statuscode as "200"
And verify that service response contain the following attribute
|City|
|Temperature|
|Humidity|
|WeatherDescription|
|WindSpeed|
|WindDirectionDegree|

Examples:
|City|
|Hyderabad|
|Bangalore|

