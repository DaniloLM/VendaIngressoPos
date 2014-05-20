package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Secao;

/**
 *
 * @author Ana Clara
 */
public interface RelatorioService {
    
    public Integer contaIngressoTotal();
    public Integer contaIngressoSecao(Evento evento, Secao secao);
    public Integer contaIngressoEvento(Evento evento);
}
