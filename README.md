# Сравнение скорости 

Запускаем докер

1. В терминале поочередно выполняем команды:

`docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped -v mysql:/var/lib/mysql mysql:8`

`docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest`

2. Разворачиваем дамп из файла: dump-hibernate-final.sql

3. выполнить код из Main класса.


