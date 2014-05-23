package br.ufg.inf.vendaingresso;

import java.util.Date;

/**
 *
 * @author danilolopesdemoraes
 */
public class Evento {
    private String nome;
    private String dataEvento;
    
    public Evento(){
        
    }

    public Evento(String nome, String dataEvento) {
        this.nome = nome;
        this.dataEvento = dataEvento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }
}
