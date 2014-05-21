package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.*;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Ana Clara
 */
public interface IngressoDAO {
    public void salvar(Ingresso ingresso, Secao secao, Evento evento);
    public void atualizar(Cliente cliente);
    public Integer getVendidosTotal();
    public ResultSet getVendidosSecao(Secao secao, Evento evento);
    public ResultSet getVendidosEvento(Evento evento);
    public List<Ingresso> getIngressoDisponiveis(Evento evento);
}
