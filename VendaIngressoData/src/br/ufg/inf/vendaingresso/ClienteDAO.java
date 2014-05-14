package br.ufg.inf.vendaingresso;

/**
 *
 * @author aluno
 */
public interface ClienteDAO {
    public void salvar(Cliente cliente);
    public Cliente getByCpf(String cpf);
}
