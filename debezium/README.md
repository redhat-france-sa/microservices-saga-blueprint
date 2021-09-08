### Debezium connector preparation

```shell
export DEBEZIUM_VERSION=1.6.2.Final
mkdir -p debezium-plugins && cd debezium-plugins
for PLUGIN in {mongodb,mysql,postgres}; do \                                                                                                                                                            ─╯
    curl https://repo.maven.apache.org/maven2/io/debezium/debezium-connector-$PLUGIN/$DEBEZIUM_VERSION/debezium-connector-$PLUGIN-$DEBEZIUM_VERSION-plugin.tar.gz | tar xz; \
done
docker build -f Dockerfile.debezium-connect -t quay.io/lbroudoux/debezium-connect:1.6.2.Final . && docker push quay.io/lbroudoux/debezium-connect:1.6.2.Final
```

```shell
oc new-app postgresql:13~https://github.com/redhat-france-sa/microservices-saga-blueprint.git \
  --name new-postgresql \
  --context-dir debezium/extending-image/ \
  -e POSTGRESQL_USER=user \
  -e POSTGRESQL_DATABASE=db \
  -e POSTGRESQL_PASSWORD=password
```