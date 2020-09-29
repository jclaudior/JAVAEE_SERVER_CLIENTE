/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.sga.cliente;

import com.jcr.sga.domain.Pessoa;
import com.jcr.sga.service.PessoaServiceRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Pichau
 */
public class ClientePessoaService {
    public static void main(String args[]){
        System.out.println("Iniciano chamada EJB deste cliente\n");
         
        try { 
            Context jndi = new InitialContext();
            PessoaServiceRemote pessoaServiceRemote = (PessoaServiceRemote) jndi.lookup("java:global/sga-jee-web/PessoaServiceImpl!com.jcr.sga.service.PessoaServiceRemote");
            List<Pessoa> pessoas = pessoaServiceRemote.listarPessoas();
            for(Pessoa pessoa: pessoas){
                System.out.println(pessoa);
            }
            System.out.println("Fim da chamada do EJB deste cliente");
        } catch (NamingException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}
