import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Pedido
 {

    String nome_cliente;
    int numero_mesa;
    double total;
    Cardapio cardapio;
    List<Item> cardapio_itens;
    List<Item> pedido = new ArrayList<Item>();
    List<Pedido> relatorio_pedidos = new ArrayList<Pedido>();
    SimpleDateFormat formatacao_data = new SimpleDateFormat("yyyymmddHHmmss");
    String data = formatacao_data.format(new Date()).trim();
    Arquivo arquivo_pedido = new Arquivo(":");    
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

    Pedido(Cardapio cardapio) throws IOException {
        System.out.println("Digite o nome do cliente:");
        this.nome_cliente = scan.readLine();
        System.out.println("Digite o número da mesa:");
        this.numero_mesa = Integer.parseInt(scan.readLine());
        this.cardapio = cardapio;
        this.cardapio_itens = cardapio.getCardapio();
    }

    Pedido(String nome_cliente, String numero_mesa, Cardapio cardapio, String id) throws IOException {
        this.nome_cliente = nome_cliente;
        this.numero_mesa = Integer.parseInt(numero_mesa);
        this.cardapio = cardapio;
        this.cardapio_itens = cardapio.getCardapio();
        this.data = id;
    }

    Pedido(String id, Cardapio cardapio) throws IOException {
        this.cardapio = cardapio;
        this.cardapio_itens = cardapio.getCardapio();
        this.recuperarPedido(id);
    }

    void incluir(String nome, String quantidade, String obs) {
        int qtd = Integer.parseInt(quantidade);
        for(Item item : cardapio_itens) {
            if(item.getNome().contains(nome)) {
                for(int j = 0; j < qtd; j++) {
                    item.setObs(obs);
                    pedido.add(new Item(item));
                }
                item.setObs(null);
            }
        }
        gravarPedido();
        imprimir();
    }

    void incluir() throws IOException {
        String nome_item_pedido, option4, quantidade, obs = null;        
        List<Item> itens_encontrados = new ArrayList<Item>();
        System.out.println("--------------------------------------------------");
        while(true){
            itens_encontrados.clear();
            System.out.println("Digite o nome do item a ser adicionado:");
            nome_item_pedido = scan.readLine();
            for(Item item : cardapio_itens){
                if(item.getNome().toLowerCase().contains(nome_item_pedido.toLowerCase())){
                    itens_encontrados.add(item);
                }
            }
            if(itens_encontrados.size() == 0){
                System.out.println("Nenhum item com nome " + nome_item_pedido + " encontrado!");
            }
            if(itens_encontrados.size() == 1){
                break;
            }
            if(itens_encontrados.size() > 1){
                for(Item item_encontrado : itens_encontrados){
                    System.out.println("O sistema encontrou: "+ item_encontrado.getNome());
                }
            }
        }
        System.out.println("Digite a quantidade:");
        quantidade = scan.readLine();
        int qtd = Integer.parseInt(quantidade);
        System.out.println("Deseja fazer alguma observação? (1 para sim/ 2 para não)");
        option4 = scan.readLine();
        int opt4 = Integer.parseInt(option4);
        if(opt4 == 1) {
            System.out.println("Escreva a observação que deseja fazer:");
            obs = scan.readLine();
        }
        for(Item item : cardapio_itens) {
            if(item.getNome().toLowerCase().contains(nome_item_pedido.toLowerCase())) {
                for(int j = 0; j < qtd; j++) {
                    item.setObs(obs);
                    pedido.add(new Item(item));
                }
                item.setObs(null);
            }
        }
        gravarPedido();
        imprimir();
    }

    void remover() throws NumberFormatException, IOException {
        String nome_item_pedido;
        int quantidade;
        List<Item> itens_encontrados = new ArrayList<Item>();
        System.out.println("--------------------------------------------------");
        while(true){
            itens_encontrados.clear();
            System.out.println("Digite o nome do item a ser removido:");
            nome_item_pedido = scan.readLine();
            for(Item item : pedido){
                if(item.getNome().toLowerCase().contains(nome_item_pedido.toLowerCase())){
                    itens_encontrados.add(item);
                }
            }
            if(itens_encontrados.size() == 0){
                System.out.println("Nenhum item com nome " + nome_item_pedido + " encontrado!");
            }
            if(itens_encontrados.size() >= 1){
                break;
            }
        }
        System.out.println("Digite a quantidade:");
        quantidade = Integer.parseInt(scan.readLine());
        int contador = 0;        
        for(Item item : itens_encontrados) {
            if(item.getNome().toLowerCase().contains(nome_item_pedido.toLowerCase())) {
                pedido.remove(item);
                contador++;
            }
            if(contador >= quantidade) {
                break;
            }                    
        }
        gravarPedido();
        imprimir();
    }

    void calcularTotal(){
        this.total = 0.00;
        for(Item item : pedido){
            this.total += item.getPreco();
        }
    }

    void imprimir() {
        calcularTotal();        
        System.out.println("----------------------COMANDA---------------------");        
        System.out.println("Cliente: " + this.nome_cliente);        
        System.out.println("Mesa: " + this.numero_mesa);
        System.out.println("ID: " + this.data);        
        System.out.println("----------------------PEDIDO---------------------");
        for(Item item : pedido) {
            item.print();
            if(item.getObs() != null)
                System.out.println("OBS: " + item.getObs());
        }
        System.out.println("TOTAL: R$ " + String.format("%.2f", this.total));
    }

    int getNumeroMesa() {
        return this.numero_mesa;
    }

    String getNomeCliente() {
        return this.nome_cliente;
    }

    List<Item> getItems() {
        return this.pedido;
    }

    double getTotal() {
        return this.total;
    }

    String getData() {
        return this.data;
    }

    void setNumeroMesa(int numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    void setNumeroMesa(String numero_mesa) {
        this.numero_mesa = Integer.parseInt(numero_mesa);
    }

    void setNomeCliente(String nome_cliente){
        this.nome_cliente = nome_cliente;
    }

    void setItems(List<Item> pedido){
        this.pedido = pedido;
    }

    void gravarPedido() {
        this.arquivo_pedido.setFolder("pedidos\\"+this.data+".txt");
        calcularTotal();
        arquivo_pedido.escreverArquivo(this);
    }

    void setTotal(String total) {
        this.total = Double.parseDouble(total);
    }
    
    void setData(String data){
        this.data = data;
    }

    void recuperarPedido(String id) {
        Pedido pedido_recuperado = arquivo_pedido.lerArquivo(id, cardapio);
        this.setItems(pedido_recuperado.getItems());
        this.setNumeroMesa(pedido_recuperado.getNumeroMesa());
        this.setNomeCliente(pedido_recuperado.getNomeCliente());
        this.setData(pedido_recuperado.getData());
        this.imprimir();
    }
}
