/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.model.User;
import br.utfpr.lsip.forum.model.Widget;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mauricio
 */
@Stateless
public class WidgetFacade extends AbstractFacade<Widget> {
    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
    public WidgetFacade() {
        super(Widget.class);
    }

    public List<Widget> findAllByTeamUser(Team team, User user) {
        TypedQuery<Widget> query = em.createNamedQuery("Widget.findAllByTeamUser", Widget.class);
//        query.setParameter("team", team);
//        query.setParameter("user", user);
        return query.getResultList();
    }
    
}
