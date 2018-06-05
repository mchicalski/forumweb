/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Team;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mauricio
 */
@Stateless
public class TeamFacade extends AbstractFacade<Team> {
    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TeamFacade() {
        super(Team.class);
    }
    
}
