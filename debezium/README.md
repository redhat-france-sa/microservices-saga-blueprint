### PostgreSQL database with write ahead logs preparation

```shell
oc new-build openshift/postgresql:12-el8~https://github.com/redhat-france-sa/microservices-saga-blueprint.git \
  --name debezium-postgresql \
  --context-dir debezium/extended-postgresql/
```

### Debezium connector preparation

```shell
cd ../util/expand-json-smt
mvn package
cp ../util/expand-json-smt/target/kafka-connect-expand-json-payload-smt-0.0.1-assemble-all.jar ./kafka-connect-expand-json-payload-smt-0.0.1-assemble-all.jar
```

```shell
cd ../../debezium
export DEBEZIUM_VERSION=1.6.2.Final
mkdir -p debezium-plugins && cd debezium-plugins
for PLUGIN in {mongodb,mysql,postgres}; do \                                                                                                                                                            ─╯
    curl https://repo.maven.apache.org/maven2/io/debezium/debezium-connector-$PLUGIN/$DEBEZIUM_VERSION/debezium-connector-$PLUGIN-$DEBEZIUM_VERSION-plugin.tar.gz | tar xz; \
done
docker build -f Dockerfile.debezium-connect -t quay.io/lbroudoux/debezium-connect:1.6.2.Final . && docker push quay.io/lbroudoux/debezium-connect:1.6.2.Final
```

### Debezium connector troubleshooting

```shell
oc exec -i $(oc get pods --field-selector=status.phase==Running | grep order-database-connect | awk '{print $1}') -- curl -X POST \
-H "Accept:application/json" \
-H "Content-Type:application/json" \
http://localhost:8083/admin/loggers/io.debezium.connector.postgresql -d '{"level": "INFO"}'


curl -s -X PUT -H "Content-Type:application/json" http://localhost:8083/admin/loggers/io.debezium.connector.postgresql -d '{"level": "INFO"}'
```