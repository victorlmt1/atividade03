import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pedido
 {

    String nome_cliente;
    int numero_mesa;
    double total;
    Cardapio cardapio;
    List<Item> lista_combinada = new ArrayList<Item>();
    List<Item> pedido = new ArrayList<Item>();
    List<Pedido> relatorio_pedidos = new ArrayList<Pedido>();


    Pedido(String nome_cliente, int numero_mesa, Cardapio cardapio) throws IOException {
        this.nome_cliente = nome_cliente;
        this.numero_mesa = numero_mesa;
        this.cardapio = cardapio;
        this.total = 0.00;       
        this.lista_combinada.addAll(cardapio.retornarPratos());
        this.lista_combinada.addAll(cardapio.retornarBebidas());
        this.lista_combinada.addAll(cardapio.retornarVinhos());
    }

    void incluir(int identificador, int quantidade, String obs) {
        for(int i = 0; i < lista_combinada.size(); i++) {
            Item item = lista_combinada.get(i);
            if(item.getId() == identificador) {
                for(int j = 0; j < quantidade; j++) {
                    item.setObs(obs);
                    pedido.add(item);
                    total += item.getPrice();
                }
                item.setObs(null);
            }
        }
        imprimir();
    }

    void remover(int identificador, int quantidade) {
        for(int i = 0; i < lista_combinada.size(); i++) {
            Item item = lista_combinada.get(i);
            if(item.getId() == identificador) {
                for(int j = 0; j < pedido.size(); j++) {
                    Item item_pedido = pedido.get(j);
                    int contador = 0;
                    if(item_pedido == item) {
                        pedido.remove(j);
                        contador++;
                        total -= item.getPrice();
                    }
                    if(contador >= quantidade) {
                        break;
                    }                    
                }
            }
        }
        imprimir();
    }

    void imprimir() {        
        System.out.println("----------------------COMANDA---------------------");        
        System.out.println("Cliente: " + this.nome_cliente);        
        System.out.println("Mesa: " + this.numero_mesa);        
        System.out.println("----------------------PEDIDO---------------------");
        for(int i = 0; i < pedido.size(); i++) {
            Item item = pedido.get(i);
            String name = item.getName();
            double price = item.getPrice();
            System.out.println(name + ' ' + price);
            if(item.getObs() != null)
            System.out.println("OBS: " + item.getObs());
        }
        System.out.println("TOTAL: R$ " + total);
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
}
