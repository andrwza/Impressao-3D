public class Impressora3D {
    private String modelo;
    private double preco;
    private double potencia;
    private String img;
    private String descricao;

    // Modelo
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Preço
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Potência
    public double getPotencia() {
        return potencia;
    }
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    // Imagem
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    // Descrição
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // ComboBox utiliza esse método para exibir na tela
    @Override
    public String toString(){
        return this.modelo;
    }
}
