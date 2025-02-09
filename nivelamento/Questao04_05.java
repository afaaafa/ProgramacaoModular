import java.util.Scanner;

public class Questao04_05 {

  public static String criptografar(String mensagem){
    StringBuilder cripto = new StringBuilder();

    for(int i = 0; i < 5; i++){
      for(int j = 0; j < mensagem.length(); j += 5){
        int pos = i + j;
        if(pos >= mensagem.length()){
          cripto.append(" ");
          break;
        }
        cripto.append(mensagem.charAt(pos));
      }

      cripto.append("*");
    }

    return cripto.toString();
  }


public static String descriptografar(String cripto) {
      cripto = cripto.replace("*", "");
      StringBuilder mensagem = new StringBuilder();

      int numBlocos = cripto.length() / 5;

      for (int i = 0; i < numBlocos; i++) {
          for (int j = 0; j < 5; j++) {
            int pos = i + j * numBlocos;
            if (pos < cripto.length())
              mensagem.append(cripto.charAt(pos));
        }
      }

      return mensagem.toString();
    }

  public static void main(String[] args){
    int opcao;
    Scanner teclado = new Scanner(System.in);
    String mensagem;


    System.out.println("1 - Criptografar");
    System.out.println("2 - Descriptografar");
    System.out.print("--> Opção: ");
    opcao = teclado.nextInt();
    teclado.nextLine();

    System.out.print("\nDigite a mensagem: ");
    mensagem = teclado.nextLine();


    System.out.print("");
    switch (opcao) {
        case 1 -> System.out.println(criptografar(mensagem));
        case 2 -> System.out.println(descriptografar(mensagem));
        default -> System.out.println("Opção não existe.");
      }

    teclado.close();
  }
}