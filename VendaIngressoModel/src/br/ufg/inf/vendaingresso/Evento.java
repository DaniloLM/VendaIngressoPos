package br.ufg.inf.vendaingresso;

import java.util.Date;

/**
 *
 * @author aluno
 */
public class Evento {
    private long id;
    private String nome;
    private Date dataEvento;

    public Evento(String nome, Date dataEvento) {
        this.nome = nome;
        this.dataEvento = dataEvento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }
}
