/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.zzz;

import br.utfpr.lsip.forum.model.Edge;
import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.service.EdgeService;
import br.utfpr.lsip.forum.service.IdeaService;
import br.utfpr.lsip.forum.service.ResourceService;
import br.utfpr.lsip.forum.web.TeamView;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author mauricio
 */
@Named(value = "treeBean")
@SessionScoped
public class TreeBean implements Serializable {
  
    @EJB
    private ResourceService resourceService;
    @EJB
    private EdgeService edgeService;
    @EJB
    private IdeaService ideaService;
    @Inject
    private TeamView teamView;
    private TreeNode root;  
  
    private TreeNode selectedNode;  
    private Team selectedTeam;
    private boolean first;
    private List<Resource> resources;
    private List<Edge> edges;
  
    public TreeBean() {  
        root = new DefaultTreeNode("Root", null);  
//        TreeNode node0 = new DefaultTreeNode("Node 0", root);  
//        TreeNode node1 = new DefaultTreeNode("Node 1", root);  
//        TreeNode node2 = new DefaultTreeNode("Node 2", root);  
//  
//        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);  
//        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);  
//  
//        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);  
//        TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);  
//  
//        TreeNode node000 = new DefaultTreeNode("Node 0.0.0", node00);  
//        TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);  
//        TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);  
//  
//        TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);  
    }  
  
    public TreeNode getRoot() {  
        System.out.println("root " + root.getChildCount());
        if (selectedTeam == null || !selectedTeam.equals(teamView.getSelected()) || root.getChildCount() == 0) {
            try{
                selectedTeam = teamView.getSelected();
                load();
                resources = resourceService.findAllByTeam(selectedTeam);
                edges = edgeService.findAllEdgesByTeam(selectedTeam);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return root;
    }  
  
    public TreeNode getSelectedNode() {  
        return selectedNode;  
    }  
  
    public void setSelectedNode(TreeNode selectedNode) {  
        this.selectedNode = selectedNode;  
    }  
  
    public void onNodeExpand(NodeExpandEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
  
    public void onNodeCollapse(NodeCollapseEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
  
    public void onNodeSelect(NodeSelectEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());  
  
        
        RequestContext.getCurrentInstance().execute("abc('"+((Resource)event.getTreeNode().getData()).getId()+"');");
        FacesContext.getCurrentInstance().addMessage(null, message); 
        Resource resource = (Resource) event.getTreeNode().getData();
        //resources = resourceService.findAllResourcesRelatedTo(resource);
        //edges = edgeService.findAllEdgesRelatedTo(resource);
//        for (Resource r : getResources()) {
//            System.out.println("okokok "+ r);
//        }
//        for (Edge e : getEdges()) {
//            System.out.println("eke source "+ e.getSource() + " target " + e.getTarget());
//        }
        
    }  
  
    public void onNodeUnselect(NodeUnselectEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
    
    public void load(TreeNode node) {
        
        if (node == null) {
            root = new DefaultTreeNode("Root", null);  
            Resource resource = resourceService.findThingByTeam(teamView.getSelected());
            if (resource == null) {
                return;
            }
            DefaultTreeNode newnode = new DefaultTreeNode(resource, root);
            newnode.setData(resource);
            load(newnode);
        } else {
            Resource resource = (Resource) node.getData();
            List<Resource> list = resourceService.findAllSubClasses(resource);
            for (Resource r : list) {
                DefaultTreeNode newnode = new DefaultTreeNode(r, node);
                newnode.setData(r);
                load(newnode);
            }
        }
    }
    
    public void load() {
        load(null);
    }

    /**
     * @return the resources
     */
    public List<Resource> getResources() {
        //System.out.println("getting resources");
        return resources;
    }


    /**
     * @return the edges
     */
    public List<Edge> getEdges() {
        //System.out.println("getting edges");
        return edgeService.findAllEdgesByTeam(selectedTeam);
    }
    
    public Integer edgeSpacing(Edge edge) {
        //System.out.println("spacing "+edge);
        for (Idea idea : ideaService.findAllIdeasByEdge(edge)) {
            //System.out.println("entra for");
//            if (idea.getImportCount().equals(2)) {
//                System.out.println("return 0");
//                return 0;
//            }
            if (idea.isWinning()) {
                return 0;
            }
        }
        //System.out.println("return 16");
        return 16;
        
    }
    
    public String edgeImage(Edge edge) {
        if (edge.isWarning()) {
            return "<img src='images/Error.png'/>";
        }
        return "";
    }
    
    public String edgeOpacity(Edge edge) {
        //System.out.println("spacing "+edge);
        for (Idea idea : ideaService.findAllIdeasByEdge(edge)) {
            //System.out.println("entra for");
//            if (idea.getImportCount().equals(2)) {
//                System.out.println("return 0");
//                return 0;
//            }
            if (idea.isWinning()) {
                return "0.95";
            }
        }
        //System.out.println("return 16");
        return "0.2";
    }

}
