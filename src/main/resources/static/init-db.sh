#!/bin/bash
# 等待 MySQL 服务启动
while ! mysqladmin ping -h"mysql" --silent; do
    sleep 1
done

# 导入数据
mysql -h db -u root -p${MYSQL_ROOT_PASSWORD} ${MYSQL_DATABASE} < /docker-entrypoint-initdb.d/chooeat_all.sql
