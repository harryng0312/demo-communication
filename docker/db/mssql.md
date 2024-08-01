### 1. MS SQL
```shell
$ docker run \
    -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=123456" \
    -p 1433:1433 --name sqlsrv1 --hostname sqlsrv1 \
    -d mcr.microsoft.com/mssql/server
```
```shell
```