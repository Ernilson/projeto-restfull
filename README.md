# crud-springboot-docker

Comandos Docker:

para iniciar um container que ja existe.(que contenha images)
 docker start NOME_CONTAINER (teste2)
ou docker stop e nome do servidor(teste2)
--------------------------------------------------------------
comando para ver as imagens no container
docker images
--------------------------------------------------------------
Renomear container
docker rename adoring_ardinghelli servidor-teste
----------------------------------------------------------------
Para Remover container
docker rm nome do container ou Id (após para o container) senão docker rm -f (para força)
----------------------------------------------------------------
Para Remover imagem
docker rmi e o Id da imagem
--------------------------------------------------------------
comando para visualizar o status do container
docker ps -a 
---------------------------------------------------------------
comando para entrar no container
docker attach nome do container ou Id
---------------------------------------------------------------
comando para ver os logs do container
docker container logs "nome do container" 
---------------------------------------------------------------
comando para sair do container
exit
-------------------------------------------------------------------
comando para mostrar atualizações ou tudo que foi mudado no container
docker diff nome do container ou Id
----------------------------------------------------------------------------
-------------------------comando para criar container--------------------------
docker run --name mybanco -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
-----------------------------------------------------------------------------
docker exec -it "nome do container" mysql -p
docker run -it "nome do container" mysql -p
------------------------------------------------------------------------------------
docker-compose up --build --force-recreate
------------------------------postgres------------------------------------------
docker exec -it postgres-0 bash
psql -U postgres
\du -> para super usuario
psql -h localhost -p 5432 -U postgres
---------------------------------------------------------------------------------
//comando para criar imagem 
docker build -t app .

// Comando para renomear e direcionar a porta na imagem mysql (já baixada) ao criar o container
docker run -p 3308:3306 --name db -e MYSQL_ROOT_PASSOWRD=root -e MYSQL_DATABASE=teste45 mysql

//comando para por aplicação num cona
docker run -p 9090:8080 --name app -e MYSQL_HOST=db -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_PORT=3306 app

//comando para rodar a aplicação na mesma rede que mysql
docker run -p 9090:8080 --name app --net segundo-network -e MYSQL_HOST=db -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_PORT=3306 app  

//Comando para criar uma network(rede) personalizada
 docker network create segundo-network

//Comando para conectar na rede o mysql
docker network connect segundo-network db

------------------------------------------Docker spring-boot mysql-----------------------------------------------------------
https://youtu.be/PAQvxqocb6A
--------------------------------------como enviar docker images para o dockerhub --------------------------------------------
https://www.youtube.com/watch?v=x1PlJRYIr0I
