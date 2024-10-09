### FCamara 🚀

*"Queremos ser como uma árvore, crescer um pouco todos os dias e tentar tocar o céu, sem perder a solidez de nossas raízes."*
Conheça: www.fcamara.com.br

## Teste para vaga de Desenvolvedor Back-end
Criar uma API REST para gerenciar um estacionamento de carros e motos.

## Cadastro de estabelecimento
Criar um cadastro da empresa com os seguintes campos:
   - Nome;
   - CNPJ;
   - Endereço;
   - Telefone;
   - Quantidade de vagas para motos;
   - Quantidade de vagas para carros.

**Todos** os campos são de preenchimento obrigatório.

## Cadastro de veículos
Criar um cadastro de veículos com os seguintes campos:
   - Marca;
   - Modelo;
   - Cor;
   - Placa;
   - Tipo.

**Todos** os campos são de preenchimento obrigatório.

## Funcionalidades
   - **Estabelecimento:** CRUD;
   - **Veículos:** CRUD;
   - **Controle de entrada e saída de veículos.**

## Requisitos
   - Modelagem de dados;
   - O retorno deverá ser em formato JSON e XML;
   - Requisições GET, POST, PUT ou DELETE, conforme a melhor prática;
   - A persistência dos dados pode ser realizada da maneira que preferir;
   - Criar README do projeto descrevendo as tecnologias utilizadas, chamadas dos serviços e configurações necessário para executar a aplicação.
   
## Ganha mais pontos
   - Desenvolver utilizando TDD;
   - Criar API de relatório;
   - Sumário da quantidade de entrada e saída;
   - Sumário da quantidade de entrada e saída de veículos por hora;
   - Criar uma solução de autenticação.

## Questionário para Avaliação de Competências

### 1. GraphQL (Implementação BFF - Backend For Frontend)
   - **Implementação:** Crie um BFF com GraphQL localmente para permitir as operações de CRUD e controle de entrada e saída de veículos. O BFF deve expor as operações e lidar com as interações entre o front-end e o back-end.
   - **Disponibilização:** Após implementar o BFF, disponibilize o projeto publicamente no GitHub, com um link no README para o repositório.
   - **Documentação:** Explique no README os benefícios de usar GraphQL no contexto do projeto, descrevendo também como configurar e rodar o BFF localmente.
   - **Questões:** Além da implementação, responda às seguintes perguntas no README:
      - **Pergunta 1**: Explique o que é o GraphQL e como ele se diferencia de uma API REST tradicional.
        - O graphQL se trata de uma linguagem de consulta para sua API, ele permite que o cliente solicite apenas os dados que ele precisa, e nada mais, enquanto uma API REST tradicional retorna todos os dados de uma vez.
      - **Pergunta 2**: Descreva como você implementaria o uso do GraphQL como BFF (Backend for Frontend) neste projeto de gerenciamento de estacionamento. Forneça exemplos práticos.
        - Se tratando de um BFF eu faria uma api separada com o objetivo de ser meu gateway onde nela eu realizaria a chamada para as apis posteriores e retornaria o resultado atraves dela.
      - **Pergunta 3**: Quais são os benefícios de utilizar GraphQL em relação à flexibilidade das consultas? Cite possíveis desafios ao utilizá-lo.
        - Identifiquei que inicialmente teriamos que planejar bem a modelagem dos dados, para que a consulta seja eficiente, e alem disso a curva de aprendizado para quem nao conhece graphQL pode ser um desafio.

### 2. Banco de Dados (Nível Básico)
   - **Pergunta 1**: Explique os principais conceitos de um banco de dados relacional, como tabelas, chaves primárias e estrangeiras.
     - banco de dados reacinal possuem tabelas para criar uma representacao dos dados, as chaves primarias sao utilizadas para identificar unicamente um registro na tabela, as chaves estrangeiras sao utilizadas para relacionar tabela
   - **Pergunta 2**: No contexto de uma aplicação de gerenciamento de estacionamento, como você organizaria a modelagem de dados para suportar as funcionalidades de controle de entrada e saída de veículos?
        - Como se trata de uma aplicacao de gerenciamento de estacionamento, eu criaria uma tabela para cada entidade, estabelecimento, veiculos, controle de entrada e saida, e faria relacionamento entre elas atraves de chaves estrangeiras.
   - **Pergunta 3**: Quais seriam as vantagens e desvantagens de utilizar um banco de dados NoSQL neste projeto?
        - Acredito que usando nosql ficaria mais facil de escalar a aplicacao, porem a modelagem de dados seria mais complexa, e a performance poderia ser prejudicada.

### 3. Agilidade (Nível Básico)
   - **Pergunta 1**: Explique o conceito de metodologias ágeis e como elas impactam o desenvolvimento de software.
          - Metodologias ageis sao metodologias de desenvolvimento de software que visam entregar valor ao cliente de forma rapida e continua, atraves de iteracoes curtas e feedbacks constantes.
          - elas ajudam a itentificar problemas mais cedo, e a entregar valor ao cliente de uma maneira mais satisfatoria.
   - **Pergunta 2**: No desenvolvimento deste projeto, como você aplicaria princípios ágeis para garantir entregas contínuas e com qualidade?
          - Posso realizar uma abordagem de desenvolvimento de software incremental, onde eu entregaria funcionalidades em pequenas partes, e com feedbacks constantes do cliente. Para garantir um pouco mais de qualidade, eu poderia utilizar a pratica de TDD.
   - **Pergunta 3**: Qual a importância da comunicação entre as equipes em um ambiente ágil? Dê exemplos de boas práticas.
          - Extremamente importante, pois ajuda tanto a alinhar expectativas, quanto a identificar problemas mais cedo. Daily seria um exemplo simples
### 4. DevOps (Nível Básico)
   - **Pergunta 1**: O que é DevOps e qual a sua importância para o ciclo de vida de uma aplicação?
     - DevOps tem como objetivo facilitar processos de desenvolvimento e operacao, atraves de automacao, colaboracao e monitoramento. A importancia dele e garantir que a aplicacao seja entregue de forma rapida e com qualidade.
   - **Pergunta 2**: Descreva como você integraria práticas de DevOps no desenvolvimento desta aplicação de estacionamento. Inclua exemplos de CI/CD.
        - Eu poderia utilizar uma ferramenta de CI/CD para automatizar o processo de deploy da aplicacao, e monitorar a aplicacao atraves de ferramentas de monitoramento.
        - Nessa aplicacao por exemplo para facilitar o deploy eu poderia dockerizar a aplicacao, e utilizar o github para automatizar o processo de deploy.
        - Daria para usar o ansible que ajudaria a organizar arquivos yaml segregando  melhor os ambientes e criar comandos para facilitar geracao dessas profiles antes de dockerizar.
   - **Pergunta 3**: Cite as ferramentas que você usaria para automatizar o processo de deploy e monitoramento da aplicação.
     - Docker, Github, Ansible, monitoramento eu poderia usar o prometheus e grafana.

## Submissão
Crie um fork do teste para acompanharmos o seu desenvolvimento através dos seus commits.

## Obrigado!
Agradecemos sua participação no teste. Boa sorte! 😄
