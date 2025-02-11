import java.util.Scanner;


public class Questao01 {

  static Scanner teclado;

  public static int lerInteiro(String mensagem) {
    System.out.print(mensagem);
    int num = Integer.parseInt(teclado.nextLine());
    return num;
  }

  public static void imprimirLinhaCheia(int tamanho) {
    for (int i = 0; i < tamanho; i++){
      System.out.print("X");
    }
    System.out.println();
  }

  public static void imprimirLinhaEspacos(int tamanho) {
    System.out.print("X");
    for(int i = 0; i < tamanho - 2; i++){
      System.out.print(" ");
    }
    System.out.println("X");

  }

  public static void main(String[] args){
    int largura, altura;
    teclado = new Scanner(System.in);

    largura = lerInteiro("Digite a largura do retângulo: ");
    altura = lerInteiro("Digite a altura do retângulo: ");

    System.out.println();

    imprimirLinhaCheia(largura);
    for(int i = 0; i < altura - 2; i++){
      imprimirLinhaEspacos(largura);
    }
    imprimirLinhaCheia(largura);

    System.out.println();

    teclado.close();
  }
}