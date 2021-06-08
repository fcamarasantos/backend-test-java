<h3 width="100%"  align="center"> 
  <img src="https://blog.fcamara.com.br/wp-content/uploads/2019/10/Logotipo-FCamara.png" alt="Background Fcamara">
</h3>

## Teste para vaga de Desenvolvedor Back-end
Criar uma API REST para gerenciar um estacionamento de carros e motos.

## Funcionalidades

   - **Estabelecimento:** CRUD;
   - **Veículos:** CRUD;
   - **Controle de entrada e saída de veículos.**
  
## Funcionalidades Extras

* [x] Criar API de relatório;
* [x] Desenvolvimento utilizando TDD;
* [x] Criar uma solução de autenticação;
* [x] Documentação da API usando Swagger;

  
## Tecnologias

   - SpringBoot
   - JpaRepository
   - H2 Database
   - JUnit
   - MockMvc e Mockito
   - SpringFox(Swagger)
   - JasperSoft
   - JWT
 
### Como instalar
<hr/>

#### Clonar o repositório
```
git clone https://github.com/GuilhermeGonzalez/backend-test-java.git
```
#### Abra o projeto em sua IDLE de preferência
```
Execute o projeto!
```
#### Acesse o link abaixo para testar pelo Swagger
```
http://localhost:8080/swagger-ui.html
```
#### Para acessar os relatórios abra o navegador e acesse os seguintes links
```
http://localhost:8080/relatorios/veiculos/porMarca
http://localhost:8080/relatorios/veiculos/listagem
http://localhost:8080/relatorios/estacionamentos/listagem
```

##### Observação:
###### Para testar quaisquer endpoints da API alem dos relatórios, é necessário ter o token de autenticação primeiro, você terá e esse token ao acessar /auth enviando o json a seguir:
```
{
  "password": "admin",
  "username": "admin"
}
```
###### O token no header deverá ter o seguinte formato "tipo token":
```
Bearer token....
```

<hr/>

#### Feedback
Feedback é sempre bem-vindo, se você tiver qualquer sugestão ou duvida por favor me enviar um e-mail: gcgonzalez99@hotmail.com
