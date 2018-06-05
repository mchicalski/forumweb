/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.service.EdgeService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mauricio
 */
@Named(value = "edgeView")
@SessionScoped
public class EdgeView implements Serializable {

    @EJB
    private EdgeService edgeService;
    
    @Inject
    private TeamView teamView;
    /**
     * Creates a new instance of EdgeView
     */
    public EdgeView() {
    }
    
    public List<Edge> findAllBySelectedTeam() {
        return edgeService.findAllEdgesByTeam(teamView.getSelected());
    }
    
    public List<Edge> findAllEdgesRelatedTo(Resource resource) {
        return edgeService.findAllEdgesRelatedTo(resource);
    }
}
