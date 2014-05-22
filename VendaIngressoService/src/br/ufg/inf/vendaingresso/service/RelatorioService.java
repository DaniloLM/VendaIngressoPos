package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Secao;

/**
 *
 * @author Ana Clara
 */
public interface RelatorioService {
    
    public int contaIngressoTotal(Funcionario funcionario);
    public int contaIngressoSecao(Evento evento, Secao secao, Funcionario funcionario);
    public int contaIngressoEvento(Evento evento, Funcionario funcionario);
}
