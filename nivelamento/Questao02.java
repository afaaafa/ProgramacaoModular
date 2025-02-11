import java.util.Scanner;

public class Questao02 {
  static Scanner teclado;

  public static int lerInteiro(String mensagem) {
    System.out.print(mensagem);
    int num = Integer.parseInt(teclado.nextLine());
    return num;
  }

  public static int menu() {
    int opcao;

    System.out.println("\n1 - Somar dois números");
    System.out.println("2 - O maior de dois números");
    System.out.println("3 - Somar N números");
    System.out.println("4 - Contador de pares de uma sequência");
    System.out.println("0 - Sair");
    opcao = lerInteiro("Opção: ");

    return opcao;
  }

  public static int somarDoisNumeros() {
    int num, num2, soma;

    num = lerInteiro("Digite o primeiro número: ");
    num2 = lerInteiro("Digite o segundo número: ");

    soma = num + num2;

    System.out.println("A soma de " + num + " + " + num2 + " é " + soma);

    return soma;
  }

  public static int maiorDosDois(){
    int num, num2;

    num = lerInteiro("Digite o primeiro número: ");
    num2 = lerInteiro("Digite o segundo número: ");

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

  public static int somarNnumeros(){
    int n, num, soma = 0;

    n = lerInteiro("Quantos números você quer somar: ");

    for(int i = 0; i < n; i++){
      num = lerInteiro((i + 1) + " - Digite o número: ");
      soma += num;
    }

    System.out.println("Resultado da soma: " + soma);
    return soma;
  }

  public static int contarPares(){
    int n, num, pares = 0;

    n = lerInteiro("Qual o tamanho da sequência: ");

    for(int i = 0; i < n; i++){
      num = lerInteiro((i + 1) + " - Digite o número: ");

      if(num % 2 == 0)
        pares++;
    }

    System.out.println("A quantidade de pares nessa sequência foi: " + pares);
    return pares;
  }

  public static void main(String[] args) {
    teclado = new Scanner(System.in);
    int opcao = menu();

    while (opcao != 0) {
      switch (opcao) {
        case 1 -> somarDoisNumeros();
        case 2 -> maiorDosDois();
        case 3 -> somarNnumeros();
        case 4 -> contarPares();
        default -> System.out.println("Opção inválida! Escolha novamente.");
      }
      opcao = menu();
    }

    teclado.close();
    System.out.println("Programa encerrado.");
  }
}
