package com.jcr.sga.domain;

import dao.UsuarioDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuarioDAOImp implements UsuarioDAO{
    
    @PersistenceContext(unitName="PessoaPU")
    EntityManager em;

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    @Override
    public Usuario buscarUsuarioPorId(Usuario usuario) {
        return em.find(Usuario.class, usuario.getIdUsuario());
    }

    @Override
    public List<Usuario> buscarUsuarioPorNome(Usuario usuario) {
        Query query = em.createQuery("from Usuario u where u.username =: username");
        query.setParameter("usuario", usuario.getUsername());
        
        return query.getResultList();
    }

    @Override
    public void inserirUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        em.remove(em.merge(usuario));
    }
    
}
