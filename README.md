
# <img align="center" alt="Java" height="80" width="100" src="https://pngimg.com/uploads/parking/parking_PNG71.png"> Api Parking

## Versão Graphql:

 - Sem relatório momentâneamente
# [Graphiql Playground aqui](https://parkinggraphql-production.up.railway.app/graphiql) <br />

---

---
## Versão Api Rest:

### ***Entidades:*** <br />
Estabelecimentos<br />
Veículos<br />
Controle de Acesso<br />
Endpoints para Dashboard ou Relatórios<br />
<br />

## ***Webservices:***

***Estabelecimentos:***
- Cadadastro
- Busca principal filtrada
- Buscas filtrada por id
- "Soft delete" Para exclusão de estabelecimento
<br />

***Veículos:*** <br />
- Cadastro
- Alteração
- Delete
- Busca geral
- Busca por Id
- Busca por Placa
- Autenticação
<br />

***Controle de Acesso:*** <br />
- Controle de entrada/Saída
<br />

***Relatório:*** <br />
- Contagem Total de Veículos
- Contagem de Veículos por Tipo
- Contagem de Veículos por Dia
- Contagem de Veículos por Hora
- Contagem de Veículos por Tipo e Hora
- Contagem de Entradas de Veículos
- Contagem de Saídas de Veículos
- Contagem de Entradas de Veículos por Hora
- Contagem de Saídas de Veículos por Hora
- Quantidade de veículos por Mês
- Quantidade de veículos por Ano
<br />

*Documentação online - testes liberados com autenticação:* <br />
[Documentação da API via Swagger Aqui](https://parkingapi-production-0b39.up.railway.app/swagger-ui/index.html)<br /><br />
*Em caso de execução em ambiente local, gentileza utilizar: http://localhost:8080/swagger-ui/index.html*


### ***Tecnologias utilizadas:***
- Linguagem Java  <br />
- Framework Spring
- Postgres  <br />
- Deploy: Railway <br />
- Documentação via Swagger


<div style="display: inline_block">
   <img align="center" alt="Java" height="70" width="40" src="https://seeklogo.com/images/J/java-logo-7833D1D21A-seeklogo.com.png">
   <img align="center" alt="Spring" height="40" width="40" src="https://github.com/harrissondutra/harrissondutra/blob/main/.img/logo-spring.png">
   <img align="center" alt="Postgres" height="40" width="40" src="https://github.com/harrissondutra/harrissondutra/blob/main/.img/postgresql_logo_icon_170835.png">
   <img align="center" alt="Railway" height="50" width="50" src="https://images.crunchbase.com/image/upload/c_pad,f_auto,q_auto:eco,dpr_1/h3m0hmstlq9maq7t8tyc">
   <img align="center" alt="Swagger" height="40" width="180" src="https://raw.githubusercontent.com/swagger-api/swagger.io/wordpress/images/assets/SWE-logo-clr.png"> 
</div>

---

## *Respostas dos questionamentos*

---

### 1. GraphQL (Implementação BFF - Backend For Frontend)

Utilize o playground para testar as queries e mutations. <br />
clique no link abaixo:
## [Graphiql Playground aqui](https://parkinggraphql-production.up.railway.app/graphiql) <br />


##### ***Queries:***
```graphql
  accessControls: [AccessControl]
  findByVehiclePlate(vehiclePlate: String): AccessControl
  establishmentById(id: ID): Establishment
  establishments: [Establishment]
  vehicleById(id: ID): Vehicle
  vehicles: [Vehicle]
```

##### ***Mutations:***
```graphql
registerEntry(plate: String, type: VehicleType,establishmentId: ID): AccessControl
registerExit(plate: String): AccessControl
createAccessControl(establishmentId: ID): AccessControl
addEstablishment(establishmentInput: EstablishmentInput): Establishment
addVehicle(vehicleInput: VehicleInput): Vehicle
```

#### Clique no link abaixo para o github da versão Graphql:
### [Repositório Github](https://github.com/harrissondutra/Parking_Graphql)

### 2. Banco de Dados (Nível Básico)

### Pergunta 1: Explique os principais conceitos de um banco de dados relacional, como tabelas, chaves primárias e estrangeiras.

Tabelas por definição, são estruturas que armazenam dados em linhas e colunas. As colunas: Representam os atributos ou campos dos dados e as linhas: Representam os registros(tuplas).

### Pergunta 2: No contexto de uma aplicação de gerenciamento de estacionamento, como você organizaria a modelagem de dados para suportar as funcionalidades de controle de entrada e saída de veículos?

No contexto do estacionamento usei:
Estabelecimentos: Contém informações sobre cada estabelecimento, incluindo o número de vagas para motos e carros.
Veículos: Contém informações sobre cada veículo, incluindo a marca, modelo, cor, placa e tipo (carro ou moto).
Controle de Acesso: Registra cada entrada e saída de veículos, referenciando as tabelas Veiculos e Estabelecimentos para manter a integridade.

Essa modelagem permite gerenciar eficientemente as entradas e saídas de veículos, além de manter um registro detalhado das operações no estacionamento.

### Pergunta 3: Quais seriam as vantagens e desvantagens de utilizar um banco de dados NoSQL neste projeto?

Vantagens:
- NoSQL permite a adição de mais servidores para lidar com o aumento de carga.
- Permite a inserção de dados sem a necessidade de um esquema fixo, facilitando uma mudança nos requisitos dos dados
- NoSQL pode oferecer melhor desempenho para operações de leitura e escrita em grande escala devido à sua capacidade de distribuir dados em vários servidores.
- Armazenar dados não estruturados ou semi-estruturados, como logs de acesso e dados de sensores.

Desvantagens:
- Muitos bancos de dados NoSQL sacrificam a consistência em favor da disponibilidade e particionamento, o que pode ser um problema.
- Alguns bancos de dados NoSQL não suportam transações ACID, o que pode ser uma limitação para operações que exigem consistência
- Consultas complexas, como junções, podem ser mais difíceis de implementar e menos eficientes em bancos de dados NoSQL.

  A decisão entre utilizar um banco de dados relacional ou NoSQL deve considerar os requisitos específicos do projeto, incluindo a necessidade de escalabilidade, flexibilidade de esquema e a importância da consistência e integridade dos dados.

### 3. Agilidade (Nível Básico)

Metodologias ágeis são abordagens iterativas e incrementais para o desenvolvimento de software, focadas em entregar valor continuamente ao cliente. Elas promovem flexibilidade, colaboração entre equipes e feedback rápido, resultando em maior qualidade e adaptabilidade do software.
Para aplicar princípios ágeis neste projeto, eu utilizaria sprints curtos de 1-2 semanas, reuniões diárias para alinhamento, revisões de sprint para feedback, retrospectivas para melhorias contínuas, integração contínua (CI) para garantir que o código esteja sempre funcional, e testes automatizados para manter a qualidade.
A comunicação eficaz é crucial em um ambiente ágil para garantir alinhamento, resolver problemas rapidamente e trocar feedback contínuo. Boas práticas incluem reuniões diárias (daily stand-ups), reuniões de planejamento de sprint, revisões de sprint, retrospectivas e o uso de ferramentas de comunicação como Slack ou Jira.


### 4. DevOps (Nível Básico)
DevOps é uma abordagem que integra desenvolvimento (Dev) e operações (Ops) para melhorar a colaboração, automação e eficiência no ciclo de vida de uma aplicação, desde o desenvolvimento até a produção. Para integrar práticas de DevOps no desenvolvimento da aplicação de estacionamento, eu utilizaria integração contínua (CI) e entrega contínua (CD) com ferramentas como Jenkins ou GitHub Actions para automatizar testes e deploys, garantindo entregas rápidas e de alta qualidade. Ferramentas como Docker e Kubernetes seriam usadas para containerização e orquestração, enquanto Prometheus e Grafana seriam empregadas para monitoramento e alertas, assegurando a estabilidade e desempenho da aplicação em produção.
Aqui estou utilizando o Railway como pipeline de desenvolvimento, porém em outras estruturas gratuitas, utilizo Docker para fazer o deploy em um conteiner.




