/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.zzz;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author mauricio
 */
@Named(value = "arcade")
@SessionScoped
public class Arcade implements Serializable {

    /**
     * Creates a new instance of Arcade
     */
    public Arcade() {
    }
    
    public void insertCoin() {
        System.out.println("BITCOINED");
        try {
            ArrayList<String> list = new ArrayList<String>();
            //list.add("DISPLAY=:1");
            ProcessBuilder pb = new ProcessBuilder("xdotool", "key", "--delay", "200", "5");
            pb.environment().put("DISPLAY", ":1.0");
            Process p = pb.start();
            System.out.println("executou");
        } catch (IOException ex) {
            Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void keyDown() {
        System.out.println("lefted");                
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String keycode = (String) map.get("keycode");
        String code = "";
        if (keycode.equals("37")) {
            code = "Left";
        } else if (keycode.equals("39")) {
            code = "Right";
        } else if (keycode.equals("38")) {
            code = "Up";
        } else if (keycode.equals("40")) {
            code = "Down";
        } else if (keycode.equals("16")) {
            code = "Ctrl";
        }
        
        try {
            ArrayList<String> list = new ArrayList<String>();
            //list.add("DISPLAY=:1");
            ProcessBuilder pb = new ProcessBuilder("xdotool", "keydown", code);
            pb.environment().put("DISPLAY", ":1.0");
            Process p = pb.start();
            System.out.println("executou");

            System.out.println("keycode down: "+keycode);
        } catch (IOException ex) {
            Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void keyUp() {
        System.out.println("lefted");                
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String keycode = (String) map.get("keycode");
        String code = "";
        if (keycode.equals("37")) {
            code = "Left";
        } else if (keycode.equals("39")) {
            code = "Right";
        } else if (keycode.equals("38")) {
            code = "Up";
        } else if (keycode.equals("40")) {
            code = "Down";
        } else if (keycode.equals("16")) {
            code = "Ctrl";
        }
        
        try {
            ArrayList<String> list = new ArrayList<String>();
            //list.add("DISPLAY=:1");
            ProcessBuilder pb = new ProcessBuilder("xdotool", "keyup", code);
            pb.environment().put("DISPLAY", ":1.0");
            Process p = pb.start();
            System.out.println("executou");

            System.out.println("keycode up: "+keycode);
        } catch (IOException ex) {
            Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
        System.out.println("started");
        try {
            ArrayList<String> list = new ArrayList<String>();
            //list.add("DISPLAY=:1");
            ProcessBuilder pb = new ProcessBuilder("xdotool", "key", "--delay", "200", "1");
            pb.environment().put("DISPLAY", ":1.0");
            Process p = pb.start();
            System.out.println("executou");
        } catch (IOException ex) {
            Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void left() {
        System.out.println("lefted");
        try {
            ArrayList<String> list = new ArrayList<String>();
            //list.add("DISPLAY=:1");
            ProcessBuilder pb = new ProcessBuilder("xdotool", "key", "--delay", "200", "Left");
            pb.environment().put("DISPLAY", ":1.0");
            Process p = pb.start();
            System.out.println("executou");
                FacesContext context = FacesContext.getCurrentInstance();
    Map map = context.getExternalContext().getRequestParameterMap();
    String keycode = (String) map.get("keycode");
            System.out.println("keycode: "+keycode);
        } catch (IOException ex) {
            Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void right() {
        System.out.println("started");
        try {
            ArrayList<String> list = new ArrayList<String>();
            //list.add("DISPLAY=:1");
            ProcessBuilder pb = new ProcessBuilder("xdotool", "key", "--delay", "200", "Right");
            pb.environment().put("DISPLAY", ":1.0");
            Process p = pb.start();
            System.out.println("executou");
        } catch (IOException ex) {
            Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void shift() {
        System.out.println("started");
        try {
            ArrayList<String> list = new ArrayList<String>();
            //list.add("DISPLAY=:1");
            ProcessBuilder pb = new ProcessBuilder("xdotool", "key", "--delay", "200", "Ctrl");
            pb.environment().put("DISPLAY", ":1.0");
            Process p = pb.start();
            System.out.println("executou");
        } catch (IOException ex) {
            Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
