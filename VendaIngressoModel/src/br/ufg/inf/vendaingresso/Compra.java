package br.ufg.inf.vendaingresso;

import java.util.Calendar;

/**
 *
 * @author aluno
 */
public class Compra {
    private long id;
    private Calendar dataCompra;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }
}
