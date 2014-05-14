package br.ufg.inf.vendaingresso;

/**
 *
 * @author aluno
 */
public interface IngressoDAO {
    public void atualizar(Ingresso ingresso);
    public int getVendidosTotal();
    public int getVendidosSecao();
    public int getVendidosEvento();
}
