/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Argument;
import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.service.ArgumentService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author mauricio
 */
@Named(value = "argumentView")
@SessionScoped
public class ArgumentView implements Serializable {

    
    private String text;
    @EJB
    private ArgumentService argumentService;
    /**
     *
     */
    @Inject
    private UserView userView;
    @Inject
    private IdeaView ideaView;
    @Inject
    private TeamView teamView;
    /**
     * Creates a new instance of ArgumentView
     */
    public ArgumentView() {
    }
    
    
    public List<Argument> findAllByIdea(Idea idea) {
        return argumentService.findAllByIdea(idea);
    }
    
    public void create() {
        System.out.println("creating arg "+ text);
        Argument argument = new Argument();
        argument.setUser(userView.getConnectedUser());
        argument.setIdea(ideaView.getOpened());
        argument.setText(text);
        argumentService.create(argument);
        System.out.println("text created: "+text);
        text = "";
        
        PushContextFactory.getDefault().getPushContext().push("/team-" + teamView.getSelected().getName(), ideaView.getOpened().getIssue().getSubject().getId());
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    
}
