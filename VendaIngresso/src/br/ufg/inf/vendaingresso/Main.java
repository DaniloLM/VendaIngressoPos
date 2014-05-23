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
        
        boolean controle = controleAcesso.login(funcionario);
        
        if (controle){
            menuprincipal();
        };
    }

    
    //MENU PRINCIPAL
    public static void menuprincipal() {
        Scanner input = new Scanner(System.in);
        int sair = 0;
        while (sair != 6) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU");
            System.out.println("(1) - REALIZAR VENDA");
            System.out.println("(2) - CADASTRAR CLIENTES");
            System.out.println("(3) - CADASTRAR EVENTOS");
            System.out.println("(4) - CADASTRAR FUNCIONÁRIOS");
            System.out.println("(5) - GERAR RELATÓRIOS");
            System.out.println("(6) - SAIR");
            System.out.println("--------------------------------------------------------------------");
            int menuprincipal = input.nextInt();
            switch (menuprincipal) {
                case 1: {
                    menuVendas();
                    break;
                }
                case 2: {
                    menuClientes();
                    break;
                }
                case 3: {
                    menuEventos(); 
                    break;
                }
                case 4: {
                    menuFuncionarios(); 
                    break;
                }
                case 5: {
                    gerarRelatorios(); 
                    break;
                }
                case 6: {
                    sair();
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    //OPÇÕES DO MENU VENDAS
    public static void menuVendas() {
        int sair = 0;
        while (sair != 3 || sair != 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("--------------------------------------------------------------------");
            System.out.println("----------------------------- VENDAS -------------------------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("(1) - VENDER INGRESSO");
            System.out.println("(2) - CANCELAR VENDA");
            System.out.println("(3) - RECUPERAR VENDA");
            System.out.println("(4) - RETORNAR AO MENU PRINCIPAL");
            System.out.println("(5) - SAIR");
            System.out.println("--------------------------------------------------------------------");            
            int menusecundario = input.nextInt();
            switch (menusecundario) {
                case 1: {
                    venderIngresso();
                    break;
                }
                case 2: {
                    cancelarVenda();
                    break;
                }
                case 3: {
                    recuperarVenda();
                    break;
                }
                case 4: {
                    retornarMenuPrincipal();
                    sair = 3;
                    break;
                }
                case 5: {
                    sair();
                    sair = 4;
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    public static void venderIngresso(){
        
    }
    
    public static void cancelarVenda(){
        
    }
    
    public static void recuperarVenda(){
        
    }
    
    //OPÇÕES DO MENU VENDAS
    public static void menuClientes() {
        int sair = 0;
        while (sair != 3 || sair != 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("--------------------------------------------------------------------");
            System.out.println("--------------------------- CLIENTES -------------------------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("(1) - CADASTRAR CLIENTE");
            System.out.println("(4) - RETORNAR AO MENU PRINCIPAL");
            System.out.println("(5) - SAIR");
            System.out.println("--------------------------------------------------------------------");            
            int menusecundario = input.nextInt();
            switch (menusecundario) {
                case 1: {
                    cadastrarCliente();
                    break;
                }
                case 2: {
                    retornarMenuPrincipal();
                    sair = 3;
                    break;
                }
                case 3: {
                    sair();
                    sair = 4;
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    public static void cadastrarCliente(){
        
    }

    
    public static void menuEventos() {
        int sair = 0;
        while (sair != 3 || sair != 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("--------------------------------------------------------------------");
            System.out.println("----------------------------- EVENTOS ------------------------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("(1) - CADASTRAR EVENTO");
            System.out.println("(2) - RETORNAR AO MENU PRINCIPAL");
            System.out.println("(3) - SAIR");
            System.out.println("--------------------------------------------------------------------");
            int menusecundario = input.nextInt();
            switch (menusecundario) {
                case 1: {
                    cadastrarEvento();
                    break;
                }
                case 3: {
                    retornarMenuPrincipal();
                    sair = 3;
                    break;
                }
                case 4: {
                    sair();
                    sair = 4;
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    public static void cadastrarEvento(){
        
    }

    public static void menuFuncionarios() {
        int sair = 0;
        while (sair != 3 || sair != 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("--------------------------------------------------------------------");
            System.out.println("--------------------------- CLIENTES -------------------------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("(1) - CADASTRAR FUNCIONARIO");
            System.out.println("(2) - RETORNAR AO MENU PRINCIPAL");
            System.out.println("(3) - SAIR");
            System.out.println("--------------------------------------------------------------------");
            int menusecundario = input.nextInt();
            switch (menusecundario) {
                case 1: {
                    cadastrarFuncionario();
                    break;
                }
                case 2: {
                    retornarMenuPrincipal();
                    sair = 3;
                    break;
                }
                case 3: {
                    sair();
                    sair = 4;
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    public static void cadastrarFuncionario(){
        
    }
    
    private static void gerarRelatorios() {
        int sair = 0;
        while (sair != 3 || sair != 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("--------------------------------------------------------------------");
            System.out.println("-------------------------- RELATÓRIOS ------------------------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("(1) - TOTAL DE INGRESSOS VENDIDOS");
            System.out.println("(2) - TOTAL DE INGRESSOS VENDIDOS POR SEÇÃO DO EVENTO");
            System.out.println("(3) - TOTAL DE INGRESSOS VENDIDOS POR EVENTO");
            System.out.println("(4) - SAIR");
            System.out.println("--------------------------------------------------------------------");
            int menusecundario = input.nextInt();
            switch (menusecundario) {
                case 1: {
                    gerarTotalVendidos();
                    break;
                }
                case 2: {
                    gerarTotalVendidosSecao();
                    sair = 3;
                    break;
                }
                case 3: {
                    gerarTotalVendidosEvento();
                    sair = 3;
                    break;
                }
                case 4: {
                    sair();
                    sair = 4;
                }
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    public static void gerarTotalVendidos(){
        
    }
    
    public static void gerarTotalVendidosSecao(){
        
    }
    
    public static void gerarTotalVendidosEvento(){
        
    }
    
    public static void retornarMenuPrincipal() {
        System.out.println("");
        System.out.println("");
        System.out.print("#MENU PRINCIPAL#");
        menuprincipal();
    }
    
    public static void sair() {
        System.exit(0);
    }
}