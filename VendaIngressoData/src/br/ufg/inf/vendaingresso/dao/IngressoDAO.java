package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.*;
import java.util.Map;

/**
 *
 * @author Ana Clara
 */
public interface IngressoDAO {
    public void salvar(Secao secao, Evento evento);
    public void atualizar(Cliente cliente, Secao secao, Evento evento);
    public int getVendidosTotal();
    public int getVendidosSecao(Secao secao, Evento evento);
    public int getVendidosEvento(Evento evento);
    public Map<String, Integer> getIngressoDisponiveis(Evento evento);
}
