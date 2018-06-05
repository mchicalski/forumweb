/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.OntologyFacade;
import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.Ontology;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.utils.OntologyUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author mauricio
 */
@Stateless
public class OntologyService {

    @EJB
    private OntologyFacade ontologyFacade;
    @EJB
    private TeamFacade teamFacade;
    @EJB
    private OntologyUtils ontologyUtils;
    @EJB
    private IdeaService ideaService;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void create(Ontology ontology) {
        ontologyFacade.create(ontology);
        ontologyUtils.loadAllClasses(ontology);
        ontologyFacade.edit(ontology);

    }
    
    public List<Ontology> listOntologiesByTeam(Team team) {
        return teamFacade.find(team.getId()).getOntologies();
    }
    
    public Integer countByTeam(Team team) {
        return listOntologiesByTeam(team).size();
    }
    
    public void merge() {
        System.out.println("mmer");
        try {
            ontologyUtils.shouldMergeOntologies();
            System.out.println("ok");
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(OntologyService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(OntologyService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
