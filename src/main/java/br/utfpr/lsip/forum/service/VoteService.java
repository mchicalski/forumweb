/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.VoteFacade;
import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.Issue;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.User;
import br.utfpr.lsip.forum.model.Vote;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mauricio
 */
@Stateless
public class VoteService {

    @EJB
    private VoteFacade voteFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Vote findByIdeaUser(Idea idea, User user) {
        return voteFacade.findByIdeaUser(idea, user);
    }

    public void create(Vote vote) {
        voteFacade.create(vote);
    }

    public Vote findByIssueUser(Issue issue, User connectedUser) {
        return voteFacade.findByIssueUser(issue, connectedUser);
    }

    public void edit(Vote vote) {
        voteFacade.edit(vote);
    }

    public void remove(Vote vote) {
        voteFacade.remove(vote);
    }

    public int countAllByIdea(Idea idea) {
        return voteFacade.countAllByIdea(idea);
    }
    
    public int countByResourceUser(Resource resource, User user) {
        return voteFacade.countByResourceUser(resource, user);
    }
}
