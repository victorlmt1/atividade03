import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Arquivo {
    private String _folder;
    private final String _separador;
    private final String _path = System.getProperty("user.dir");

    Arquivo(final String pasta, final String separador) {
        this._folder = pasta;
        this._separador = separador;
    }

    Arquivo(final String separador) {
        this._separador = separador;
    }

    List<Item> lerArquivo() throws FileNotFoundException {
        final List<Item> lista = new ArrayList<Item>();
        final File arquivo = new File(this._path + this._folder);
        final Scanner leitor = new Scanner(new FileInputStream(arquivo));
        while (leitor.hasNext()) {
            final String linha = leitor.nextLine();
            final String[] partes = linha.split(this._separador);
            lista.add(new Item(partes[0], partes[1]));
        }
        leitor.close();
        return lista;
    }

    Pedido lerArquivo(final String id, final Cardapio cardapio) {

        try {
            String numero_mesa = null;
            String nome_cliente = null;
            String total = null;
            final List<Item> listaItens = new ArrayList<Item>();
            Pedido pedido;
            final File arquivo = new File(this._path + "\\pedidos\\" + id + ".txt");
            Scanner leitor;
            leitor = new Scanner(new FileInputStream(arquivo));
            while (leitor.hasNext()) {
                final String linha = leitor.nextLine();
                final String[] partes = linha.split(this._separador);
                if (partes[0].equals("Cliente")) {
                    nome_cliente = partes[1];
                } else if (partes[0].equals("Mesa")) {
                    numero_mesa = partes[1];
                } else if (partes[0].equals("Total")) {
                    total = partes[1];
                } else {
                    if (partes[2] != null) {
                        listaItens.add(new Item(partes[0], partes[1], partes[2]));
                    } else {
                        listaItens.add(new Item(partes[0], partes[1]));
                    }
                }
            }
            leitor.close();
            pedido = new Pedido(nome_cliente, numero_mesa, cardapio, id);
            pedido.setItems(listaItens);
            pedido.setNomeCliente(nome_cliente);
            pedido.setNumeroMesa(numero_mesa);
            pedido.setTotal(total);
            if (total != null) {
                return pedido;
            }
        } catch (final FileNotFoundException e1) {
            System.out.println("Erro ao buscar arquivo do pedido. Criando novo pedido.");
            try {
                return new Pedido(cardapio);
            } catch (final IOException e) {
                System.out.println("Erro no sistema. Por favor contate um administrador.");
            }
        } catch (final IOException e) {
            System.out.println("Erro ao ler arquivo do pedido.");
            try {
                return new Pedido(cardapio);
            } catch (final IOException e2) {
                System.out.println("Erro no sistema. Por favor contate um administrador.");
            }
        }
        return null;
    }

    void escreverArquivo(final List<Item> lista) {
        FileWriter arquivo;
        try {
            arquivo = new FileWriter(this._path + this._folder, false);
            final PrintWriter gravador = new PrintWriter(arquivo);
            for (final Item item : lista) {
                gravador.println(item.getNome() + this._separador + item.getPreco());
            }
            gravador.close();
        } catch (final IOException e) {
            System.out.println("Erro ao gravar dados no arquivo.");
        }
    }

    void escreverArquivo(final Pedido pedido) {
        FileWriter arquivo;
        try {
            arquivo = new FileWriter(this._path + this._folder, false);
            final PrintWriter gravador = new PrintWriter(arquivo);
            gravador.println("Cliente:" + pedido.getNomeCliente());
            gravador.println("Mesa:" + Integer.toString(pedido.getNumeroMesa()));
            gravador.println("Total:" + Double.toString(pedido.getTotal()));
            for (final Item item : pedido.getItems()) {
                gravador.println(item.getNome() + this._separador + item.getPreco() + this._separador + item.getObs());
            }
            gravador.close();
        } catch (final IOException e) {
            System.out.println("Erro ao gravar dados do pedido no arquivo.");
        }
    }

    void setFolder(final String folder) {
        this._folder = folder;
	}
}
