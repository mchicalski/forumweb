/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.service.IssueService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mauricio
 */
@Named(value = "issueView")
@SessionScoped
public class IssueView implements Serializable {

    @Inject
    private ResourceView resourceView;
    
    @EJB
    private IssueService issueService;
    private Issue opened;
    /**
     * Creates a new instance of IssueView
     */
    public IssueView() {
    }
    
    public void open(Issue issue) {
        if (!issue.equals(opened)) {
            setOpened(issue);
        } else {
            setOpened(null);
        }
        RequestContext.getCurrentInstance().execute("update"+issue.getSubject().getId()+"();"); //
    }
    
    public boolean compareOpenedIssue(Issue issue) {
        if (issue.equals(getOpened())) {
            return true;
        }
        return false;
    }
    
    public List<Issue> getIssues() {
        return issueService.findAllIssuesByResource(resourceView.getOpened());
    }

    /**
     * @return the opened
     */
    public Issue getOpened() {
        return opened;
    }

    /**
     * @param opened the opened to set
     */
    public void setOpened(Issue opened) {
        this.opened = opened;
    }
    
}
