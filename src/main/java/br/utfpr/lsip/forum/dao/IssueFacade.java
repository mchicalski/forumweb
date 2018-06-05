/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.Resource;
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
public class IssueFacade extends AbstractFacade<Issue> {

    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public IssueFacade() {
        super(Issue.class);
    }

    public List<Issue> findAllByResource(Resource resource) {
         TypedQuery<Issue> query = em.createNamedQuery("Issue.findAllByResource", Issue.class);
        query.setParameter("resource", resource);
        return query.getResultList();   
    }
    
}
