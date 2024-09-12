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

## Listar Estabelecimentos
`GET /establishments`
Retorna todos os estabelecimentos cadastrados caso não passe nenhum parametro no @RequestParam

`GET /establishments/1`
Retorna o estabelecimento por id


`GET /establishments/cnpj/40245027000169`
Retorna o estabelecimento por cnpj

        `curl --location 'http://localhost:8080/establishments/cnpj/40245027000169' \
        --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwMjM5OH0.tzwqHXPgZAuxycrMPaYOoZ7fDSrxUtjE0c60V9gRkLU' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6'`


## Atualiza Estabelecimento

`PUT 'http://localhost:8080/establishments/9e5f5223-ead1-4a7f-9bce-2dbd0ad63b13`

        `curl --location --request PUT 'http://localhost:8080/establishments/9e5f5223-ead1-4a7f-9bce-2dbd0ad63b13' \
        --header 'Content-Type: application/json' \
        --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwMjM5OH0.tzwqHXPgZAuxycrMPaYOoZ7fDSrxUtjE0c60V9gRkLU' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6' \
        --data '{
            "name": "ESTACIONAMENTO DO FALCAO",
            "cnpj": "40245027000169",
            "address": {
                "street": "RUA CORONEL JOAO CORDEIRO",
                "number": "67",
                "city": "CAPITAL DO CEARÁ"
            },
            "phone": "85998654679",
            "numberCarSpaces": 10,
            "numberMotorcycleSpaces": 10
        }'`


## Deleta Estabelecimento
      `DELETE 'http://localhost:8080/establishments/3de98d93-c14e-4004-9842-a5844103578c`  
          
        
        `curl --location --request DELETE 'http://localhost:8080/establishments/3de98d93-c14e-4004-9842-a5844103578c' \
        --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwNzY2Nn0.udu8g7KpZzlxWglGVpvVXsLIEbwg02NjxvlKaAko0bI' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6'`

* Alterar token da request passando o token gerado no `POST /auth/login`


## Cadastro de veículos
`POST /vehicles`
- Exemplo de body da request:
  #CARRO:

  `{
  "brand": "FIAT",
  "model": "CRONOS",
  "color": "BRANCO",
  "licencePlate": "ORT25B9",
  "type": "CAR"
  }`

#MOTO:
`{
"brand": "YAMAHA",
"model": "FACTOR 150",
"color": "PRETO",
"licencePlate": "HCG4T88",
"type": "MOTORCYCLE"
}`


      `curl --location 'http://localhost:8080/vehicles' \
      --header 'Content-Type: application/json' \
      --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwNjU2Nn0.FiS_A3QzP0893lb4IGbRO5JVmuaKPio5siE0Db_agSc' \
      --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6' \
      --data '{
          "brand": "FIAT",
          "model": "CRONOS",
          "color": "BRANCO",
          "licencePlate": "ORT25B9",
          "type": "CAR"
      }'`

* Alterar token da request passando o token gerado no `POST /auth/login`


## Listar Veículos
`GET http://localhost:8080/vehicles`
Retorna todos os veículos cadastrados caso não passe nenhum parametro no @RequestParam


        `curl --location 'http://localhost:8080/vehicles' \
        --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwMjM5OH0.tzwqHXPgZAuxycrMPaYOoZ7fDSrxUtjE0c60V9gRkLU' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6'`


`GET http://localhost:8080/vehicles?brand=FIAT&model=CRONOS&color=BRANCO&type=CAR`
Retorna todos os veículos cadastrados com os parametros fornecidos no @RequestParam

        `curl --location 'http://localhost:8080/vehicles?brand=FIAT&model=CRONOS&color=BRANCO&type=CAR' \
        --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwMjM5OH0.tzwqHXPgZAuxycrMPaYOoZ7fDSrxUtjE0c60V9gRkLU' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6'`

`GET 'http://localhost:8080/vehicles/OCR5YBC'`
Retorna um veículo buscando por placa

      `curl --location 'http://localhost:8080/vehicles/OCR5YBC' \
      --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwMjM5OH0.tzwqHXPgZAuxycrMPaYOoZ7fDSrxUtjE0c60V9gRkLU' \
      --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6'`


## Atualiza veículo
`PUT 'http://localhost:8080/vehicles/98bb3a2e-1dac-402b-9c58-ffdad2d54450`

        `curl --location --request PUT 'http://localhost:8080/vehicles/98bb3a2e-1dac-402b-9c58-ffdad2d54450' \
        --header 'Content-Type: application/json' \
        --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwMjM5OH0.tzwqHXPgZAuxycrMPaYOoZ7fDSrxUtjE0c60V9gRkLU' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6' \
        --data '{
            "brand": "FIAT",
            "model": "CRONOS",
            "color": "BRANCO",
            "licencePlate": "ORT25B9",
            "type": "CAR"
        }'`


## Deleta um veículo
`DELETE 'http://localhost:8080/vehicles/4d860137-3d34-4a00-a58c-3dce07b807c4`

        `curl --location --request DELETE 'http://localhost:8080/vehicles/4d860137-3d34-4a00-a58c-3dce07b807c4' \
        --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwMjM5OH0.tzwqHXPgZAuxycrMPaYOoZ7fDSrxUtjE0c60V9gRkLU' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6'`


## Funcionalidade de Entrada/Saída e veículos:
- Entrada: no body da request enviar placa do veículo e id do estabelecimento

          `curl --location 'http://localhost:8080/parking/getIn' \
        --header 'Content-Type: application/json' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6' \
        --data '{
            "licensePlate": "OTT8T98",
            "establishmentId": "7c9f1b17-e965-4cdc-a7ca-a70e95723e1a"
        }'`

- Saída: no body da request enviar placa de veiculo e id do estabelecimento

          `curl --location 'http://localhost:8080/parking/getOut' \
        --header 'Content-Type: application/json' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6' \
        --data '{
            "licensePlate": "OTR4B26",
            "establishmentId": "416683fa-b44f-4289-bb29-ca8d302cfccf"
        }'`


## Relatórios
- Gerar Relatório de Estacionamento

`GET /reports/parking/3de98d93-c14e-4004-9842-a5844103578c`
Gera um relatório em PDF com todas as entradas e saídas de veículos de um estacionamento.

`GET /reports/parking/3de98d93-c14e-4004-9842-a5844103578c?dateTimeInitial=2024-09-03T05:30:00&dateTimeFinal=2024-09-03T05:30:00`
Gera um relatório em PDF com as entradas e saídas de veículos de um estacionamento filtrando por horario (formato `yyyy-MM0ddThh:mm:ss`).

        `curl --location 'http://localhost:8080/reports/parking/fe43f547-eb4f-4691-ba7d-4653661da680' \
        --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrZW5kLXRlc3QtamF2YSIsInN1YiI6ImNpZHJhbyIsImV4cCI6MTcyNTQwNzY2Nn0.udu8g7KpZzlxWglGVpvVXsLIEbwg02NjxvlKaAko0bI' \
        --header 'Cookie: JSESSIONID=B263D8C31DE84FC86B3A000503B8C9A6'`


## Configuração para Execução
## Pré-requisitos
- Java 21
- Gradle

## Como Executar
- Clone o repositório:
  `git clone https://github.com/mrenancidrao/backend-test-java.git`
  `cd backend-test-java`

- Build o projeto:
  `./gradlew build`

- Rode a aplicação:
  `./gradlew bootRun`


## Como executar 2:
- Também é possível executar diretamente pela IDE (Intellij, Eclipse, etc.)
- Clicar com botão direito na classe BackendTestJavaApplication.java e selecionar 'Run BackendTestJavaApplication.main()'


## Acesse o H2 Console no browser:

- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testjava
- Usuário: sa
- Senha: 123456


## Autenticação JWT
- Após registrar um usuário, use o endpoint /auth/login para obter o token JWT. Inclua esse token no header Authorization para acessar endpoints protegidos:
  `Authorization: Bearer <token>`


## Considerações Finais

Este projeto é uma implementação básica de um sistema de gestão de estacionamento com autenticação JWT e geração de relatórios em PDF.
Como melhorias posso destacar:
- A inclusão de camadas de handle de errors específicos para melhorar o retorno de erros para os clientes da API;
- As implementações de ParkingService (CarParkingServiceImpl e MotorcycleParkingServiceImpl) possuem código que se repete. Seria uma boa abordagem utilizar um padrão Template Method para deixar o codigo mais limpo e reutilizável.
- Adicionar id do estabelecimento no token para pegar essa informação automaticamente, ao invés de passar no body da request
- Usar uma api de autenticação mais robusta como um keycloack
- separar em microserviços
- adicionar duração de permanencia no relatório
- swagger para documentar a API