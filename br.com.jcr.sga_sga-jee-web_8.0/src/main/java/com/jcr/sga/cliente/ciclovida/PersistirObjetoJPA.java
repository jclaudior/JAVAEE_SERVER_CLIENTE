package com.jcr.sga.cliente.ciclovida;


import com.jcr.sga.domain.Pessoa;
import javax.persistence.*;
import org.apache.logging.log4j.*;


public class PersistirObjetoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        
        
        //Criar novo objeto
        //Objeto em estado transitivo
        Pessoa pessoa1 = new Pessoa("Pedro", "Luna", "puma@gmail.com","113321456");
        
        
        //Iniciar a transação
        tx.begin();
        
        
        //executar sql
        em.persist(pessoa1);
        
        
        //commit/rollback
        tx.commit();
        
        //Objeto em estado datached
        log.debug("Obejeto persistido - estado datached: " + pessoa1);
        
        //Encerrar Entity manager
        em.close();
        
    }
    
    
}
