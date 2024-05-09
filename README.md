<img src="https://github.com/brunnotm23/database-banco-javer/assets/99679969/695b8917-3a06-43dc-931a-d7c0c988904e.png" width="250" height="250">

# Database H2 do Banco Javer
API REST do Banco **Javer**

É responsável por expor os endpoints das operações CRUD (Create, Read, Update e Delete) da aplicação [Database Banco Javer](https://github.com/brunnotm23/database-banco-javer), 
além de criar um endpoint adicional para o cálculo de score de um dado cliente

## Requisitos:
- JDK 17 (recomendado: versão 17 como "JAVA_HOME" nas variáveis de ambiente do seu sitema)
- Maven (usar compilador com a versão 17 do Java)
- [Database Banco Javer](https://github.com/brunnotm23/database-banco-javer) **em execução**

## Execução:
### Passo 0:
Garantir que a aplicação [Database Banco Javer](https://github.com/brunnotm23/database-banco-javer) está em execução
### Passo 1:
Fazer o download .zip do repositório ou cloná-lo utilizando o comando `git clone https://github.com/brunnotm23/api-rest-banco-javer.git` no terminal
### Passo 2:
Executar a classe DatabaseApplication do diretório `src/main/java/io/github/brunnotoscano/DatabaseApplication.java`

OU

Utilizar os seguintes comandos em sequência: `cd database-banco-javer` e `mvn spring-boot:run`

## Onde Acessar:
Os endpoints estão configurados no diretório http://localhost:8081/clientes

Referir à documentação para obter mais detalhes sobre as operações e sintaxe de cada endpoint

## Operações
### GET
- **Busca de cliente por ID**
- **Busca de cliente por propriedades**
### POST
- **Criação de clientes na base de dados**
### PUT
- **Atualização de clientes existentes**
### DELETE
- **Remoção de clientes da base de dados**
## PATCH
- **Calcula e salva o score de um dado cliente**

## Documentação
A documentação dos métodos, retornos e requisições esperadas pode ser acessada pelo endereço http://localhost:8081/documentacao.html (só funcionará com a aplicação em execução)
