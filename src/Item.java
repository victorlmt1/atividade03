import java.util.regex.Pattern;

//Cliente class

public class Item {

    private String nome;
    private double preco;
    private String obs = null;

    Item(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    Item(Item item) {
        this.nome = item.getNome();
        this.preco = item.getPreco();
        this.obs = item.getObs();
    }

    Item(String arg1, String arg2) {
        if(Pattern.matches("^[0-9]+.[0-9]+$", arg1.replace(',', '.'))){
            this.preco = Double.parseDouble(arg1.replaceAll(",", "."));
            this.nome = arg2;
        } else {
            this.preco = Double.parseDouble(arg2.replaceAll(",", "."));
            this.nome = arg1;
        }
    }

    Item(String arg1, String arg2, String obs) {
        if(Pattern.matches("^[0-9]+.[0-9]+$", arg1.replace(',', '.'))){
            this.preco = Double.parseDouble(arg1.replaceAll(",", "."));
            this.nome = arg2;
        } else {
            this.preco = Double.parseDouble(arg2.replaceAll(",", "."));
            this.nome = arg1;
        }
        this.obs = obs;
    }

    void setObs(String obs) {
        this.obs = obs;
    }

    String getNome() {
        return this.nome;
    }

    double getPreco() {
        return this.preco;
    }

    String getObs() {
        return this.obs;
    }

    void print() {
        System.out.println("R$ " + String.format("%.2f", this.preco) + "             " + this.nome);
    }
}
