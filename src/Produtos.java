public class Produtos {
    private String nomeProduto;
    private double valorProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    @Override
    public String toString() {
        return getNomeProduto() + " - " + String.format("%.2f", valorProduto);
    }
}
