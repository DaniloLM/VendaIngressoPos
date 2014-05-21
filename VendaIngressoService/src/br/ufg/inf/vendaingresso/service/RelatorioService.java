package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Secao;

/**
 *
 * @author Ana Clara
 */
public interface RelatorioService {
    
    public int contaIngressoTotal();
    public int contaIngressoSecao(Evento evento, Secao secao);
    public int contaIngressoEvento(Evento evento);
}
