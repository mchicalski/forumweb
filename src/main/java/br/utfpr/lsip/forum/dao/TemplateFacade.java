/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.dao;

import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Template;
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
public class TemplateFacade extends AbstractFacade<Template> {

    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TemplateFacade() {
        super(Template.class);
    }


    public List<Template> findAllByType(String type) {
        TypedQuery<Template> query = em.createNamedQuery("Template.findAllByType", Template.class);
        query.setParameter("type", type);
        return query.getResultList();   
    }
    
}
