

# Order


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Unique identifier of order | 
**status** | [**StatusEnum**](#StatusEnum) | Status of Order | 
**customerId** | **String** |  | 
**productItems** | [**List&lt;ProductItem&gt;**](ProductItem.md) |  | 
**totalPrice** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  | 
**paymentCardId** | **String** |  | 
**shippingAddressId** | **String** |  | 



## Enum: StatusEnum

Name | Value
---- | -----
CREATED | &quot;CREATED&quot;
VALIDATED | &quot;VALIDATED&quot;
CANCELED | &quot;CANCELED&quot;
FAILED | &quot;FAILED&quot;



## Enum: CurrencyEnum

Name | Value
---- | -----
EUR | &quot;EUR&quot;
USD | &quot;USD&quot;



