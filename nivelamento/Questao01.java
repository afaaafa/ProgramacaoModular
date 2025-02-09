import java.util.Scanner;


public class Questao01 {
  public static void main(String[] args){
    int largura, altura;
    Scanner teclado = new Scanner(System.in);

    System.out.print("Digite a largura do retângulo: ");
    largura = teclado.nextInt();

    System.out.print("Digite a altura do retângulo: ");
    altura = teclado.nextInt();

    System.out.println();

    for(int i = 0; i < altura; i++){
      for(int j = 0; j < largura; j++){
        if(i == 0 || i == altura - 1 || j == 0 || j == largura - 1)
          System.out.print("X");
        else
          System.out.print(" ");
      }

      System.out.println();
    }

    System.out.println();

    teclado.close();
  }
}