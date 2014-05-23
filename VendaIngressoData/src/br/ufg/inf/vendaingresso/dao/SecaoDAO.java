package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Secao;

/**
 *
 * @author Ana Clara
 */
public interface SecaoDAO {
    public void salvar(Secao secao, Evento evento);
}
