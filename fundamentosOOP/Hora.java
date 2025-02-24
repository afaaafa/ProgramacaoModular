public class Hora {
  private int horas;
  private int minutos;
  private int segundos;

  public Hora(int horas, int minutos, int segundos) {
    this.setHora(horas);
    this.setMinutos(minutos);
    this.setSegundos(segundos);
  }

  public void setHora(int hora) {
    this.horas = (hora >= 0) ? hora % 24 : 0;
  }

  public void setMinutos(int minutos) {
    this.minutos = (minutos >= 0 && minutos < 60) ? minutos : 0;
  }

  public void setSegundos(int segundos) {
    this.segundos = (segundos >= 0 && segundos < 60) ? segundos : 0;
  }

  public int getHoras() {
    return this.horas;
  }

  public int getMinutos() {
    return this.minutos;
  }

  public int getSegundos() {
    return this.segundos;
  }

  public void ajustarHora(int horas, int minutos, int segundos) {
    setHora(horas);
    setMinutos(minutos);
    setSegundos(segundos);
  }

  public Hora increamentar(int horas, int minutos, int segundos) {
    int segundosSoma = this.segundos + segundos;
    int minutosExtra = segundosSoma / 60;
    int segundosRestantes = segundosSoma % 60;

    int minutosSoma = this.minutos + minutosExtra + minutos;
    int horasExtra = minutosSoma / 60;
    int minutosRestantes = minutosSoma % 60;

    int horasFinais = (this.horas + horas + horasExtra) % 24;

    return new Hora(horasFinais, minutosRestantes, segundosRestantes);
  }

  public boolean isAdiante(Hora hora) {
    int thisEmSegundos = this.getHoras() * 3600 + this.getMinutos() * 60 + this.getSegundos();
    int outraEmSegundos = hora.getHoras() * 3600 + hora.getMinutos() * 60 + hora.getSegundos();

    return thisEmSegundos > outraEmSegundos;
  }

  public String horaFormatada() {
    return String.format("%02d:%02d:%02d", this.horas, this.minutos, this.segundos);
  }

  public static void main(String[] args) {

    Hora hora = new Hora(13, 03, 00);
    Hora hora2 = new Hora(16, 40, 50);
    Hora hora3 = hora.increamentar(8, 10, 83);
    Hora hora4 = hora.increamentar(0, 0, 0);

    System.out
        .printf("%s está adiante de %s => %b\n", hora.horaFormatada(), hora2.horaFormatada(), hora.isAdiante(hora2));

    System.out
        .printf("%s está adiante de %s => %b\n", hora3.horaFormatada(), hora2.horaFormatada(), hora3.isAdiante(hora2));

    System.out
        .printf("%s está adiante de %s => %b\n", hora4.horaFormatada(), hora.horaFormatada(), hora4.isAdiante(hora));

  }

}
