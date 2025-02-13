import java.util.Scanner;

public class Questao03 {
  static Scanner teclado;

  public static double lerNumero(String mensagem){
    double numero;
    System.out.print(mensagem);
    numero = teclado.nextDouble();

    return numero;
  }

  public static void lerNotas(double[] atividades){
    for(int i = 0; i < atividades.length; i++){
      atividades[i] = lerNumero("\tNota " + (i + 1) + " :  " );
    }
  }

  public static double calcularMedia(double[] atividades, double peso){
    double media, soma = 0;
    for(int i = 0; i < atividades.length; i++){
      soma += atividades[i];
    }
    media = soma / atividades.length;
    return media * peso;
  }

  public static void main(String[] args) {
    teclado = new Scanner(System.in);

    double[] exercicios = new double[4];
    double[] provas = new double[2];
    double notaTrabalho;

    double notaFinal;

    System.out.println("\nDigite as notas dos exercícios (0 a 100): ");
    lerNotas(exercicios);

    System.out.println("Digite as notas das provas (0 a 100): ");
    lerNotas(provas);

    notaTrabalho = lerNumero("Digite a nota do trabalho prático (0 a 20): ");

    double mediaExercicios = calcularMedia(exercicios, 0.2);
    double mediaProvas = calcularMedia(provas, 0.6);

    notaFinal = mediaExercicios + mediaProvas + notaTrabalho;
    System.out.printf("\nSua nota final é: %.2f\n", notaFinal);

    teclado.close();
  }
}