package br.ufg.inf.vendaingresso;

/**
 *
 * @author aluno
 */
public interface EventoDAO {
    public void salvar(Evento evento);
    public void getByNome(String nome);
}
