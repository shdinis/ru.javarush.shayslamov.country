
# Speed Comparison Mysql and Redis

Launching docker

1. In the terminal, execute the following commands one by one:

`docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped -v mysql:/var/lib/mysql mysql:8`

`docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest`


2. Expand the dump

3. Run the code from the Main class.
