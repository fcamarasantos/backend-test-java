## Projeto Spring Boot - Sistema de Gestão de Estacionamento
API REST para gerecenciamento de estacionamento de veículos (carros e motos), com funcionalidades de cadastro de estabelecimentos, veículos e controle de entrada e saída.
Também foi adicionada funcionalidades de autenticação básica do Spring Security e geração de relatório básico em PDF, com detalhes e sumário de entrada e saída de veículos.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.3.3
    - Spring Data JPA
    - Spring Validation
    - Spring Web
    - Spring Security
- Postgres
- Lombok
- IText para geração de PDFs.

## Endpoints da API
## Autenticação
http://localhost:8080/parking-api/login
user comum: pedro.lucas
password: 1234
user admin: admin
password: 9876

## Swagger
http://localhost:8080/vehicle-api/swagger-ui/index.html

## Configuração para Execução
## Pré-requisitos
- Java 17 SDK

## Como Executar
- Clone o repositório:
  `git clone https://github.com/fcamarasantos/backend-test-java.git`
- Clicar com botão direito na classe ParkingApiApplication.java e selecionar 'Run ParkingApiApplication.main()'
