/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.service.TeamService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author mauricio
 */
@Named(value = "teamView")
@SessionScoped
public class TeamView implements Serializable {

    @Inject 
    private TeamService teamService;
    @Inject
    private HeaderView header;
    
    private Team selected;
    private boolean creating;
    /**
     * Creates a new instance of TeamView
     */
    public TeamView() {
    }

    public void create() {
        teamService.create(selected);
        toogleCreating();
        PushContextFactory.getDefault().getPushContext().push("/groupsUpdate", "");
    }
    
    public void toogleCreating() {
        if (isCreating()) {
            setCreating(false);
        } else {
            setCreating(true);
            selected = new Team();
        }
    }
    
    public List<Team> listAllTeams() {
        List<Team> list = teamService.findAll();
        return list;
    }
    
    public void open(Team team) {
        selected = team;
        header.setOpenView("team");
    }

    /**
     * @return the selected
     */
    public Team getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Team selected) {
        this.selected = selected;
    }

    /**
     * @return the creating
     */
    public boolean isCreating() {
        return creating;
    }

    /**
     * @param creating the creating to set
     */
    public void setCreating(boolean creating) {
        this.creating = creating;
    }
    
}
