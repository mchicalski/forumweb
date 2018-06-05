/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Team;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mauricio
 */
@Stateless
public class TeamService {

    @EJB
    private TeamFacade teamFacade;
    @EJB
    private ChatService chatService;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    

    public void create(Team team) {
        teamFacade.create(team);
        chatService.createChat(team.getName());
        
    }
    
    public List<Team> findAll() {
        return teamFacade.findAll();
    }
    
    public Team find(Team team) {
        return teamFacade.find(team.getId());
    }
}
