
# <img align="center" alt="Java" height="80" width="80" src="https://drive.google.com/uc?export=view&id=1cPXuoKDHXEGQpBtLRpNHI70F5zV8FPOm"> Api Car Sale
<br />

### 1. GraphQL (Implementação BFF - Backend For Frontend)
Este projeto, neste ponto é somente utilizando API Rest, será refeito de forma à utilizar o GraphQL. <br />
Toda documentação desta API está no Swagger para testes em:  <br />
[Documentação da API Aqui](https://parkingapi-production-0b39.up.railway.app/swagger-ui/index.html) <br />
Utilizei o padrão DTO para modelagens de Request/Response

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

### ***Entidades:*** <br />
Usuários<br />
Carros Usados<br />
<br />

## ***Webservices:***

***Veículos:***
- Caradastro de veículos
- Busca principal filtrada por preço
- Buscas filtrada por marca
- Busca filtrada por ano
- Atualização de cadastro de veículo
- "Soft delete" Para exclusão de veículos
  <br />

***Usuários:*** <br />
- Cadastro de Usuários
- Autenticação
  <br />

[Documentação da API Aqui](https://api-car-sale.onrender.com/swagger-ui/index.html)


##### **Atenção** - O Swagger pode demorar um pouco a iniciar, pois o servidor Host, precisa ser iniciado.<br />
****Como trata-se de um serviço gratuito, pode levar um tempo maior, no primeiro acesso.***

### ***Tecnologias utilizadas:***
- Linguagem Java  <br />
- Framework Spring
- Docker  <br />
- Postgres  <br />
- Render.com  <br />
- Documentação via Swagger


<div style="display: inline_block">
   <img align="center" alt="Java" height="70" width="40" src="https://seeklogo.com/images/J/java-logo-7833D1D21A-seeklogo.com.png">
   <img align="center" alt="Spring" height="40" width="40" src="https://github.com/harrissondutra/harrissondutra/blob/main/.img/logo-spring.png">
   <img align="center" alt="Postgres" height="40" width="40" src="https://github.com/harrissondutra/harrissondutra/blob/main/.img/postgresql_logo_icon_170835.png">
   <img align="center" alt="Docker" height="50" width="50" src="https://cdn.iconscout.com/icon/free/png-256/free-docker-logo-icon-download-in-svg-png-gif-file-formats--wordmark-programming-langugae-language-pack-logos-icons-1175229.png?f=webp&w=256">
   <img align="center" alt="Render" height="50" width="50" src="https://cdn.sanity.io/images/34ent8ly/production/ec37a3660704e1fa2b4246c9a01ab34e145194ad-824x824.png">
   <img align="center" alt="Swagger" height="40" width="180" src="https://raw.githubusercontent.com/swagger-api/swagger.io/wordpress/images/assets/SWE-logo-clr.png"> 
</div>
