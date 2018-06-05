/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.IdeaFacade;
import br.utfpr.lsip.forum.dao.IssueFacade;
import br.utfpr.lsip.forum.dao.ResourceFacade;
import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.Resource;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mauricio
 */
@Stateless
public class IssueService {

    @EJB
    private IdeaFacade ideaFacade;
    @EJB
    private TeamFacade teamFacade;
    @EJB
    private ResourceFacade resourceFacade;
    @EJB
    private IssueFacade issueFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Issue> findAllIssuesByResource(Resource resource) {
        return issueFacade.findAllByResource(resource);
    }
}
