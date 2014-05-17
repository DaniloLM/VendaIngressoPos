package br.ufg.inf.vendaingresso;

import java.util.Scanner;

public class Main {

    static int conta_vendas;
    static double caixa;

    public static void main(String args[]) {
        System.out.println("###################################################################");
        System.out.println("######################### SEJA BEM VINDO ##########################");
        System.out.println("############## POR FAVOR INFORME OS DADOS SOLICITADOS #############");
        System.out.println("###################################################################");
        menuprincipal();
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
            System.out.println("(2) - CADASTRAR EVENTOS");
            System.out.println("(3) - CADASTRAR FUNCIONARIO");
            System.out.println("(4) - SAIR");
            System.out.println("##############################################");
            int menuprincipal = input.nextInt();
            switch (menuprincipal) {
                case 1: {
                    menuvendas();
                    break;
                }
                case 2: {
                    cadastrareventos();
                    break;
                }
                case 3: {
                    cadastrarfuncionario();
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

    public static void cadastrareventos() {

    }

    public static void cadastrarfuncionario() {

    }

    public static void sairsistema() {
        System.exit(0);
    }

    /*
    * OPÇÕES DO MENU "VENDAS"
    */
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

}
