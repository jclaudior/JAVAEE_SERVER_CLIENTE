package com.jcr.sga.service;

import com.jcr.sga.domain.Usuario;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface UsuarioServiceRemote {
    public List<Usuario> listarTodosUsuarios();
    
    public Usuario buscarUsuarioPorId(Usuario usuario);
    
    public List<Usuario> buscarUsuarioPorNome(Usuario usuario);
    
    public void inserirUsuario(Usuario usuario);
    
    public void alterarUsuario(Usuario usuario);
    
    public void deleteUsuario(Usuario usuario);
    
}
