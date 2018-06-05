/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Argument;
import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Idea;
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
public class ArgumentFacade extends AbstractFacade<Argument> {

    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ArgumentFacade() {
        super(Argument.class);
    }

    public List<Argument> findAllByIdea(Idea idea) {
        TypedQuery<Argument> query = em.createNamedQuery("Argument.findAllByIdea", Argument.class);
        query.setParameter("idea", idea);
        return query.getResultList();    
    }
    
}
