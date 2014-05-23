package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Evento; 
import br.ufg.inf.vendaingresso.Secao;

/**
 *
 * @author Ana Clara
 */
public interface SecaoService {
    
    public void cadastrarSecao(Secao secao, Evento evento);    
}
