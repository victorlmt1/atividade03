import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RelacaoPedidos
 {
    List<Pedido> pedidos = new ArrayList<Pedido>();

    RelacaoPedidos() {
    }

    void incluir(Pedido pedido) throws IOException {
        this.pedidos.add(pedido);
        SimpleDateFormat formatted_date = new SimpleDateFormat("yyyy-mm-dd-HH-mm");
        String date = formatted_date.format(new Date()).replace(' ','-');
        FileWriter pedido_writer = new FileWriter(".\\pedidos\\mesa-"+pedido.getNumeroMesa()+"-data-"+date+".txt", true);
        PrintWriter gravador = new PrintWriter(pedido_writer);
        gravador.println("----------------------COMANDA---------------------");        
        gravador.println("Cliente: " + pedido.getNomeCliente());        
        gravador.println("Mesa: " + pedido.getNumeroMesa());        
        gravador.println("----------------------PEDIDO---------------------");
        List<Item> items = pedido.getItems();
        for(int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String name = item.getName();
            double price = item.getPrice();
            gravador.println(name + ' ' + price);
            if(item.getObs() != null)
            gravador.println("OBS: " + item.getObs());
        }
        gravador.println("TOTAL: R$ " + pedido.getTotal());
        gravador.close();
    }

    void remover(int numero_mesa) {
        for(int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if(pedido.getNumeroMesa() == numero_mesa) {
                 pedidos.remove(i);                 
            }
        }
    }

    void imprimir(int numero_mesa) {
        for(int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            if(pedido.getNumeroMesa() == numero_mesa) {
                 pedido.imprimir();                 
            }
        }
    }

    void imprimirTodos() {
        for(int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            pedido.imprimir();
        }
        if(pedidos.size() == 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("Nenhum pedido cadastrado.");
            System.out.println("--------------------------------------------------");
        }
    }
}
