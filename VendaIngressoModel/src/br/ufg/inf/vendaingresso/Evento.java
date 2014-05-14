package br.ufg.inf.vendaingresso;

import java.util.Calendar;

/**
 *
 * @author aluno
 */
public class Evento {
    private long id;
    private String nome;
    private Calendar dataEvento;

    public Evento(String nome, Calendar dataEvento) {
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

    public Calendar getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Calendar dataEvento) {
        this.dataEvento = dataEvento;
    }
}
