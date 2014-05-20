package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.dao.SecaoDAO;
import br.ufg.inf.vendaingresso.dao.impl.SecaoDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.SecaoService;


/**
 *
 * @author Ana Clara
 */
public class SecaoServiceImpl implements SecaoService {

    SecaoDAO secaoDAO;

    public SecaoServiceImpl() {
        secaoDAO = new SecaoDAOImpl();
    }

    @Override
    public void cadastrarSecao(Secao secao) {
        validate(secao);
        secaoDAO.salvar(secao);
    }
   
    private void validate(Secao secao) {
        if (secao == null) {
            throw new SaveException("Seção não pode ser vazia.");
        } else {
            if (secao.getNome() == null || secao.getNome().equals("")) {
                throw new SaveException("Nome da seção não pode ser vazio.");
            } 
        }
    }
}
