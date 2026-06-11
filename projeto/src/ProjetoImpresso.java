public class ProjetoImpresso {
    private String nomeArquivo;
    private String descricao;
    private double quantidadeMaterial;
    private double tempoImpressao;

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQuantidadeMaterial() {
        return quantidadeMaterial;
    }
    public void setQuantidadeMaterial(double quantidadeMaterial) {
        this.quantidadeMaterial = quantidadeMaterial;
    }

    public double getTempoImpressao() {
        return tempoImpressao;
    }
    public void setTempoImpressao(double tempoImpressao) {
        this.tempoImpressao = tempoImpressao;
    }
    
    public String getNomeArquivo() {
        return nomeArquivo;
    }
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}
