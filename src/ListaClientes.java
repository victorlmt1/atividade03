// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// public class ListaClientes {
//     public static void main(String[] args) throws IOException {
        
//         File arquivo = new File("C:\\Users\\Victor\\Documents\\Engenharia de Software\\Segundo Semestre\\Desenvolvimento de Software\\Atividades\\Atividade 3\\atividade03\\clientes.csv");
//         Scanner leitor = new Scanner(new FileInputStream(arquivo));
//         List<Cliente> lista = new ArrayList<Cliente>();
//         while (leitor.hasNext()) {
//             String linha = leitor.nextLine();
//             String[] partes = linha.split(";");
//             if(partes[0] == "CPF"){
//                 leitor.nextLine();
//             } else {
//                new Cliente(Integer.parseInt(partes[0]), partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
//             }            
//             leitor.nextLine();
//         }
//         System.out.println(lista);
//         // FileWriter arquivoGravado = new FileWriter("C:\\Users\\Victor\\Documents\\Engenharia de Software\\Segundo Semestre\\Desenvolvimento de Software\\Atividades\\Atividade 3\\atividade03\\clientesGravado.csv");
//         // PrintWriter gravador = new PrintWriter(arquivo);

//     }
// }
