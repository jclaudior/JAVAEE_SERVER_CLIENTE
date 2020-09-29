
package com.jcr.sga.cliente.ciclovida;

import com.jcr.sga.domain.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ExcluirObjetoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
              
        
        //Iniciar a transação
        tx.begin();
        
        
        //executar sql tipo select
         Pessoa pessoa1 = em.find(Pessoa.class, 5);
        
        //Objeto em estado datached
        log.debug("Obejeto recuperado " + pessoa1);
        
        //remove na mesma transação
        //em.remove(pessoa1);
               
        //commit
        tx.commit();
        
        //cria uma nova transação
        EntityTransaction tx2 = em.getTransaction();
        
         //Iniciar a transação
        tx2.begin();
        
        //remove usa o merge para sincronizar a informação com o banco
        em.remove(em.merge(pessoa1));
        
        
        //commit
        tx2.commit();
        
        //Objeto em estado datached
        log.debug("Obejeto deletado " + pessoa1);
        
        //Encerrar Entity manager
        em.close();
        
    }
    
    
}
