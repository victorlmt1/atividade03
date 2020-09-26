import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {
    public static void main(final String[] args) throws IOException {        
        System.out.println("--------------------------------------------------");
        System.out.println("Seja bem vindo ao sistema de gestão de restaurantes.");
        System.out.println("--------------------------------------------------");
        final Cardapio cardapio = new Cardapio();
        Integer option, option1;        
        final Scanner scan = new Scanner(new InputStreamReader(System.in, "UTF-8"));
        do {
            //Menu
            System.out.println("--------------------------------------------------");
            System.out.println("Digite a opção desejada:");
            System.out.println("1 - Mostrar Cardápio");
            System.out.println("2 - Cadastrar/Remover item no cardápio");
            System.out.println("3 - Cadastrar novo pedido");
            System.out.println("4 - Recuperar Pedido");
            System.out.println("0 - Sair");
            System.out.println("--------------------------------------------------");
            //read user input               
            option1 = scan.nextInt();
            switch(option1) {
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
                    option = scan.nextInt();
                    switch(option) {
                        case 0:
                        break;
                        case 1:
                            cardapio.adicionarPrato();
                        break;
                        case 2:
                            cardapio.adicionarBebida();
                        break;
                        case 3:
                            cardapio.adicionarVinho();
                        break;
                        case 4:
                            cardapio.removerPrato();
                        break;
                        case 5:
                            cardapio.removerBebida();
                        break;
                        case 6:
                            cardapio.removerVinho();
                        break;
                        default:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Opção inválida, por favor tente novamente.");
                            System.out.println("--------------------------------------------------");
                        break;
                    }
                break;
                case 3:
                    final Pedido pedido = new Pedido(cardapio);
                    do {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Digite a opção desejada:");
                        System.out.println("1 - Incluir Item");
                        System.out.println("2 - Excluir Item");
                        System.out.println("3 - Mostrar Cardápio");
                        System.out.println("0 - Sair");
                        System.out.println("--------------------------------------------------");
                        option = scan.nextInt();
                        switch(option) {
                            case 0:
                            break;
                            case 1:
                                pedido.incluir();
                            break;
                            case 2:
                                pedido.remover();
                            break;
                            case 3:
                                cardapio.imprimirCardapio();
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
                    } while (option != 0);
                break;
                case 4:
                    System.out.println("Digite o nome ID do pedido que deseja recuperar:");
                    scan.nextLine();
                    String id = scan.nextLine();
                    Pedido pedido_recuperado = new Pedido(id, cardapio);
                    do {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Digite a opção desejada:");
                        System.out.println("1 - Incluir Item");
                        System.out.println("2 - Excluir Item");
                        System.out.println("3 - Mostrar Cardápio");
                        System.out.println("0 - Sair");
                        System.out.println("--------------------------------------------------");
                        option = scan.nextInt();
                        switch(option) {
                            case 0:
                            break;
                            case 1:
                                pedido_recuperado.incluir();
                            break;
                            case 2:
                                pedido_recuperado.remover();
                            break;
                            case 3:
                                cardapio.imprimirCardapio();
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
                    } while (option != 0);
                break;
                default:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Opção inválida, por favor tente novamente.");
                    System.out.println("--------------------------------------------------");
                break;
            }
        } while (option1 != 0);
        scan.close();
    }
}
