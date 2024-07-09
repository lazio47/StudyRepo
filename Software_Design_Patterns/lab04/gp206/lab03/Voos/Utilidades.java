

public class Utilidades {
    public static Type criarType(String string) {
        switch (string) {
            case "E":
                return Type.E;
            case "T":
                return Type.T;
            default:
                System.err.println("Tipo de classe inválido!");
                return Type.NONE; // Certifique-se de que "NONE" é uma opção válida em seu enum Type ou ajuste conforme necessário.
        }
    }
}
