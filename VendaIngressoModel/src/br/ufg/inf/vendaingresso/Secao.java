package br.ufg.inf.vendaingresso;

/**
 *
 * @author aluno
 */
public class Secao {
    private String nome;
    private double valor;

    public Secao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
