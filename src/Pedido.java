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

    Pedido(final Cardapio cardapio) throws IOException {
        System.out.println("Digite o nome do cliente:");
        this.nome_cliente = scan.readLine();
        System.out.println("Digite o número da mesa:");
        this.numero_mesa = Integer.parseInt(scan.readLine());
        this.cardapio = cardapio;
        this.cardapio_itens = cardapio.getCardapio();
    }

    Pedido(final String nome_cliente, final String numero_mesa, final Cardapio cardapio, final String id)
            throws IOException {
        this.nome_cliente = nome_cliente;
        this.numero_mesa = Integer.parseInt(numero_mesa);
        this.cardapio = cardapio;
        this.cardapio_itens = cardapio.getCardapio();
        this.data = id;
    }

    Pedido(final String id, final Cardapio cardapio) throws IOException {
        this.cardapio = cardapio;
        this.cardapio_itens = cardapio.getCardapio();
        this.recuperarPedido(id);
    }

    void incluir(final String nome, final String quantidade, final String obs) {
        final int qtd = Integer.parseInt(quantidade);
        for (final Item item : cardapio_itens) {
            if (item.getNome().contains(nome)) {
                for (int j = 0; j < qtd; j++) {
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
        final List<Item> itens_encontrados = new ArrayList<Item>();
        System.out.println("--------------------------------------------------");
        while (true) {
            itens_encontrados.clear();
            System.out.println("Digite o nome do item a ser adicionado:");
            nome_item_pedido = scan.readLine();
            for (final Item item : cardapio_itens) {
                if (item.getNome().toLowerCase().contains(nome_item_pedido.toLowerCase())) {
                    itens_encontrados.add(item);
                }
            }
            if (itens_encontrados.size() == 0) {
                System.out.println("Nenhum item com nome " + nome_item_pedido + " encontrado!");
            }
            if (itens_encontrados.size() == 1) {
                break;
            }
            if (itens_encontrados.size() > 1) {
                for (final Item item_encontrado : itens_encontrados) {
                    System.out.println("O sistema encontrou: " + item_encontrado.getNome());
                }
            }
        }
        System.out.println("Digite a quantidade:");
        quantidade = scan.readLine();
        final int qtd = Integer.parseInt(quantidade);
        System.out.println("Deseja fazer alguma observação? (1 para sim/ 2 para não)");
        option4 = scan.readLine();
        final int opt4 = Integer.parseInt(option4);
        if (opt4 == 1) {
            System.out.println("Escreva a observação que deseja fazer:");
            obs = scan.readLine();
        }
        for (final Item item : cardapio_itens) {
            if (item.getNome().toLowerCase().contains(nome_item_pedido.toLowerCase())) {
                for (int j = 0; j < qtd; j++) {
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
        final List<Item> itens_encontrados = new ArrayList<Item>();
        System.out.println("--------------------------------------------------");
        while (true) {
            itens_encontrados.clear();
            System.out.println("Digite o nome do item a ser removido:");
            nome_item_pedido = scan.readLine();
            for (final Item item : pedido) {
                if (item.getNome().toLowerCase().contains(nome_item_pedido.toLowerCase())) {
                    itens_encontrados.add(item);
                }
            }
            if (itens_encontrados.size() == 0) {
                System.out.println("Nenhum item com nome " + nome_item_pedido + " encontrado!");
            }
            if (itens_encontrados.size() >= 1) {
                break;
            }
        }
        System.out.println("Digite a quantidade:");
        quantidade = Integer.parseInt(scan.readLine());
        int contador = 0;
        for (final Item item : itens_encontrados) {
            if (item.getNome().toLowerCase().contains(nome_item_pedido.toLowerCase())) {
                pedido.remove(item);
                contador++;
            }
            if (contador >= quantidade) {
                break;
            }
        }
        gravarPedido();
        imprimir();
    }

    void calcularTotal() {
        this.total = 0.00;
        for (final Item item : pedido) {
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
        for (final Item item : pedido) {
            item.print();
            if (item.getObs() != null)
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

    void setNumeroMesa(final int numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    void setNumeroMesa(final String numero_mesa) {
        this.numero_mesa = Integer.parseInt(numero_mesa);
    }

    void setNomeCliente(final String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    void setItems(final List<Item> pedido) {
        this.pedido = pedido;
    }

    void gravarPedido() {
        this.arquivo_pedido.setFolder("\\pedidos\\" + this.data + ".txt");
        calcularTotal();
        arquivo_pedido.escreverArquivo(this);
    }

    void setTotal(final String total) {
        this.total = Double.parseDouble(total);
    }

    void setData(final String data) {
        this.data = data;
    }

    void recuperarPedido(final String id) {
        final Pedido pedido_recuperado = arquivo_pedido.lerArquivo(id, cardapio);
        this.setItems(pedido_recuperado.getItems());
        this.setNumeroMesa(pedido_recuperado.getNumeroMesa());
        this.setNomeCliente(pedido_recuperado.getNomeCliente());
        this.setData(pedido_recuperado.getData());
        this.imprimir();
    }
}
