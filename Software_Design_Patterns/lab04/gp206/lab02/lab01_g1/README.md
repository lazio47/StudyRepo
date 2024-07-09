# Aula01 - Notes


## Exercicio 1

### How to Compile

```bash
javac *.java
```


### 1.1

```bash
java WSolver <input_file> 
```

**input_file** in this file you should have the puzzle to solve and the expected output.

### 1.2

#### If you want not write in file (just in terminal)
```bash
java WSGenerator -i <wlist_name> -s <N>
```

#### Write in file
```bash
java WSGenerator -i <wlist_name> -s <N> -o <file_out>
```


**wlist_name** this file must have the list of words that you want to put in the alphabet soup

**N** is a number that represents the size of the puzzle (width and height)

**file_out** is the name of the file that we want to create and write both the alphabet soup and the word list as well

## Ficheiros input/output
### ficheiro input
* sdl_01.txt -> (sopa de letras e lista de palavras necessárias para se usar o WSolver.java)
* wlist1.txt -> (lista de palavras separadas por ";")
* wlist2.txt -> (lista de palavras separadas maioritariamente por linha e ";")
* wlist3.txt -> (lista de palavras separadas de vária maneiras, por linhas, ";" e espaços)

### ficheiro output
* sopa1.txt  -> (output do WSGenerator.java usando o wlist1.txt, a dimensão da sopa de letras utilizada foi 12) 
* sopa2.txt  -> (output do WSGenerator.java usando o wlist2.txt, a dimensão da sopa de letras utilizada foi 20) 
* sopa3.txt  -> (output do WSGenerator.java usando o wlist3.txt, a dimensão da sopa de letras utilizada foi 30) 
* out1.txt   -> (output usando o WSolver.java que resolve a sopa1.txt)
* out2.txt   -> (output usando o WSolver.java que resolve a sopa2.txt)
* out3.txt   -> (output usando o WSolver.java que resolve a sopa3.txt)