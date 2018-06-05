/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Template;
import br.utfpr.lsip.forum.service.TemplateService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author mauricio
 */
@Named(value = "templateView")
@ApplicationScoped
public class TemplateView {

    @EJB
    private TemplateService templateService;
    /**
     * Creates a new instance of TemplateView
     */
    public TemplateView() {
    }
    
    public void insert() {
        templateService.insert();
    }
    
    public List<Template> findAll() {
        return templateService.findAll();
    }
    
    public String getTemplateForIdea(Idea idea) {
        return templateService.getTemplateFor(idea);
        
    }
}
