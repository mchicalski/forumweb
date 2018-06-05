/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.ArgumentFacade;
import br.utfpr.lsip.forum.model.Argument;
import br.utfpr.lsip.forum.model.Idea;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mauricio
 */
@Stateless
public class ArgumentService {

    @EJB 
    private ArgumentFacade argumentFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Argument> findAllByIdea(Idea idea) {
        return argumentFacade.findAllByIdea(idea);
    }

    public void create(Argument argument) {
        argumentFacade.create(argument);
    }
}
