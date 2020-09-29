
package com.jcr.sga.cliente.jpql;

import com.jcr.sga.domain.Pessoa;
import com.jcr.sga.domain.Usuario;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TesteJPQL {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        
        String jpql = null;
        Query q = null;
        List<Pessoa> pessoas = null;
        Pessoa pessoa = null;
        Iterator  iter = null;
        Object[] tupla = null;
        List<String> nomes = null;
        List<Usuario> usuarios = null;
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        //listar todas pessoas jpql
        log.debug("Consulta de todas pessoas:");
        jpql = "SELECT p FROM Pessoa p";
        pessoas = em.createQuery(jpql).getResultList();
        
        mostrarPessoas(pessoas);
        
        
        //Consulta de pessoa pelo id jpql
        log.debug("Consulta pessoa pelo id:");
        jpql = "SELECT p FROM Pessoa p WHERE p.idPessoa = 1";
        pessoa = (Pessoa) em.createQuery(jpql).getSingleResult();
        //log.debug(pessoa);
        
        //Consulta de pessoa pelo nome jpql
        jpql = "SELECT p FROM Pessoa p WHERE p.nome = 'DANIELA'";
        pessoa = (Pessoa) em.createQuery(jpql).getSingleResult();
        log.debug(pessoa);
        
        //consulta de dados individuais array de tipo objeto de 3 colunas
        log.debug("Consulta de dados individuais:");
        jpql = "SELECT p.nome AS nomePessoa, p.apelido AS apelidoPessoa, p.email AS emailPessoa FROM Pessoa p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[]) iter.next();
            String nome = (String) tupla[0];
            String apelido = (String) tupla[1];
            String email = (String) tupla[2];
            
            //log.debug("Nome: " + nome + " Apelido: " + apelido + " E-mail: " + email);
        }
        
        //Consulta Objeto pessoa e id em um array
         log.debug("Consulta Objeto pessoa e id em um array:");
         jpql = "SELECT p, p.idPessoa FROM Pessoa p";
         iter = em.createQuery(jpql).getResultList().iterator();
         while(iter.hasNext()){
             tupla = (Object[]) iter.next();
             pessoa = (Pessoa) tupla[0];
             int id = (int) tupla[1];
             
             log.debug("ID: " + id);
             log.debug("Pessoa: " + pessoa);
         }
         
         
         //consulta de todas pessoas atributos especificos
         log.debug("Consulta de todas pessoas atributos especificos(apenas id): ");
         jpql = "SELECT new com.jcr.sga.domain.Pessoa(p.idPessoa) FROM Pessoa p";
         pessoas = em.createQuery(jpql).getResultList();
         mostrarPessoas(pessoas);
        
         
         //retornar valor maximo e minimo do id
         log.debug("retornar valor maximo e minimo do id e quantidade de pessoas:");
         jpql =  "SELECT MIN(p.idPessoa) as MinId, MAX(p.idPessoa) as MaxId, COUNT(p.idPessoa) as QtdPessoas FROM Pessoa p";
         iter = em.createQuery(jpql).getResultList().iterator();
         while(iter.hasNext()){
             tupla = (Object[]) iter.next();
             Integer idMin = (Integer) tupla[0];
             Integer idMax = (Integer) tupla[1];
             Long  qtdPessoa = (Long) tupla[2];
             
             log.debug("Id minimo: " + idMin + " id maximo: " + idMax + " Quantidade de Pessoas: " + qtdPessoa);
             
         }
         
         
         //Conta o numero de pessoas com nomes 
         log.debug("Numero de pessoas com nomes distintos:");
         jpql = "SELECT COUNT(DISTINCT p.nome)FROM Pessoa p";
         Long qtdPessoaNmDist = (Long) em.createQuery(jpql).getSingleResult();
         log.debug("Quantidade de pessoas com nomes distintos: " + qtdPessoaNmDist);
         
         
         //Concatenar Nome e Apelido e Realizar UPPERCASE
         log.debug("Concatenar Nome e Apelido e Realizar UPPERCASE");
         jpql= "SELECT UPPER(CONCAT(p.nome,' ',p.apelido)) as Nome FROM  Pessoa p";
         nomes = em.createQuery(jpql).getResultList();
         for (String nomeApelido : nomes){
             log.debug("Nome e Apelido: " + nomeApelido);
         }
         
         
         //buscar pesso pelo id passando um parametro
         log.debug("Pessoa buscada pelo Id passsado pelo Parametro :");
         int idPessoa = 1;
         jpql = "SELECT p FROM Pessoa p WHERE p.idPessoa = :id";
         q = em.createQuery(jpql);
         q.setParameter("id", idPessoa);
         pessoa = (Pessoa) q.getSingleResult();
         log.debug("Pessoa: " + pessoa);
         
         //lista de pessoas com a letra a no seu nome
         log.debug("Buscando pessoas com a letra a em seu nome :");
         String letra = "%a%";
         jpql="SELECT p FROM Pessoa p WHERE UPPER(p.nome) LIKE UPPER(:letra)";
         q = em.createQuery(jpql);
         q.setParameter("letra", letra);
         pessoas = q.getResultList();
         mostrarPessoas(pessoas);
         
         log.debug("Busca pessoa entre 1 e 3: ");
         int param1 = 1;
         int param2 = 3;
         jpql = "SELECT p FROM Pessoa p WHERE p.idPessoa BETWEEN :param1 AND :param2";
         q = em.createQuery(jpql);
         q.setParameter("param1", param1);
         q.setParameter("param2", param2);
         pessoas = q.getResultList();
         mostrarPessoas(pessoas);
         
         //ordenando select
         log.debug("Busca pessoa ordenada: ");
         jpql = "SELECT p FROM Pessoa p WHERE p.idPessoa > 2 ORDER BY p.idPessoa DESC";
         pessoas = em.createQuery(jpql).getResultList();
         mostrarPessoas(pessoas);
         
         
         //usando subquerys
         log.debug("Usando subquerys:");
         jpql = "SELECT p FROM Pessoa p WHERE p.idPessoa IN (SELECT min(p1.idPessoa) FROM Pessoa p1)";
         pessoas = em.createQuery(jpql).getResultList();
         mostrarPessoas(pessoas);
         
         //uso de join com lazy loading
         log.debug("Uso de join com lazy loading");
         jpql = "SELECT u FROM Usuario u JOIN u.pessoa p";
         usuarios = em.createQuery(jpql).getResultList();
         mostrarUsuarios(usuarios);
         
         
         //uso de left join
         log.debug("Uso de left join com eager loading");
         jpql = "SELECT u FROM Usuario u LEFT JOIN fetch u.pessoa p";
         usuarios = em.createQuery(jpql).getResultList();
         mostrarUsuarios(usuarios);
         
         
         
         
         
         
         
         
    }
   

    private static void mostrarPessoas(List<Pessoa> pessoas) {
        for(Pessoa pessoa: pessoas){
            log.debug(pessoa);
        }
    }

    private static void mostrarUsuarios(List<Usuario> usuarios) {
        for(Usuario usuario: usuarios){
            log.debug(usuario);
        }
    }
    
}
