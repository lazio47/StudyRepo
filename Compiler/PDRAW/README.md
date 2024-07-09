# Tema **PDraw**, grupo **pdraw-t10**
-----

## Constituição dos grupos e participação individual global

| NMec | Nome | Participação |
|:---:|:---|:---:|
| 108237 | AFONSO LUIS OLIVEIRA BAIXO | 18.0% |
| 107214 | BEATRIZ FILIPA DA SILVA FERREIRA | 16.0% |
| 113144 | JOÃO PEDRO MOREIRA VIEGAS | 16.0% |
| 103511 | LUÍS ERNESTO FERREIRA DÁ MESQUITA DE CASTRO LEAL | 16.0% |
| 112876 | RICARDO ANTONIO SANTOS BASTOS MARTINS | 16.0% |
| 115697 | SHELTON LÁZIO AGOSTINHO | 18.0% |

## Relatório

### 1. Introdução

O principal objetivo deste projeto foi o desenvolvimento de uma linguagem de programação compilada, capaz de criar programas numa linguagem de programação genérica, no nosso caso Python, com análise semântica para deteção de erros. Permitindo assim o desenho de imagens, através da criação e manipulação de canetas(PenType) dentro de um ambiente gráfico(Canvas), com suporte para vários tipos de operções e configurações das propriedade, de forma a permitir o desenho livre com as canetas.

Em complemento a esta primeira linguagem foi também criada uma segunda linguagem (interpretada), mais simples, e na qual existe apenas uma caneta, ímplicita.

Para se conseguirmos criar estas linguagens e alcançar os objetivos do projeto PDRaw, recorremos às ferramentas do **ANTLR4** para construção da árvore sintática e **StringTemplates** para a geração de código na linguagem de destino.

Para analizar semanticamente e compilar o código da primeira linguagem utilizamos a linguagem de programação **JAVA** e escolhemos o **Python** como nossa linguagem destino.

### 2. Gramáticas

Foi elaborada uma gramática para definir a estrutura sintática das expressões e comandos aceitos pela linguagem PDraw.

A gramática define regras para declarações de variáveis com diferentes tipos de dados, conversão entre tipos de dados, expressões aritméticas e booleanas, definição de canetas, canvas e configuração das suas propriedades, movimentos de canetas, instruções de condicionais, loops e funcões. 
Tudo isto irá permitir ao compilador entender e processar corretamente os comandos fornecidos pelo utilizador

### 3. Compilador, Interpretador e Analise Semântica

Para criar o compilador desta linguagem usamos o padrão de software Visitor, no qual cada nó da árvore sintática é visitado de maneira recursiva. Desta forma conseguimos ter mais flexibilidade e facilidade para fazer o tratamento da árvore sintática produzida pelo parser do **ANTLR4**.

Já para a gestão dos templates a serem usados para a geração do código da nossa linguagem destino, usamos a biblioteca **StringTemplate**, escrita em java, biblioteca essa que se encarrega de carregar o ficheiro de templates, com a extensão **.st**, e de fornecer uma API robusta e amigável para a inserção de outros templates recursivamente.


### 4. Tratamento de erros semânticos

Dentro dos nossos ficheiros para a análise semântica cada visitor retorna um boolean com o resultado da verificação de erros específicos, permitindo fazer uma apanha e tratamento de todos os erros que possam acontecer na lossa linguagem.
Alguns dessses erros mais comuns seriam por exemplo: 
- Tentar atribuir um valor que não corresponde ao tipo de variável definida, ex: int x = "dez";
- Tentar realizar operações entre tipos de dados não compativeis, ex: 10 - "dez";


### 5.Organização do repositório

- **doc** - vazio.
- **src** - Diretório que contém os seguintes elementos:
  1. Código fonte do projeto, nomeadamente os ficheiros para as duas gramáticas, o visitor, a Main, vários tipos possiveis e 3 subdiretórios error, Tradução e SecondaryGrammarSemanticAnalysis
  2. **error** - contém os ficheiros criados pelo professor para a apanha e apresentação dos erros
  3. **Traducao** - contém o ficheiro com a tradução direta de exemplos, o Subdiretório __Primária__ que contém os ficheiros necessários ao Compilador, como o python.stg e visitor do tipo ST, e o subdiretório __Secundaria__ para o interpreter.py
  4. **SecondaryGrammarSemanticAnalysis**, que contém já os ficheiros, em Python, necessários para fazer a análise semântica da linguagem secundária. 
- **examples** - Diretório que contém os seguintes elementos: 
  1. Exemplos corretos e ilustrativos das linguagens criadas, para demonstrar quais as suas capacidades e funcionalidades.
  2. Exemplos incorretos do uso da linguagem para demonstrar a nossa capacidade de deteção de erros.
  3. Script Bash para correr e testar todos estes exemplos.


### 6. Como Compilar e Correr o Código

Para correr o código da análise semântica da gramática secundária é necessário estar no diretório /src/SecondaryGrammarSemanticAnalysis e executar os seguintes comandos:

```
  $ antlr4-build -python
  $ antlr4-run -python < ../../examples/<ficheiro_de_teste>
```

Para correr o código da análise semântica da gramática primária é necessário estar no diretório /src e executar os seguintes comandos:

```
  $ antlr4-build PDrawGrammar
  $ antlr4-run < ../examples/<ficheiro_de_teste>
```

## Contribuições
- Afonso Luis - Análise semântica da linguagem principal e secundária, criação dos ficheiros com os vários tipos
- Beatriz Ferreira - interpretador da gramática secundária e exemplos
- João Pedro - gramática principal e secundária e ficheiros exemplo
- Luís Leal - Análise semântica da linguagem secundária, criação dos ficheiros com os vários tipos
- Ricardo Martins - gramática principal e secundária, ficheiros exemplo e relatório
- Shelton Agostinho - Compilador da linguagem Primaria e Interpretador
