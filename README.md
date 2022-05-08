
# Estacionamento de Veículos com Nestjs e Mysql

API construída para teste de desenvolvedor fullstack para o cliente Dr. Consulta

## Objetivo do Projeto
Permitir registro de entradas e saídas de veículos em estabelecimentos.

## Funcionalidades
- [x]  Estabelecimentos (Listagem, Cadastro, alteração, atualização, deleção)
- [x]  Veículos (Listagem, Cadastro, alteração, atualização, deleção)
- [x]  Log de eventos (Registro, listagem)

## Tecnologias utilizadas
* Framework Nodejs - <a href="https://nestjs.com/" about="_blank">Nestjs 8.0.0</a>
* Banco de dados - <a href="https://dev.mysql.com/downloads/mysql/8.0.html" about="_blank">Mysql 8.0.0</a>
* ORM - <a href="https://www.npmjs.com/package/typeorm" about="_blank">TypeORM 5.2.1</a>
* TypeScript - <a href="https://www.typescriptlang.org/docs/handbook/release-notes/typescript-4-3.html" about="_blank">4.3.5</a>
* Documentação - <a href="https://www.npmjs.com/package/@nestjs/swagger?activeTab=versions" about="_blank">Nest Swagger 5.2.1</a>

### Pré-requisitos

Você vai precisar ter instalado em sua máquina as seguintes ferramentas:
* [Git](https://git-scm.com)
* [Node.js](https://nodejs.org/en/).
* [Mysql](https://dev.mysql.com/downloads/installer/)
* [Mysql WorkBench (Criar banco de dados)](https://www.jetbrains.com/datagrip/download/?source=google&medium=cpc&campaign=15034928143&term=mysql%20gui&gclid=CjwKCAjwjtOTBhAvEiwASG4bCANtIirgdt1u0tE2VS4tCyx4oxSvZtIiAjDYLfaYSPp7Uf91JIgOwBoCrVUQAvD_BwE#section=windows)
* [Insomnia (Testar requisições)](https://insomnia.rest/download)

Arquivos Úteis: 
* [Collection de exemplo para o insomnia](https://drive.google.com/file/d/1LgeC3i8azuCdpRIIeiA5wpROx7VmiT6g/view?usp=sharing)
* [Modelagem inicial do banco(rascunho)](https://drive.google.com/file/d/18H3V6Mwued1fftxeePxtz_nPAFtwUgJy/view?usp=sharing)
* [Script que comecei fazendo na mão :c](https://drive.google.com/file/d/12QlQwfOMN9PzXm88faEiakvKyRscKULa/view?usp=sharing)
* [Modelagem final do banco rsrs :E](https://drive.google.com/file/d/1awDnBSJrtl83N9f16Ek11oHj_BlPctqY/view?usp=sharing)

### 🎲 Rodando o back-end (api)

#### Certifique-se de já estar com o mysql local rodando na porta 3306, banco de dados local para teste criado, e informações como "database, host, username e password" em mãos.

```bash
# Clone este repositório
$ git clone <https://github.com/carlosgizbert/backend-test-jest>

# Acesse a pasta do projeto no terminal/cmd
$ cd backend-test-jest

# Instale as dependências
$ npm install

# Acesse a pasta de configuração do banco de dados Mysql
$ cd /src/data/database.providers.ts
altere os valores para o seu banco de dados local
      username: 'nome_banco_mysql_local',
      password: 'senha_banco_mysql_local',
      database: 'nome_banco_mysql_local',

# Execute a aplicação em modo de desenvolvimento
$ npm run start:dev
Após isso, a aplicação irá montar as tabelas no banco de dados local de forma automática, e você já poderá utilizar a api em sua máquina.

# O servidor inciará na porta:3000 e a página inicial será a documentação do swagger explicando as rotas e propriedades.
* Acesse <http://localhost:3000>.
```
