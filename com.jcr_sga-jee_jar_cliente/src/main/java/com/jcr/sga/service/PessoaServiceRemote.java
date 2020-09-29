

package com.jcr.sga.service;

import com.jcr.sga.domain.Pessoa;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface PessoaServiceRemote {
    public List<Pessoa> listarPessoas ();    
    
    public Pessoa encontrarPessoaPorId (Pessoa pessoa);
    
    public Pessoa encontrarPessoaPoeEmail (Pessoa pessoa);
    
    public void registrarPessoa (Pessoa pessoa);
    
    public void modificarPessoa (Pessoa pessoa);
    
    public void deletarPessoa (Pessoa pessoa);
    
}
