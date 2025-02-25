import java.util.Scanner;

public class Calculadora {
  private static Scanner teclado;

  public static double[] extrairValores(String[] componentes) {
    double[] valores = new double[2];

    valores[0] = Double.parseDouble(componentes[0]);
    valores[1] = Double.parseDouble(componentes[2]);

    return valores;
  }

  public static String lerCli() {
    System.out.print("Digite a operação: ");
    return teclado.nextLine();
  }

  public static double executarOperacoes(double[] valores, String operador) {
    double resultado = 0;

    switch (operador) {
      case "+" -> resultado = valores[0] + valores[1];
      case "-" -> resultado = valores[0] - valores[1];
      case "*" -> resultado = valores[0] * valores[1];
      case "/" -> resultado = valores[0] / valores[1];
    }

    return resultado;
  }

  // O comando esperado é: <valor> <operador> <valor>
  // ATENÇÃO: Existe um espaço entre os valores e o operador
  public static double interpretarLinha(String comando) {
    comando = comando.replace(",", ".");
    double resultado = 0;
    String[] componentes = comando.split(" ");

    if (componentes.length == 3) {
      double[] valores = extrairValores(componentes);
      String operador = componentes[1];
      resultado = executarOperacoes(valores, operador);
    }

    return resultado;
  }

  public static void main(String[] args) {
    teclado = new Scanner(System.in);
    String comando;
    do {
      comando = lerCli();
      double resultado = interpretarLinha(comando);
      System.out.format("%s = %.2f \n", comando, resultado);
    } while (!comando.equals("FIM"));

    teclado.close();
  }
}