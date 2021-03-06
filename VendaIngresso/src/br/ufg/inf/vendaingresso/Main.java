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
import java.util.Scanner;

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
        
        System.out.println("----------------------------- LOGIN --------------------------------");
        
        System.out.print("Login: ");
        String login = scanner.nextLine();
        funcionario.setLogin(login);
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        funcionario.setSenha(senha);
        
        boolean controle = controleAcesso.login(funcionario);
        
        if (controle){
            menuprincipal();
        } else {
            System.out.println("Usuário ou senha inválidos!");
            login();
        }
        
    }
    
    public static void menuprincipal() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------------------ MENU PRINCIPAL ----------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU:");
        System.out.println("(1) - VENDAS");
        System.out.println("(2) - CADASTRAR FUNCIONÁRIOS");
        System.out.println("(3) - CADASTRAR EVENTOS");
        System.out.println("(4) - CADASTRAR CLIENTES");
        System.out.println("(5) - GERAR RELATÓRIOS");
        System.out.println("(6) - LOGOUT");
        System.out.println("(7) - FECHAR");
        System.out.println("--------------------------------------------------------------------");

        String o = input.next();
        int opcao = Integer.parseInt(o);
        
        switch (opcao) {
            case 1: {
                menuVendas();
                break;
            }
            case 2: {
                cadastrarFuncionario(); 
                break;
            }
            case 3: {
                menuEventos(); 
                break;
            }
            case 4: {
                cadastrarCliente();
                break;
            }
            case 5: {
                gerarRelatorios();
                break;
            }
            case 6: {
                logout();
                break;
            }
            case 7: {
                sair();
                break;
            }
            default:
                System.out.println("Opção inválida!");
                menuprincipal();
                break;
        }
    }
    
    public static void menuVendas() {
        Scanner input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------");
        System.out.println("----------------------------- VENDAS -------------------------------");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("SELECIONE UMA DAS OPÇÕES DO MENU:");
        System.out.println("(1) - VENDER INGRESSO");
        System.out.println("(2) - RECUPERAR VENDA");
        System.out.println("(3) - RETORNAR AO MENU PRINCIPAL");
        System.out.println("(4) - LOGOUT");
        System.out.println("(5) - FECHAR");
        System.out.println("--------------------------------------------------------------------");            
        
        String menuSecStr = input.nextLine();
        int menuSecundario = Integer.parseInt(menuSecStr);
        switch (menuSecundario) {
            case 1: {
                venderIngresso();
                break;
            }
            case 2: {
                recuperarVenda();
                break;
            }
            case 3: {
                menuprincipal();
                break;
            }
            case 4: {
                logout();
                break;
            }
            case 5: {
                sair();
                break;
            }
            default:
                System.out.println("Opção inválida!");
                menuprincipal();
                break;
        }
    }
    
    public static void venderIngresso(){
        CompraService compra = new CompraServiceImpl();
        Funcionario funcionario = new Funcionario();
        Cliente cliente = new Cliente();
        Secao secao = new Secao();
        Evento evento = new Evento();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("-------------------------- VENDER INGRESSO -------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Informe o evento: ");
        String nomeevento = scanner.nextLine();
        evento.setNome(nomeevento);
        
        System.out.println(compra.recuperarAssentosDisponiveis(evento));
        
        System.out.print("Informe o CPF do funcionário: ");
        String cpfFuncionario = scanner.nextLine();
        funcionario.setCpf(cpfFuncionario);
        
        System.out.print("Informe o CPF do cliente: ");
        String cpfCliente = scanner.nextLine();
        cliente.setCpf(cpfCliente);
        
        System.out.print("Informe a seção: ");
        String secaoNome = scanner.nextLine();
        secao.setNome(secaoNome);
        
        compra.cadastrarCompra(cliente, funcionario, secao, evento);
        menuVendas();
        
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
        String cpfFuncionario = scanner.nextLine();
        funcionario.setCpf(cpfFuncionario);
        
        System.out.print("Informe o CPF do cliente: ");
        String cpfCliente = scanner.nextLine();
        cliente.setCpf(cpfCliente);
        
        System.out.println(compra.recuperarCompra(cliente, funcionario));
        menuVendas();
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
        String cpfFuncionario = scanner.nextLine();
        funcionario.setCpf(cpfFuncionario);
        
        System.out.print("Informe o CPF do cliente: ");
        String cpfCliente = scanner.nextLine();
        cliente.setCpf(cpfCliente);
        
        compra.cancelarCompra(cliente, funcionario);
    }
    
    public static void cadastrarCliente() {
        Cliente cliente = new Cliente();
        ClienteService clienteservice = new ClienteServiceImpl();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("------------------------ CADASTRAR CLIENTE -------------------------");
        System.out.println("--------------------------------------------------------------------");
        
        System.out.print("Nome: ");
        String nome = input.nextLine();
        cliente.setNome(nome);
        
        System.out.print("CPF: ");
        String cpf = input.nextLine();
        cliente.setCpf(cpf);
        
        clienteservice.cadastrarCliente(cliente);
                
        menuprincipal();       
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
        
        String opcaoStr = input.nextLine();
        int opcao = Integer.parseInt(opcaoStr);
        
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
                menuprincipal();
                break;
            }
            case 4: {
                logout();
                break;
            }
            case 5: {
                sair();
                break;
            }
            default:
                System.out.println("Opção inválida!");
                menuEventos();
                break;
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
        
        System.out.print("Nome do evento: ");
        String nome = input.nextLine();
        evento.setNome(nome);
        
        System.out.print("Dia: ");
        String dia = input.nextLine();
        
        System.out.print("Mês: ");
        String mes = input.nextLine();
        
        System.out.print("Ano: ");
        String ano = input.nextLine();
        
        String data = ano + "/" + mes + "/" + dia;
        evento.setDataEvento(data);
        
        eventoservice.cadastrarEvento(evento);
        
        System.out.print("Informe o número de seções do evento: ");
        String countsecao = input.nextLine();
        int count = Integer.parseInt(countsecao);
        int i = 0; 
        while(i<count){
            System.out.print("Nome da seção: ");
            String nomesecao = input.nextLine();
            secao.setNome(nomesecao);
        
            System.out.print("Valor: ");
            String v = input.nextLine();
            Double valor = Double.parseDouble(v);
            secao.setValor(valor);
        
            secaoservice.cadastrarSecao(secao, evento);
            
            System.out.print("Número de ingressos da sessão: ");
            String c = input.nextLine();
            int countingressos = Integer.parseInt(c);
            int j = 0;
            while(j<countingressos){
                ingressoservice.cadastrarIngresso(secao, evento);
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
        
        System.out.print("Nome do evento: ");
        String nomeevento = input.nextLine();
        evento.setNome(nomeevento);
        
        System.out.print("Nome da seção: ");
        String nomesecao = input.nextLine();
        secao.setNome(nomesecao);

        System.out.print("Valor: ");
        String valorStr = input.nextLine();
        Double valor = Double.parseDouble(valorStr);
        secao.setValor(valor);

        secaoservice.cadastrarSecao(secao, evento);

        System.out.print("Número de ingressos da sessão: ");
        
        String countIngressosStr = input.nextLine();
        int countIngressos = Integer.parseInt(countIngressosStr);
        int j = 0;
        while(j<countIngressos){
            ingressoservice.cadastrarIngresso(secao, evento);
            j++;
        }
        
        menuEventos();
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
        String nome = input.nextLine();
        funcionario.setNome(nome);
        
        System.out.print("CPF: ");
        String cpf = input.nextLine();
        funcionario.setCpf(cpf);
        
        System.out.print("Login: ");
        String login = input.nextLine();
        funcionario.setLogin(login);
        
        System.out.print("Senha: ");
        String senha = input.nextLine();
        funcionario.setSenha(senha);
        
        System.out.print("Cargo [1 - Gerente] [2 - Operador]:");
        String cargoStr = input.nextLine();
        long cargo = Long.parseLong(cargoStr);
        acesso.setId(cargo);
        
        funcionarioservice.cadastrarFuncionario(funcionario, acesso);
        menuprincipal();
    }
    
    private static void gerarRelatorios() {
        Funcionario funcionario = new Funcionario();
        ControleAcessoService controleAcesso = new ControleAcessoServiceImpl();
        Scanner input = new Scanner(System.in);
        
        System.out.print("Informe seu CPF: ");
        String cpf = input.nextLine();
        funcionario.setCpf(cpf);

        boolean controle = controleAcesso.verificaAcesso(funcionario);

        if (controle){
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

            String o = input.next();
            int opcao = Integer.parseInt(o);

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
                    break;
                }
                case 5: {
                    sair();
                    break;
                }
                default:
                    System.out.println("Opção inválida!");
                    menuprincipal();
                    break;
            }  
        }else{
            System.out.println("Você não tem permissão para gerar relatórios.");
            menuprincipal();
        }   
    }
    
    public static void gerarTotalVendidos(){
        RelatorioService relatorioservice = new RelatorioServiceImpl();
        System.out.print("Quantidade de ingressos vendidos no total: ");
        System.out.println(relatorioservice.contaIngressoTotal()); 
        menuprincipal();
    }
    
    public static void gerarTotalVendidosSecao(){
        RelatorioService relatorioservice = new RelatorioServiceImpl();
        Evento evento = new Evento();
        Secao secao = new Secao();
        Scanner input = new Scanner(System.in);
        
        System.out.print("Informe o evento: ");
        String nome = input.nextLine();
        evento.setNome(nome);
        
        System.out.print("Informe a seção: ");
        String nomesecao = input.nextLine();
        secao.setNome(nomesecao);
        
        System.out.print("Quantidade de ingressos vendidos na seção: ");
        System.out.println(relatorioservice.contaIngressoSecao(evento, secao));
        menuprincipal();
    }
    
    public static void gerarTotalVendidosEvento(){
        RelatorioService relatorioservice = new RelatorioServiceImpl();
        Evento evento = new Evento();
        Scanner input = new Scanner(System.in);
        
        System.out.print("Informe o evento: ");
        String nome = input.nextLine();
        evento.setNome(nome);
        
        System.out.print("Quantidade de ingressos vendidos no evento: ");
        System.out.println(relatorioservice.contaIngressoEvento(evento));
        menuprincipal();
    }
    
    public static void logout(){
        login();
    }
    
    public static void sair() {
        System.exit(0);
    }
}