FROM mysql:latest

ENV MYSQL_DATABASE mcda
COPY create_schema.sql data_schema.sql /docker-entrypoint-initdb.d/

