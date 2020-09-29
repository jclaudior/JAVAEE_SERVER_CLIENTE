/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.jcr.sga.domain.Usuario;
import java.util.List;

/**
 *
 * @author Pichau
 */
public interface UsuarioDAO {
    
    public List<Usuario> listarTodosUsuarios();
    
    public Usuario buscarUsuarioPorId(Usuario usuario);
    
    public List<Usuario> buscarUsuarioPorNome(Usuario usuario);
    
    public void inserirUsuario(Usuario usuario);
    
    public void alterarUsuario(Usuario usuario);
    
    public void deleteUsuario(Usuario usuario);
    
}
