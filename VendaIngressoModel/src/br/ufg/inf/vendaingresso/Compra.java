package br.ufg.inf.vendaingresso;

import java.util.Date;

/**
 *
 * @author aluno
 */
public class Compra {
    private long id;
    private Date dataCompra;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }
}
