/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.service.ResourceService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author mauricio
 */
@Named(value = "resourceView")
@SessionScoped
public class ResourceView implements Serializable {

    @EJB
    private ResourceService resourceService;
    
    @Inject
    private TeamView teamView;
    
    private Resource opened;
    /**
     * Creates a new instance of ResourceView
     */
    public ResourceView() {
    }
    
    public List<Resource> findAllResourcesBySelectedTeam() {
        return resourceService.findAllByTeam(teamView.getSelected());
    }
    
    public List<Resource> findAllEdgesBySelectedTeam() {
        return resourceService.findAllByTeam(teamView.getSelected());
    }
    
    public Integer countAllByTeam(Team team) {
        return resourceService.countAllByTeam(team);
    }
    
    public List<Resource> findAllResourcesRelatedTo(Resource resource) {
        return resourceService.findAllResourcesRelatedTo(resource);
    }
    
    public void setOpenedResource(Resource resource) {
        if (getOpened() != resource) {
            Resource closing = getOpened();
            setOpened(resource);
            if (closing != null) {
                RequestContext.getCurrentInstance().execute("update"+closing.getId()+"();"); //
            }
        } else {
            setOpened(null);
        }
        System.out.println("pushing /team-" + teamView.getSelected().getName() + "-" + resource.getId().toString());
        //PushContextFactory.getDefault().getPushContext().push("/team-" + teamView.getSelected().getName(), resource.getId().toString());
        System.out.println("pushed");
    }
    
    public boolean compareOpenedResource(Resource resource) {
        if (getOpened() != null && getOpened().equals(resource))
            return true;
        return false;
    }
    
    public void handleMessage() {
        //RequestContext.getCurrentInstance().execute("abc('"+((Resource)event.getTreeNode().getData()).getId()+"');");
    }

    /**
     * @return the opened
     */
    public Resource getOpened() {
        return opened;
    }

    /**
     * @param opened the opened to set
     */
    public void setOpened(Resource opened) {
        this.opened = opened;
    }
}
