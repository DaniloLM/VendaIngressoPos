package br.ufg.inf.vendaingresso;

import br.ufg.inf.vendaingresso.service.ControleAcessoService;
import br.ufg.inf.vendaingresso.service.impl.ControleAcessoServiceImpl;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static int conta_vendas;
    static double caixa;
       

    public static void main(String args[]) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@inforplace.no-ip.org:1521:pos [pos em POS]","pos","pos#123");
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente");
            while(rs.next()) {
               String nome =  rs.getString("nome");
               System.out.println(nome);
            }
        }catch(ClassNotFoundException e) {
           throw new RuntimeException("Erro ao conectar com o banco" + e.getMessage());
        }catch(SQLException e) {
            throw new RuntimeException("Erro de SQL" + e.getSQLState());
        } finally {
            System.out.println("Conexao finalizada....");
        }
        
        
        /**
        ControleAcessoService controleAcesso = new ControleAcessoServiceImpl();
        Funcionario funcionario = new Funcionario();
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("---------------------- VENDA DE INGRESSOS --------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Login: ");
        String login = scanner.next();
        funcionario.setLogin(login);
        
        System.out.print("Senha: ");
        String senha = scanner.next();
        funcionario.setSenha(senha);
        
        controleAcesso.login(funcionario);
        
        
        // menuprincipal();
        */
    }

    
    //MENU PRINCIPAL
    public static void menuprincipal() {
        Scanner input = new Scanner(System.in);
        int sair = 0;
        while (sair != 4) {
            System.out.println("");
            System.out.println("##############################################");
            System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU");
            System.out.println("(1) - VENDAS");
            System.out.println("(2) - EVENTOS");
            System.out.println("(3) - FUNCIONARIOS");
            System.out.println("(4) - SAIR");
            System.out.println("##############################################");
            int menuprincipal = input.nextInt();
            switch (menuprincipal) {
                case 1: {
                    menuvendas();
                    break;
                }
                case 2: {
                    menueventos();
                    break;
                }
                case 3: {
                    menufuncionarios(); 
                    break;
                }
                case 4: {
                    sairsistema();
                }
                default:
                    System.out.println("Esta não é uma opção válida!");
            }
        }
    }
    
    
    
    //OPÇÕES DO MENU "PRINCIPAL"
    public static void menuvendas() {
        int sair = 0;
        while (sair != 3 || sair != 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("");
            System.out.println("#VENDAS#");
            System.out.println("##############################################");
            System.out.println("(1) - VENDER INGRESSO");
            System.out.println("(2) - CANCELAR VENDA");
            System.out.println("(3) - RETORNAR AO MENU PRINCIPAL");
            System.out.println("(4) - SAIR");
            System.out.println("##############################################");
            int menusecundario = input.nextInt();
            switch (menusecundario) {
                case 1: {
                    venderingresso();
                    break;
                }
                case 2: {
                    cancelarvenda();
                    break;
                }
                case 3: {
                    retornarmenuprincipal();
                    sair = 3;
                    break;
                }
                case 4: {
                    sairsistema();
                    sair = 4;
                }
                default:
                    System.out.println("Esta não é uma opção válida!");
            }
        }
    }

    public static void menueventos() {
        int sair = 0;
        while (sair != 3 || sair != 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("");
            System.out.println("#EVENTOS#");
            System.out.println("##############################################");
            System.out.println("(1) - CADASTRAR EVENTO");
            System.out.println("(2) - CANCELAR EVENTO");
            System.out.println("(3) - RETORNAR AO MENU PRINCIPAL");
            System.out.println("(4) - SAIR");
            System.out.println("##############################################");
            int menusecundario = input.nextInt();
            switch (menusecundario) {
                case 1: {
                    cadastrarevento();
                    break;
                }
                case 2: {
                    excluirevento();
                    break;
                }
                case 3: {
                    retornarmenuprincipal();
                    sair = 3;
                    break;
                }
                case 4: {
                    sairsistema();
                    sair = 4;
                }
                default:
                    System.out.println("Esta não é uma opção válida!");
            }
        }

    }

    public static void menufuncionarios() {
         int sair = 0;
        while (sair != 3 || sair != 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("");
            System.out.println("#FUNCIONARIOS#");
            System.out.println("##############################################");
            System.out.println("(1) - CADASTRAR FUNCIONARIO");
            System.out.println("(2) - EXCLUIR FUNCIONARIO");
            System.out.println("(3) - RETORNAR AO MENU PRINCIPAL");
            System.out.println("(4) - SAIR");
            System.out.println("##############################################");
            int menusecundario = input.nextInt();
            switch (menusecundario) {
                case 1: {
                    cadastrarfuncionario();
                    break;
                }
                case 2: {
                    excluirfuncionario();
                    break;
                }
                case 3: {
                    retornarmenuprincipal();
                    sair = 3;
                    break;
                }
                case 4: {
                    sairsistema();
                    sair = 4;
                }
                default:
                    System.out.println("Esta não é uma opção válida!");
            }
        }

    }

    public static void sairsistema() {
        System.exit(0);
    }
    
    
    
    //OPÇÕES DO MENU "VENDAS"
    public static void venderingresso() {

    }

    public static void cancelarvenda() {

    }

    public static void retornarmenuprincipal() {
        System.out.println("");
        System.out.println("");
        System.out.print("#MENU PRINCIPAL#");
        menuprincipal();
    }
     
    
    
    //OPÇÕES DO MENU "EVENTOS"
    public static void cadastrarevento(){

}
    
    public static void excluirevento(){
        
    }
    
    
    
     //OPÇÕES DO MENU "FUNCIONARIOS"
    public static void cadastrarfuncionario(){
        
    }
    
    public static void excluirfuncionario(){
        
    }
}