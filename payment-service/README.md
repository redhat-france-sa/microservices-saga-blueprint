# payment-service Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/order-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Related Guides

- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more


## Building and pushing container image

With Jib:

```shell
./mvnw package -Dquarkus.container-image.build=true -Dquarkus.container-image.image=quay.io/lbroudoux/microsaga-payment-service:1.0.0-SNAPSHOT -Dquarkus.container-image.push=true
```

## Testing locally

```shell
$ curl -X POST http://localhost:8080/api/payment -H 'Content-type: application/json' -d '{"orderId":"123456", "amount":10.0, "currency":"EUR", "paymentCardId":"5138"}'
{"id":"fd7b9654-5bf4-4a29-8b76-4d36267bc162","status":"ACCEPTED","paymentCardId":"5138","amount":10.0,"currency":"EUR","orderId":"123456"}

$ curl -X GET http://localhost:8080/api/payment/fd7b9654-5bf4-4a29-8b76-4d36267bc162
{"id":"fd7b9654-5bf4-4a29-8b76-4d36267bc162","status":"ACCEPTED","paymentCardId":"5138","amount":10.0,"currency":"EUR","orderId":"123456"}

curl -X PUT http://localhost:8080/api/payment/fd7b9654-5bf4-4a29-8b76-4d36267bc162/cancel
{"id":"fd7b9654-5bf4-4a29-8b76-4d36267bc162","status":"CANCELED","paymentCardId":"5138","amount":10.0,"currency":"EUR","orderId":"123456"
```