package br.ufg.inf.vendaingresso;

/**
 *
 * @author aluno
 */
public class Ingresso {
    private long id;
    private double valor;

    public Ingresso(double valor) {
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
