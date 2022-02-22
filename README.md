# GERA API
GERA (Gerador de Resumos de Textos Automático) é uma API que resume textos em português brasileiro ou em português, com base na teoria de padrões léxicos de Michael Hoey e no estudo de Paulo César Fernandes de Oliviera. <br>
*API desenvolvida para o projeto de conclusão de curso da aluna Alice Borges dos Santos, do curso de Bacharelado em Sistemas de Informação do Instituto Federal Catarinense de Ciência e Tecnologia.* <br>
[LIVE DEMO ](https://summarizer-tcc.herokuapp.com/swagger-ui.html) <br> 

## Tecnologias
    Intellij Idea Ultimate (2021.1.2)
    Java (5.11.6)
    Spring Boot (2.4.4)
    Spring Fox (2.9.2)
    Apache PDFBox (2.0.24)
    Maven (4.0.0)
    Swagger (2.9.2) 
    JUnit4
    Mockito
    Heroku

## Instalação

### Instalando Maven
  [Download](https://maven.apache.org/download.cgi) <br>
  [Instalação](https://maven.apache.org/install.html)

  Para testar a instalação do Maven, no prompt de comando digite mvn:
```bash
$ mvn -v
```

### Clonando o repositório
```bash
$ git clone git@github.com:aliceborges/gera-api.git
```

### Compilando e empacotando a aplicação
```bash
$ cd gera-api
$ mvn compile
$ mvn package
```

### Testando a aplicação
```bash
$ cd gera-api
$ mvn test
```

### Executando a aplicação
```bash
$ cd gera-api
$ mvn spring-boot:run
```

### Acessando endpoints
  Após executar a aplicação, teste os endpoints no Swagger:
  http://localhost:8080/swagger-ui.html

### Deploy

  [Heroku](https://summarizer-tcc.herokuapp.com/swagger-ui.html#/)<br>
  
## Documentation
API: A documentação da API é o próprio Swagger. <br>
[Diagrama de Caso de Uso](https://github.com/aliceborges/gera-api/blob/main/documentation/Diagrama%20de%20Caso%20de%20Uso.jpg) <br>
[Fluxograma da API](https://github.com/aliceborges/gera-api/blob/main/documentation/Fluxograma%20da%20API.jpg) <br>
[Fluxograma da Análise das Frases](https://github.com/aliceborges/gera-api/blob/main/documentation/Fluxograma%20da%20An%C3%A1lise%20das%20Frases.jpg)<br>
[Fluxograma da Análise das Palavras](https://github.com/aliceborges/gera-api/blob/main/documentation/Fluxograma%20da%20An%C3%A1lise%20das%20Palavras.jpg)<br>

  
## Autora
  [Alice Borges dos Santos](https://www.linkedin.com/in/alice-borges/)
