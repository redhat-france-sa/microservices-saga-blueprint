

# Payment


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Unique identifier of Payment | 
**status** | [**StatusEnum**](#StatusEnum) | Status of Payment | 
**paymentCardId** | **String** | Card identifier for payment | 
**amount** | **Double** | The amount to be charged | 
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  | 
**orderId** | **String** |  | 



## Enum: StatusEnum

Name | Value
---- | -----
ACCEPTED | &quot;ACCEPTED&quot;
DENIED | &quot;DENIED&quot;
CANCELED | &quot;CANCELED&quot;



## Enum: CurrencyEnum

Name | Value
---- | -----
EUR | &quot;EUR&quot;
USD | &quot;USD&quot;



