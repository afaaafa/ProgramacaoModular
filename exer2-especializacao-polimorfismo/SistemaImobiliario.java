
import java.time.LocalDate;

public class SistemaImobiliario {

    public static void main(String[] args) {
        Imobiliaria imobiliaria = new Imobiliaria("ImobPro");

        // Criando proprietários
        Proprietario joao = new Proprietario("João Silva", "joao@email.com");
        Proprietario maria = new Proprietario("Maria Souza", "maria@email.com");

        // Criando benefícios
        Beneficio piscina = new Beneficio("Piscina", 150.0);
        Beneficio areaLazer = new Beneficio("Área de Lazer", 100.0);
        Beneficio elevador = new Beneficio("Elevador", 80.0);
        Beneficio areaPrivativa = new Beneficio("Área Privativa", 120.0);

        // Criando imóveis
        Casa casa1 = new Casa(300000.0, "Rua A, 123", 2010, joao);
        casa1.adicionarBeneficio(piscina);
        casa1.adicionarBeneficio(areaLazer);

        Apartamento apto1 = new Apartamento(250000.0, "Av. B, 456", 2015, maria, 300.0);
        apto1.adicionarBeneficio(elevador);

        Apartamento apto2 = new Apartamento(400000.0, "Av. C, 789", 2000, joao, 450.0);
        apto2.adicionarBeneficio(elevador);
        apto2.adicionarBeneficio(areaPrivativa);

        // Adicionando imóveis aos proprietários
        joao.adicionarImovel(casa1);
        joao.adicionarImovel(apto2);
        maria.adicionarImovel(apto1);

        // Adicionando imóveis à imobiliária
        imobiliaria.adicionarImovel(casa1);
        imobiliaria.adicionarImovel(apto1);
        imobiliaria.adicionarImovel(apto2);

        // Demonstrando o cálculo de aluguel para um apartamento
        System.out.println("======== DEMONSTRAÇÃO DO CÁLCULO DE ALUGUEL ========");
        System.out.println("Apartamento na " + apto2.endereco);
        System.out.println("Valor de venda: R$ " + apto2.valorVenda);

        // Calculando e exibindo o valor do aluguel do apartamento
        double valorAluguel = apto2.calcularAluguel();
        System.out.println("Valor base do aluguel (0,4%): R$ " + (apto2.valorVenda * 0.004));
        System.out.println("Idade do imóvel: " + (LocalDate.now().getYear() - apto2.anoConstrucao) + " anos");
        System.out.println("Desconto por idade: " + (apto2.calcularDesconto() * 100) + "%");
        System.out.println("Valor dos benefícios: R$ " + apto2.calcularValorBeneficios());
        System.out.println("Valor do aluguel: R$ " + valorAluguel);
        System.out.println("Valor do condomínio: R$ " + apto2.getValorCondominio());
        System.out.println("Valor total a pagar: R$ " + apto2.calcularValorTotal());
        System.out.println("Comissão da imobiliária (12%): R$ " + apto2.calcularComissao());

        // Demonstrando relatório de rendimentos para o proprietário
        System.out.println("\n======== RELATÓRIO DE RENDIMENTOS ========");
        imobiliaria.mostrarBalanco(joao);
    }
}
