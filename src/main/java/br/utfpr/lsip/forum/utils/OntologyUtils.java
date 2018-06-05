/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.utils;

import br.utfpr.lsip.forum.dao.EdgeFacade;
import br.utfpr.lsip.forum.dao.IdeaFacade;
import br.utfpr.lsip.forum.dao.IssueFacade;
import br.utfpr.lsip.forum.dao.ResourceFacade;
import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.IssueKind;
import br.utfpr.lsip.forum.model.Ontology;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.service.IdeaService;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.io.XMLUtils;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.NamespaceUtil;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.util.OWLOntologySingletonSetProvider;
import org.semanticweb.owlapi.util.SimpleRootClassChecker;

/**
 *
 * @author mauricio
 */
@Stateless
public class OntologyUtils {

    @EJB
    private ResourceFacade resourceFacade;
    @EJB
    private EdgeFacade edgeFacade;
    @EJB
    private IssueFacade issueFacade;
    @EJB
    private IdeaFacade ideaFacade;
    @EJB
    private TeamFacade teamFacade;
    @EJB
    private IdeaService ideaService;
    
    public static List<String> R_VALUES = Arrays.asList("+R", "-R", "~R");
    public static List<String> I_VALUES = Arrays.asList("+I", "-I", "*I"); //I* = +I+O, +I = +I-O 
    public static List<String> D_VALUES = Arrays.asList("+D", "-D");
    public static List<String> U_VALUES = Arrays.asList("+U", "-U", "~U");

    
public void shouldMergeOntologies() throws OWLOntologyCreationException,
OWLOntologyStorageException {
// Just load two arbitrary ontologies for the purposes of this example
OWLOntologyManager man = OWLManager.createOWLOntologyManager();
    System.out.println("abr1");
man.loadOntologyFromOntologyDocument(IRI.create("file:/home/mauricio/Downloads/dbpedia_3.8.owl"));
System.out.println("abr2");
man.loadOntologyFromOntologyDocument(IRI
.create("file:/home/mauricio/Downloads/grupoD.owl"));
// Create our ontology merger
OWLOntologyMerger merger = new OWLOntologyMerger(man);
// We merge all of the loaded ontologies. Since an OWLOntologyManager is
// an OWLOntologySetProvider we just pass this in. We also need to
// specify the URI of the new ontology that will be created.
IRI mergedOntologyIRI = IRI.create("http://www.semanticweb.com/mymergedont");
OWLOntology merged = merger.createMergedOntology(man, mergedOntologyIRI);
// Print out the axioms in the merged ontology.
for (OWLAxiom ax : merged.getAxioms()) {
System.out.println(ax);
}
// Save to RDF/XML
man.saveOntology(merged, new RDFXMLOntologyFormat(),
IRI.create("file:/home/mauricio/mergedont2.owl"));
} 

    public void loadAllResources(Ontology _ontology) {
        loadAllClasses(_ontology);
        if (true)
            return;
        try {
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
            OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new ByteArrayInputStream(_ontology.getFile()));
            System.out.println("Ontology Loaded...");
            System.out.println("Ontology : " + ontology.getOntologyID());
            System.out.println("Format : " + manager.getOntologyFormat(ontology));
            //OWLClass clazz = manager.getOWLDataFactory().getOWLThing();
            //loadSubClasses(_ontology, clazz, ontology, 0);
            System.out.println("xml util " + XMLUtils.getNCNamePrefix("http://www.co-ode.org/ontologies/pizza/pizza.owl#Pizza"));
            System.out.println("xml util " + XMLUtils.getNCNameSuffix("http://www.co-ode.org/ontologies/pizza/pizza.owl#Pizza"));
            System.out.println("xml util " + XMLUtils.getNCNamePrefix("http://dbpedia.org/ontology/Activity"));
            System.out.println("xml util " + XMLUtils.getNCNameSuffix("http://dbpedia.org/ontology/Activity"));            
            System.out.println("xml util " + XMLUtils.getNCNamePrefix("owl:Thing"));
            System.out.println("xml util " + XMLUtils.getNCNameSuffix("owl:Thing"));
            
            SimpleRootClassChecker checker = new SimpleRootClassChecker(new OWLOntologySingletonSetProvider(ontology).getOntologies());
            for (OWLClass owlClass : ontology.getClassesInSignature()) {
                if (checker.isRootClass(owlClass)) {
                    System.out.println("root "+owlClass);
                    loadSubClasses(_ontology, owlClass, ontology, 0);
                }
                //System.out.println("owlClass" + owlClass);
            }
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(OntologyUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Resource loadSubClasses(Ontology _ontology, OWLClass clazz, OWLOntology ontology, int t) {
            Set<OWLClassExpression> subClasses = clazz.getSubClasses(ontology);
            Iterator<OWLClassExpression> i = subClasses.iterator();
            t++;
            String classLocalName = XMLUtils.getNCNameSuffix(clazz.toString().replace("<", "").replace(">", ""));
            Resource resource = null;
            try {
                resource = resourceFacade.findByNameTeam(classLocalName, _ontology.getTeam()); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (resource == null) {

                resource = new Resource();
                resource.setUri(clazz.toString());
                resource.setTeam(_ontology.getTeam());
                resource.setName(classLocalName);
                resourceFacade.create(resource);
                Issue issue = new Issue();
                issue.setKind(IssueKind.TAXONOMY);
                issue.setSubject(resource);
                issueFacade.create(issue);
                resource.getIssues().add(issue);
                //resourceFacade.edit(resource);
            }

            //resourceFacade
            while (i.hasNext())  {
                OWLClass nc = i.next().asOWLClass();
                System.out.println("");
//                for (int x = t; x > 0; x--) {
//                    System.out.print("\t");
//                }
                System.out.print("Class : " + nc);
                Resource resource2 = loadSubClasses(_ontology, nc, ontology, t);
                System.out.println("subclasses "+resource.getSubClasses());
                resource.getSubClasses().add(resource2);

                resourceFacade.edit(resource);
                resourceFacade.edit(resource2);
                Edge edge = new Edge();
                edge.setSource(resource2);
                edge.setTarget(resource);
                edge.setTeam(_ontology.getTeam());
                edgeFacade.create(edge);
                
                for (Issue issue2 : resourceFacade.find(resource2.getId()).getIssues()) {
                    if (issue2.getKind().equals(IssueKind.TAXONOMY)) {
                        System.out.println("TAXYYYYY");
//                        for (Idea idea2 : issue2.getIdeas()) {
//                            for (Resource r : idea2.getObjectProperties()) {
//                                if ()
//                            }
//                        }
                        Idea idea = new Idea();
                        idea.setIssue(issue2);
                        idea.getObjectProperties().add(resource);
                        ideaFacade.create(idea);
                        issue2.getIdeas().add(idea);
                        issueFacade.edit(issue2);
                    }
                }
                System.out.println("r3");
            }
            return resource;
    }
    
    
    
    public void loadAllClasses(Ontology ontology) {
        try {
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
            OWLOntology _ontology = manager.loadOntologyFromOntologyDocument(new ByteArrayInputStream(ontology.getFile()));
            
            //1st pass LOAD CLASSES
            System.out.println("//1st pass LOAD CLASSES");
            for (OWLClass clazz : _ontology.getClassesInSignature()) {
                System.out.println("CLAZZZ : " + clazz.toString());
                String classLocalName = XMLUtils.getNCNameSuffix(clazz.toString().replace("<", "").replace(">", ""));
                Resource resource = null;
                try {
                    resource = resourceFacade.findByNameTeam(classLocalName, ontology.getTeam()); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (resource == null) {
                    resource = new Resource();
                    resource.setUri(clazz.toString());
                    resource.setTeam(ontology.getTeam());
                    resource.setName(classLocalName);
                    resourceFacade.create(resource);
                    for (IssueKind value :IssueKind.values()) {
                        createIssue(value, resource);
                        System.out.println("creating : "+resource + " - " + value);
                    }

                }
            }
            
            
            //2nd pass LOAD EDGES
            System.out.println("//2nd pass LOAD EDGES");
            SimpleRootClassChecker checker = new SimpleRootClassChecker(new OWLOntologySingletonSetProvider(_ontology).getOntologies());
            for (OWLClass owlClass : _ontology.getClassesInSignature()) {
                if (checker.isRootClass(owlClass)) {
                    System.out.println(owlClass);
                    loadEdges(ontology, _ontology, owlClass);
                }
            }
            
            
            //3rd pass LOAD IDEAS
            System.out.println("//3rd pass LOAD IDEAS");
            for (OWLClass owlClass : _ontology.getClassesInSignature()) {
                if (owlClass.toString().equals("owl:Thing"))
                    continue;
                loadIdea(ontology, _ontology, owlClass);
                System.out.println("i "+ owlClass);
            }
            

            
        } catch (Exception ex) {
            Logger.getLogger(OntologyUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createIssue(IssueKind issueKind, Resource resource) {
        Issue issue = new Issue();
        issue.setKind(issueKind);
        issue.setSubject(resource);
        issueFacade.create(issue);
        resource.getIssues().add(issue);
        resourceFacade.edit(resource);
        if (!issueKind.equals(IssueKind.TAXONOMY)) {
            loadOntoCleanIdeas(issueKind, issue);
        }
    }
    
    private void loadOntoCleanIdeas(IssueKind issueKind, Issue issue) {
        List<String> values = getValuesOC(issueKind);
        for (String value : values) {
            Idea idea = new Idea();
            idea.setIssue(issue);
            idea.setDataProperty(value);
            ideaFacade.create(idea);
        }
    }
    
    private Resource loadEdges(Ontology ontology, OWLOntology _ontology, OWLClass clazz) {
        System.out.println("1" + clazz);
        Set<OWLClassExpression> subClasses = clazz.getSubClasses(_ontology);
        Iterator<OWLClassExpression> subClassesIterator = subClasses.iterator();
        String classLocalName = XMLUtils.getNCNameSuffix(clazz.toString().replace("<", "").replace(">", ""));
        Resource resource = null;
        try {
            resource = resourceFacade.findByNameTeam(classLocalName, ontology.getTeam());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resource != null) {
            while (subClassesIterator.hasNext())  {
                OWLClass subClass = subClassesIterator.next().asOWLClass();
                Resource subResource = loadEdges(ontology, _ontology, subClass);
                if (edgeFacade.findBySourceTarget(subResource, resource) == null) {
                    resource.getSubClasses().add(subResource);
                    //subResource.getSubClassOf().add(resource);
                    resourceFacade.edit(resource);
                    resourceFacade.edit(subResource);
                    Edge edge = new Edge();
                    edge.setSource(subResource);
                    edge.setTarget(resource);
                    edge.setTeam(ontology.getTeam());
                    edgeFacade.create(edge);
                }
            }
        }
        return resource;
    }
    
    
    private void loadIdea(Ontology ontology, OWLOntology _ontology, OWLClass clazz) {
        System.out.println("     load idea>"+clazz);
        Set<OWLClassExpression> superClasses = clazz.getSuperClasses(_ontology);
        Iterator<OWLClassExpression> superClassesIterator = superClasses.iterator();
        String classLocalName = XMLUtils.getNCNameSuffix(clazz.toString().replace("<", "").replace(">", ""));
        Resource resource = null;
        try {
            resource = resourceFacade.findByNameTeam(classLocalName, ontology.getTeam());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resource != null) {
            Issue issueTx = null;
            for(Issue issue : resource.getIssues()) {
                if (issue.getKind().equals(IssueKind.TAXONOMY)) {
                    issueTx = issue;
                    break;
                }
            }
            Idea ideaTx = new Idea();
            System.out.println("ideatx");
            while (superClassesIterator.hasNext())  {
                OWLClass superClass = superClassesIterator.next().asOWLClass();
                String superClassLocalName = XMLUtils.getNCNameSuffix(superClass.toString().replace("<", "").replace(">", ""));
                Resource superResource = resourceFacade.findByNameTeam(superClassLocalName, ontology.getTeam());
                ideaTx.getObjectProperties().add(superResource);
                System.out.println("superclass");
            }
            System.out.println("++"+ideaTx.getObjectProperties());
            boolean contains = false;
            if (issueTx == null) {
                System.out.println("returnin");
                return;
            }
            for (Idea idea : issueTx.getIdeas()) {
                if (idea.getObjectProperties().containsAll(ideaTx.getObjectProperties()) && ideaTx.getObjectProperties().containsAll(idea.getObjectProperties())) {
                    idea.setImportCount(idea.getImportCount()+1);
                    contains = true;
                    ideaFacade.edit(idea);
                    break;
                }
            }
            
            if (!contains) {
                ideaTx.setImportCount(1);
                ideaTx.setIssue(issueTx);
                ideaTx.setDataProperty("Tx");
                   for (Resource r : ideaTx.getObjectProperties()) {
                        ideaTx.getEdges().add(edgeFacade.findBySourceTarget(resource, r));
                    }
                ideaFacade.create(ideaTx);
                issueTx.getIdeas().add(ideaTx);
                issueFacade.edit(issueTx);
            }
            System.out.println("PROCESSINGVOTES");
            
            ideaService.processVotes(ideaTx);
                

            
        }
    }
    
    private List<String> getValuesOC(IssueKind issueKind) {
        switch (issueKind) {
            case RIGIDITY: return R_VALUES;
            case IDENTITY: return I_VALUES;
            case DEPENDENCE: return D_VALUES;
            case UNITY: return U_VALUES;
            
            default:return null;
        }

    }

}
