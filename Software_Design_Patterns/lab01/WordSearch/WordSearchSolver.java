
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class WordSearchSolver {
    public static boolean validadePuzzle = true;
    final static int MAX_SIZE = 40;
    private char[][] sopa;
    private HashMap<String, ArrayList<String>> palavrasPosicaoSentido = new HashMap<String,ArrayList<String>>();
    private int matrizSize = 0;
    private  String[] palavras;
    private char[][] matrizDeOutput;
    private List<String> palavrasLista;


    public WordSearchSolver(String filename) throws FileNotFoundException {
        validadePuzzle = true;

        if (verificarPuzzle(filename)){
            this.palavrasLista = palavrasParaEncontrar(filename);
            /* Letras a maiúsculas */
            for (int i = 0; i < palavrasLista.size(); i++) {
                String termo = palavrasLista.get(i);
                palavrasLista.set(i, termo.toUpperCase());
            }

            this.palavras = palavrasLista.toArray(new String[0]);
            this.sopa = lerSopa(filename);
            this.matrizSize = sopa.length;
            procurar(sopa, palavrasLista);

            /* Ordenar para detetar substrings */
            palavrasLista = ordenarPorTamanho(Arrays.copyOf(palavras, palavras.length));
            excluirSubstring(palavrasLista);

            /* Verifica se há palavras repetidas */
            if(!puzzleValidoPorRepeticaoPalavra()){
                System.err.println("Puzzle Inválido: Palavra Repetida ou uma palavra não existe no Puzzle!");
                validadePuzzle = false;
            }
            if (validadePuzzle){
                // Criar matriz de output
                this.matrizDeOutput = matrizOutput();
            }
        }else{
            validadePuzzle = false;
            System.out.println("puzzle invalido");
        }
    }

    public char[][] getMatrizDeOutput(){
        return this.matrizDeOutput;
    }

    /* Verifica a validade do ficheiro do puzzle */
    /*
       1. Verificar se o ficheiro existe
       2. Ler a primeira linha para obter o número de colunas
       3. Para cada caracter, ver se é uma letra e se é maiúscula
       4. Ler o mesmo número de linhas que o número de colunas
       5. Verificar sempre se cada linha tem o mesmo número de caracteres que a primeira linha e se é uma letra e maiuscula
       6. Assumir que estamos a ler as palavras a encontrar
       7. ler até \n || ; || ,|| (space) || EOF
       8. Palavra tem de ser toda em minúsculas ou mista, não pode ser toda em maiúsculas
       9. Palavra nao pode ser maior que o número de colunas
    */

    static boolean verificarPuzzle(String filename) throws FileNotFoundException{

        int nColunasVerificacao = 0;
        int nColunas = 0;

       /* Verificar se o ficheiro existe */
       File file = new File(filename);
       if (!file.exists() && !file.isDirectory()) {
           System.err.println("Ficheiro não existe");
           validadePuzzle = false;
           return false;
       }

       Scanner scanner = new Scanner(file);

       /* Ler a primeira linha para obter o número de colunas */
       String data = scanner.nextLine();
       linhaVazia(data);
       nColunas = data.length();
       if (nColunas > 40 || nColunas < 0){
            validadePuzzle = false;
       }

       /* Verificar se dá para ler um quadrado de letras maiusculas */
       for (int i = 1; i < nColunas; i++) {
           if (scanner.hasNextLine()) {
               data = scanner.nextLine();
               linhaVazia(data);
               nColunasVerificacao = data.length();
               
               /* Se o numero de colunas for diferente do primeiro, o puzzle não é quadrado */
               /* Verifica também se o caracter é uma letra maiúsculas */
               if (nColunasVerificacao != nColunas || !data.matches("[A-Z]+")) {
                   System.out.println("O puzzle não é quadrado");
                   validadePuzzle = false;
                    scanner.close();
                   return false;
               }
           } else {
               /* Diferente numero de linhas e colunas, o puzzle não é quadrado */
               if (i != nColunas - 1) {
                   System.err.println("O puzzle não é quadrado");
                   validadePuzzle = false;
                   scanner.close();
                   return false;
               }
               break;
           }
       }  
       
       /* Verificação das palavras */
       if (scanner.hasNextLine()) {
           data = scanner.nextLine();
           linhaVazia(data);
           String[] palavras = data.split("[,;\\s]+");
           verificarPalavra(palavras, nColunas);
           scanner.close();
       }
       scanner.close();
       return true;    
   }

   private static void verificarPalavra(String[] palavras, int nColunas) {
    for (String palavra : palavras) {
        if (palavra.length() > nColunas) {
            System.err.println("A palavra " + palavra + " é maior que o número de colunas");
            validadePuzzle = false;
        }
        /* Se a palavra for toda de maiúsculas ou tiver caracteres especiais ou números, ela não é válida */
        if (palavra.matches("[A-Z]+") || palavra.matches(".*[^a-zA-Z].*")) {
            System.err.println("A palavra " + palavra + " não é válida");
            validadePuzzle = false;
        }
    }
   }

   private static void linhaVazia(String linha){
        if(linha.length() == 0){
            System.err.println("Puzzle inválido: Linha Vazia!");
            validadePuzzle = false;
        }
   }
   /* FIM Verifica a validade do ficheiro do puzzle */

   /* Lê o puzzle */
    private char[][] lerSopa(String filename) throws FileNotFoundException {
        List<String> linhas = new ArrayList<>();
    
        try (Scanner scanner = new Scanner(new File(filename))) {
            /* Saltar as primeiras linhas até chegar à matriz da sopa */
            while (scanner.hasNextLine() && !scanner.hasNext("[A-Z]+")) {
                scanner.nextLine();
            }
            /* Ler as linhas da matriz da sopa */
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.matches("[A-Z]+")) {
                    linhas.add(linha);
                } else {
                    break;  // Termina a leitura se encontrar algo diferente de letras maiúsculas
                }
            }
        }
    
        int numRows = linhas.size();
        int numCols = numRows > 0 ? linhas.get(0).length() : 0;
    
        /* Criar a matriz de caracteres */ 
        char[][] sopa = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                sopa[i][j] = linhas.get(i).charAt(j);
            }
        }
        /* Atualiza a variável matrizSize */
        setMatrizSize(numRows);

        return sopa;
    }
    /* FIM Lê o puzzle */

    /* Palavras a encontrar */
    static ArrayList<String> palavrasParaEncontrar(String filename) throws FileNotFoundException{
        ArrayList<String> palavras = new ArrayList<String>();
        int nLinhas = 0;

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String frasesComPalavrasEncontrar = scanner.nextLine();
        nLinhas = frasesComPalavrasEncontrar.length();

        // Para ir até à linha com as palavras a serem encontradas
        for (int i = 1; i < nLinhas; i++) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
        while (scanner.hasNextLine()) {
            frasesComPalavrasEncontrar = scanner.nextLine();
            String[] palavrasEncontrar = frasesComPalavrasEncontrar.split("[,;\\s]+");
            for (String palavra : palavrasEncontrar) {
                palavras.add(palavra);
            }
        }
        scanner.close();
        return palavras;
    }
    /* FIM Palavras a encontrar */

    /* Procura as ocorrências de cada palavra */
    private void procurar(char[][] sopa, List<String> listaPalavras){
        for (String palavra : listaPalavras) {
            palavrasPosicaoSentido.put(palavra, new ArrayList<String>());
            procurarPalavra(sopa, palavra);
        }
    }

    private void procurarPalavra(char[][] sopa, String palavra) {
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                if (sopa[i][j] == palavra.charAt(0)){

                    // Direcção x para direita
                    procurarNoSentido(i, j, sopa, palavra, 0, 1, "Right");

                    // Direcção x para esquerda
                    procurarNoSentido(i, j, sopa, palavra, 0, -1, "Left");

                    // Direcação y para a baixo
                    procurarNoSentido(i, j, sopa, palavra, 1, 0, "Down");

                    // Direcação y para a cima
                    procurarNoSentido(i, j, sopa, palavra, -1, 0, "Up");

                    // Direcação oblíqua para a esquerda baixo
                    procurarNoSentido(i, j, sopa, palavra, 1, -1, "DownLeft");

                    // Direcação oblíqua para a direita baixo
                    procurarNoSentido(i, j, sopa, palavra, 1, 1, "DownRight");

                    // Direcação oblíqua para a esquerda cima
                    procurarNoSentido(i, j, sopa, palavra, -1, -1, "UpLeft");

                    // Direcação oblíqua para a direita cima
                    procurarNoSentido(i, j, sopa, palavra, -1, 1, "UpRight");
                }
            }
        }
    }

    private void procurarNoSentido(int i, int j, char[][] sopa, String palavra, int ii, int jj, String sentido){
        if (proximaLetraEIgual(i, j, sopa, palavra, ii, jj)) {
            if(procuraLinear(sopa, palavra, 0, i, j, ii, jj)){
                palavrasPosicaoSentido.get(palavra).add((1+i)+","+(1+j)+","+sentido+","+palavra.length());
            }
        }
    }

    private static boolean proximaLetraEIgual(int i, int j, char[][] sopa, String palavra, int ii, int jj){
        boolean result = i+ii>=0 && i+ii < sopa.length && j+jj>=0 && j+jj < sopa.length && sopa[i+ii][j+jj] == palavra.charAt(1);
        return result;
    }

    private static boolean procuraLinear(char[][] sopa, String palavra, int indice, int i, int j, int ii, int jj) {
        if (palavra.charAt(indice) != sopa[i][j]){
            return false;
        }
        indice++;
        if (indice == palavra.length()){
            return true;
        }
        if (i+ii < 0 || i+ii >= sopa.length || j+jj < 0 || j+jj >= sopa.length) {
            return false;
        }
        return procuraLinear( sopa, palavra, indice, i+ii, j+jj, ii, jj);
    }

    /* FIM Procura as ocorrências de cada palavra */

    /* Eliminar Substrings */
    public void excluirSubstring(List<String> listaPalavras){
        for (int i = listaPalavras.size() - 2; i >= 0; i--){
            String palavra1 = listaPalavras.get(i);
            for (int j = i+1; j < listaPalavras.size(); j++){
                String palavra2 = listaPalavras.get(j);
                if (palavra2.contains(palavra1) && palavrasOcorrem(palavra1, palavra2)){
                    removeSubstring(palavra1, palavra2);
                }
            }
        }
    }

    private boolean palavrasOcorrem(String palavra1, String palavra2){
        return palavraOcorre(palavra1) && palavraOcorre(palavra2);
    }

    private boolean palavraOcorre(String palavra1){
        return (!palavrasPosicaoSentido.get(palavra1).isEmpty());
    }

    private void removeSubstring(String palavra1, String palavra2){
        Iterator<String> iterator = palavrasPosicaoSentido.get(palavra1).iterator();

        String palavra2PosicaoSentido = palavrasPosicaoSentido.get(palavra2).get(0);

        while(iterator.hasNext()){
            String palavra1PosicaoSentido = iterator.next();
            if(estaContido(palavra1PosicaoSentido, palavra2PosicaoSentido)){
                iterator.remove();
            }
        }
    }

    private boolean estaContido(String palavra1PosicaoSentido, String palavra2PosicaoSentido){
        int ponto1x = Integer.parseInt(palavra1PosicaoSentido.split(",")[0]);
        int ponto1y = Integer.parseInt(palavra1PosicaoSentido.split(",")[1]);
        int ponto2x = Integer.parseInt(palavra2PosicaoSentido.split(",")[0]);
        int ponto2y = Integer.parseInt(palavra2PosicaoSentido.split(",")[1]);
        int tamanhoPalavra1 = Integer.parseInt(palavra1PosicaoSentido.split(",")[3]);
        int tamanhoPalavra2 = Integer.parseInt(palavra2PosicaoSentido.split(",")[3]);
        String sentidoPalavra1 = palavra1PosicaoSentido.split(",")[2];
        String sentidoPalavra2 = palavra2PosicaoSentido.split(",")[2];

        int distanciaPontoInicial = distanciaPontoInicial(ponto1x, ponto1y, ponto2x, ponto2y);

        boolean requisito1 = (distanciaPontoInicial <= tamanhoPalavra2);
        boolean requisito2 = naMesmaLinha(palavra1PosicaoSentido, palavra2PosicaoSentido) && condicaoParaMesmoSentido(sentidoPalavra1, sentidoPalavra2, tamanhoPalavra1, tamanhoPalavra2, distanciaPontoInicial);
        boolean requisito3 = mesmaLinhaEContida(palavra1PosicaoSentido, palavra2PosicaoSentido);
        return requisito1 && requisito2 || requisito1 && requisito3;
    }

    private static int distanciaPontoInicial(int ponto1x, int ponto1y, int ponto2x, int ponto2y) {
        return Math.abs(ponto2y - ponto1y) != 0 ? 
        Math.abs(ponto2y - ponto1y) : 
        Math.abs(ponto2x - ponto1x);
    }

    private static boolean condicaoParaMesmoSentido(String sentido1, String sentido2, int tamanho1, int tamanho2, int distancia){
        return (sentido1.equals(sentido2) && tamanho2 >= (tamanho1 + distancia));
    }

    private boolean mesmaLinhaEContida(String palavra1PosicaoSentido, String palavra2PosicaoSentido){
        return naMesmaLinha(palavra1PosicaoSentido, palavra2PosicaoSentido) && pontoFinalContido(palavra1PosicaoSentido, palavra2PosicaoSentido) && mesmaDireccao(palavra1PosicaoSentido, palavra2PosicaoSentido);
    }

    private boolean pontoFinalContido(String palavra1PosicaoSentido, String palavra2PosicaoSentido){
        int ponto1x = Integer.parseInt(palavra1PosicaoSentido.split(",")[0])-1;
        int ponto1y = Integer.parseInt(palavra1PosicaoSentido.split(",")[1])-1;
        int ponto2x = Integer.parseInt(palavra2PosicaoSentido.split(",")[0])-1;
        int ponto2y = Integer.parseInt(palavra2PosicaoSentido.split(",")[1])-1;
        int tamanhoPalavra1 = Integer.parseInt(palavra1PosicaoSentido.split(",")[3]);
        String sentido = palavra1PosicaoSentido.split(",")[2];
        String sentido2 = palavra2PosicaoSentido.split(",")[2];

        if(ponto1x == ponto2x && ponto1y == ponto2y && (!sentido.equals(sentido2))) {
            return false;
        }

        int deltax = variacaoEmX(sentido);
        int deltay = variacaoEmY(sentido);
        int ponto1xAux = ponto1x;
        int ponto1yAux = ponto1y;

        for(int i = 0; i < tamanhoPalavra1; i++) {
            ponto1xAux += deltax;
            ponto1yAux += deltay;
        }
        if (ponto1x > ponto2x && ponto1xAux < ponto2x){
            return false;
        }
        if (ponto1x < ponto2x && ponto1xAux > ponto2x){
            return false;
        }
        if (ponto1y > ponto2y && ponto1yAux < ponto2y){
            return false;
        }
        if (ponto1y < ponto2y && ponto1yAux > ponto2y){
            return false;
        }
        return true;
    }

    private static boolean mesmaDireccao(String palavra1PosicaoSentido, String palavra2PosicaoSentido){
        String sentido1 = palavra1PosicaoSentido.split(",")[2];
        String sentido2 = palavra2PosicaoSentido.split(",")[2];

        switch (sentido1) {
            case "Up":
            case "Down":
                return sentido2.equals("Up") || sentido2.equals("Down") ? true : false;
            case "Right":
            case "Left":
                return sentido2.equals("Right") || sentido2.equals("Left") ? true : false;
            case "UpRight":
            case "DownLeft":
                return sentido2.equals("UpRight") || sentido2.equals("DownLeft") ? true : false;
            case "UpLeft":
            case "DownRight":
                return sentido2.equals("UpLeft") || sentido2.equals("DownRight") ? true : false;
            default:
                System.err.println("ERROR: Direcao invalida: "+sentido1);
                return false;
        }
    }

    private boolean naMesmaLinha(String palavra1PosicaoSentido, String palavra2PosicaoSentido){
        int ponto1x = Integer.parseInt(palavra1PosicaoSentido.split(",")[0]) - 1;
        int ponto1y = Integer.parseInt(palavra1PosicaoSentido.split(",")[1]) - 1;
        int ponto2x = Integer.parseInt(palavra2PosicaoSentido.split(",")[0]) - 1;
        int ponto2y = Integer.parseInt(palavra2PosicaoSentido.split(",")[1]) - 1;
        String sentido = palavra1PosicaoSentido.split(",")[2];

        int deltax = variacaoEmX(sentido);
        int deltay = variacaoEmY(sentido);
        int ponto1xAux = ponto1x;
        int ponto1yAux = ponto1y;

        while(ponto1xAux < matrizSize && ponto1xAux >= 0 && ponto1yAux < matrizSize && ponto1yAux >= 0) {
            if (ponto1xAux == ponto2x && ponto1yAux == ponto2y){
                return true;
            }
            ponto1xAux += deltax;
            ponto1yAux += deltay;
        }

        ponto1xAux = ponto1x;
        ponto1yAux = ponto1y;

        while(ponto1xAux < matrizSize && ponto1xAux >= 0 && ponto1yAux < matrizSize && ponto1yAux >= 0) {
            if (ponto1xAux == ponto2x && ponto1yAux == ponto2y){
                return true;
            }
            ponto1xAux -= deltax;
            ponto1yAux -= deltay;
        }

        return false;
    }

    private static int variacaoEmY(String sentido){
        if(sentido.contains("Right")){
            return 1;
        }
        if(sentido.contains("Left")){
            return -1;
        }
        return 0;
    }

    private static int variacaoEmX(String sentido){
        if(sentido.contains("Down")){
            return 1;
        }
        if(sentido.contains("Up")){
            return -1;
        }
        return 0;
    }

    /* FIM Eliminar Substrings */

    /* Resultados e Output */

    public void printMatriz() {
        char[][] matriz = getMatrizDeOutput();
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public char[][] matrizOutput(){
        char[][] matrizOutput = new char[matrizSize][matrizSize];
        for (int i = 0; i < matrizSize; i++){
            for (int j = 0; j < matrizSize; j++){
                matrizOutput[i][j] = '.';
            }
        }
        for(String palavra : palavras){
            if (palavraOcorre(palavra)){
                for (int i = 0; i < palavrasPosicaoSentido.get(palavra).size(); i++){
                    matrizOutput = introduzirPalavraNaMatrizOutput(palavra, matrizOutput);
                }
            }
        }
        return matrizOutput;
    }

    public void printOutput(){
        System.out.println("");
        for(String palavra : palavras) {
            if(palavraOcorre(palavra)){
                String[] palavraDadosArray = palavrasPosicaoSentido.get(palavra).get(0).split(",");
                System.out.printf("%-"+matrizSize+"s \t %-2s \t %s,%-2s \t %s\n", palavra, palavraDadosArray[3], palavraDadosArray[0], palavraDadosArray[1], palavraDadosArray[2]);
            }
        }
        System.out.println("");
    }

    private char[][] introduzirPalavraNaMatrizOutput(String palavra, char[][] matrizOutput){
        String[] dadosPalavra = palavrasPosicaoSentido.get(palavra).get(0).split(",");
        int pontox = Integer.parseInt(dadosPalavra[0])-1;
        int pontoy = Integer.parseInt(dadosPalavra[1])-1;
        String sentido = dadosPalavra[2];
        int deltay = variacaoEmY(sentido);
        int deltax = variacaoEmX(sentido);

        int tamanhoPalavra = palavra.length();
        for( int indice = 0; indice < tamanhoPalavra; indice++){
            matrizOutput[pontox][pontoy] = palavra.charAt(indice);
            pontox += deltax;
            pontoy += deltay;
        }
        return matrizOutput;
    }
    /* FIM Resultados e Output */

    /* Ordenar para facilitar operações */
    private static List<String> ordenarPorTamanho(String[] palavras){
        List<String> listaPalavras = Arrays.asList(palavras);
        Comparator<String> comaparadorPorTamanho = Comparator.comparingInt(String::length);
        listaPalavras.sort(comaparadorPorTamanho);
        return listaPalavras;
    }
    /* FIM Ordenar para facilitar operações */

    /* Verifica se há palavras repetidas */
    public boolean puzzleValidoPorRepeticaoPalavra(){
        for (String palavra : palavrasPosicaoSentido.keySet()){
            if(palavrasPosicaoSentido.get(palavra).isEmpty()){
                return false;
            }
            if(isPalindromo(palavra) && (palavrasPosicaoSentido.get(palavra).size() > 2)) {
                return false;
            }else if(!isPalindromo(palavra) && palavrasPosicaoSentido.get(palavra).size() > 1){
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindromo(String palavra) {
        String palavraInvertida = new StringBuilder(palavra).reverse().toString();
        return palavra.equals(palavraInvertida);
    }
    /* FIM Verifica se há palavras repetidas */

    public static int getMaxSize() {
        return MAX_SIZE;
    }

    public char[][] getSopa() {
        return sopa;
    }

    public void setSopa(char[][] sopa) {
        this.sopa = sopa;
    }

    public HashMap<String, ArrayList<String>> getPalavrasPosicaoSentido() {
        return palavrasPosicaoSentido;
    }

    public void setPalavrasPosicaoSentido(HashMap<String, ArrayList<String>> palavrasPosicaoSentido) {
        this.palavrasPosicaoSentido = palavrasPosicaoSentido;
    }

    public int getMatrizSize() {
        return matrizSize;
    }

    public void setMatrizSize(int matrizSize) {
        this.matrizSize = matrizSize;
    }

    public String[] getPalavras() {
        return palavras;
    }

    public void setPalavras(String[] palavras) {
        this.palavras = palavras;
    }

    public void setMatrizDeOutput(char[][] matrizDeOutput) {
        this.matrizDeOutput = matrizDeOutput;
    }

    public List<String> getPalavrasLista() {
        return palavrasLista;
    }

    public void setPalavrasLista(List<String> palavrasLista) {
        this.palavrasLista = palavrasLista;
    }
}
