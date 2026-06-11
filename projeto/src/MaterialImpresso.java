public class MaterialImpresso {

    private String material;
    private double densidade;
    private double c_grama;

    // Material
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    // Densidade
    public double getDensidade() {
        return densidade;
    }

    public void setDensidade(double densidade) {
        this.densidade = densidade;
    }

    // Custo por grama
    public double getC_grama() {
        return c_grama;
    }

    public void setC_grama(double c_grama) {
        this.c_grama = c_grama;
    }
}