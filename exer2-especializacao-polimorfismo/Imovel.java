
import java.util.ArrayList;
import java.util.List;

public abstract class Imovel {

    protected double valorVenda;
    protected String endereco;
    protected int anoConstrucao;
    protected Proprietario proprietario;
    protected List<Beneficio> beneficios = new ArrayList<>();

    public Imovel(double valorVenda, String endereco, int anoConstrucao, Proprietario proprietario) {
        this.valorVenda = valorVenda;
        this.endereco = endereco;
        this.anoConstrucao = anoConstrucao;
        this.proprietario = proprietario;
    }

    public void adicionarBeneficio(Beneficio beneficio) {
        beneficios.add(beneficio);
    }

    public abstract double calcularAluguel();

    protected abstract double calcularDesconto();

    public double calcularComissao() {
        return calcularAluguel() * 0.12;
    }

    protected double calcularValorBeneficios() {
        return beneficios.stream()
                .mapToDouble(Beneficio::getValor)
                .sum();
    }

}
