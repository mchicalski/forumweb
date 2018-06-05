/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.service.ResourceService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author mauricio
 */
@FacesConverter(forClass=Resource.class,value="resourceConverter")
@RequestScoped
public class ResourceConverter implements Converter {

    @EJB
    private ResourceService resourceService;
    @Inject
    private TeamView teamView;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return resourceService.findByNameTeam(value,  teamView.getSelected());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "null";
        } 
        Resource resource = (Resource) value;
        return resource.getName();
    }
    
}
