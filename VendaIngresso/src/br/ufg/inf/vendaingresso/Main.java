package br.ufg.inf.vendaingresso;

import br.ufg.inf.vendaingresso.service.ClienteService;
import br.ufg.inf.vendaingresso.service.CompraService;
import br.ufg.inf.vendaingresso.service.ControleAcessoService;
import br.ufg.inf.vendaingresso.service.EventoService;
import br.ufg.inf.vendaingresso.service.FuncionarioService;
import br.ufg.inf.vendaingresso.service.IngressoService;
import br.ufg.inf.vendaingresso.service.RelatorioService;
import br.ufg.inf.vendaingresso.service.SecaoService;
import br.ufg.inf.vendaingresso.service.impl.ClienteServiceImpl;
import br.ufg.inf.vendaingresso.service.impl.CompraServiceImpl;
import br.ufg.inf.vendaingresso.service.impl.ControleAcessoServiceImpl;
import br.ufg.inf.vendaingresso.service.impl.EventoServiceImpl;
import br.ufg.inf.vendaingresso.service.impl.FuncionarioServiceImpl;
import br.ufg.inf.vendaingresso.service.impl.IngressoServiceImpl;
import br.ufg.inf.vendaingresso.service.impl.RelatorioServiceImpl;
import br.ufg.inf.vendaingresso.service.impl.SecaoServiceImpl;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {     

    public static void main(String args[]) {        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("---------------------- VENDA DE INGRESSOS --------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        login();
    }
    
    public static void login(){
        ControleAcessoService controleAcesso = new ControleAcessoServiceImpl();
        Funcionario funcionario = new Funcionario();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("----------------------------- LOGIN --------------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Login: ");
        String login = scanner.next();
        funcionario.setLogin(login);
        
        System.out.print("Senha: ");
        String senha = scanner.next();
        funcionario.setSenha(senha);
        
        boolean controle = controleAcesso.login(funcionario);
        
        if (controle){
            menuprincipal();
        } else {
            login();
        }
        
    }
    
    public static void menuprincipal() {
        Funcionario funcionario = new Funcionario();
        ControleAcessoService controleAcesso = new ControleAcessoServiceImpl();
                
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------------------ MENU PRINCIPAL ----------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU:");
        System.out.println("(1) - REALIZAR VENDA");
        System.out.println("(2) - CADASTRAR CLIENTES");
        System.out.println("(3) - CADASTRAR EVENTOS");
        System.out.println("(4) - CADASTRAR FUNCIONÁRIOS");
        System.out.println("(5) - GERAR RELATÓRIOS");
        System.out.println("(6) - LOGOUT");
        System.out.println("(7) - FECHAR");
        System.out.println("--------------------------------------------------------------------");

        int opcao = input.nextInt();
        
        switch (opcao) {
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
                System.out.print("Informe seu CPF: ");
                String cpf = input.next();
                funcionario.setCpf(cpf);

                boolean controle = controleAcesso.verificaAcesso(funcionario);

                if (controle){
                   gerarRelatorios();  
                }
                break;
            }
            case 6: {
                logout();
            }
            case 7: {
                sair();
            }
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    public static void menuVendas() {
        Scanner input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("----------------------------- VENDAS -------------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU:");
        System.out.println("(1) - VENDER INGRESSO");
        System.out.println("(2) - CANCELAR VENDA");
        System.out.println("(3) - RECUPERAR VENDA");
        System.out.println("(4) - RETORNAR AO MENU PRINCIPAL");
        System.out.println("(5) - LOGOUT");
        System.out.println("(6) - FECHAR");
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
                break;
            }
            case 5: {
                logout();
            }
            case 6: {
                sair();
            }
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    public static void venderIngresso(){
        CompraService compra = new CompraServiceImpl();
        IngressoService ingresso = new IngressoServiceImpl();
        Funcionario funcionario = new Funcionario();
        Cliente cliente = new Cliente();
        Secao secao = new Secao();
        Evento evento = new Evento();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("-------------------------- VENDER INGRESSO -------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Informe o evento: ");
        String nomeevento = scanner.next();
        nomeevento = nomeevento.substring(0,1).toUpperCase().concat(nomeevento.substring(1));
        evento.setNome(nomeevento);
        
        System.out.println(compra.recuperarAssentosDisponiveis(evento));
        
        System.out.print("Informe o CPF do funcionário: ");
        String cpfFuncionario = scanner.next();
        funcionario.setCpf(cpfFuncionario);
        
        System.out.print("Informe o CPF do cliente: ");
        String cpfCliente = scanner.next();
        cliente.setCpf(cpfCliente);
        
        System.out.print("Informe a seção: ");
        String secaoNome = scanner.next();
        secao.setNome(secaoNome);
        
        compra.cadastrarCompra(cliente, funcionario, secao);
    }
    
    public static void recuperarVenda(){
        CompraService compra = new CompraServiceImpl();
        Funcionario funcionario = new Funcionario();
        Cliente cliente = new Cliente();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------------------- RECUPERAR VENDA --------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Informe o CPF do funcionário: ");
        String cpfFuncionario = scanner.next();
        funcionario.setCpf(cpfFuncionario);
        
        System.out.print("Informe o CPF do cliente: ");
        String cpfCliente = scanner.next();
        cliente.setCpf(cpfCliente);
        
        compra.recuperarCompra(cliente, funcionario);
    }
    
    public static void cancelarVenda(){
        CompraService compra = new CompraServiceImpl();
        Funcionario funcionario = new Funcionario();
        Cliente cliente = new Cliente();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------------------ CANCELAR VENDA ----------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Informe o CPF do funcionário: ");
        String cpfFuncionario = scanner.next();
        funcionario.setCpf(cpfFuncionario);
        
        System.out.print("Informe o CPF do cliente: ");
        String cpfCliente = scanner.next();
        cliente.setCpf(cpfCliente);
        
        compra.cancelarCompra(cliente, funcionario);
    }
    
    public static void menuClientes() {
        Scanner input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("--------------------------- CLIENTES -------------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU:");
        System.out.println("(1) - CADASTRAR CLIENTE");
        System.out.println("(2) - RETORNAR AO MENU PRINCIPAL");
        System.out.println("(3) - LOGOUT");
        System.out.println("(4) - FECHAR");
        System.out.println("--------------------------------------------------------------------");            

        int opcao = input.nextInt();

        switch (opcao) {
            case 1: {
                cadastrarCliente();
                break;
            }
            case 2: {
                retornarMenuPrincipal();
                break;
            }
            case 3: {
                logout();
            }
            case 4: {
                sair();
            }
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    public static void cadastrarCliente() {
        Cliente cliente = new Cliente();
        ClienteService clienteservice = new ClienteServiceImpl();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------------------ CADASTRAR CLIENTE -------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Nome: ");
        String nome = input.next();
        cliente.setNome(nome);
        
        System.out.print("CPF: ");
        String cpf = input.next();
        cliente.setCpf(cpf);
        
        clienteservice.cadastrarCliente(cliente);
                
        menuClientes();       
    }

    
    public static void menuEventos() {       
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("----------------------------- EVENTOS ------------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU:");
        System.out.println("(1) - CADASTRAR EVENTO");
        System.out.println("(2) - CADASTRAR SEÇÕES");
        System.out.println("(3) - RETORNAR AO MENU PRINCIPAL");
        System.out.println("(4) - LOGOUT");
        System.out.println("(5) - FECHAR");
        System.out.println("--------------------------------------------------------------------");
        
        int opcao = input.nextInt();
        
        switch (opcao) {
            case 1: {
                cadastrarEvento();
                break;
            }
            case 2: {
                cadastrarSecao();
                break;
            }
            case 3: {
                retornarMenuPrincipal();
                break;
            }
            case 4: {
                logout();
            }
            case 5: {
                sair();
            }
            default:
                System.out.println("Opção inválida!");
                menuEventos();
        }
        
    }
    
    public static void cadastrarEvento() {
        Evento evento = new Evento();
        Secao secao = new Secao();
        
        EventoService eventoservice = new EventoServiceImpl();
        SecaoService secaoservice = new SecaoServiceImpl();
        IngressoService ingressoservice = new IngressoServiceImpl();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------------------ CADASTRAR EVENTOS -------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Nome: ");
        String nome = input.next();
        evento.setNome(nome);
        
        System.out.print("Ano: ");
        String ano = input.next();
        
        System.out.print("Mês: ");
        String mes = input.next();
        
        System.out.print("Dia: ");
        String dia = input.next();
        
        String data = ano + "/" + mes + "/" + dia;
        evento.setDataEvento(data);
        
        eventoservice.cadastrarEvento(evento);
        
        System.out.println("Informe o número de seções do evento: ");
        int countsecao = input.nextInt();
        int i = 0; 
        while(i<=countsecao){
            System.out.print("Nome: ");
            String nomesecao = input.next();
            secao.setNome(nomesecao);
        
            System.out.print("Valor: ");
            Double valor = input.nextDouble();
            secao.setValor(valor);
        
            secaoservice.cadastrarSecao(secao, evento);
            
            System.out.println("Número de ingressos da sessão: ");
            int countingressos = input.nextInt();
            int j = 0;
            while(j<=countingressos){
                ingressoservice.cadastrarIngresso(secao);
                j++;
            }
            i++;
        }
        
        menuEventos();
    }
    
    public static void cadastrarSecao() {
        Evento evento = new Evento();
        Secao secao = new Secao();
        
        SecaoService secaoservice = new SecaoServiceImpl();
        IngressoService ingressoservice = new IngressoServiceImpl();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------------------- CADASTRAR SEÇÕES -------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.println("Evento: ");
        String nomeevento = input.next();
        evento.setNome(nomeevento);
        
        System.out.print("Nome: ");
        String nomesecao = input.next();
        secao.setNome(nomesecao);

        System.out.print("Valor: ");
        Double valor = input.nextDouble();
        secao.setValor(valor);

        secaoservice.cadastrarSecao(secao, evento);

        System.out.println("Número de ingressos da sessão: ");
        int countingressos = input.nextInt();
        int j = 0;
        while(j<=countingressos){
            ingressoservice.cadastrarIngresso(secao);
            j++;
        }
        
        menuEventos();
    }
        
    

    public static void menuFuncionarios() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("-------------------------- FUNCIONÁRIOS ----------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU:");
        System.out.println("(1) - CADASTRAR FUNCIONARIO");
        System.out.println("(2) - RETORNAR AO MENU PRINCIPAL");
        System.out.println("(3) - LOGOUT");
        System.out.println("(4) - FECHAR");
        System.out.println("--------------------------------------------------------------------");
        
        int opcao = input.nextInt();
        
        switch (opcao) {
            case 1: {
                cadastrarFuncionario();
                break;
            }
            case 2: {
                retornarMenuPrincipal();
                break;
            }
            case 3: {
                logout();
            }
            case 4: {
                sair();
            }
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    public static void cadastrarFuncionario() {
        Funcionario funcionario = new Funcionario();
        Acesso acesso = new Acesso();
        FuncionarioService funcionarioservice = new FuncionarioServiceImpl();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("---------------------- CADASTRAR FUNCIONÁRIOS ----------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Nome: ");
        String nome = input.next();
        funcionario.setNome(nome);
        
        System.out.print("CPF: ");
        String cpf = input.next();
        funcionario.setCpf(cpf);
        
        System.out.print("Login: ");
        String login = input.next();
        funcionario.setLogin(login);
        
        System.out.print("Senha: ");
        String senha = input.next();
        funcionario.setSenha(senha);
        
        System.out.println("Cargo: ");
        String cargo = input.next();
        cargo = cargo.substring(0,1).toUpperCase().concat(cargo.substring(1));
        acesso.setTipo(cargo);
        
        funcionarioservice.cadastrarFuncionario(funcionario, acesso);
        menuFuncionarios();
    }
    
    private static void gerarRelatorios() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("-------------------------- RELATÓRIOS ------------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU:");
        System.out.println("(1) - TOTAL DE INGRESSOS VENDIDOS");
        System.out.println("(2) - TOTAL DE INGRESSOS VENDIDOS POR SEÇÃO DO EVENTO");
        System.out.println("(3) - TOTAL DE INGRESSOS VENDIDOS POR EVENTO");
        System.out.println("(4) - LOGOUT");
        System.out.println("(5) - FECHAR");
        System.out.println("--------------------------------------------------------------------");
        
        int opcao = input.nextInt();
        
        switch (opcao) {
            case 1: {
                gerarTotalVendidos();
                break;
            }
            case 2: {
                gerarTotalVendidosSecao();
                break;
            }
            case 3: {
                gerarTotalVendidosEvento();
                break;
            }
            case 4: {
                logout();
            }
            case 5: {
                sair();
            }
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    public static void gerarTotalVendidos(){
        RelatorioService relatorioservice = new RelatorioServiceImpl();
        System.out.print("Quantidade de ingressos vendidos no total: ");
        relatorioservice.contaIngressoTotal();  
    }
    
    public static void gerarTotalVendidosSecao(){
        RelatorioService relatorioservice = new RelatorioServiceImpl();
        Evento evento = new Evento();
        Secao secao = new Secao();
        Scanner input = new Scanner(System.in);
        
        System.out.println("Informe o evento: ");
        String nome = input.next();
        nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
        evento.setNome(nome);
        
        System.out.println("Informe a seção: ");
        String nomesecao = input.next();
        nomesecao = nomesecao.substring(0,1).toUpperCase().concat(nomesecao.substring(1));
        secao.setNome(nomesecao);
        
        System.out.print("Quantidade de ingressos vendidos na seção: ");
        relatorioservice.contaIngressoSecao(evento, secao);
    }
    
    public static void gerarTotalVendidosEvento(){
        RelatorioService relatorioservice = new RelatorioServiceImpl();
        Evento evento = new Evento();
        Scanner input = new Scanner(System.in);
        
        System.out.println("Informe o evento: ");
        String nome = input.next();
        nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
        evento.setNome(nome);
        
        System.out.print("Quantidade de ingressos vendidos no evento: ");
        relatorioservice.contaIngressoEvento(evento);
    }
    
    public static void retornarMenuPrincipal() {
        menuprincipal();
    }
    
    public static void logout(){
        login();
    }
    
    public static void sair() {
        System.exit(0);
    }
}