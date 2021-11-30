CREATE ROLE "kogito-user" WITH
    LOGIN
    SUPERUSER
    INHERIT
    CREATEDB
    CREATEROLE
    NOREPLICATION
    ENCRYPTED PASSWORD 'md54adb613a8ffdd707e032c918d791e2e5';

CREATE ROLE "saga-admin" WITH
LOGIN
SUPERUSER
INHERIT
CREATEDB
CREATEROLE
NOREPLICATION
PASSWORD 'pass';

CREATE DATABASE ordersdb
    WITH
    OWNER = "saga-admin"
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
CREATE DATABASE paymentsdb
    WITH
    OWNER = "saga-admin"
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
CREATE DATABASE stocksdb
    WITH
    OWNER = "saga-admin"
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- CREATE DATABASE kogito
--     WITH
--     OWNER = "kogito-user"
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'en_US.utf8'
--     LC_CTYPE = 'en_US.utf8'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;