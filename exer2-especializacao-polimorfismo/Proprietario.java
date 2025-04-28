
import java.util.ArrayList;
import java.util.List;

public class Proprietario {

    private String nome;
    private String contato;
    private List<Imovel> imoveis = new ArrayList<>();

    public Proprietario(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public void adicionarImovel(Imovel imovel) {
        imoveis.add(imovel);
    }

    public double calcularRendimentoBruto() {
        return imoveis.stream()
                .mapToDouble(Imovel::calcularAluguel)
                .sum();
    }

    public double calcularRendimentoLiquido() {
        return imoveis.stream()
                .mapToDouble(imovel -> imovel.calcularAluguel() - imovel.calcularComissao())
                .sum();
    }

    public String getNome() {
        return this.nome;
    }
}
