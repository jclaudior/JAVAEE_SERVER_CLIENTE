package com.jcr.sga.cliente.criteria;

import com.jcr.sga.domain.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.apache.logging.log4j.*;

public class TesteAPICriteria {
    private static final Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = null;
        CriteriaQuery <Pessoa> criteriaQuery = null;
        Root<Pessoa> fromPessoa = null;
        TypedQuery<Pessoa> query = null;
        Pessoa pessoa = null;
        List<Pessoa> pessoas = null;
        
        //Query utilizando API Criteria
        //Consulta de todas as Pessoas
        
        //No Objeto EntityManger criar um instancia CriteriaBuilder
        cb = em.getCriteriaBuilder();
        
        //Criar um objeto CriteriaQuery
        criteriaQuery = cb.createQuery(Pessoa.class);
        
        //Criar objeto raiz de Query
        fromPessoa = criteriaQuery.from(Pessoa.class);
        
        //Selecionamos o necessario de from
        criteriaQuery.select(fromPessoa);
        
        //Criando a query typeFace
        query = em.createQuery(criteriaQuery);
        
        //Executar a consulta
        pessoas = query.getResultList();
        
        //impressao no console
        //mostrarPessoas(pessoas); 
        
        //consultar pessoa filtrando pelo id
        log.debug("Consulta de pessoa com id = 1: ");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Pessoa.class);
        fromPessoa = criteriaQuery.from(Pessoa.class);
        criteriaQuery.select(fromPessoa).where(cb.equal(fromPessoa.get("idPessoa"), 1));
        pessoa = em.createQuery(criteriaQuery).getSingleResult();
        log.debug(pessoa);
        
        //Consulta de pessoa com id = 1
        log.debug("Consulta de pessoa com o ID =1: ");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Pessoa.class);
        fromPessoa = criteriaQuery.from(Pessoa.class);
        criteriaQuery.select(fromPessoa);
        
        //A classe Predicate permite adicionar varios criterios dinamicos
        List<Predicate> criterios = new ArrayList<Predicate>();
        
        //verificar se temos criterios adicionados
        Integer idPessoaParam = 1;
        ParameterExpression<Integer> parametro = cb.parameter(Integer.class, "idPessoa");
        criterios.add(cb.equal(fromPessoa.get("idPessoa"), parametro));
        
        if(criterios.isEmpty()){
            throw new RuntimeException("Sem criterios!");
        }else if(criterios.size() == 1){
            criteriaQuery.where(criterios.get(0));
        }else{
            criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
        }
        
        query = em.createQuery(criteriaQuery);
        
        //alterar valor do parametro
        query.setParameter("idPessoa", idPessoaParam);
        
        //executar query
        pessoa = query.getSingleResult();
        log.debug(pessoa);
        
        
        
        
        
    }
    private static void mostrarPessoas(List<Pessoa> pessoas) {
        for(Pessoa pessoa: pessoas){
            log.debug(pessoa);
        }
    }
    
}
