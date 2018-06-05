/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Ontology;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.model.Widget;
import java.util.List;
import javax.ejb.EJB;
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
public class ResourceFacade extends AbstractFacade<Resource> {

    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ResourceFacade() {
        super(Resource.class);
    }

    public Resource findByTeamURI(Team team, String uri) {
        TypedQuery<Resource> query = em.createNamedQuery("Resource.findByTeamURI", Resource.class);
        query.setMaxResults(1);
        query.setParameter("team", team);
        query.setParameter("uri", uri);
        return query.getSingleResult();    
    }
    
    public int countAllByTeam(Team team) {
        Query query = em.createNamedQuery("Resource.countAllByTeam");
        query.setMaxResults(1);
        query.setParameter("team", team);
        return ((Long)query.getSingleResult()).intValue();    
    }

    public Resource findThingByTeam(Team team) {
        TypedQuery<Resource> query = em.createNamedQuery("Resource.findThingByTeam", Resource.class);
        query.setMaxResults(1);
        query.setParameter("team", team);
        if (query.getResultList().size() <= 0) {
            return null;
        }
        return query.getSingleResult();        
    }
    
    public Resource findByNameTeam(String name, Team team) {
        TypedQuery<Resource> query = em.createNamedQuery("Resource.findByNameTeam", Resource.class);
        query.setMaxResults(1);
        query.setParameter("name", name);
        query.setParameter("team", team);
        if (query.getResultList().size() <= 0) {
            return null;
        }
        return query.getSingleResult();     
    }

}
