/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.IssueKind;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.service.IdeaService;
import br.utfpr.lsip.forum.service.ResourceService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author mauricio
 */
@Named(value = "ideaView")
@SessionScoped
public class IdeaView implements Serializable {

    @Inject
    private IssueView issueView;
    @EJB
    private IdeaService ideaService;
    @EJB
    private ResourceService resourceService;
    @Inject 
    private OntologyView ontologyView;
    @Inject 
    private TeamView teamView;
    
    private Idea opened;
    
    private String selectedResource;
    /**
     * Creates a new instance of IdeaView
     */
    public IdeaView() {
    }
    
    public void open(Idea idea) {
        if (!idea.equals(opened)) {
            setOpened(idea);
        } else {
            setOpened(null);
        }
        RequestContext.getCurrentInstance().execute("update"+idea.getIssue().getSubject().getId()+"();"); //
    }
    
    public boolean compareOpenedIdea(Idea idea) {
        if (idea.equals(getOpened())) {
            return true;
        }
        return false;
    }
    
    public List<Idea> getIdeas() {
        //System.out.println("gettin ideas for " + issueView.getOpened());
//        for (Idea idea : ideaService.findAllIdeasByIssue(issueView.getOpened())) {
//            System.out.println("idea - "+ idea);
//        }
        return ideaService.findAllIdeasByIssue(issueView.getOpened());
    }

    /**
     * @return the opened
     */
    public Idea getOpened() {
        return opened;
    }

    /**
     * @param opened the opened to set
     */
    public void setOpened(Idea opened) {
        this.opened = opened;
    }
    
    public String score(Idea idea) {
        return String.format("%.2f",ideaService.score(idea));
    }
    
    public boolean isWinner(Idea idea) {
        return ideaService.isWinner(idea);
    }
    
    public boolean showCreateIdea(Issue issue) {
        if (IssueKind.TAXONOMY.equals(issue.getKind())) {
            return true;
        }
        return false;
    }
    
    public List<Resource> listResourcesToIssue(Issue issue) {
        List<Resource> resources = resourceService.findAllByTeam(teamView.getSelected());
        return resources;
    }

    /**
     * @return the selectedResource
     */
    public String getSelectedResource() {
        return selectedResource;
    }

    /**
     * @param selectedResource the selectedResource to set
     */
    public void setSelectedResource(String selectedResource) {
        this.selectedResource = selectedResource;
    }
    
    public void createIdea(Issue issue) {
        if (resourceService.findByNameTeam(selectedResource, teamView.getSelected()).equals(issue.getSubject())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro ao criar idea", "O recurso origem e destino sao iguais!"));
            return;
        }
        if (selectedResource == null || selectedResource.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro ao criar idea", "Selecione um recurso"));
            return;
        }
        ideaService.createIdea(issue, resourceService.findByNameTeam(selectedResource, teamView.getSelected()));
        System.out.println("ok agora vai push");
        PushContextFactory.getDefault().getPushContext().push("/team-" + teamView.getSelected().getName(), issueView.getOpened().getSubject().getId());
        //criar edge
        //criar ideia e adicionar edge
    }

}
