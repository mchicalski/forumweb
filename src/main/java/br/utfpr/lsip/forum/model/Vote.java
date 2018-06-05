/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author mauricio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Vote.findByIdeaUser",query = "SELECT v FROM Vote v WHERE v.idea = :idea AND v.user = :user"),
    @NamedQuery(name = "Vote.countAllByIdea",query = "SELECT COUNT(v) FROM Vote v WHERE v.idea = :idea"),
    @NamedQuery(name = "Vote.findByIssueUser",query = "SELECT v FROM Vote v WHERE v.idea.issue = :issue AND v.user = :user"),
    @NamedQuery(name = "Vote.countByResourceUser",query = "SELECT COUNT(v) FROM Vote v WHERE v.idea.issue.subject = :resource AND v.user = :user"),
    @NamedQuery(name = "Vote.findWinnerByIssue",query = "SELECT v FROM Vote v WHERE v.idea = :idea AND COUNT(v) >= (SELECT COUNT(v) FROM Vote v WHERE v.idea = :idea)")
})
public class Vote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Idea idea;
    @ManyToOne
    private User user;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.utfpr.lsip.forum.model.Vote[ id=" + id + " ]";
    }

    /**
     * @return the idea
     */
    public Idea getIdea() {
        return idea;
    }

    /**
     * @param idea the idea to set
     */
    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
}
