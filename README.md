# Mini Autorizador #


## Aplicação - Overview ##

Esta aplicação foi desenvolvida na linguagem Java, utilizando [Spring Boot](https://spring.io/projects/spring-boot) e seus recursos, com arquitetura RESTful. Disponibilizando serviços de criação de um cartão com saldo incial fixo, recuperação de saldo e transações, ou seja, o débito de valores no saldo de um dado cartão persistido em um banco [MySql](https://www.mysql.com/).

## Testes ##

  Para auxiliar na criação dos serviços e garantir os resultados esperados e propostos, foram desenvolvidos testes unitários para todos os serviços com os cenários de sucesso e erros utilizando o [Spring Boot Test](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html) com MockMvc.

## Versionamento de código ##

  Para desenvolvimento de funcionalidades e geração de releases, utilizei o [Gitflow](https://github.com/nvie/gitflow), que é um modelo de controle de branches que suporta todo o ciclo de vida de uma entrega: desenvolvimento, homologação e release.

##  Build e deploy ##

* **Build** Este projeto contém um arquivo pom.xml, utilizado para descrever suas dependências e fazer o build via [Maven](https://maven.apache.org/).
  Existem 2 perfis configurados, para suportar diferentes ambientes de execução: **local**, para desenvolvimento local, **deploy-container** para publicação em containers.
  
* **Deploy** Esta aplicação pode ser publicada em formato jar, para execução local e em um container, que contém o jar em um ambiente isolado de execução.

##  Instalação e execução ##

### Pré requisitos ###
   
   *  Java17+
   *  Docker
   *  Docker-compose
   *  Maven

   
### Executando 
  
  - Executando em containers:
  	
Clone ou faça download do código desta aplicação e execute a partir da pasta raíz do projeto:
	
	mvn install -DskipTests -Dspring.profiles.active=deploy-container
	
Inicie e execute os containers da imagem do MySql e da aplicação com o seguinte comando:
  	 
	docker-compose up --build --force-recreate
	 
  - Executando localmente:
  		
Comente dentro do arquivo docker-compose.yml na pasta raiz do projeto, o service miniautorizador, e execute:
	
	docker-compose up --build --force-recreate

Execute a partir da pasta raiz do projeto:
	
	mvn install -Dspring.profiles.active=local

Dentro da pasta target na pasta raiz do projeto execute:
	
	java -jar -Dspring.profiles.active=local mini-autorizador-0.0.1-SNAPSHOT.jar


##  Testando ##

- Os testes podem ser realizados via ferramentas API-Clients como o [Insomnia](https://insomnia.rest/download) e [Postman](https://www.postman.com/), porém também foi configurado para o projeto a ferramenta [Swagger](https://swagger.io/), que expõe tanto a documentação da API, quanto permite a execução dos serviços.
 Para utilizar o Swagger, basta colar a seguinte URL em seu navegador de preferência, após subir a aplicação conforme descrito na seção anterior deste documento ou clicar [aqui](http://localhost:8080/api/swagger-ui/index.html#/):
		
	http://localhost:8080/api/swagger-ui/index.html#/

# Tecnologias ##

-  [Spring Boot](https://spring.io/projects/spring-boot)
-  [Docker](https://www.docker.com/)
-  [Gitflow](https://github.com/nvie/gitflow)
-  [Maven](https://maven.apache.org/)