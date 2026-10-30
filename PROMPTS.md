# PROMPTS: Grupo ____

**Integrantes:**

1.Rafael Sant' Anna dos Santos
2.Bernardo Soares Zacharias 
3.Pedro Delgado Pereira Pinto

**Prova:** API em MVC (Reserva de Salas)

**Usamos IA?** (X) Sim  ( ) Não

**Qual ferramenta e modelo (se usaram):** Claude (modelo Claude Opus 5.5)

> Quem não usou IA: troque "prompt" por "passo". Em cada bloco abaixo, descreva o que vocês fizeram naquele passo, na ordem em que aconteceu, e preencha os outros campos do mesmo jeito.

## Registro, na ordem

Copie o bloco abaixo para cada prompt. Escreva o prompt exatamente como foi enviado.

### Prompt 1

**O prompt (cópia exata):**

Atue como um desenvolvedor Java Senior. Preciso implementar a API REST completa do projeto "Escola-base" (Spring Boot 3.5.14, Java 17, Spring Data JPA, H2) para o sistema de Reserva de Salas da Escola, respeitando estritamente o padrão MVC e as regras da prova.

Crie os pacotes e classes dentro de src/main/java/escola:

model/Sala: Entidade JPA com id (IDENTITY), codigo (único, obrigatório/@NotBlank), nome (obrigatório/@NotBlank), capacidade (Integer, obrigatório/@NotNull, > 0 /@Positive) e reservada (boolean, inicia false).

repository/SalaRepository: Interface estendendo JpaRepository<Sala, Long> com o método boolean existsByCodigo(String codigo).

Exceções customizadas: SalaNaoEncontradaException e RegraDeNegocioException.

service/SalaService: Concentra TODAS as regras de negócio:

Cadastrar: verifica se o código já existe (lança RegraDeNegocioException se existir); garante id=null e reservada=false.

Listar todas as salas e buscar por id (lança SalaNaoEncontradaException se não existir).

Reservar: busca sala pelo id; se já estiver reservada, lança RegraDeNegocioException; altera reservada=true e salva.

Liberar: busca sala pelo id; se NÃO estiver reservada, lança RegraDeNegocioException; altera reservada=false e salva.

Excluir: busca sala pelo id; se estiver reservada, lança RegraDeNegocioException; caso contrário, deleta.


**O que a IA devolveu (uma ou duas linhas):** Implementou o modelo de dados, o repositório JPA, as exceções customizadas e a regra de negócio no Service.

**O que fizemos com a resposta:** (x) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos):**

### Prompt 2

**O prompt (cópia exata):**  
Agora vamos com a controler, quero que voce siga com as mesmas instruções e regras do prompt que foi enviado anteriormente.

controller/SalaController: Anotado com @RestController e @RequestMapping("/salas"). Deve injetar APENAS a SalaService (não usar Repository). Mapeie:
POST /salas -> 201 Created (com @Valid)
GET /salas -> 200 OK
GET /salas/{id} -> 200 OK
POST /salas/{id}/reservar -> 200 OK
POST /salas/{id}/liberar -> 200 OK
DELETE /salas/{id} -> 204 No Content
controller/TratadorDeErros: Anotado com @RestControllerAdvice para converter exceções nos status HTTP:
SalaNaoEncontradaException -> 404 Not Found
RegraDeNegocioException e DataIntegrityViolationException -> 409 Conflict
MethodArgumentNotValidException, HttpMessageNotReadableException e MethodArgumentTypeMismatchException -> 400 Bad Request
Gere os arquivos roteiro.http e roteiro.cmd com os 10 passos do roteiro de conferência da prova para testarmos a aplicação.

> 

**O que a IA devolveu (uma ou duas linhas):** Criou os endpoints HTTP no Controller, o tratamento global de erros e os scripts de teste.

**O que fizemos com a resposta:** (x) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos):** não foi necessário realizar nenhuma mudança, tudo funcionou perfeitamente!

(continuem com quantos blocos precisarem)

## Como conferimos

Descrevam como vocês testaram o que ficou pronto. Colem aqui a saída do programa (POO) ou o resultado do roteiro (API) e digam se bateu com a tabela da prova. Se não bateu, digam o que fizeram.

Testamos as rotas com o postman e tudo funcionou, não consegui rodar o código pois o pc da escola não deixou

## Onde a IA errou, ou onde nós erramos

Contem pelo menos um erro que apareceu durante a prova: o que era, como perceberam e como resolveram. Se não apareceu nenhum, expliquem como vocês têm certeza disso.

Ele alucinou com a arquitetura do projeto.

## Respostas finais, sem consultar a IA

Respondam com as suas palavras, nos últimos 5 minutos. Não copiem de lugar nenhum.

**1. Pergunta 1**
O Controller recebe o `POST /salas/{id}/reservar`, chama a Service que usa o Repository para buscar a Sala (Model) no banco; a Service valida o estado da entidade Sala, altera `reservada` para `true`, salva via Repository e o Controller devolve a resposta HTTP `200 OK`.

**2. Pergunta 2**
Fica na Service para respeitar a separação de responsabilidades (o Controller cuida apenas de HTTP e rotas, enquanto a Service isola as regras de negócio). Isso evita duplicação de lógica e permite reusar essa regra em outros fluxos sem depender da camada Web.

**3. Pergunta 3**
Nenhuma classe do código Java precisa mudar, pois o Spring Data JPA e o Hibernate abstraem as operações do banco de dados. Apenas as configurações de conexão em `application.properties` e a dependência do novo driver no `pom.xml` precisam ser atualizadas.