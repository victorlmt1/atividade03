import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cardapio {

    List<Item> lista_vinhos = new ArrayList<Item>();
    List<Item> lista_pratos = new ArrayList<Item>();
    List<Item> lista_bebidas = new ArrayList<Item>();

    Cardapio() throws IOException {
        atualizarCardapio();
    }

    void atualizarCardapio() throws FileNotFoundException {
        int id = 0;
        lista_pratos.clear();
        lista_bebidas.clear();
        lista_vinhos.clear();   
        //PRATOS     
        File pratos = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\pratos.csv");
        Scanner leitor_pratos = new Scanner(new FileInputStream(pratos));
                
        while (leitor_pratos.hasNext()) {
            id++;
            String linha = leitor_pratos.nextLine();
            String[] partes = linha.split(";");
                lista_pratos.add(new Item(id, partes[0], Double.parseDouble(partes[1])));
        }

        //BEBIDAS     
        File bebidas = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\bebidas-tabuladas.txt");
        Scanner leitor_bebidas = new Scanner(new FileInputStream(bebidas));
                
        while (leitor_bebidas.hasNext()) {
            id++;
            String linha = leitor_bebidas.nextLine();
            String[] partes = linha.split("\\t+");
            lista_bebidas.add(new Item(id, partes[1], Double.parseDouble(partes[0].replaceAll(",","."))));
        }
        //VINHOS     
        File vinhos = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\vinhos-tabulados.txt");
        Scanner leitor_vinhos = new Scanner(new FileInputStream(vinhos));
                
        while (leitor_vinhos.hasNext()) {
            id++;
            String linha = leitor_vinhos.nextLine();
            String[] partes = linha.split("\\t+");
                lista_vinhos.add(new Item(id,partes[1], Double.parseDouble(partes[0])));           
        }
        leitor_pratos.close();
        leitor_bebidas.close();
        leitor_vinhos.close();
    }

    void imprimirCardapio() {
        System.out.println("----------------------PRATOS---------------------");
        for(int i = 0; i < lista_pratos.size(); i++) {
            Item prato = lista_pratos.get(i);
            System.out.println(prato.getId() + " - " + prato.getName() + " - " + prato.getPrice());
        }
        System.out.println("--------------------------------------------------");
        System.out.println("----------------------BEBIDAS---------------------");
        for(int i = 0; i < lista_bebidas.size(); i++) {
            Item bebida = lista_bebidas.get(i);
            System.out.println(bebida.getId() + " - " + bebida.getName() + " - " + bebida.getPrice());
        }
        System.out.println("--------------------------------------------------");
        System.out.println("----------------------VINHOS---------------------");
        for(int i = 0; i < lista_vinhos.size(); i++) {
            Item vinho = lista_vinhos.get(i);
            System.out.println(vinho.getId() + " - " + vinho.getName() + " - " + vinho.getPrice());
        }
        System.out.println("--------------------------------------------------");
    }

    void adicionarPrato(String name, double price) throws IOException {
        FileWriter pratos = new FileWriter("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\pratos.csv", true);
        PrintWriter gravador = new PrintWriter(pratos);
        gravador.println(name + ';' + price);
        gravador.close();
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Cardápio atualizado com sucesso.");
        System.out.println("--------------------------------------------------");
        imprimirCardapio();
    }

    void removerPrato(String prato_to_remove) throws IOException {        
        File pratos = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\pratos.csv");
        File pratos_leitura_temp = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\pratos_temp.csv");        
        Scanner leitor_pratos = new Scanner(new FileInputStream(pratos));
        FileWriter pratos_temp = new FileWriter("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\pratos_temp.csv", true);
        PrintWriter gravador = new PrintWriter(pratos_temp);
                
        while (leitor_pratos.hasNext()) {
            String linha = leitor_pratos.nextLine();
            String[] partes = linha.split(";");
                if(!partes[0].equals(prato_to_remove)) {
                    gravador.println(linha);
                }
        }        
        gravador.close();
        leitor_pratos.close();
        pratos_temp.close();
        pratos.delete();
        pratos_leitura_temp.renameTo(pratos);
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Cardápio atualizado com sucesso.");
        System.out.println("--------------------------------------------------");
        imprimirCardapio();
    }

    void adicionarBebida(String name, double price) throws IOException {
        FileWriter bebidas = new FileWriter("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\bebidas-tabuladas.txt", true);
        PrintWriter gravador = new PrintWriter(bebidas);
        gravador.println(price + "\t" + name);
        gravador.close();
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Cardápio atualizado com sucesso.");
        System.out.println("--------------------------------------------------");
        imprimirCardapio();
    }

    void removerBebida(String bebida_to_remove) throws IOException {        
        File bebidas = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\bebidas-tabuladas.txt");
        File bebidas_leitura_temp = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\bebidas-tabuladas-temp.txt");        
        Scanner leitor_bebidas = new Scanner(new FileInputStream(bebidas));
        FileWriter bebidas_temp = new FileWriter("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\bebidas-tabuladas-temp.txt", true);
        PrintWriter gravador = new PrintWriter(bebidas_temp);
                
        while (leitor_bebidas.hasNext()) {
            String linha = leitor_bebidas.nextLine();
            String[] partes = linha.split("\\t+");
                if(!partes[1].equals(bebida_to_remove)) {
                    gravador.println(linha);
                }
        }        
        gravador.close();
        leitor_bebidas.close();
        bebidas_temp.close();
        bebidas.delete();
        bebidas_leitura_temp.renameTo(bebidas);
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Cardápio atualizado com sucesso.");
        System.out.println("--------------------------------------------------");
        imprimirCardapio();
    }

    void adicionarVinho(String name, double price) throws IOException {
        FileWriter vinhos = new FileWriter("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\vinhos-tabulados.txt", true);
        PrintWriter gravador = new PrintWriter(vinhos);
        gravador.println(price + "\t" + name);
        gravador.close();
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Cardápio atualizado com sucesso.");
        System.out.println("--------------------------------------------------");
        imprimirCardapio();
    }

    void removerVinho(String vinho_to_remove) throws IOException {        
        File vinhos = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\vinhos-tabulados.txt");
        File vinhos_leitura_temp = new File("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\vinhos-tabulados-temp.txt");        
        Scanner leitor_vinhos = new Scanner(new FileInputStream(vinhos));
        FileWriter vinhos_temp = new FileWriter("C:\\engenharia-projetos\\desenvolvimento-de-software\\atividades\\java-menu-system\\data_sources\\vinhos-tabulados-temp.txt", true);
        PrintWriter gravador = new PrintWriter(vinhos_temp);
                
        while (leitor_vinhos.hasNext()) {
            String linha = leitor_vinhos.nextLine();
            String[] partes = linha.split("\\t+");
                if(!partes[1].equals(vinho_to_remove)) {
                    gravador.println(linha);
                }
        }        
        gravador.close();
        leitor_vinhos.close();
        vinhos_temp.close();
        vinhos.delete();
        vinhos_leitura_temp.renameTo(vinhos);
        atualizarCardapio();
        System.out.println("--------------------------------------------------");
        System.out.println("Cardápio atualizado com sucesso.");
        System.out.println("--------------------------------------------------");
        imprimirCardapio();
    }

    List<Item> retornarPratos() {
        return this.lista_pratos;
    }
    List<Item> retornarBebidas() {
        return this.lista_bebidas;
    }
    List<Item> retornarVinhos() {
        return this.lista_vinhos;
    }
}
