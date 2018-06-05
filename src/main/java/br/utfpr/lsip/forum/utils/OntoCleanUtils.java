/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.utils;

import br.utfpr.lsip.forum.dao.EdgeFacade;
import br.utfpr.lsip.forum.dao.ResourceFacade;
import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.IssueKind;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.service.TemplateService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mauricio
 */
@Stateless
public class OntoCleanUtils {

    @EJB
    private ResourceFacade resourceFacade;
    @EJB
    private EdgeFacade edgeFacade;
    @EJB
    private TemplateService templateService;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void checkOntoClean(Resource resource) {
        resource = resourceFacade.find(resource.getId());
        System.out.println(" ************ CHECKING ONTOCLEAN ");
        
        List<Edge> edges = resource.getEdgesAsSource();
        edges.addAll(resource.getEdgesAsTarget());
        
        System.out.println("Dependencia " + resource.getDependence());
        System.out.println("Rigidez " + resource.getRigidity());
        System.out.println("Unidade " + resource.getUnity());
        System.out.println("Identidade " + resource.getIdentityy());
        
        for (Edge edge : edges) {
            Resource source = edge.getSource();
            Resource target = edge.getTarget();
            edge.setWarning(false);
            edge.setMetaproperties("");
            edge.setText("");
            
            //DEPENDENCE
            if (source.getDependence().equals("-D") && target.getDependence().equals("+D")) {
                edge.setWarning(true);
                edge.setMetaproperties(edge.getMetaproperties() + " D");
                edge.setText(edge.getText() + " &#10; " + templateService.justify(IssueKind.DEPENDENCE, source, target) );
            }
            
            //RIGIDITY
            if (source.getRigidity().equals("+R") && (target.getRigidity().equals("-R") || target.getRigidity().equals("~R")) ) {
                edge.setWarning(true);
                edge.setMetaproperties(edge.getMetaproperties() + " R");
                edge.setText(edge.getText() + " &#10; " + templateService.justify(IssueKind.RIGIDITY, source, target) );
            } else if (source.getRigidity().equals("-R") && target.getRigidity().equals("~R") ) {
                edge.setWarning(true);
                edge.setMetaproperties(edge.getMetaproperties() + " R");
                edge.setText(edge.getText() + " &#10; " + templateService.justify(IssueKind.RIGIDITY, source, target) );
//            } else if (source.getRigidity().equals("~R") && (target.getRigidity().equals("-R") || target.getRigidity().equals("+R")) ) {
//                edge.setWarning(true);
//                edge.setMetaproperties(edge.getMetaproperties() + " R");
//                edge.setText(edge.getText() + " &#10; " + templateService.justify(IssueKind.RIGIDITY, source, target) );
            }
            
            //IDENTITY
            if (source.getIdentityy().equals("-I") && (target.getIdentityy().equals("+I") || target.getIdentityy().equals("*I")) ) {
                edge.setWarning(true);
                edge.setMetaproperties(edge.getMetaproperties() + " I");
                edge.setText(edge.getText() + " &#10; " + templateService.justify(IssueKind.IDENTITY, source, target) );
            }
            
            //UNITY
            if (source.getUnity().equals("-U") && (target.getUnity().equals("+U") || target.getUnity().equals("~U")) ) {
                edge.setWarning(true);
                edge.setMetaproperties(edge.getMetaproperties() + " U");
                edge.setText(edge.getText() + " &#10; " + templateService.justify(IssueKind.UNITY, source, target) );
            } else if (source.getUnity().equals("+U") && target.getUnity().equals("~U") ) {
                edge.setWarning(true);
                edge.setMetaproperties(edge.getMetaproperties() + " U");
                edge.setText(edge.getText() + " &#10; " + templateService.justify(IssueKind.UNITY, source, target) );
            } else if (source.getUnity().equals("~U") && target.getUnity().equals("+U") ) {
                edge.setWarning(true);
                edge.setMetaproperties(edge.getMetaproperties() + " U");
                edge.setText(edge.getText() + " &#10; " + templateService.justify(IssueKind.UNITY, source, target) );
            }
            edgeFacade.edit(edge);
            
        }        
        System.out.println("************ ONTOCLEAN PASSED");
    }

}
