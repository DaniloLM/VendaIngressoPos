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
<<<<<<< HEAD
    public int getVendidosTotal();
=======
    public void atualizar(Cliente cliente);
    public ResultSet getVendidosTotal();
>>>>>>> 8e8b02d1764a220a475d8243bd2ce657a9a8c261
    public ResultSet getVendidosSecao(Secao secao, Evento evento);
    public ResultSet getVendidosEvento(Evento evento);
    public List<Ingresso> getIngressoDisponiveis(Evento evento);
}
