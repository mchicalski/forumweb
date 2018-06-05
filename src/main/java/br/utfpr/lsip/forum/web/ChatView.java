/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.web;

import br.utfpr.lsip.forum.service.ChatService;
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
@Named(value = "chatView")
@SessionScoped
public class ChatView implements Serializable {
    
    @Inject
    private ChatService chatService;
    @Inject
    private TeamView teamView;
    
    private String message = "";

    
    public void sendMessage() {
        chatService.putMessage(teamView.getSelected().getName(), message);
        PushContextFactory.getDefault().getPushContext().push("/chat-" + teamView.getSelected().getName(), message);
        message = "";
    }
    
    public List<String> list() {
        if (teamView.getSelected() == null)
            return null;
        return chatService.listChatMessages(teamView.getSelected().getName());
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
