/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Argument;
import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.model.User;
import br.utfpr.lsip.forum.model.Vote;
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
public class VoteFacade extends AbstractFacade<Vote> {

    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public VoteFacade() {
        super(Vote.class);
    }

    public List<Vote> findAllByIdea(Idea idea) {
        TypedQuery<Vote> query = em.createNamedQuery("Vote.findAllByIdea", Vote.class);
        query.setParameter("idea", idea);
        return query.getResultList();
    }
    
    public Vote findByIdeaUser(Idea idea, User user) {
        TypedQuery<Vote> query = em.createNamedQuery("Vote.findByIdeaUser", Vote.class);
        query.setMaxResults(1);
        query.setParameter("idea", idea);
        query.setParameter("user", user);
        if (query.getResultList().size() <= 0) {
            return null;
        }
        return query.getSingleResult();  
    }
    
    public Vote findByIssueUser(Issue issue, User user) {
        TypedQuery<Vote> query = em.createNamedQuery("Vote.findByIssueUser", Vote.class);
        query.setMaxResults(1);
        query.setParameter("issue", issue);
        query.setParameter("user", user);
        if (query.getResultList().size() <= 0) {
            return null;
        }
        return query.getSingleResult();  
    }
    
    public int countAllByIdea(Idea idea) {
        Query query = em.createNamedQuery("Vote.countAllByIdea");
        query.setMaxResults(1);
        query.setParameter("idea", idea);
        return ((Long)query.getSingleResult()).intValue();
    }    
    
    public int countByResourceUser(Resource resource, User user) {
        Query query = em.createNamedQuery("Vote.countByResourceUser");
        query.setMaxResults(1);
        query.setParameter("resource", resource);
        query.setParameter("user", user);
        return ((Long)query.getSingleResult()).intValue();
    }    
}
