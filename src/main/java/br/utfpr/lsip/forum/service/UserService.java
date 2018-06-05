/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.UserFacade;
import br.utfpr.lsip.forum.model.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mauricio
 */
@Stateless
public class UserService {

    @EJB
    private UserFacade userFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void create(User user) {
        userFacade.create(user);
    }
    
    public User login(User user) {
        return userFacade.login(user);
    }
}
