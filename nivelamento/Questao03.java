import java.util.Scanner;

public class Questao03 {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);

    float[] exercicios = new float[4];
    float[] provas = new float[2];
    float trabalho;

    float somaExercicios = 0;
    float somaProvas = 0;
    float notaFinal;

    System.out.println("Digite as notas dos exercícios (0 a 100): ");
    for (int i = 0; i < exercicios.length; i++) {
      System.out.print(String.format("Exercício %02d: ", i + 1));
      exercicios[i] = teclado.nextFloat();
      somaExercicios += exercicios[i];
    }

    System.out.println("\nDigite as notas das provas (0 a 100): ");
    for (int i = 0; i < provas.length; i++) {
      System.out.print(String.format("Prova %02d: ", i + 1));
      provas[i] = teclado.nextFloat();
      somaProvas += provas[i];
    }

    System.out.print("\nDigite a nota do trabalho prático (0 a 20): ");
    trabalho = teclado.nextFloat();

    teclado.close();

    float mediaExercicios = somaExercicios / exercicios.length;
    float mediaProvas = somaProvas / provas.length;

    float trabalhoAjustado = (trabalho / 20) * 100;

    notaFinal = (mediaExercicios * 0.2f) + (mediaProvas * 0.6f) + (trabalhoAjustado * 0.2f);

    System.out.println(String.format("\nSua nota final é: %.2f", notaFinal));
  }
}