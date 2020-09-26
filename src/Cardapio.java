import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cardapio {

    Arquivo pratos = new Arquivo("data_sources\\pratos.csv", ";");
    Arquivo bebidas = new Arquivo("data_sources\\bebidas-tabuladas.txt", "\t");
    Arquivo vinhos = new Arquivo("data_sources\\vinhos-tabulados.txt", "\t");

    List<Item> lista_vinhos = new ArrayList<Item>();
    List<Item> lista_pratos = new ArrayList<Item>();
    List<Item> lista_bebidas = new ArrayList<Item>();
    List<Item> cardapio = new ArrayList<Item>();
    Scanner scan = new Scanner(new InputStreamReader(System.in, "UTF-8"));

    Cardapio() throws IOException {
        atualizarCardapio();
    }

    void atualizarCardapio() {
        lista_pratos.clear();
        lista_bebidas.clear();
        lista_vinhos.clear();
        cardapio.clear(); 
        try {
            this.lista_pratos = pratos.lerArquivo();
        } catch (final FileNotFoundException e) {
            System.out.println("Erro ao atualizar a lista de pratos do cardápio.");
        }
        try {
            this.lista_bebidas = bebidas.lerArquivo();
        } catch (final FileNotFoundException e) {
            System.out.println("Erro ao atualizar a lista de bebidas do cardápio.");
        }
        try {
            this.lista_vinhos = vinhos.lerArquivo();
        } catch (final FileNotFoundException e) {
            System.out.println("Erro ao atualizar a lista de vinhos do cardápio.");
        }
        this.cardapio.addAll(lista_pratos);
        this.cardapio.addAll(lista_bebidas);
        this.cardapio.addAll(lista_vinhos);
    }

    void imprimirCardapio() {
        System.out.println("----------------------PRATOS---------------------");
        for(final Item prato : lista_pratos) {
            prato.print();
        }
        System.out.println("--------------------------------------------------");
        System.out.println("----------------------BEBIDAS---------------------");
        for(final Item bebida : lista_bebidas) {
            bebida.print();
        }
        System.out.println("--------------------------------------------------");
        System.out.println("----------------------VINHOS---------------------");
        for(final Item vinho : lista_vinhos) {
            vinho.print();
        }
        System.out.println("--------------------------------------------------");
    }

    void adicionarPrato() throws IOException {
        String nome_prato = null;
        String preco_prato = null;
        System.out.println("--------------------------------------------------");
        System.out.println("Digite o nome do prato a ser adicionado:");
        nome_prato = scan.nextLine();
        System.out.println("Digite o preço de "+ nome_prato +" em Reais:");
        preco_prato = scan.nextLine();
        lista_pratos.add(new Item(nome_prato, preco_prato));
        pratos.escreverArquivo(lista_pratos);
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Prato inserido com sucesso.");
        System.out.println("--------------------------------------------------");
    }

    void removerPrato() throws IOException { 
        String nome;
        List<Item> itens_encontrados = new ArrayList<Item>();
        while(true){
            itens_encontrados.clear();            
            System.out.println("--------------------------------------------------");
            System.out.println("Digite o nome do prato a ser removido:");
            nome = scan.nextLine();
            for(Item item : lista_pratos){
                if(item.getNome().toLowerCase().contains(nome.toLowerCase())){
                    itens_encontrados.add(item);
                }
            }
            if(itens_encontrados.size() == 0){
                System.out.println("Nenhum prato com nome " + nome + " encontrado!");
                break;
            }
            if(itens_encontrados.size() == 1){
                Item item_encontrado = itens_encontrados.get(0);
                System.out.println("Removendo o prato: " + item_encontrado.getNome() + "!");
                lista_pratos.remove(item_encontrado);                
                System.out.println("--------------------------------------------------");
                System.out.println("Prato removido com sucesso.");
                System.out.println("--------------------------------------------------");
                break;
            }
            if(itens_encontrados.size() > 1){
                for(Item item_encontrado : itens_encontrados){
                    System.out.println("O sistema encontrou: "+ item_encontrado.getNome());
                }
            }
        }
        pratos.escreverArquivo(lista_pratos);
        atualizarCardapio();
    }

    void adicionarBebida() throws IOException {
        String nome_bebida = null;
        String preco_bebida = null;
        System.out.println("--------------------------------------------------");
        System.out.println("Digite o nome da bebida a ser adicionada:");
        nome_bebida = scan.nextLine();
        System.out.println("Digite o preço de "+ nome_bebida +" em Reais:");
        preco_bebida = scan.nextLine();
        lista_bebidas.add(new Item(nome_bebida, preco_bebida));
        bebidas.escreverArquivo(lista_bebidas);
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Bebida inserida com sucesso.");
        System.out.println("--------------------------------------------------");
    }

    void removerBebida() throws IOException {
        String nome = null;
        List<Item> itens_encontrados = new ArrayList<Item>();
        while(true){
            itens_encontrados.clear();            
            System.out.println("--------------------------------------------------");
            System.out.println("Digite o nome da bebida a ser removida:");
            nome = scan.nextLine();
            for(Item item : lista_bebidas){
                if(item.getNome().toLowerCase().contains(nome.toLowerCase())){
                    itens_encontrados.add(item);
                }
            }
            if(itens_encontrados.size() == 0){
                System.out.println("Nenhuma bebida com nome " + nome + " encontrada!");
                break;
            }
            if(itens_encontrados.size() == 1){
                Item item_encontrado = itens_encontrados.get(0);
                System.out.println("Removendo a bebida: " + item_encontrado.getNome() + "!");
                lista_bebidas.remove(item_encontrado);                
                System.out.println("--------------------------------------------------");
                System.out.println("Bebida removida com sucesso.");
                System.out.println("--------------------------------------------------");
                break;
            }
            if(itens_encontrados.size() > 1){
                for(Item item_encontrado : itens_encontrados){
                    System.out.println("O sistema encontrou: "+ item_encontrado.getNome());
                }
            }
        }
        bebidas.escreverArquivo(lista_bebidas);
        atualizarCardapio();
    }

    void adicionarVinho() throws IOException {
        String nome_vinho = null;
        String preco_vinho = null;
        System.out.println("--------------------------------------------------");
        System.out.println("Digite o nome do vinho a ser adicionado:");
        nome_vinho = scan.nextLine();
        System.out.println("Digite o preço de "+ nome_vinho +" em Reais:");
        preco_vinho = scan.nextLine();
        lista_vinhos.add(new Item(nome_vinho, preco_vinho));
        vinhos.escreverArquivo(lista_vinhos);
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Vinho inserido com sucesso.");
        System.out.println("--------------------------------------------------");
    }

    void removerVinho() throws IOException {
        String nome = null;
        List<Item> itens_encontrados = new ArrayList<Item>();
        while(true){
            itens_encontrados.clear();            
            System.out.println("--------------------------------------------------");
            System.out.println("Digite o nome do vinho a ser removido:");
            nome = scan.nextLine();
            for(Item item : lista_vinhos){
                if(item.getNome().toLowerCase().contains(nome.toLowerCase())){
                    itens_encontrados.add(item);
                }
            }
            if(itens_encontrados.size() == 0){
                System.out.println("Nenhum vinho com nome " + nome + " encontrado!");
                break;
            }
            if(itens_encontrados.size() == 1){
                Item item_encontrado = itens_encontrados.get(0);
                System.out.println("Removendo o vinho: " + item_encontrado.getNome() + "!");
                lista_vinhos.remove(item_encontrado);                
                System.out.println("--------------------------------------------------");
                System.out.println("Vinho removido com sucesso.");
                System.out.println("--------------------------------------------------");
                break;
            }
            if(itens_encontrados.size() > 1){
                for(Item item_encontrado : itens_encontrados){
                    System.out.println("O sistema encontrou: "+ item_encontrado.getNome());
                }
            }
        }
        vinhos.escreverArquivo(lista_vinhos);
        atualizarCardapio();
    }

    List<Item> getCardapio() {
        return this.cardapio;
    }
}
