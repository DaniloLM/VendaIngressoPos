package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Compra;
import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Ingresso;
import br.ufg.inf.vendaingresso.Secao;
import java.util.Map;

/**
 *
 * @author Ana Clara
 */
public interface CompraService {
    
    public void cadastrarCompra(Cliente cliente, Funcionario funcionario, Secao secao, Evento evento);
    public void cancelarCompra(Cliente cliente, Funcionario funcionario);
    public Map<String, String> recuperarCompra(Cliente cliente, Funcionario funcionario);
    public Map<String, Integer> recuperarAssentosDisponiveis(Evento evento);
}
