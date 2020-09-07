import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {        
        System.out.println("--------------------------------------------------");
        System.out.println("Seja bem vindo ao sistema de gestão de restaurantes.");
        System.out.println("--------------------------------------------------");
        Cardapio cardapio = new Cardapio();
        RelacaoPedidos pedidos = new RelacaoPedidos();
        int option;        
        Scanner scan = new Scanner(new InputStreamReader(System.in, "UTF-8"));
        do {
            //Menu
            System.out.println("--------------------------------------------------");
            System.out.println("Digite a opção desejada:");
            System.out.println("1 - Mostrar Cardápio");
            System.out.println("2 - Cadastrar/Remover item no cardápio");
            System.out.println("3 - Cadastrar novo pedido");
            System.out.println("4 - Mostrar pedidos");
            System.out.println("0 - Sair");
            System.out.println("--------------------------------------------------");
            //read user input
            option = scan.nextInt();
            switch(option) {
                case 0:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Obrigado por utilizar o sistema.");
                    System.out.println("Digite qualquer tecla para continuar e pressione enter.");
                    System.out.println("--------------------------------------------------");
                    scan.next();
                break;
                case 1:
                    cardapio.imprimirCardapio();
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite qualquer tecla para continuar e pressione enter.");
                    System.out.println("--------------------------------------------------");
                    scan.next();
                break;
                case 2:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite a opção desejada:");
                    System.out.println("1 - Cadastrar prato");
                    System.out.println("2 - Cadastrar bebida");
                    System.out.println("3 - Cadastrar vinho");
                    System.out.println("4 - Remover prato");
                    System.out.println("5 - Remover bebida");
                    System.out.println("6 - Remover vinho");
                    System.out.println("0 - Sair");
                    System.out.println("--------------------------------------------------");
                    int option2 = scan.nextInt();
                    String name;
                    String price;
                    switch(option2) {
                        case 0:
                        break;
                        case 1:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o nome do prato:");
                            scan.nextLine();
                            name = scan.nextLine();
                            System.out.println("Digite o preço do prato:");
                            price = scan.nextLine();
                            cardapio.adicionarPrato(name, Double.parseDouble(price.replace(',','.')));
                        break;
                        case 2:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o nome da bebida:");
                            scan.nextLine();
                            name = scan.nextLine();
                            System.out.println("Digite o preço da bebida:");
                            price = scan.nextLine();
                            cardapio.adicionarBebida(name, Double.parseDouble(price.replace(',','.')));
                        break;
                        case 3:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o nome do vinho:");
                            scan.nextLine();
                            name = scan.nextLine();
                            System.out.println("Digite o preço do vinho:");
                            price = scan.nextLine();
                            cardapio.adicionarVinho(name, Double.parseDouble(price.replace(',','.')));
                        break;
                        case 4:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o nome do prato a ser removido:");
                            scan.nextLine();
                            name = scan.nextLine();
                            cardapio.removerPrato(name);
                        break;
                        case 5:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o nome da bebida a ser removida:");
                            scan.nextLine();
                            name = scan.nextLine();
                            cardapio.removerBebida(name);
                        break;
                        case 6:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o nome do vinho a ser removido:");
                            scan.nextLine();
                            name = scan.nextLine();
                            cardapio.removerVinho(name);
                        break;
                        default:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Opção inválida, por favor tente novamente.");
                            System.out.println("--------------------------------------------------");
                        break;
                    }
                break;
                case 3:
                    System.out.println("Digite o nome do cliente:");
                    scan.nextLine();
                    String nome_cliente = scan.nextLine();
                    System.out.println("Digite o número da mesa:");
                    int numero_mesa = scan.nextInt();
                    Pedido pedido = new Pedido(nome_cliente, numero_mesa, cardapio);
                    int option3;
                    do {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Digite a opção desejada:");
                        System.out.println("1 - Incluir Item");
                        System.out.println("2 - Excluir Item");
                        System.out.println("3 - Mostrar Cardápio");
                        System.out.println("4 - Finalizar Pedido");
                        System.out.println("0 - Sair");
                        System.out.println("--------------------------------------------------");
                        option3 = scan.nextInt();
                        int option4;
                        String id;
                        String quantity;
                        String obs = null;
                        switch(option3) {
                            case 0:
                            break;
                            case 1:
                                System.out.println("--------------------------------------------------");
                                System.out.println("Digite o identificador do item a ser adicionado:");
                                scan.nextLine();
                                id = scan.nextLine();
                                System.out.println("Digite a quantidade:");
                                quantity = scan.nextLine();
                                System.out.println("Deseja fazer alguma observação? (1 para sim/ 2 para não)");
                                option4 = scan.nextInt();
                                if(option4 == 1) {
                                    System.out.println("Escreva a observação que deseja fazer:");
                                    scan.nextLine();
                                    obs = scan.nextLine();
                                }
                                pedido.incluir(Integer.parseInt(id), Integer.parseInt(quantity), obs);
                                obs = null;
                            break;
                            case 2:
                                System.out.println("--------------------------------------------------");
                                System.out.println("Digite o identificador do item a ser removido:");
                                scan.nextLine();
                                id = scan.nextLine();
                                System.out.println("Digite a quantidade:");
                                quantity = scan.nextLine();
                                pedido.remover(Integer.parseInt(id), Integer.parseInt(quantity));
                            break;
                            case 3:
                                cardapio.imprimirCardapio();
                                System.out.println("--------------------------------------------------");
                                System.out.println("Digite qualquer tecla para continuar e pressione enter.");
                                System.out.println("--------------------------------------------------");
                                scan.next();
                            break;
                            case 4:
                                pedidos.incluir(pedido);
                                option3 = 0;
                            break;
                            default:
                                System.out.println("--------------------------------------------------");
                                System.out.println("Opção inválida, por favor tente novamente.");
                                System.out.println("--------------------------------------------------");
                            break;
                        }
                    } while (option3 != 0);
                break;
                case 4:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite a opção desejada:");
                    System.out.println("1 - Todos os pedidos");
                    System.out.println("2 - Imprimir pedido da mesa");
                    System.out.println("0 - Sair");
                    System.out.println("--------------------------------------------------");
                    int option5 = scan.nextInt();
                    switch(option5){
                        case 0:                            
                        break;
                        case 1:
                            pedidos.imprimirTodos();
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite qualquer tecla para continuar e pressione enter.");
                            System.out.println("--------------------------------------------------");
                            scan.next();
                        break;
                        case 2:
                            System.out.println("Digite o número da mesa:");
                            int id_mesa_pedido = scan.nextInt();
                            pedidos.imprimir(id_mesa_pedido);
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite qualquer tecla para continuar e pressione enter.");
                            System.out.println("--------------------------------------------------");
                            scan.next();
                        break;
                        default:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Opção inválida, por favor tente novamente.");
                            System.out.println("--------------------------------------------------");
                        break;
                    }
                break;
                default:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Opção inválida, por favor tente novamente.");
                    System.out.println("--------------------------------------------------");
                break;
            }
        } while (option != 0);
        scan.close();
    }
}
