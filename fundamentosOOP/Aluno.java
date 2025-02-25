public class Aluno {
  private int cargaHorariaIdeal = 40;
  private double notaAprovacao = 60;
  private int frequencia;
  private double[] notasExercicios;
  private double[] notasProvas;
  private double notaTrabalho;
  private double notaFinal;

  private double[] setNotas(double[] notas) {
    double[] notasDefinidas = new double[notas.length];

    for (int i = 0; i < notas.length; i++) {
      notasDefinidas[i] = notas[i];
    }

    return notasDefinidas;
  }

  public Aluno(double[] notasExercicios, double[] notasProvas, double notaTrabalho, int frequencia) {
    this.frequencia = frequencia;
    this.notaTrabalho = notaTrabalho;

    // É possível usar um método do Java de copiar arrays
    this.notasExercicios = setNotas(notasExercicios);
    this.notasProvas = setNotas(notasProvas);

    setNotaFinal();
  }

  private static double calcularMedia(double[] notas, double peso) {
    double soma = 0;

    for (int i = 0; i < notas.length; i++) {
      soma += notas[i];
    }

    return (soma / notas.length) * peso;
  }

  private void setNotaFinal() {
    double mediaExercicios = calcularMedia(notasExercicios, 0.2);
    double mediaProvas = calcularMedia(notasProvas, 0.6);

    this.notaFinal = mediaExercicios + mediaProvas + this.notaTrabalho;
  }

  public double notaFinal() {
    return this.notaFinal;
  }

  public boolean isAprovado() {
    return frequencia >= cargaHorariaIdeal && notaFinal() >= notaAprovacao;
  }
}
