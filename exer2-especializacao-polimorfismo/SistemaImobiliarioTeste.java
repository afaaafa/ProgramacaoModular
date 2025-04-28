
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe única que agrupa todos os testes unitários para o sistema imobiliário.
 * Cobre os contextos: comissão da imobiliária, busca por ano e cálculo de
 * rendimentos.
 */
public class SistemaImobiliarioTeste {

    private Imobiliaria imobiliaria;
    private Proprietario joao;
    private Proprietario maria;
    private Casa casa1;
    private Apartamento apto1;
    private Apartamento apto2;

    @BeforeEach
    void configurar() {
        // Configuração inicial para todos os testes
        imobiliaria = new Imobiliaria("ImobTest");
        joao = new Proprietario("João Silva", "joao@email.com");
        maria = new Proprietario("Maria Souza", "maria@email.com");

        // Criando benefícios
        Beneficio piscina = new Beneficio("Piscina", 150.0);
        Beneficio areaLazer = new Beneficio("Área de Lazer", 100.0);
        Beneficio elevador = new Beneficio("Elevador", 80.0);
        Beneficio areaPrivativa = new Beneficio("Área Privativa", 120.0);

        // Criando imóveis
        casa1 = new Casa(300000.0, "Rua A, 123", 2010, joao);
        casa1.adicionarBeneficio(piscina);
        casa1.adicionarBeneficio(areaLazer);

        apto1 = new Apartamento(250000.0, "Av. B, 456", 2015, maria, 300.0);
        apto1.adicionarBeneficio(elevador);

        apto2 = new Apartamento(400000.0, "Av. C, 789", 2000, joao, 450.0);
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
    }

    // INÍCIO DOS TESTES DE COMISSÃO DA IMOBILIÁRIA (12% SOBRE O VALOR DO ALUGUEL)
    @Test
    @DisplayName("Teste de cálculo de comissão para casa")
    void testeCalcularComissaoCasa() {
        // Dados do teste:
        // Valor da casa: 300.000
        // Alíquota: 0,5% = 1.500
        // Idade em 2025: 15 anos (2025 - 2010)
        // Desconto: 2 períodos de 5 anos = 2 * 10% = 20%
        // Valor com desconto: 1.500 * (1 - 0,2) = 1.200
        // Benefícios: Piscina (150) + Área de Lazer (100) = 250
        // Valor total aluguel: 1.200 + 250 = 1.450
        // Comissão esperada (12%): 1.450 * 0,12 = 174

        double comissaoEsperada = 174.0;  // 12% de 1.450
        double comissaoCalculada = casa1.calcularComissao();

        assertEquals(comissaoEsperada, comissaoCalculada, 0.01,
                "A comissão calculada deve ser 12% do valor do aluguel");
    }

    @Test
    @DisplayName("Teste de cálculo de comissão para apartamento")
    void testeCalcularComissaoApartamento() {
        // Dados do teste:
        // Valor do apto: 250.000
        // Alíquota: 0,4% = 1.000
        // Idade em 2025: 10 anos (2025 - 2015)
        // Desconto: 1 período de 5 anos = 1 * 5% = 5%
        // Valor com desconto: 1.000 * (1 - 0,05) = 950
        // Benefícios: Elevador (80) = 80
        // Valor total aluguel: 950 + 80 = 1.030
        // Comissão esperada (12%): 1.030 * 0,12 = 123,6

        double comissaoEsperada = 123.6;  // 12% de 1.030
        double comissaoCalculada = apto1.calcularComissao();

        assertEquals(comissaoEsperada, comissaoCalculada, 0.01,
                "A comissão calculada deve ser 12% do valor do aluguel, sem incluir condomínio");
    }

    @Test
    @DisplayName("Teste de cálculo de comissão total da imobiliária")
    void testeCalcularComissaoTotal() {
        // Casa1: 174.0
        // Apto1: 123.6
        // Apto2: 
        // - Valor: 400.000
        // - Alíquota: 0,4% = 1.600
        // - Idade em 2025: 25 anos (2025 - 2000)
        // - Desconto: 4 períodos de 5 anos = 4 * 5% = 20% (desconto abaixo do máximo de 30%)
        // - Valor com desconto: 1.600 * (1 - 0,2) = 1.280
        // - Benefícios: Elevador (80) + Área Privativa (120) = 200
        // - Valor total aluguel: 1.280 + 200 = 1.480
        // - Comissão: 1.480 * 0,12 = 177,6
        // Total comissão: 174 + 123,6 + 177,6 = 475,2

        double comissaoTotalEsperada = 475.2;
        double comissaoTotalCalculada = imobiliaria.calcularComissaoTotal();

        assertEquals(comissaoTotalEsperada, comissaoTotalCalculada, 0.01,
                "A comissão total deve ser a soma das comissões de todos os imóveis");
    }

    // INÍCIO DOS TESTES DE BUSCA DE IMÓVEIS POR ANO DE CONSTRUÇÃO
    @Test
    @DisplayName("Teste de busca por ano existente")
    void testeBuscaPorAnoExistente() {
        List<Imovel> imoveisAno2010 = imobiliaria.buscarPorAno(2010);

        assertEquals(1, imoveisAno2010.size(),
                "Deve encontrar exatamente 1 imóvel construído em 2010");
        assertEquals(casa1, imoveisAno2010.get(0),
                "O imóvel encontrado deve ser casa1");
    }

    @Test
    @DisplayName("Teste de busca por ano com múltiplos imóveis")
    void testeBuscaPorAnoMultiplosImoveis() {
        // Adicionar outro imóvel de 2010 para teste
        Casa casa2 = new Casa(350000.0, "Rua X, 789", 2010, maria);
        imobiliaria.adicionarImovel(casa2);

        List<Imovel> imoveisAno2010 = imobiliaria.buscarPorAno(2010);

        assertEquals(2, imoveisAno2010.size(),
                "Deve encontrar exatamente 2 imóveis construídos em 2010");
        assertTrue(imoveisAno2010.contains(casa1) && imoveisAno2010.contains(casa2),
                "A lista deve conter casa1 e casa2");
    }

    @Test
    @DisplayName("Teste de busca por ano inexistente")
    void testeBuscaPorAnoInexistente() {
        List<Imovel> imoveisAno1990 = imobiliaria.buscarPorAno(1990);

        assertTrue(imoveisAno1990.isEmpty(),
                "A lista deve estar vazia pois não há imóveis de 1990");
    }

    @Test
    @DisplayName("Teste de impressão de dados dos imóveis de um ano específico")
    void testeImpressaoDadosPorAno() {
        // Redirecionar saída do System.out para capturar o resultado
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Método auxiliar para imprimir os dados de imóveis de 2010
        imobiliaria.imprimirDadosImoveisPorAno(2010);

        // Restaurar a saída padrão
        System.setOut(originalOut);

        // Verificar a saída
        String output = outputStream.toString();
        assertTrue(output.contains("Rua A, 123"),
                "A saída deve conter o endereço do imóvel de 2010");
        assertTrue(output.contains("Valor de venda: R$ 300000.0"),
                "A saída deve conter o valor de venda do imóvel");
    }

    // INÍCIO DOS TESTES DE CÁLCULO DE RENDIMENTOS DOS PROPRIETÁRIOS
    @Test
    @DisplayName("Teste de cálculo de rendimento bruto de um proprietário")
    void testeRendimentoBruto() {
        // Joao possui:
        // - casa1: 1.450
        // - apto2: 1.480
        // Total bruto esperado: 2.930

        double rendimentoBrutoEsperado = 2930.0;
        double rendimentoBrutoCalculado = joao.calcularRendimentoBruto();

        assertEquals(rendimentoBrutoEsperado, rendimentoBrutoCalculado, 0.01,
                "O rendimento bruto deve ser a soma dos aluguéis de todos os imóveis do proprietário");
    }

    @Test
    @DisplayName("Teste de cálculo de rendimento líquido de um proprietário")
    void testeRendimentoLiquido() {
        // Joao possui:
        // - casa1: 1.450 (bruto) - 174 (comissão) = 1.276 (líquido)
        // - apto2: 1.480 (bruto) - 177,6 (comissão) = 1.302,4 (líquido)
        // Total líquido esperado: 2.578,4

        double rendimentoLiquidoEsperado = 2578.4;
        double rendimentoLiquidoCalculado = joao.calcularRendimentoLiquido();

        assertEquals(rendimentoLiquidoEsperado, rendimentoLiquidoCalculado, 0.01,
                "O rendimento líquido deve ser a soma dos aluguéis menos as comissões");
    }

    @Test
    @DisplayName("Teste de exibição do balanço de um proprietário")
    void testeExibicaoBalanco() {
        // Redirecionar saída do System.out para capturar o resultado
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Executar o método a ser testado
        imobiliaria.mostrarBalanco(joao);

        // Restaurar a saída padrão
        System.setOut(originalOut);

        // Verificar se a saída contém as informações esperadas
        String output = outputStream.toString();
        assertTrue(output.contains("Balanço do Proprietário: João Silva"),
                "A saída deve conter o nome do proprietário");
        assertTrue(output.contains("Valor Bruto Total: R$ 2930.0"),
                "A saída deve conter o valor bruto correto");
        assertTrue(output.contains("Valor Líquido (após comissão): R$ 2578.4"),
                "A saída deve conter o valor líquido correto");
        assertTrue(output.contains("Comissão da Imobiliária (12%): R$ 351.6"),
                "A saída deve conter o valor da comissão correto");
    }

    // TESTES ADICIONAIS PARA VERIFICAÇÃO DE CÁLCULOS DE ALUGUEL
    @Test
    @DisplayName("Teste de desconto para imóvel com menos de 5 anos")
    void testeDescontoImovelNovo() {
        // Criar um imóvel com ano de construção recente (menos de 5 anos)
        int anoAtual = LocalDate.now().getYear();
        Casa casaNova = new Casa(400000.0, "Rua Nova, 100", anoAtual - 3, joao);

        // Para imóveis com menos de 5 anos, o desconto deve ser zero
        assertEquals(0.0, casaNova.calcularDesconto(), 0.001,
                "Imóvel com menos de 5 anos não deve ter desconto");
    }

    @Test
    @DisplayName("Teste de desconto máximo para casa muito antiga")
    void testeDescontoMaximoCasa() {
        // Criar uma casa muito antiga (50 anos)
        int anoAtual = LocalDate.now().getYear();
        Casa casaAntiga = new Casa(200000.0, "Rua Antiga, 200", anoAtual - 50, joao);

        // O desconto máximo é 30%
        assertEquals(0.3, casaAntiga.calcularDesconto(), 0.001,
                "O desconto não deve ultrapassar 30% mesmo para imóveis muito antigos");
    }

    @Test
    @DisplayName("Teste de cálculo completo do aluguel de apartamento")
    void testeCalculoApartamentoCompleto() {
        // Dados do apto2:
        // - Valor: 400.000
        // - Alíquota: 0,4% = 1.600
        // - Idade em 2025: 25 anos (2025 - 2000)
        // - Desconto: 4 períodos de 5 anos = 4 * 5% = 20%
        // - Valor com desconto: 1.600 * (1 - 0,2) = 1.280
        // - Benefícios: Elevador (80) + Área Privativa (120) = 200
        // - Valor total aluguel: 1.280 + 200 = 1.480

        double aluguelEsperado = 1480.0;
        double aluguelCalculado = apto2.calcularAluguel();

        assertEquals(aluguelEsperado, aluguelCalculado, 0.01,
                "O valor do aluguel deve considerar alíquota, desconto por idade e benefícios");

        // Valor total com condomínio (não afeta comissão ou repasse ao proprietário)
        double valorTotalEsperado = 1480.0 + 450.0; // Aluguel + condomínio
        double valorTotalCalculado = apto2.calcularValorTotal();

        assertEquals(valorTotalEsperado, valorTotalCalculado, 0.01,
                "O valor total deve incluir aluguel e condomínio");
    }
}
