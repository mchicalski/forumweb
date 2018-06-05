/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.model.Team;
import br.utfpr.lsip.forum.model.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author mauricio
 */
@Named(value = "headerView")
@SessionScoped
public class HeaderView implements Serializable {

    private String openView = "login";
    private User connectedUser;
    private Team selectedTeam;
    /**
     * Creates a new instance of HeaderView
     */
    public HeaderView() {
    }
    
    public boolean compareView(String otherView) {
        if (openView == null || openView.isEmpty()) {
            return false;
        }
        return openView.equalsIgnoreCase(otherView);
    }

    /**
     * @return the openView
     */
    public String getOpenView() {
        return openView;
    }

    /**
     * @param openView the openView to set
     */
    public void setOpenView(String openView) {
        this.openView = openView;
    }

    /**
     * @return the connectedUser
     */
    public User getConnectedUser() {
        return connectedUser;
    }

    /**
     * @param connectedUser the connectedUser to set
     */
    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    /**
     * @return the selectedTeam
     */
    public Team getSelectedTeam() {
        return selectedTeam;
    }

    /**
     * @param selectedTeam the selectedTeam to set
     */
    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }
}
