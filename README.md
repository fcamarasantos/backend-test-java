## Teste para vaga de Desenvolvedor Back-end
Criar uma API REST para gerenciar um estacionamento de carros e motos criado pela FCamara

### Modelagem de Dados

<img src="https://i.imgur.com/n7TsL06.png">

## Tecnologia utilizadas

- Java 11
- Spring Framework
  - Spring Security
  - Spring Data
  - Spring Validation
- Authentication JWT
- Documentação no Postman

## Requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas: JDK-11 e Maven. Uma boa IDE para utilizar o projeto é a IntellJ mas podendo usar VSCode.

## Como rodar o projeto

- Baixe o projeto ou o clone o repositorio
- Crie o banco de dados parking e popule o banco de dados com o arquivo data.sql
- Acesse a pasta do projeto por meio da linha de comando
- Na pasta do projeto use os comandos <code>mvn install</code> e depois <code>mvn clean package</code>
- Para rodar a aplicação use o comando <code>java -jar <arquivo.jar com a versao atual></code>
- A aplicação será iniciada na rota localhost:8080
