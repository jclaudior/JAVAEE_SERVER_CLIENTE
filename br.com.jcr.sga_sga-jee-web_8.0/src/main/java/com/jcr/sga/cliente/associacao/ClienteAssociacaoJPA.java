
package com.jcr.sga.cliente.associacao;

import com.jcr.sga.domain.Pessoa;
import com.jcr.sga.domain.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.*;

public class ClienteAssociacaoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        List <Pessoa> pessoas = em.createNamedQuery("Pessoa.findAll").getResultList();
        
        em.close();
        
        imprimirPessoas(pessoas);
    }
    
    
    private static void imprimirPessoas(List<Pessoa> pessoas){
        for(Pessoa pessoa : pessoas){
            log.debug("Pessoa: "+ pessoa);
            for(Usuario usuario: pessoa.getUsuarioList()){
                log.debug("Usuario: "+usuario);
            }
        }
    }
}
