/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.sga.cliente.ciclovida;

import static com.jcr.sga.cliente.ciclovida.ExcluirObjetoJPA.log;
import com.jcr.sga.domain.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Pichau
 */
public class AtualizarObjetoSessionLarga {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
              
        
        //Iniciar a transação
        tx.begin();
        
        
        //executar sql tipo select
         Pessoa pessoa1 = em.find(Pessoa.class, 1);
        
        
        
        //Objeto em estado datached
        log.debug("Obejeto recuperado " + pessoa1);
        
        
        
        //modificar valor pessoa1
        pessoa1.setEmail("JOAOCLAUDIODIVA@GMAIL.COM");
               
        //commit
        tx.commit();
     
        //Objeto em estado datached
        log.debug("Obejeto alterado " + pessoa1);
        
        //Encerrar Entity manager
        em.close();
        
    }
}
