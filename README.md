Gerenciador de Publicações

Este projeto, desenvolvido em Java, implementa um sistema de gerenciamento de publicações (livros e revistas) com interface gráfica. O sistema aplica os principais conceitos da Programação Orientada a Objetos (POO), tais como herança, polimorfismo, encapsulamento e abstração.

Conceitos de POO Aplicados

Herança
A classe Publicacao é uma superclasse abstrata. As classes Livro e Revista estendem essa classe, reutilizando atributos como titulo, autor e ano.

Polimorfismo
Objetos das classes Livro e Revista são tratados como instâncias de Publicacao, permitindo o uso de listas genéricas sem perda de comportamento específico das subclasses.

Encapsulamento
Os atributos das classes são definidos como private, e o acesso a eles é controlado por meio de métodos get e set.

Abstração
A classe Publicacao é abstrata e define uma estrutura comum que é estendida por classes concretas. Não pode ser instanciada diretamente.

Estrutura do Projeto

- Publicacao.java: Superclasse abstrata que representa uma publicação genérica.
- Livro.java: Classe que representa livros, incluindo campos como número de páginas.
- Revista.java: Classe que representa revistas, com atributos como volume e edição.
- GerenciadorPublicacoes.java: Responsável por adicionar, listar, salvar e carregar publicações por meio de serialização.
- GUI.java: Interface gráfica construída com Swing, possibilitando o cadastro, visualização e gerenciamento das publicações.
- Main.java: Classe principal que inicializa a aplicação.

Funcionalidades

- Cadastro de livros e revistas.
- Edição e exclusão de registros.
- Listagem das publicações cadastradas.
- Armazenamento e recuperação dos dados por meio de arquivo binário (publicacoes.dat).

Instruções para Execução

1. Compile os arquivos .java com o seguinte comando:
   javac *.java

2. Execute a aplicação:
   java Main

Certifique-se de que todos os arquivos .java estejam no mesmo diretório e que a JDK esteja corretamente instalada no sistema.

Tecnologias Utilizadas

- Linguagem de Programação: Java
- Interface Gráfica: Swing
- Armazenamento Persistente: Serialização em arquivo binário (publicacoes.dat)

Observações Finais

Este projeto foi desenvolvido como parte da terceira avaliação da disciplina de Paradigmas de Programação, com o objetivo de aplicar na prática os conceitos fundamentais da Programação Orientada a Objetos.
