package br.ufg.inf.vendaingresso;

import java.sql.ResultSet;

/**
 *
 * @author aluno
 */
public interface IngressoDAO {
    public void salvar(Ingresso ingresso);
    public ResultSet getVendidosTotal();
    public ResultSet getVendidosSecao(Secao secao, Evento evento);
    public ResultSet getVendidosEvento(Evento evento);
}
