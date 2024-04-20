import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numero1 = 0;
        int numero2 = 0;
        int resultado = 0;
        String operator = "";

        System.out.println("Bem vindo a calculadora rápida Java.");
        System.out.println("Digite apenas 2 números");

        numero1 = in.nextInt();
        numero2 = in.nextInt();

        System.out.println("Agora digite o simbolo da operação");
        operator = in.next();

        resultado = calcularResultado(numero1, numero2, operator);
        System.out.println("Resultado: " + resultado);
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