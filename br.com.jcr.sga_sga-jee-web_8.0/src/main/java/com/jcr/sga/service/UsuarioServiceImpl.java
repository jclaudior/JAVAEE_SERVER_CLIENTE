/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.sga.service;

import com.jcr.sga.domain.Usuario;
import dao.UsuarioDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UsuarioServiceImpl implements UsuarioServiceRemote, UsuarioService{

    @Inject
    UsuarioDAO usuarioDAO;
    
    @Override
    public List<Usuario> listarTodosUsuarios() {
        return usuarioDAO.listarTodosUsuarios();
    }

    @Override
    public Usuario buscarUsuarioPorId(Usuario usuario) {
        return usuarioDAO.buscarUsuarioPorId(usuario);
    }

    @Override
    public List<Usuario> buscarUsuarioPorNome(Usuario usuario) {
        return usuarioDAO.buscarUsuarioPorNome(usuario);
    }

    @Override
    public void inserirUsuario(Usuario usuario) {
        usuarioDAO.inserirUsuario(usuario);
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        usuarioDAO.alterarUsuario(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        usuarioDAO.deleteUsuario(usuario);
    }
    
}
