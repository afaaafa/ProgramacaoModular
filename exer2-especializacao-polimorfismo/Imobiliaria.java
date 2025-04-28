
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Imobiliaria {

    private String nome;
    private List<Imovel> imoveis = new ArrayList<>();

    public Imobiliaria(String nome) {
        this.nome = nome;
    }

    public void adicionarImovel(Imovel imovel) {
        imoveis.add(imovel);
    }

    public List<Imovel> buscarPorAno(int ano) {
        return imoveis.stream()
                .filter(imovel -> imovel.anoConstrucao == ano)
                .collect(Collectors.toList());
    }

    public double calcularComissaoTotal() {
        return imoveis.stream()
                .mapToDouble(Imovel::calcularComissao)
                .sum();
    }

    public void mostrarBalanco(Proprietario proprietario) {
        double valorBruto = proprietario.calcularRendimentoBruto();
        double valorLiquido = proprietario.calcularRendimentoLiquido();

        System.out.println("Balanço do Proprietário: " + proprietario.getNome());
        System.out.println("Valor Bruto Total: R$ " + valorBruto);
        System.out.println("Valor Líquido (após comissão): R$ " + valorLiquido);
        System.out.println("Comissão da Imobiliária (12%): R$ " + (valorBruto - valorLiquido));
    }
}
