/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.ResourceFacade;
import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mauricio
 */
@Stateless
public class ResourceService {
    
    @EJB
    private TeamFacade teamFacade;
    @EJB
    private ResourceFacade resourceFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Resource> findAllByTeam(Team team) {
        if (team == null || teamFacade.find(team.getId()) == null)
            return new ArrayList<Resource>();
        return teamFacade.find(team.getId()).getResources();
    }
    
    public Integer countAllByTeam(Team team) {
        return resourceFacade.countAllByTeam(team);
    }
    
    public Resource findThingByTeam(Team team) {
        return resourceFacade.findThingByTeam(team);
    }

    public List<Resource> findAllSubClasses(Resource resource) {
        if (resource == null)
            return new ArrayList<Resource>();
        System.out.println("count "+resourceFacade.find(resource.getId()).getSubClasses().size());
        return resourceFacade.find(resource.getId()).getSubClasses();
    }
    
    public List<Resource> findAllResourcesRelatedTo(Resource resource) {
        resource = resourceFacade.find(resource.getId());
        ArrayList<Resource> list = new ArrayList<Resource>();
        list.addAll(resource.getSubClassOf());
        list.addAll(resource.getSubClasses());
        list.add(resource);
        return list;
    }
    
    public Resource findByNameTeam(String name, Team team) {
        return resourceFacade.findByNameTeam(name, team);
    }
    
    public Resource find(Long id) {
        return resourceFacade.find(id);
    }
}
