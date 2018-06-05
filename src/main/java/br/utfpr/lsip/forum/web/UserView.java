/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.User;
import br.utfpr.lsip.forum.service.UserService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mauricio
 */
@Named(value = "userView")
@SessionScoped
public class UserView implements Serializable {

    @Inject
    private UserService userService;
    @Inject
    private HeaderView header;
    
    private String password;
    private boolean connected = false;
    private User connectedUser = new User();
    /**
     * Creates a new instance of UserView
     */
    public UserView() {
    }
    
    public void login() {
        connectedUser = userService.login(connectedUser);
        if (connectedUser == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro no login", "Usuário ou senha nao conferem"));
            connectedUser = new User();
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você está conectado", "Olá " + connectedUser.getUsername()));
        header.setOpenView("home");
        connected = true;
    }
    
    public void logout() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(connectedUser.getUsername(), "desconectado"));
        header.setOpenView("login");
        connected = false;
        connectedUser = new User();
    }
    
    public void create() {
        if (!connectedUser.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "A senha nao confere"));
            return;
        }
        userService.create(connectedUser);
        header.setOpenView("login");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você está cadastrado", connectedUser.getUsername()));
    }
    
    public boolean isConnected() {
        return connected;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the connectedUser
     */
    public User getConnectedUser() {
        return connectedUser;
    }

    /**
     * @param connectedUser the connectedUser to set
     */
    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }


}
