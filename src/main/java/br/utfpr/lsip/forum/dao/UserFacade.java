/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mauricio
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public UserFacade() {
        super(User.class);
    }

    /**
     * @author mauricio
     * 
     * @param user the user containing the fields username and password to login
     * @return returns a connected user if login data matches, otherwise null
     */
    public User login(User user) {
        TypedQuery<User> query = em.createNamedQuery("User.getByUsernamePassword", User.class);
        query.setMaxResults(1); //errors if resultSet has more than 1 rec when calling getSingleResult() 
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
