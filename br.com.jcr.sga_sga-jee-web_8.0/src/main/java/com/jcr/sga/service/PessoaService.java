/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.sga.service;

import com.jcr.sga.domain.Pessoa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pichau
 */
@Local
public interface PessoaService {
    public List<Pessoa> listarPessoas ();    
    
    public Pessoa encontrarPessoaPorId (Pessoa pessoa);
    
    public Pessoa encontrarPessoaPoeEmail (Pessoa pessoa);
    
    public void registrarPessoa (Pessoa pessoa);
    
    public void modificarPessoa (Pessoa pessoa);
    
    public void deletarPessoa (Pessoa pessoa);
}
