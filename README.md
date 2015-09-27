xy-inc
============

API HTTP para busca de CEP. Essa aplicação fornece dois serviços para busca de CEPs: 

buscarEnderecoPorCep: Onde é passado um CEP como parâmetro e é retornado uma lista de endereços.
buscarCepPorEndereco: Onde é passado uma descrição de logradouro como parâmetro e é retornado uma lista de endereço com seus respectivos CEPs. 

A busca de CEP é realizada através do consumo do HTML do site do próprio correio. O sistema interpreta o HTML e faz o parser dessa informação, respondendo assim para o usuário final um json;

Arquitetura
=============

O sistema foi desenvolvido com Java 8 e o framework Spring Boot. 

As dependências e build da aplicação são gerenciadas pelo maven. 

O projeto pode ser importado facilmente em qualquer IDE com suporte a java e maven.

Build e execução
==================

O build é feito através do próprio maven através do seguinte comando:

```xml

mvn clean assembly:assembly

```   

Após realizar o comando acima a aplicação se encontrara dentro da pasta targert com o nome "xy-inc-0.0.1-SNAPSHOT-distribution.zip". 

Para executar a aplicação basta descompactar o arquivo  xy-inc-0.0.1-SNAPSHOT-distribution.zip e rodar o jar xy-inc-0.0.1-SNAPSHOT.jar (java -jar xy-inc-0.0.1-SNAPSHOT.jar). Por padrão a porta que irá rodar o sistema será a porta 8080, mas a mesma pode ser alterada dentro do arquivo "application.properties" que também fica dentro do xy-inc-0.0.1-SNAPSHOT-distribution.zip.


Serviços
========

Com a aplicação rodando os serviços são acessados através das seguintes URL

```xml

http://localhost:8080/xyinc/services/cep/buscarEnderecoPorCep?cep=38411-001

```   
e

```xml

http://localhost:8080/xyinc/services/cep/buscarCepPorEndereco?logradouro=Rondon%20Pacheco%20Uberlandia

```   

Em caso de sucesso o retorno dos dois métodos ocorrera em um json no seguinte formato:

```xml

[{
    "logradouro": "Avenida Rondon Pacheco - lado par",
    "bairro": "Morada da Colina",
    "localidade": "Uberlândia",
    "uf": "MG",
    "cep": "38411-001"
}]

```   

Em caso de falhas de validações o retorno se dará no seguinte formanto:

```xml

{
    "timestamp": 1443389815974,
    "status": 412,
    "message": "CEP não informado!",
    "error": "Precondition Failed"
}

```   

