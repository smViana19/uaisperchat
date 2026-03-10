# Projeto de Estudo - Chat em Tempo Real

## Visão Geral

Este projeto é uma aplicação voltada para estudo, criada para explorar comunicação em tempo real utilizando WebSockets com o protocolo STOMP em um ambiente Spring Boot.

O principal objetivo é evoluir gradualmente o sistema até se tornar uma aplicação simples de mensagens entre usuários. O projeto será desenvolvido de forma incremental, com foco em aprendizado de arquitetura backend, padrões de mensageria e comunicação em tempo real.

Este repositório é destinado a experimentação, aprendizado e exploração de arquitetura utilizando tecnologias modernas do ecossistema Java.

## Tecnologias

O projeto é desenvolvido utilizando as seguintes tecnologias:

- Java 17
- Spring Boot
- Spring WebSocket
- Protocolo STOMP
- Maven

## Objetivos

O objetivo principal deste projeto é estudar e implementar um sistema de mensagens em tempo real enquanto novas funcionalidades são adicionadas progressivamente.

Cada etapa do desenvolvimento irá introduzir novos conceitos de arquitetura e desafios técnicos.

Principais áreas de estudo:

- Comunicação via WebSocket
- Broker de mensagens com STOMP
- Mensageria orientada a eventos
- Arquitetura backend com Spring Boot
- Design de sistemas em tempo real

## Escopo Atual

A versão inicial do projeto foca em estabelecer uma conexão básica via WebSocket e permitir a troca de mensagens em tempo real.

Funcionalidades iniciais:

- Conexão WebSocket
- Manipulação de mensagens com STOMP
- Broadcast de mensagens
- Modelo simples de mensagens

## Funcionalidades Planejadas

O projeto irá evoluir gradualmente com a adição de novas capacidades.

### Funcionalidades de Usuário

- Autenticação de usuários
- Identificação de usuários
- Mensagens privadas entre usuários

### Funcionalidades de Chat

- Salas de chat (rooms)
- Mensagens diretas
- Histórico de mensagens
- Persistência de mensagens

### Funcionalidades de Mídia

- Upload de arquivos
- Compartilhamento de arquivos dentro do chat

### Melhorias do Sistema

- Armazenamento de mensagens
- Paginação de mensagens
- Melhor tratamento na entrega de mensagens
- Configuração básica de segurança

## Arquitetura

O sistema seguirá uma arquitetura típica de WebSocket utilizando Spring:

- Configuração do endpoint WebSocket
- Broker de mensagens STOMP
- Controllers de mensagens
- Modelos de mensagens
- Camada de serviços para regras de negócio

Futuramente o sistema poderá incluir integração com banco de dados e mecanismos de autenticação.

Este projeto busca proporcionar experiência prática em:

- Sistemas backend em tempo real
- Comunicação com WebSockets
- Padrões de mensageria com STOMP
- Arquiteturas de chat escaláveis
- Design incremental de software

## Melhorias Futuras

Possíveis melhorias futuras incluem:

- Integração com banco de dados relacional ou NoSQL
- Autenticação utilizando JWT
- Garantia de entrega de mensagens
- Sistema de presença (usuários online/offline)
- Indicador de digitação
- Confirmação de leitura de mensagens

## Aviso

Este projeto é destinado a fins educacionais e de experimentação. A arquitetura e a implementação podem evoluir conforme novos conceitos forem explorados durante o desenvolvimento.
