/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.sgc.web;

import com.jcr.sga.domain.Usuario;
import com.jcr.sga.service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pichau
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuarios"})
public class UsuarioServlet extends HttpServlet {
    
    @Inject
    UsuarioService usuarioService;
   
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Usuario> usuarios = usuarioService.listarTodosUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("listandoUsuario.jsp").forward(request, response);
    }

}
