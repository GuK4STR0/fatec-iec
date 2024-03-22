import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Bem vindo a calculadora rápida Java.");
        System.out.println("Digite 2 números");

        int numero1 = in.nextInt();
        int numero2 = in.nextInt();

        System.out.println("Agora digite o simbolo da operação que deseja fazer");

        String operacao = in.next();

        System.out.println("Resultado: " + calcularResultado(numero1, numero2, operacao));
    }

    private static int calcularResultado(int numero1, int numero2, String operacao){
        switch (operacao){
            case "+":
                return numero1 + numero2;
            case "-":
                return numero1 - numero2;
            case "*":
                return numero1 * numero2;
            case "/":
                if (numero2 == 0){
                    System.out.println("Divisão inválida");
                    return 0;
                }else {
                    return numero1 / numero2;
                }
            default:
                System.out.println("Números inválidos");
                return 0;
        }
    }
}