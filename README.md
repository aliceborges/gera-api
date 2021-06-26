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
$ git clone git@github.com:aliceborges/summarizer.git
```

### Compilando e empacotando a aplicação
```bash
$ cd summarizer
$ mvn compile
$ mvn package
```

### Testando a aplicação
```bash
$ cd summarizer
$ mvn test
```

### Executando a aplicação
```bash
$ cd summarizer
$ mvn spring-boot:run
```

### Acessando endpoints
  Após executar a aplicação, teste os endpoints no Swagger:
  http://localhost:8080/swagger-ui.html

### Deploy

  [Heroku](https://summarizer-tcc.herokuapp.com/swagger-ui.html#/)<br>
  
## Autora
  [Alice Borges dos Santos](https://www.linkedin.com/in/alice-borges/)
