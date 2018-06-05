/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.dao.OntologyFacade;
import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.Ontology;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.service.IdeaService;
import br.utfpr.lsip.forum.service.OntologyService;
import br.utfpr.lsip.forum.service.TeamService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author mauricio
 */
@Named(value = "ontologyView")
@SessionScoped
public class OntologyView implements Serializable {

    @Inject
    private OntologyService ontologyService;
    
    @Inject
    private TeamView teamView;
    
    @EJB
    private IdeaService ideaService;
    @EJB
    private TeamService teamService;
    /**
     * Creates a new instance of OntologyView
     */
    public OntologyView() {
    }
    
   public void handleFileUpload(FileUploadEvent event) { 
        Ontology ontology = new Ontology();
        ontology.setFile(event.getFile().getContents());
        ontology.setFileName(event.getFile().getFileName());
        ontology.setTeam(teamView.getSelected());
        ontologyService.create(ontology);
    }
   
   public List<Ontology> listOntologiesBySelectedTeam() {
       return ontologyService.listOntologiesByTeam(teamView.getSelected());
   }
   
   public Integer countByTeam(Team team) {
       return ontologyService.listOntologiesByTeam(team).size();
   }
   
   public void merge(){
       System.out.println("ui-button");
       processVotes();
       //ontologyService.merge();
   }
   
   public void processVotes() {

   }
   
}
