/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.sga.domain;

import dao.PessoaDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PessoaDAOImp implements PessoaDAO{
    
    @PersistenceContext(unitName="PessoaPU")
    EntityManager em;

    @Override
    public List<Pessoa> buscarTodasPessoas() {
        return em.createNamedQuery("Pessoa.findAll").getResultList();
    }

    @Override
    public Pessoa buscarPessoaPorId(Pessoa pessoa) {
        return em.find(Pessoa.class, pessoa.getIdPessoa());
    }

    @Override
    public Pessoa buscarPessoaPorEmail(Pessoa pessoa) {
        Query query = em.createQuery("from Pessoa p where p.email=: emailPessoa");
        query.setParameter("emailPessoa", pessoa.getEmail());
        return (Pessoa) query.getSingleResult();
    }

    @Override
    public void inserirPessoa(Pessoa pessoa) {
        em.persist(pessoa);
    }

    @Override
    public void atualizarPessoa(Pessoa pessoa) {
        em.merge(pessoa);
    }

    @Override
    public void deletarPessoa(Pessoa pessoa) {
       em.remove(em.merge(pessoa));
    }
    
    
    
}
