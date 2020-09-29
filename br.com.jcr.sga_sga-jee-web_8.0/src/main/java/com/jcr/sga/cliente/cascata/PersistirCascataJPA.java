
package com.jcr.sga.cliente.cascata;

import com.jcr.sga.domain.Pessoa;
import com.jcr.sga.domain.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersistirCascataJPA {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        
        Pessoa pessoa1 = new Pessoa("MARIA ARAUJO","DILEUSA","MARIA@GMAIL.COM","1122222222");
        
        Usuario usuario1 = new Usuario("maria","123",pessoa1);
        
        tx.begin();
        
        em.persist(usuario1);
        
        tx.commit();
        
        log.debug("Pessoa persistida: "+ pessoa1);
        log.debug("Usuario persistido: "+ usuario1);
        
        
        
        em.close();
        
        
    }
    
}
