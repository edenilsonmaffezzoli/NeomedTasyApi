# NeomedTasy API

API REST para integração com o sistema Tasy do Hospital Dona Helena, desenvolvida em Spring Boot para processamento de exames médicos.

## 📋 Descrição

Repositório interno para integração da Neomed com o Tasy - Cliente Hospital Dona Helena. Esta API fornece endpoints para recebimento e processamento de dados de exames médicos, com autenticação via token estático e integração com banco de dados.

## 🚀 Tecnologias

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL/MariaDB**
- **Maven**
- **Docker**
- **Swagger/OpenAPI**

## 🔐 Autenticação

A API utiliza autenticação via token estático que deve ser enviado no header `Authorization`:

```
Authorization: r5b6gVwpy8M41WLh7y1Q5ao5MUQYi2398Bltak3h7lxHXX8HuyetAV81reKzwR8E
```

## 📚 Endpoints

### Exames
- **POST** `/api/exames` - Receber dados de exame
  - Content-Type: `application/json`
  - Requer token de autorização

### Documentação
- **GET** `/swagger-ui/index.html` - Interface Swagger UI
- **GET** `/v3/api-docs` - Documentação OpenAPI

## 🛠️ Desenvolvimento Local

### Pré-requisitos
- Java 17+
- Maven 3.6+
- MySQL/MariaDB

### Executar Localmente

```bash
# Clonar o repositório
git clone <repository-url>
cd NeomedTasyApi/APITasy

# Configurar banco de dados no application.properties
# Executar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 🐳 Deploy com Docker

### Construir Imagem

```bash
# No diretório do projeto
docker build -t hdh/apitasy:latest .
```

### Deploy no Servidor

#### 1. Copiar JAR para o Servidor

```bash
scp api.jar root@10.3.66.74:/root/
```

#### 2. Conectar ao Servidor

```bash
ssh root@10.3.66.74
```

#### 3. Gerenciar Container no Servidor

```bash
# Listar containers em execução
docker ps

# Parar container atual
docker stop apitasy-container

# Remover container
docker rm apitasy-container

# Executar nova versão
docker run -d --name apitasy-container -p 443:8080 hdh/apitasy:latest
```

### Verificar Deploy

```bash
# Verificar logs do container
docker logs apitasy-container

# Verificar status
docker ps
```

## 🌐 Acesso em Produção

- **URL Base**: `https://10.3.66.74:443`
- **Swagger UI**: `https://10.3.66.74/swagger-ui/index.html`
- **Endpoint Principal**: `https://10.3.66.74/api/exames`

## 📝 Exemplo de Uso

### Enviar Exame via cURL

```bash
curl -X POST https://10.3.66.74/api/exames \
  -H "Authorization: r5b6gVwpy8M41WLh7y1Q5ao5MUQYi2398Bltak3h7lxHXX8HuyetAV81reKzwR8E" \
  -H "Content-Type: application/json" \
  -d '{
    "paciente": {
      "nome": "João Silva",
      "cpf": "12345678901"
    },
    "exame": {
      "tipo": "Hemograma",
      "data": "2024-01-15"
    }
  }'
```

### Enviar Exame via Postman

1. **Método**: POST
2. **URL**: `https://10.3.66.74/api/exames`
3. **Headers**:
   - `Authorization`: `r5b6gVwpy8M41WLh7y1Q5ao5MUQYi2398Bltak3h7lxHXX8HuyetAV81reKzwR8E`
   - `Content-Type`: `application/json`
4. **Body**: JSON com dados do exame

## 🔧 Configuração

### Variáveis de Ambiente

- `SPRING_DATASOURCE_URL` - URL do banco de dados
- `SPRING_DATASOURCE_USERNAME` - Usuário do banco
- `SPRING_DATASOURCE_PASSWORD` - Senha do banco

### Portas

- **Desenvolvimento**: 8080
- **Produção**: 443 (mapeada para 8080 interno)

## 📊 Monitoramento

```bash
# Verificar logs em tempo real
docker logs -f apitasy-container

# Verificar uso de recursos
docker stats apitasy-container

# Verificar se o container está rodando
docker ps | grep apitasy
```

## 🚨 Troubleshooting

### Problemas Comuns

1. **Erro 405 Method Not Allowed**
   - Certifique-se de usar POST para `/api/exames`

2. **Erro "Invalid token or not provided"**
   - Verifique se o header `Authorization` está correto

3. **Erro de conexão**
   - Verifique se o container está rodando: `docker ps`
   - Verifique os logs: `docker logs apitasy-container`

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto é propriedade do Hospital Dona Helena.

---

**Desenvolvido por Pualsati** 🏥
