/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.EdgeFacade;
import br.utfpr.lsip.forum.dao.ResourceFacade;
import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Edge;
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
public class EdgeService {

    @EJB
    private EdgeFacade edgeFacade;
    @EJB
    private TeamFacade teamFacade;
    @EJB
    private ResourceFacade resourceFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Edge> findAllEdgesByTeam(Team team) {
        if (team == null || teamFacade.find(team.getId()) == null)
            return new ArrayList<Edge>();
        return teamFacade.find(team.getId()).getEdges();
    }

    public List<Edge> findAllEdgesRelatedTo(Resource resource) {
        resource = resourceFacade.find(resource.getId());
        ArrayList<Edge> list = new ArrayList<Edge>();
        list.addAll(resource.getEdgesAsSource());
        list.addAll(resource.getEdgesAsTarget());
        return list;
    }
}
