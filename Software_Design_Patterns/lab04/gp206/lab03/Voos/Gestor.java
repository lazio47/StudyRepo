
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Gestor {
    static HashMap<String, Voos> voos = new HashMap<>();

   
    public static void tratamentoOption(String comando, String[] options){
        switch (comando){
            case "H":
                if(options.length!=1){
                    System.err.println("Erro: Esta opção não aceita argumentos.\n");
                    return;
                }
                Menu.menu();
                break;

            case "I":
                if(options.length!=2){
                    System.err.println("Usage: I <Voos/{filename}>\n");
                    return;
                }
                String filename = options[1];
                readFile(filename);
                System.out.println();
                break;

            case "M":
                if(options.length!=2){
                    System.err.println("Usage: M <codigo_Voo>\n");
                    return;
                }
                String codigo = options[1];
                Voos voo = voos.get(codigo);
                if (voo == null){
                    System.err.println("Código Do Voo Inexistente!\n");
                    return;
                }
                voo.printMap();
                System.out.println();
                break;

            case "F":
                if(options.length!=3 && options.length != 4){
                    System.err.println("Usage: f <code_Voo> <num_seats_executive>(optional) <num_seats_tourist>\n");
                    return;
                }
                novoVoo(options);
                System.out.println();
                break;

            case "R":
                if (options.length != 4) {
                    System.err.println("Usage: f <code_Voo> <Class> <number_seats>\n");
                    return;
                }
                novaReserva(options);
                System.out.println("Reserva feita com sucesso!\n");
                break;

            case "C":
                if (options.length != 2) {
                    System.err.println("Usage: f <code_Voo:reservation_code>\n");
                    return;
                }
                if (removeRes(options)) {
                    System.out.println("Reserva cancelada com sucesso!\n");
                }
                break;

            case "Q":
                System.out.println("Conclusão de saida do programa!");
                System.exit(0);
                break;

            default:
                System.err.println("ERRO: Invalid Option!\n");
        }
    }

    private static boolean removeRes(String[] info) {
        String[] options = info[1].split(":");
        String codigo = options[0];
        if (!(voos.containsKey(codigo))) {
            System.err.println("Voo enexistente");
            return false;
        }
        Voos voo = voos.get(codigo);
        int reservation_code;
       
        try{
            reservation_code= Integer.parseInt(options[1]);
        }catch (Exception e){
            System.err.println("Número de reserva inválido!");
            return false;
        }
         boolean foundIt = voo.removeReservation(reservation_code);
        if (!foundIt) {
            System.err.println("Reserva não encontrada!");
            return false;
        }
        return true;
    }

    private static void readFile(String filename) {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            String[] firstLine = sc.nextLine().split("\\s+");

            if ((firstLine[0].charAt(0) != '>') || (firstLine.length > 3)) {
                System.err.println("Ficheiro Mal Formatado (nº x nº)");
            }

            String codigo = firstLine[0].substring(1); 
            if (voos.containsKey(codigo)) {
                System.err.println("igo já existente!");

            }
            int numFilasE = 0, numLugaresE = 0;
            int indiceTur;

            if (firstLine.length == 3){
                String[] executive = firstLine[1].split("x");
                numFilasE = Integer.parseInt(executive[1]);
                numLugaresE = Integer.parseInt(executive[0]);
                indiceTur = 2;
            }else{
                indiceTur = 1;
            }

            int numFilasT, numLugaresT;
            String[] T = firstLine[indiceTur].split("x");
            numFilasT = Integer.parseInt(T[1]);
            numLugaresT = Integer.parseInt(T[0]);

            //criar aviao e voo
            int[][] exec = new int[numFilasE][numLugaresE];
            int[][] tur = new int[numFilasT][numLugaresT];
            Aviao aviao = new Aviao(exec, tur);
            Voos voo = new Voos(codigo, aviao);

            voos.put(codigo,voo);

            ArrayList<String> badReservas = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] info = line.split(" ");

                boolean isValid = addRes(codigo, info);

                if (!isValid && info.length == 2) 
                    badReservas.add(info[0] + " " + info[1]);

            }
            sc.close();
            System.out.print("Não foi possível obter lugares para a reserva: ");
            for (String badReserva : badReservas)
                System.out.println(badReserva + "; "); 
        }catch (FileNotFoundException e){
            System.err.println("Ficheiro não encontrado!");
        }catch (NumberFormatException e) {
            System.err.println("Mau formato!");
        }
    }
  

    //cria um voo
    private static void novoVoo(String[] options){
        String codigo = options[1];
        try{
            if (voos.containsKey(codigo)) {
                System.err.println("Código já existente!");
                return;
            }
            int numFilasE = 0, numLugaresE = 0;
            int indiceTur;

            if (options.length == 4){
                String[] executive = options[2].split("x");
                numFilasE = Integer.parseInt(executive[1]);
                numLugaresE = Integer.parseInt(executive[0]);
                indiceTur = 3;
            }else{
                indiceTur = 2;
            }

            int numFilasT, numLugaresT;
            String[] T = options[indiceTur].split("x");
            numFilasT = Integer.parseInt(T[1]);
            numLugaresT = Integer.parseInt(T[0]);

            int[][] exec = new int[numFilasE][numLugaresE];
            int[][] tur = new int[numFilasT][numLugaresT];
            Aviao aviao = new Aviao(exec, tur);
            Voos voo = new Voos(codigo, aviao);
            voos.put(codigo,voo);

        }catch (Exception e){
            System.err.println("Mau formato!");
        }

    }

    private static void novaReserva(String[] options) {
        String codigo = options[1];
        boolean isValid = addRes(codigo, new String[]{options[2], options[3]});
        if (!isValid) {
            System.err.println("Não é possível colocar a reserva!");
            return;
        }
        Voos voo = voos.get(codigo);
    
        Type type = Utilidades.criarType(options[2]);
        if (type == Type.NONE) {
            return;
        }
    
        int numSeats = Integer.parseInt(options[3]);
        voo.setReservation(numSeats, type);
    }

    //validaçao de uma reserva
    private static boolean addRes(String codigo, String[] infoRes) {
        Type Type = criarType(infoRes[0]);
        if(Type == Type.NONE){return false;}
        boolean madeRes = false;

        if (voos.containsKey(codigo))
            madeRes = voos.get(codigo).setReservation(Integer.parseInt(infoRes[1]), Type);
        return madeRes;
    }
   

    private static Type criarType(String string) {
        switch (string){
            case "E":
                return Type.E;
            case "T":
                return Type.T;
            default:
                System.err.println("Tipo de classe inválido!");
                return Type.NONE;
        }
    }

    public static void main(String[] args)  {
        Scanner sc;
        File comandos;
        boolean lerFile = false;
        if (args.length < 1){
            Menu.menu();
            sc = new Scanner(System.in);
        }
        else if (args.length == 1){
            lerFile = true;
            comandos = new File(args[0]);
            try{
                sc = new Scanner(comandos);
            }catch (FileNotFoundException e) {
                System.err.println("Erro: Ficheiro de comandos não encontrado!");
                return;
            }
        } else{
            System.err.println("Erro: Aceitar Ficheiro de comandos ou nenhum argumento!");
            return;
        }

        String[] options; 

        while (true){
            if (!lerFile){
                System.out.println("Escolha uma opção: (H para ajuda)");
            }
            options = sc.nextLine().split("\\s+");
            String command = options[0].toUpperCase();
            if(args.length == 1){
                for (int i = 0; i< options.length; i++)
                    System.out.print(options[i] + " ");
                System.out.println();
            }
            tratamentoOption(command,options);
        }
    }
}