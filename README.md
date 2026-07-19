# cadastro-investidor-api

Microsserviço para CRUD de investidores da plataforma de negociação. API RESTful com banco PostgreSQL dedicado, seguindo o padrão Database per Service.

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/cadastro-investidor-api/v1/cadastros/investidor` | Listar investidores |
| GET | `/cadastro-investidor-api/v1/cadastros/investidor/{codigo}` | Buscar investidor por código |
| POST | `/cadastro-investidor-api/v1/cadastros/investidor` | Criar investidor |
| PUT | `/cadastro-investidor-api/v1/cadastros/investidor/{codigo}` | Atualizar investidor |
| DELETE | `/cadastro-investidor-api/v1/cadastros/investidor/{codigo}` | Inativa o investidor (delete lógico via `apta_negociacao = false`) |

O corpo de criação e atualização contém somente os campos editáveis:

```json
{
  "nome": "Maria Silva",
  "cpf": "12345678901",
  "apta_negociacao": true
}
```

Código, data de criação e data de atualização são gerados pelo banco.

## Nginx

```nginx
location /cadastro-investidor-api/ {
    proxy_pass http://cadastro-investidor-api:8080;
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
}
```

## Variáveis de ambiente

| Variável | Descrição |
|----------|-----------|
| `DB_URL` | URL JDBC do PostgreSQL |
| `DB_USERNAME` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `APP_PORT` | Porta da aplicação (padrão: 8080) |

Para execução local, crie um arquivo `.env` com as variáveis acima. O arquivo `.env` não é versionado.

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
