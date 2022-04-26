1.docker pull mongo:4.4.13

2.docker run -d --name mongodb -p 27017:27017 -v /mydata/mongodb:/data/db -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root --privileged=true --restart always mongo:4.4.13

3.docker logs mongodb

4.docker exec -it mongodb bash

5.docker exec -it mongodb mongo iot

6.use admin

7.db.auth("root","root")

8.db.createUser({user:"admin",pwd:"admin",roles:[{role:"dbOwner",db:"test"}]})

9 use test

10.db.test.insertOne({name:"lijiale",age:"22"})

11.db.test.find()

在navicat中通过验证数据库admin的方式输入root，root帐户进行远程连接

