[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/MarcosVP-Fatec/tst230110-java-back)
* Se não rodar automaticamente execute o comando no terminal: **mvn spring-boot:run**
* Ignorar os avisos de "Install"
* Após rodar pegue o link disponibilizado na aba **'PORTS'** para a porta **8080**. Pode ser necessário clicar no cadeado para mutar o State para **open(public)**

# Respostas
https://github.com/MarcosVP-Fatec/tst230110-java-back/blob/master/avaliao_desenvolvedor_backend.docx

# Java-Back
## Execução com Debug
mvn spring-boot:run -Dspring-boot.run.fork=false

---------------------------------------------------------------
## Candidato
* **Marcos Vinicio Pereira**

## Recursos disponibilizados
* Arquivo com a coleção de testes do Postman no diterório src/main/resource/Tst230110.postman_collection.json


---------------------------------------------------------------
## Avaliação Desenvolvedor Back-end Attornatus

O objetivo deste documento é identificar seus conhecimentos quanto às tecnologias utilizadas no cotidiano de desenvolvimento da equipe de Back-end na Attornatus Procuradoria Digital.

Esta análise propõe avaliar os seguintes temas: 
- Qualidade de código
- Java, Spring boot
- API REST
- Testes

A entrega deverá ser feita da seguinte forma:
- O prazo para entrega da avaliação será de até 7 dias após envio da mesma
- Encaminhar este documento com as perguntas respondidas e com o link do código público em sua conta do GitHub
- Opcionalmente, caso você consiga fazer o build da aplicação, poderá também informar o link de acesso


Qualidade de código

1. Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?
1. Em qual etapa da implementação você considera a qualidade de software?


Desafio Java

Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:  
- Criar uma pessoa
- Editar uma pessoa
- Consultar uma pessoa
- Listar pessoas
- Criar endereço para pessoa
- Listar endereços da pessoa
- Poder informar qual endereço é o principal da pessoa  

Uma Pessoa deve ter os seguintes campos:  
- Nome
- Data de nascimento
- Endereço:
	- Logradouro
	- CEP
	- Número
	- Cidade

Requisitos  
- Todas as respostas da API devem ser JSON  
- Banco de dados H2

Diferencial
- Testes
- Clean Code
 
Será levado em avaliação 
- Estrutura, arquitetura e organização do projeto  
- Boas práticas de programação  
- Alcance dos objetivos propostos.

----------------------------------

> "É preciso amar as pessoas  
> Como se não houvesse amanhã  
> Por que se você parar pra pensar  
> Na verdade não há"  (Renato Russo)

