import java.util.Scanner;

public class Questao04_05 {
  static Scanner teclado;

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

  public static int menu(){
    int opcao;

    System.out.println("1 - Criptografar");
    System.out.println("2 - Descriptografar");
    System.out.println("0 - Para sair do programa");
    opcao = lerInteiro("---> Opção: ");

    return opcao;
  }

  public static int lerInteiro(String mensagem) {
    System.out.print(mensagem);
    int num = Integer.parseInt(teclado.nextLine());
    return num;
  }

  public static String lerMensagem(String mensagem) {
    System.out.print("\n" + mensagem);
    mensagem = teclado.nextLine();
    return mensagem;
  }

  public static void main(String[] args){
    int opcao;
    teclado = new Scanner(System.in);

    do{
      String resposta = "Essa opção não existe!";
      opcao = menu();

      resposta = switch (opcao) {
        case 1 -> criptografar(lerMensagem("Digite sua frase: "));
        case 2 -> descriptografar(lerMensagem("Digite sua frase: "));
        case 0 -> "Saindo do programa...";
        default -> "";
      };

      System.out.println(resposta + "\n");
    }while(opcao != 0);


    teclado.close();
  }
}