package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.Acesso;

/**
 *
 * @author danilolopesdemoraes
 */
public interface AcessoDAO {
    public Acesso getByTipo(String tipo);
}
