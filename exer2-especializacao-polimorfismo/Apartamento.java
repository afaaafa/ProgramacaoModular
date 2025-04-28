
import java.time.LocalDate;

public class Apartamento extends Imovel {

    private static final double ALIQUOTA_ALUGUEL = 0.004;
    private static final double DESCONTO_POR_PERIODO = 0.05;
    private static final double DESCONTO_MAXIMO = 0.30;

    private final double valorCondominio;

    public Apartamento(double valorVenda, String endereco, int anoConstrucao, Proprietario proprietario, double valorCondominio) {
        super(valorVenda, endereco, anoConstrucao, proprietario);
        this.valorCondominio = valorCondominio;
    }

    @Override
    public double calcularAluguel() {
        // Valor base do aluguel (0,4% do valor de venda)
        double valorBase = valorVenda * ALIQUOTA_ALUGUEL;

        // Aplicar desconto por idade
        double valorComDesconto = valorBase * (1 - calcularDesconto());

        // Adicionar valor dos benefícios
        return valorComDesconto + calcularValorBeneficios();
    }

    @Override
    protected double calcularDesconto() {
        int idadeImovel = LocalDate.now().getYear() - anoConstrucao;
        if (idadeImovel < 5) {
            return 0;
        }

        // Calcula quantos períodos de 5 anos se passaram após os primeiros 5 anos
        int periodos = (idadeImovel - 5) / 5 + 1;

        // Calcula o desconto (5% por período)
        double desconto = periodos * DESCONTO_POR_PERIODO;

        // Limita o desconto máximo a 30%
        return Math.min(desconto, DESCONTO_MAXIMO);
    }

    public double getValorCondominio() {
        return valorCondominio;
    }

    // Retorna o valor total a ser pago pelo inquilino (aluguel + condomínio)
    public double calcularValorTotal() {
        return calcularAluguel() + valorCondominio;
    }
}
