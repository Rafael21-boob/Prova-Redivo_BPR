# Reserva de Salas da Escola: API em MVC

**Grupo:** ____

**Integrantes:**

1.Rafael Sant' Anna dos Santos
2.Bernardo Soares Zacharias 
3.Pedro Delgado Pereira Pinto

## Como rodar o projeto

O projeto já vem pronto: Spring Boot, Spring Web, Spring Data JPA, validação e banco H2 em memória. Ele sobe sem nenhuma classe sua, e quando sobe com sucesso a API fica em `http://localhost:8080`.

Escolha um jeito:

**Pelo terminal, dentro desta pasta (não precisa ter o Maven instalado)**

No Windows:

```bash
mvnw.cmd spring-boot:run
```

No Linux ou Mac:

```bash
./mvnw spring-boot:run
```

**Pela IDE (IntelliJ, Eclipse, VS Code)**

Abra esta pasta como projeto Maven e execute a classe `EscolaApplication`.

Para parar, use `Ctrl+C` no terminal (ou o botão de parar da IDE).

> **Atenção.** Na primeira vez, o Maven baixa as dependências e isso leva alguns minutos, então precisa de internet. Rodem uma vez antes de começar a prova, para não perder tempo.

## Onde ficam as classes

A classe principal, `EscolaApplication`, está em `src/main/java/escola`. As pastas das camadas (pacotes) **não vêm prontas: vocês criam**, uma para cada camada, dentro de `src/main/java/escola`. Elas precisam ficar dentro do pacote `escola` (ou em subpacotes dele) para o Spring encontrar tudo sozinho.

O banco é H2 em memória: ele começa **vazio** toda vez que a aplicação sobe. Pare e suba de novo para recomeçar o roteiro do zero.

## Roteiro de conferência

Façam os pedidos na ordem, com o banco vazio. Preencham as duas últimas colunas com o que a API realmente fez.

| Passo | O que fazer | Status esperado | Rota que usamos | Status que deu |
| --- | --- | --- | --- | --- |
| 1 | Cadastrar a sala de código LAB-01, nome Lab Informatica, capacidade 30 | 201 | `POST /salas` | |
| 2 | Cadastrar outra sala com o mesmo código LAB-01 | 409 | `POST /salas` | |
| 3 | Cadastrar uma sala sem nome | 400 | `POST /salas` | |
| 4 | Reservar a sala 1 | 200 | `POST /salas/1/reservar` | |
| 5 | Reservar a sala 1 de novo | 409 | `POST /salas/1/reservar` | |
| 6 | Excluir a sala 1 (que está reservada) | 409 | `DELETE /salas/1` | |
| 7 | Liberar a sala 1 | 200 | `POST /salas/1/liberar` | |
| 8 | Liberar a sala 1 de novo | 409 | `POST /salas/1/liberar` | |
| 9 | Excluir a sala 1 | 204 | `DELETE /salas/1` | |
| 10 | Buscar a sala 1 | 404 | `GET /salas/1` | |

Os pedidos exatos estão em `roteiro.http` (IntelliJ ou VS Code com REST Client) e em `roteiro.cmd` (terminal do Windows). A API também tem `GET /salas` (listar todas, 200), que o roteiro não usa.

Se algum passo não bateu, escrevam aqui o que aconteceu:

## Arquivo do processo

O arquivo `PROMPTS.md`, nesta mesma pasta, é o registro do processo. Preencham durante a prova.
