/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.User;
import br.utfpr.lsip.forum.model.Vote;
import br.utfpr.lsip.forum.service.IdeaService;
import br.utfpr.lsip.forum.service.IssueService;
import br.utfpr.lsip.forum.service.ResourceService;
import br.utfpr.lsip.forum.service.VoteService;
import br.utfpr.lsip.forum.utils.OntoCleanUtils;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author mauricio
 */
@Named(value = "voteView")
@SessionScoped
public class VoteView implements Serializable {

    @EJB
    private VoteService voteService;
    @EJB
    private IdeaService ideaService;
    @EJB
    private ResourceService resourceService;
    @EJB 
    private IssueService issueService;
    @EJB
    private OntoCleanUtils ontoCleanUtils;
    
    @Inject
    private UserView userView;
    @Inject 
    private IssueView issueView;
    @Inject
    private TeamView teamView;
    /**
     * Creates a new instance of VoteView
     */
    public VoteView() {
    }
    
    public String getCoinImageFor(Idea idea) {
        //System.out.println("geting coin img");
        Vote vote = voteService.findByIdeaUser(idea, userView.getConnectedUser());
        //System.out.println("get vote img");
        //Vote vote = null;
        if (vote != null) {
            return "Coin-Single-Gold.png";
        } else {
            return "Coin-Single-Silver.png";
        }
    }
    
    public void vote(Idea idea) {
        Vote vote = voteService.findByIssueUser(idea.getIssue(), userView.getConnectedUser());
        if (vote != null) {
            voteService.remove(vote);
        }
        vote = new Vote();
        vote.setIdea(idea);
        vote.setUser(userView.getConnectedUser());
        voteService.create(vote);
        ideaService.processVotes(idea);
        ontoCleanUtils.checkOntoClean(idea.getIssue().getSubject());
        System.out.println("PUSHING VOTE");
        PushContextFactory.getDefault().getPushContext().push("/team-" + teamView.getSelected().getName(), issueView.getOpened().getSubject().getId());

    }
    
    public int countAllByIdea(Idea idea) {
        return voteService.countAllByIdea(idea);
    }
    
    public boolean alreadyVotedResource(Resource resource) {
//        for (Issue issue : issueService.findAllIssuesByResource(resource)) {
//            Vote vote = voteService.findByIssueUser(issue, userView.getConnectedUser());
//            if (vote == null) {
//                return false;
//            }
//        }
        if (voteService.countByResourceUser(resource, userView.getConnectedUser()) == 5) {
            return true;
        }
        return false;
    }
    
    public boolean alreadyVotedIssue(Issue issue) {
        Vote vote = voteService.findByIssueUser(issue, userView.getConnectedUser());
        if (vote == null) {
            return false;
        }
        return true;
    }
}
