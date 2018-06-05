/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.zzz;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author mauricio
 */
@Named(value="counter")
@ApplicationScoped
public class Counter implements Serializable {  
  
    private int count;
 
    public Counter() {

    }
    public int getCount() {
        return count;
    }
 
    public void setCount(int count) {
        this.count = count;
    }
 
    public synchronized void increment() {
        count++;
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push("/counter", String.valueOf(count));
    }

}  
