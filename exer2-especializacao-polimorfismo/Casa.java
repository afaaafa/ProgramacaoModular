
import java.time.LocalDate;

public class Casa extends Imovel {

    private static final double ALIQUOTA_ALUGUEL = 0.005; // 0,5% do valor de venda
    private static final double DESCONTO_POR_PERIODO = 0.10; // 10% a cada 5 anos
    private static final double DESCONTO_MAXIMO = 0.30; // Máximo de 30% de desconto

    public Casa(double valorVenda, String endereco, int anoConstrucao, Proprietario proprietario) {
        super(valorVenda, endereco, anoConstrucao, proprietario);
    }

    @Override
    public double calcularAluguel() {
        // Valor base do aluguel (0,5% do valor de venda)
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

        // Calcula o desconto (10% por período)
        double desconto = periodos * DESCONTO_POR_PERIODO;

        // Limita o desconto máximo a 30%
        return Math.min(desconto, DESCONTO_MAXIMO);
    }
}
