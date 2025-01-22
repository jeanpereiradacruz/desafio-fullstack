# Aplicação Gerenciamento de Produtos Favoritos 

## Funcionalidades:
 - Cadastro de Clientes
 - Login
 - Cadastro de lista de Produtos Favoritos
 - Favoritar Produtos

## Validações: 

### Cliente:
 - Cadastro deve conter nome, email e senha
 - Cliente não pode se registrar duas vezes com o mesmo endereço de email

### Login: 
 - O cliente deve conseguir logar com o seu e-mail e senha cadastrado previamente
 - Retorno amigável para o cliente caso os dados de login estejam incorretos

### Cadastro Lista de produtos favoritos
 - O cadastro deve conter título e descrição
 - O cliente deve conseguir criar, visualizar, editar e remover lista de produtos favoritos
 - O cliente pode ter apenas 1 lista de produtos favoritos
 - Ao excluir a lista, os produtos favoritados serão desfavoritados no momento da exclusão

### Favoritar Produtos
 - Deve ser possível visualizar todos produtos do catalogo (busca api externa)
 - A apresentação dos favoritos e da lista de produtos deve conter o título, imagem e preço
 - O cliente deve conseguir favoritar qualquer produto do catálogo
 - Não deve ser possível favoritar um produto que não exista;
 - Não deve ser possível favoritar um produto mais de uma vez;
 - A lista deve conter no máximo 5 produtos favoritados;
 - O cliente deve conseguir acessar sua tela de produtos favoritos.

## Tecnologias utilizadas

### Frontend:
 - React com Typescript
#### Biblioteca: 
 - MUI para estilização dos componentes 

#### Backend: 
 - Java 17, Springboot, Spring Security
#### Biblioteca: 
 - JsonWebToken para geração e validação de token

#### Banco de dados
 - Mysql 8.0

## Instruções de instalação 
1 - Faça o clone do projeto através da url => https://github.com/jeanpereiradacruz/desafio-fullstack.git

### Execução frontend utilizando Docker
Após descompactar a pasta do projeto, entre na pasta desafio-frontend e verá um arquivo DockerFile na raiz, se você tiver o docker instalado pode 
executar a partir deste arquivo, sem necessitar de instalações extras.

1 - Crie um arquivo .env na raiz do projeto frontend, e insira essa 2 linhas:

VITE_API_URL=http://localhost:8080

FAKE_API_URL=https://fakestoreapi.com/products

2 - Estando no diretório do DockerFile execute os seguintes comandos no terminal: 

Comando: ```docker build -t desafio-tecnico .```
Isso irá fazer o build do projeto, após concluido execute: ```docker run -p 5173:80 desafio-tecnico``` 
Se trocar a porta 5173 por outra, lembre-se de alterar no backend também, pois esta é a rota permitida para requisições pro backend.

### Execução do backend com Docker
Após descompactar a pasta do projeto, entre na pasta desafio-backend e verá um arquivo DockerCompose na raiz, se você tiver o docker instalado pode 
executar a partir deste arquivo, sem necessitar de instalações extras.

1 - Crie um arquivo .env na raiz do projeto backend, e insira essas linhas:

SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/desafio?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC

SPRING_DATASOURCE_USERNAME=root

SPRING_DATASOURCE_PASSWORD=root

SPRING_SECURITY_USER=user

SPRING_SECURITY_PASSWORD=123

JWT_SECRET_KEY=chaveSecretaSuperSegura123456789012345
FAKE_API_URL=https://fakestoreapi.com/products/{id}

Fique a vontade para alterar as credenciais, são só exemplo, as que devem ser mantidas são a do mysql e a do fake api, não as altere.
A porta espelho na maquina local é a 3307, certifique-se que não há serviços rodando nela.

1 - Estando no diretório do DockerCompose, execute os seguintes comandos no terminal: 

Comando: ```docker compose up --build```
, para confirmar, digite ```docker ps``` para ver o container em execução.

### Pronto!

Neste momento você deve estar conseguindo executar a aplicação frontend no endeco =>  http://localhost:5173/
E a aplicação backend estara rodando na porta 8080.







