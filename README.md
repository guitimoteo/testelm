# Teste de backend

Teste de consumo de um topico em amqp utilizando kafka como broker.

* ProductServer - aplicação responsável por prover a api e envio do arquivo .xls para o  topico do broker. 

* ProductConsumer - aplicação resonsável por consumir o topico de arquivos e enviar o conteúdo para a base de dados.

* Commons - módulo que abriga entidades em comuns entre as duas aplicações acima.

## Api

A api do productserver foi documentada em http://localhost:8080/swagger-ui.html#/

