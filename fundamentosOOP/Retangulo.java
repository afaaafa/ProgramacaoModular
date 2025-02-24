
public class Retangulo {
  private int largura;
  private int altura;
  private int deslocamento;
  private char borda;
  private String deslocamentoString;

  public Retangulo(int altura, int largura, int deslocamento, char borda) {
    this.altura = altura;
    this.largura = largura;
    this.deslocamento = deslocamento;
    this.borda = borda;
    setDeslocamentoString(deslocamento);

  }

  public void setBorda(char borda) {
    this.borda = borda;
  }

  public void setDeslocamento(int deslocamento) {
    this.deslocamento = deslocamento;
    setDeslocamentoString(deslocamento);
  }

  public void setDeslocamentoString(int deslocamento) {
    String desloc = "";

    for (int i = 0; i < deslocamento; i++) {
      desloc += " ";
    }

    this.deslocamentoString = desloc;
  }

  public int getDeslocamento() {
    return this.deslocamento;
  }

  public char getBorda() {
    return this.borda;
  }

  private String imprimirDeslocamento() {
    return this.deslocamentoString;
  }

  private String imprimirLinha() {
    char borda = getBorda();
    String linha = "";

    linha += borda;
    for (int i = 0; i < this.largura - 2; i++) {
      linha += " ";
    }
    linha += borda;

    return String.format("%s%s\n", imprimirDeslocamento(), linha);
  }

  private String imprimirLinhaCompleta() {
    char borda = getBorda();
    String linha = "";

    for (int i = 0; i < this.largura; i++) {
      linha += borda;
    }

    return String.format("%s%s\n", imprimirDeslocamento(), linha);
  }

  private String imprimirColunas() {
    String colunas = "";

    for (int i = 0; i < this.altura - 2; i++) {
      colunas += imprimirLinha();
    }

    return colunas;
  }

  public void imprimir() {
    System.out.print(this.imprimirLinhaCompleta());
    System.out.print(this.imprimirColunas());
    System.out.print(this.imprimirLinhaCompleta());
  }

  public static void main(String[] args) {
    Retangulo x = new Retangulo(10, 10, 10, '.');
    x.imprimir();

    x.setBorda('I');
    x.setDeslocamento(0);
    x.imprimir();

    x.setBorda('5');
    x.setDeslocamento(20);
    x.imprimir();
  }
}