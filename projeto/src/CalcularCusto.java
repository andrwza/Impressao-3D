public class CalcularCusto {
    // Custo da máquina por hora
    public static double custoMaquinaPorHora(double precoImpressora, int anos, int dias, double horasPorDia) {
        double horasTotais = anos * dias * horasPorDia;
        return precoImpressora / horasTotais;
    }

    // Custo do material
    public static double custoMaterial(double gramasUtilizadas, double custoPorGrama) {
        return gramasUtilizadas * custoPorGrama;
    }

    // Custo da energia
    public static double custoEnergia(double potenciaWatts, double horasImpressao, double valorKwh) {
        double energiaKwh = (potenciaWatts / 1000.0) * horasImpressao;
        return energiaKwh * valorKwh;
    }

    // Custo de mão de obra
    public static double custoMaoObra(double horasTrabalho, double valorHora) {
        return horasTrabalho * valorHora;
    }

    // Taxa de falha 
    public static double materialComFalha(double materialPrevisto) {
        return materialPrevisto * 1.10;
    }

    // Custo total
    public static double custoTotal(double custoMaterial, double custoMaquina, double custoEnergia, double custoMaoObra, double manutencao) {
        return custoMaterial + custoMaquina + custoEnergia + custoMaoObra + manutencao;
    }

    // Margem de lucro (Valor pode alterar)
    public static double valorVenda(double custoTotal) {
        return custoTotal * 1.30;
    }
}