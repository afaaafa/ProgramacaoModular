import java.util.Scanner;

public class Questao02 {

  public static int menu(Scanner teclado) {
    int opcao;

    System.out.println("\n1 - Somar dois números");
    System.out.println("2 - O maior de dois números");
    System.out.println("3 - Somar N números");
    System.out.println("4 - Contador de pares de uma sequência");
    System.out.println("0 - Sair");
    System.out.print("Opção: ");

    opcao = teclado.nextInt();

    return opcao;
  }

  public static int somar(Scanner teclado) {
    int num, num2, soma;

    System.out.print("Digite o primeiro número: ");
    num = teclado.nextInt();

    System.out.print("Digite o segundo número: ");
    num2 = teclado.nextInt();

    soma = num + num2;

    System.out.println("A soma de " + num + " + " + num2 + " é " + soma);

    return soma;
  }

  public static int maiorDosDois(Scanner teclado){
    int num, num2;

    System.out.print("Digite o primeiro número: ");
    num = teclado.nextInt();

    System.out.print("Digite o segundo número: ");
    num2 = teclado.nextInt();

    if(num > num2){
      System.out.println("O número " + num + " é maior que " + num2);
      return num;
    }
    else if(num2 > num){
      System.out.println("O número " + num2 + " é maior que " + num);
      return num2;
    }
    else{
      System.out.println("Os números são iguais!");
      return num;
    }
  }

  public static int somarNnumeros(Scanner teclado){
    int n, num, soma = 0;

    System.out.print("Quantos números você quer somar: ");
    n = teclado.nextInt();

    for(int i = 0; i < n; i++){
      System.out.print((i + 1) + " - Digite o número: ");
      num = teclado.nextInt();

      soma += num;
    }

    System.out.println("Resultado da soma: " + soma);
    return soma;
  }

  public static int contarPares(Scanner teclado){
    int n, num, pares = 0;

    System.out.print("Qual o tamanho da sequência: ");
    n = teclado.nextInt();

    for(int i = 0; i < n; i++){
      System.out.print((i + 1) + " - Digite o número: ");
      num = teclado.nextInt();

      if(num % 2 == 0)
        pares++;
    }

    System.out.println("A quantidade de pares nessa sequência foi: " + pares);
    return pares;
  }

  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);
    int opcao = menu(teclado);

    while (opcao != 0) {
      switch (opcao) {
        case 1 -> somar(teclado);
        case 2 -> maiorDosDois(teclado);
        case 3 -> somarNnumeros(teclado);
        case 4 -> contarPares(teclado);
        default -> System.out.println("Opção inválida! Escolha novamente.");
      }
      opcao = menu(teclado);
    }

    teclado.close();
    System.out.println("Programa encerrado.");
  }
}
