/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.dao.WidgetFacade;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Widget;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mauricio
 */
@Named(value = "widgetView")
@SessionScoped
public class WidgetView implements Serializable {

    @Inject
    private WidgetFacade widgetFacade;
    @Inject
    private UserView userView;
    @Inject
    private TeamView teamView;
    /**
     * Creates a new instance of WidgetView
     */
    public WidgetView() {
    }
    
    public List<Widget> listWidgets() {
        return widgetFacade.findAllByTeamUser(teamView.getSelected(), userView.getConnectedUser());
    }
    
    public boolean ok(Resource r) {
        System.out.println("..."+r);
        System.out.println("..."+r.getUri());
        if (r.toString().equals("Pessoa"))
            return true;
        return false;
    }
}
