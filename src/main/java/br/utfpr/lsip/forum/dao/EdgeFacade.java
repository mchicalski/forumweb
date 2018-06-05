/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
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
public class EdgeFacade extends AbstractFacade<Edge> {

    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public EdgeFacade() {
        super(Edge.class);
    }

    public List<Edge> findAllByTeam(Team team) {
        TypedQuery<Edge> query = em.createNamedQuery("Edge.findAllByTeam", Edge.class);
        query.setParameter("team", team);
        return query.getResultList();    
    }
    
    public Edge findBySourceTarget(Resource source, Resource target) {
        TypedQuery<Edge> query = em.createNamedQuery("Edge.findBySourceTarget", Edge.class);
        query.setMaxResults(1);
        query.setParameter("source", source);
        query.setParameter("target", target);
        if (query.getResultList().size() <= 0) {
            return null;
        }
        return query.getSingleResult();       
    }
    
}
