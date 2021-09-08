# microservices-saga-blueprint
Architecture blueprint for demonstrating Saga with microservices

## Pre-requisites

```shell
sh-4.4$ psql
psql (10.17)
Type "help" for help.

postgres=# \l
List of databases
Name    |  Owner   | Encoding |  Collate   |   Ctype    |   Access privileges   
-----------+----------+----------+------------+------------+-----------------------
ordersdb  | userIUO  | UTF8     | en_US.utf8 | en_US.utf8 |
postgres  | postgres | UTF8     | en_US.utf8 | en_US.utf8 |
template0 | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
|          |          |            |            | postgres=CTc/postgres
template1 | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
|          |          |            |            | postgres=CTc/postgres
(4 rows)

postgres=# \c ordersdb
You are now connected to database "ordersdb" as user "postgres".
ordersdb=# \dn
List of schemas
Name  |  Owner   
--------+----------
public | postgres
(1 row)

ordersdb=# \dt
List of relations
Schema |     Name      | Type  |  Owner  
--------+---------------+-------+---------
public | orders        | table | userIUO
public | outboxevent   | table | userIUO
public | product_items | table | userIUO
```

## Deployment

### Order Service

```shell
oc project microsaga-order
oc new-app --template=postgresql-persistent --name=order-database -p DATABASE_SERVICE_NAME=order-database -p POSTGRESQL_DATABASE=ordersdb -e POSTGRESQL_ADMIN_PASSWORD=
oc label dc/order-database app.openshift.io/runtime=postgresql app.kubernetes.io/part-of=order-service-app --overwrite 
```

### Order Saga
