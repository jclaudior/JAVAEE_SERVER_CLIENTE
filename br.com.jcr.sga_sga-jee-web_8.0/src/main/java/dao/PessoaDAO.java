
package dao;

import com.jcr.sga.domain.Pessoa;
import java.util.List;


public interface PessoaDAO {
    public List<Pessoa> buscarTodasPessoas();
    
    public Pessoa buscarPessoaPorId(Pessoa pessoa);
    
    public Pessoa buscarPessoaPorEmail(Pessoa pessoa);
    
    public void inserirPessoa(Pessoa pessoa);
    
    public void atualizarPessoa (Pessoa pessoa);
    
    public void deletarPessoa (Pessoa pessoa);
    
    
    
    
}
