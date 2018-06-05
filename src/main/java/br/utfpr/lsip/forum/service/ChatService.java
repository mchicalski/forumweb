/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.TeamFacade;
import br.utfpr.lsip.forum.model.Team;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author mauricio
 */
@Singleton
public class ChatService {

    @Inject
    private TeamFacade teamFacade;

    private HashMap<String, List<String>> chats;
    
 
    @PostConstruct
    public void init() {
        chats = new HashMap<String, List<String>>();
        for (Team team : teamFacade.findAll()) {
            createChat(team.getName());
        }
    }
    
    public void createChat(String chat) {
        chats.put(chat, new ArrayList<String>());
        chats.get(chat).add("chat iniciado: " + Calendar.getInstance().getTime().toString());
    }
    
    public List<String> listChatMessages(String chat) {
        return chats.get(chat);
    }
    
    public synchronized void putMessage(String chat, String message) {
        chats.get(chat).add(": " + message);
    }

}
