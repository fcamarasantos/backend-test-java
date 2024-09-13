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
  `git clone https://github.com/pedrolucas557/backend-test-java.git`
- Clicar com botão direito na classe ParkingApiApplication.java e selecionar 'Run ParkingApiApplication.main()'

## Questionário para Avaliação de Competências
## 1. GraphQL (Implementação BFF - Backend For Frontend)
- Pergunta 1: Explique o que é o GraphQL e como ele se diferencia de uma API REST tradicional.
- Pergunta 2: Descreva como você implementaria o uso do GraphQL como BFF (Backend for Frontend) neste projeto de 
gerenciamento de estacionamento. Forneça exemplos práticos.
- Pergunta 3: Quais são os benefícios de utilizar GraphQL em relação à flexibilidade das consultas?
Cite possíveis desafios ao utilizá-lo.
- - -
- Resposta(Pergunta 1.1): GraphQL é uma forma de consultar APIs onde o cliente (frontend) pode pedir exatamente os dados
que precisa, sem mais nem menos. APIs REST geralmente têm vários endpoints (ex.: /users, /posts). No GraphQL, há apenas
um endpoint, e todas as consultas (queries) são feitas nele.
- Resposta(Pergunta 1.2): Implementação de Queries e Mutations:
Exemplo de Query:
type Query {
  veiculoPorPlaca(placa: String!): Veiculo
  veiculosPorMarca(marca: String!): [Veiculo]
  estabelecimentos: [Estabelecimento]
  }
Exemplo de Mutation:
  type Mutation {
  cadastrarVeiculo(marca: String!, modelo: String!, cor: String!, placa: String!, tipo: String!): Veiculo
  cadastrarEstabelecimento(nome: String!, cnpj: String!, endereco: String!, telefone: String!, vagasMotos: Int!, vagasCarros: Int!): Estabelecimento
  }
- Resposta(Pergunta 1.3): Alguns benefícios em relação a flexibilidade é que você pede só o que precisa com GraphQL, 
consulta única para dados relacionados e facilidade de 
evolução. Alguns dos possíveis desafios ao utilizá-lo são a complexidade na configuração inicial, perfomance de 
consultas ea segurança.

## 2. Banco de Dados (Nível Básico)
- Pergunta 1: Explique os principais conceitos de um banco de dados relacional, como tabelas,
chaves primárias e estrangeiras.
- Pergunta 2: No contexto de uma aplicação de gerenciamento de estacionamento, como você organizaria a modelagem
de dados para suportar as funcionalidades de controle de entrada e saída de veículos?
- Pergunta 3: Quais seriam as vantagens e desvantagens de utilizar um banco de dados NoSQL neste projeto?
- - -
- Resposta(Pergunta 2.1): Uma tabela em um banco de dados relacional é como uma planilha que você usa para organizar 
informações. Ela armazena dados sobre um determinado assunto (por exemplo, "veículos" ou "estacionamento"),
de forma que cada linha da tabela representa um item ou registro, e cada coluna descreve um detalhe desse item.
- Resposta(Pergunta 2.2): A chave primária é um campo especial dentro da tabela que serve como identificador único de
cada registro. É como um RG ou CPF para cada linha da tabela, garantindo que cada registro seja único. Geralmente,
é um número (ID), mas pode ser qualquer coisa que identifique um item sem repetir.
- Resposta(Pergunta 2.3): Algumas vantagens seriam à flexibilidade no modelo de dados, a escalabilidade horizontal e a 
alta perfomance com grandes volumes.
Algumas desvantagens seriam à falta de relacionamentos complexos, consistência eventual e menos ferramentas de análise 
de dados.

## 3. Agilidade (Nível Básico)

- Pergunta 1: Explique o conceito de metodologias ágeis e como elas impactam o desenvolvimento de software.
- Pergunta 2: No desenvolvimento deste projeto, como você aplicaria princípios ágeis para garantir entregas contínuas
e com qualidade?
- Pergunta 3: Qual a importância da comunicação entre as equipes em um ambiente ágil? Dê exemplos de boas práticas.
- - -
- Resposta(Pergunta 3.1):
  As metodologias ágeis são uma forma mais dinâmica e flexível de gerenciar projetos, especialmente no desenvolvimento
de software. A ideia central é dividir o trabalho em pequenas partes, chamadas de sprints ou ciclos curtos,
que geralmente duram de 1 a 4 semanas. 
- Resposta(Pergunta 3.2): Dividir o Projeto em Pequenas Tarefas (Sprints), Sprint 1: Criar o cadastro de veículos.
  Sprint 2: Implementar a funcionalidade de reserva de vagas.
  Sprint 3: Integrar a autenticação de usuários.
  - Resposta(Pergunta 3.3): A comunicação entre as equipes em um ambiente ágil é fundamental para o sucesso do projeto.
  Como as metodologias ágeis são baseadas em ciclos curtos de desenvolvimento e em entregas contínuas, a colaboração
  e o alinhamento constante entre todos os membros da equipe garantem que todos estejam na mesma página, evitando
  retrabalhos e problemas de integração. Alguns bons exemplos: Reuniões diárias mais conhecidas como Daily, ferramentas
  de colaboração como Jira, Trello e outros.

## 4. DevOps (Nível Básico)
- Pergunta 1: O que é DevOps e qual a sua importância para o ciclo de vida de uma aplicação?
- Pergunta 2: Descreva como você integraria práticas de DevOps no desenvolvimento desta aplicação de estacionamento.
Inclua exemplos de CI/CD.
- Pergunta 3: Cite as ferramentas que você usaria para automatizar o processo de deploy e monitoramento da aplicação.
- - -
- Resposta(Pergunta 4.1): DevOps é uma maneira de aproximar as equipes de desenvolvimento (Dev) e operações (Ops)
para que elas trabalhem juntas de forma mais integrada e ágil. No DevOps, muita coisa é automatizada, como os testes e 
a implantação do código.
- Resposta(Pergunta 4.2): Integrar práticas de DevOps no desenvolvimento da aplicação de estacionamento pode acelerar
as entregas, melhorar a qualidade do código e facilitar o gerenciamento da infraestrutura. Alguns exemplos:
Automação do Build e Testes (CI - Integração Contínua), entrega contínua e monitoramento e feedback contínuo. 
- Resposta(Pergunta 4.3): Algumas ferramentas que eu usaria seriam: Jenkins, GitLab CI/CD, AWS CodePipeline, CircleCI e
GitHub Actions.