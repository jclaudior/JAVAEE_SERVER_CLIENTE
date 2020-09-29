/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.sga.cliente.ciclovida;

import static com.jcr.sga.cliente.ciclovida.EncontrarObjetoJPA.log;
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
public class AtualizarObjetoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
              
        
        //Iniciar a transação
        tx.begin();
        
        
        //executar sql tipo select
         Pessoa pessoa1 = em.find(Pessoa.class, 1);
        
        
        //commit
        tx.commit();
        
        //Objeto em estado datached
        log.debug("Obejeto recuperado " + pessoa1);
        
        //modificar valor pessoa1
        pessoa1.setEmail("JOAOCLAUDIO@GMAIL.COM");
        
        //executar transação 2
        EntityTransaction tx2 = em.getTransaction();
        
        //iniciar transação 2
        tx2.begin();
        
        
        //modificar objeto
        em.merge(pessoa1);
        
        //salva alteração sem encerrar transação para continuar trabalhando na mesma
        //em.flush();
        
        //commit/rollback termina transação
        tx2.commit();
        
        //Objeto em estado datached
        log.debug("Obejeto alterado " + pessoa1);
        
        
        //Encerrar Entity manager
        em.close();
        
    }
    
}
