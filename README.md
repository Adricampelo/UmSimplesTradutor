# Tradução Dirigida por Sintaxe

Implementação de um tradutor simples baseado em Tradução Dirigida por Sintaxe, desenvolvido em Java para a disciplina de Compiladores.
## Estrutura do Projeto

### Scanner

Responsável pela análise léxica, identificando números, identificadores, palavras reservadas, operadores e símbolos da linguagem.

### Parser

Responsável pela análise sintática, descendente recursivo. Verifica se a sequência de tokens segue a gramática definida e gera as instruções da tradução.

### Interpretador

Responsável pela execução das instruções geradas pelo Parser. Utiliza uma pilha para realizar as operações e um mapa para armazenar os valores das variáveis.
