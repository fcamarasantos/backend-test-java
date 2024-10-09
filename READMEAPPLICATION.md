### Tecnologias usadas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [Spring Doc Open API](https://springdoc.org/)
- [Lombok](https://projectlombok.org/)
- [Maven](https://maven.apache.org/)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Mockito](https://site.mockito.org/)
- [JUnit](https://junit.org/junit5/)


### Chamadas

- **Estabelecimento:** CRUD
  - **GET** /estabelecimento/
  - **GET** /estabelecimento/{id}
  - **POST** /estabelecimento/
  - **PUT** /estabelecimento/{id}
  - **DELETE** /estabelecimento/{id}

- **Veículos:** CRUD
  - **GET** /veiculos/
  - **GET** /veiculos/{id}
  - **POST** /veiculos/
  - **PUT** /veiculos/{id}
  - **DELETE** /veiculos/{id}

- **Controle de entrada e saída de veículos**
  - **GET** /entrada-saida/vagas-disponiveis
  - **POST** /entrada-saida/entrada
  - **POST** /entrada-saida/saida

### Documentacao via Swagger
  
Adicionei um redirecinamento no endpoint padrao "/" que redireciona para o swagger diretamente

- **Swagger:** http://localhost:8080/v3/api-docs

### Como rodar o projeto

1. Clone o projeto
2. Entre na pasta do projeto
3. Execute o comando `docker-compose up --build` para que ele suba uma instancia do postgres
4. Execute o comando `mvn clean install` para que ele baixe as dependencias e gere o jar
5. Execute a aplicacao na IDE que desejar ou execute o comando `java -jar target/{nome-do-jar}.jar` para rodar o jar gerado

### Observações

- Criei uma pasta `postman` caso deseje ultizar o postman para testar as chamadas
- Criei uma pasta `sampledata` caso queria alimentar o banco com alguns scripts python [sim eu sei que poderia ser escrito na migration do flyway xD]