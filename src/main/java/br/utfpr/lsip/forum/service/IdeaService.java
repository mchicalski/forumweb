/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.EdgeFacade;
import br.utfpr.lsip.forum.dao.IdeaFacade;
import br.utfpr.lsip.forum.dao.IssueFacade;
import br.utfpr.lsip.forum.dao.ResourceFacade;
import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Issue;
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
public class IdeaService {

    @EJB
    private IdeaFacade ideaFacade;
    @EJB
    private TeamFacade teamFacade;
    @EJB
    private ResourceFacade resourceFacade;
    @EJB
    private IssueFacade issueFacade;
    @EJB
    private EdgeFacade edgeFacade;
    @EJB
    private VoteService voteService;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Edge> findAllIdeasByResource(Resource resource) {
        resource = resourceFacade.find(resource.getId());
        ArrayList<Edge> list = new ArrayList<Edge>();
        list.addAll(resource.getEdgesAsSource());
        list.addAll(resource.getEdgesAsTarget());
        return list;
    }

    public List<Idea> findAllIdeasByIssue(Issue issue) {
        return issueFacade.find(issue.getId()).getIdeas();
    }
    
    public List<Idea> findAllIdeasByEdge(Edge edge) {
        return edgeFacade.find(edge.getId()).getIdeas();
    }
    
    public boolean isWinner(Idea idea) {
        Float max = new Float(0);
        Float that = voteService.countAllByIdea(idea) + new Float(idea.getImportCount())/issueFacade.find(idea.getIssue().getId()).getSubject().getTeam().getOntologies().size();
        boolean empate = true;
        for (Idea i : issueFacade.find(idea.getIssue().getId()).getIdeas()) {
            Float temp = voteService.countAllByIdea(i) + new Float(i.getImportCount())/i.getIssue().getSubject().getTeam().getOntologies().size();
            //System.out.println("soutin votes:"+ i.getVotes() + " import:"+temp);
            if (temp > max) {
                max = temp;
                empate=false;
                continue;
            }
            if (max.equals(temp)) {
                empate = true;
            }
        }
        
        if (!empate && that >= max) {
            idea.setWinning(true);
            ideaFacade.edit(idea);
            return true;
        }
        idea.setWinning(false);
        ideaFacade.edit(idea);
        return false;
    }
    
    
    public void processVotes(Idea idea) {
        try {
        Float max = new Float(0);
        //Float that = voteService.countAllByIdea(idea) + new Float(idea.getImportCount())/issueFacade.find(idea.getIssue().getId()).getSubject().getTeam().getOntologies().size();
        Float that = voteService.countAllByIdea(idea) + new Float(idea.getImportCount())/teamFacade.find(resourceFacade.find(idea.getIssue().getSubject().getId()).getTeam().getId()).getOntologies().size();
            //System.out.println("sput = "+ teamFacade.find(resourceFacade.find(idea.getIssue().getSubject().getId()).getTeam().getId()).getOntologies().size());
        boolean empate = true;
        Idea winner = null;
        for (Idea i : issueFacade.find(idea.getIssue().getId()).getIdeas()) {
            //Float temp = voteService.countAllByIdea(i) + new Float(i.getImportCount())/i.getIssue().getSubject().getTeam().getOntologies().size();
            Float temp = voteService.countAllByIdea(i) + new Float(i.getImportCount())/teamFacade.find(resourceFacade.find(idea.getIssue().getSubject().getId()).getTeam().getId()).getOntologies().size();
            //System.out.println("soutin votes:"+ i.getVotes() + " import:"+temp);
            if (temp > max) {
                max = temp;
                winner = i;
                empate=false;
                continue;
            }
            if (max.equals(temp)) {
                empate = true;
            }
        }
        for (Idea i : issueFacade.find(idea.getIssue().getId()).getIdeas()) {
            if (!empate && i.equals(winner)) {
                i.setWinning(true);
                ideaFacade.edit(i);
                setOntoClean(i);
            } else {
                i.setWinning(false);
                ideaFacade.edit(i);
                if (empate) {
                    setOntoCleanNull(i);
                }
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
        public Float score(Idea idea) {
            int votes = voteService.countAllByIdea(idea);
            Float score = votes + new Float(idea.getImportCount())/issueFacade.find(idea.getIssue().getId()).getSubject().getTeam().getOntologies().size();
            return score;
        }

    private void setOntoClean(Idea i) {
        Resource r = resourceFacade.find(i.getIssue().getSubject().getId());
        switch(i.getIssue().getKind()) {
            case RIGIDITY :
                r.setRigidity(i.getDataProperty());
                resourceFacade.edit(r);
                break;
            case IDENTITY :
                r.setIdentityy(i.getDataProperty());
                resourceFacade.edit(r);    
                break;
            case DEPENDENCE :
                r.setDependence(i.getDataProperty());
                resourceFacade.edit(r); 
                break;
            case UNITY :
                r.setUnity(i.getDataProperty());
                resourceFacade.edit(r);
                break;
        }    
    }

    private void setOntoCleanNull(Idea i) {
        Resource r = resourceFacade.find(i.getIssue().getSubject().getId());
        switch(i.getIssue().getKind()) {

            case RIGIDITY :
                r.setRigidity("");
                resourceFacade.edit(r);
                break;
            case IDENTITY :
                r.setIdentityy("");
                resourceFacade.edit(r);  
                break;
            case DEPENDENCE :
                r.setDependence("");
                resourceFacade.edit(r);
                break;
            case UNITY :
                r.setUnity("");
                resourceFacade.edit(r);
                break;
        }
    }    

    public void createIdea(Issue issue, Resource target) {
        Resource source = issue.getSubject();
        //1st create edge
        issue = issueFacade.find(issue.getId());
        System.out.println("//1st create edge");
        try {
        Edge edge = edgeFacade.findBySourceTarget(source, target);
        if (edge == null) {
            target = resourceFacade.find(target.getId());
            target.getSubClasses().add(source);
            //subResource.getSubClassOf().add(resource);
            resourceFacade.edit(target);
            resourceFacade.edit(source);
            edge = new Edge();
            edge.setSource(source);
            edge.setTarget(target);
            edge.setTeam(target.getTeam());
            edgeFacade.create(edge);
        }
        //2nd create idea
        System.out.println("//2nd create idea");
        Idea ideaTx = new Idea();
        ideaTx.setIssue(issue);
        ideaTx.getEdges().add(edge);
        ideaTx.getObjectProperties().add(target);
        ideaTx.setDataProperty("Tx");
        ideaFacade.create(ideaTx);
        issue.getIdeas().add(ideaTx);
        issueFacade.edit(issue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }


}
