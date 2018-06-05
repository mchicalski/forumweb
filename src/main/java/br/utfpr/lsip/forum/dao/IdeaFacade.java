/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Team;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mauricio
 */
@Stateless
public class IdeaFacade extends AbstractFacade<Idea> {

    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public IdeaFacade() {
        super(Idea.class);
    }

    public List<Idea> findAllByTeam(Idea idea) {
//        TypedQuery<Idea> query = em.createNamedQuery("Idea.findAllByTeam", Idea.class);
//        query.setParameter("idea", idea);
//        return query.getResultList();    
        return null;
    }
    
    public Idea findByIdeaObjects(Idea idea) {
        TypedQuery<Idea> query = em.createNamedQuery("Idea.findByIdeaObjects", Idea.class);
        query.setMaxResults(1);
        query.setParameter("issue", idea.getIssue());
        query.setParameter("objects", idea.getObjectProperties());
        if (query.getResultList().size() <= 0) {
            return null;
        }
        return query.getSingleResult();     
    }



    
}
