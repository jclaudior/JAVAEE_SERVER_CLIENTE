
package com.jcr.sga.service;

import com.jcr.sga.domain.Pessoa;
import dao.PessoaDAO;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PessoaServiceImpl implements PessoaServiceRemote, PessoaService{

    @Inject
    private PessoaDAO pessoaDAO;
    
    @Resource
    private SessionContext contexto;
    
    @Override
    public List<Pessoa> listarPessoas() {
        return pessoaDAO.buscarTodasPessoas();
    }

    @Override
    public Pessoa encontrarPessoaPorId(Pessoa pessoa) {
        return pessoaDAO.buscarPessoaPorId(pessoa);
    }

    @Override
    public Pessoa encontrarPessoaPoeEmail(Pessoa pessoa) {
        return pessoaDAO.buscarPessoaPorId(pessoa);
    }

    @Override
    public void registrarPessoa(Pessoa pessoa) {
      pessoaDAO.inserirPessoa(pessoa);
    }

    @Override
    public void modificarPessoa(Pessoa pessoa) {
        try{
            pessoaDAO.atualizarPessoa(pessoa);
        }
        catch(Throwable t){
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void deletarPessoa(Pessoa pessoa) {
        pessoaDAO.deletarPessoa(pessoa);
    }
    
}
