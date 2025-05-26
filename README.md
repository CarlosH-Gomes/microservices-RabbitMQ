# microservices-RabbitMQ
microservices com RabbitMQ 

Este projeto é composto por **dois microservices** e **uma biblioteca compartilhada** que simula um sistema de pedidos assíncrono usando RabbitMQ.

Spring Boot RabbitMQ - Processamento de Pedidos

Este projeto é um exemplo de integração entre Spring Boot e RabbitMQ para simular o processamento de pedidos com:

- Fila de pedidos usando RabbitMQ
- DTO contendo ID, email do cliente, lista de produtos e status do pedido
- Delay artificial de 2 segundos para simular processamento
- Armazenamento **em memória** do status do pedido
- Endpoint REST para consultar o status de um pedido por ID

---

##  Tecnologias

- Java 17+
- Spring Boot
- Spring AMQP (RabbitMQ)
- RabbitMQ (mensageria)
- Spring Web (REST API)

---

## 🧱 Arquitetura

## RabbitMQ 

Utilizar o docker-composer do projeto para criação do rabbitMQ

Na pasta onde está localizado o arquivo docker-compose.yml, execute: ` docker-compose up -d`

## 📂 Módulos

### `librabbitmq` (biblioteca)

Contém os DTOs usados para troca de mensagens entre os serviços, basta abrir o projetos e "mvn install"

- `PedidoDTO`
- `ProdutoDTO`
- `PedidoStatus`
- `PedidoStatusDTO`
- `RabbitMQConstates`

##### OS SERVIÇOS PODEM SER EXECUTADOS EM PARALELO, PRODUCER FUNCIONA NA PORTA 8080 E O CONSUMER NA 8081 ####

### `pedido-producer` (microserviço) 

- API REST para criar e enviar pedidos para a fila RabbitMQ porta 8080
- Usa `RabbitTemplate` para publicar mensagens
- Exemplo de envio de pedido:
  
POST /pedidos

```json
{
  "emailCliente": "cliente@exemplo.com",
  "produtos": [
    { "nome": "Caneta", "quantidade": 10 },
    { "nome": "Caderno", "quantidade": 5 }
  ]
}
```

### `pedido-consumerr` (microserviço)

- Consome mensagens da fila fila.pedidos
- Simula delay de 2 segundos para processar o pedido
- Armazena o status do pedido em memória
- Disponibiliza um endpoint para consulta:

GET /pedido/{id}
