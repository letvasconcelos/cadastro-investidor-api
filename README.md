# cadastro-investidor-api

Microsserviço para CRUD de investidores da plataforma de negociação. API RESTful com banco PostgreSQL dedicado, seguindo o padrão Database per Service.

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/v1/cadastros/investidor?codigo={codigo}` | Buscar investidor por código |
| POST | `/v1/cadastros/investidor` | Criar investidor |
| PUT | `/v1/cadastros/investidor` | Atualizar investidor |
| DELETE | `/v1/cadastros/investidor?codigo={codigo}` | Remover investidor |

## Variáveis de ambiente

| Variável | Descrição |
|----------|-----------|
| `DB_URL` | URL JDBC do PostgreSQL |
| `DB_USERNAME` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `APP_PORT` | Porta da aplicação (padrão: 8080) |

Para execução local, copie `.env.example` para `.env` e substitua os valores conforme o seu ambiente. O arquivo `.env` não é versionado.

## Secrets para deploy

Cadastre os seguintes GitHub Actions Secrets:

- `EC2_HOST`
- `EC2_USER`
- `EC2_SSH_KEY`
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

O valor de `DB_URL` deve incluir host, porta e database:

```text
jdbc:postgresql://<host>:5432/<database>
```

Esta API não utiliza SQS. As credenciais e URLs das filas não devem ser configuradas neste repositório.
