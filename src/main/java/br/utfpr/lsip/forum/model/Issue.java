/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author mauricio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Issue.findAllByResource",query = "SELECT i FROM Issue i WHERE i.subject = :resource")
})
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Resource subject;
    private IssueKind kind;
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private List<Idea> ideas = new ArrayList<Idea>();

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
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.utfpr.lsip.forum.model.Issue[ id=" + id + " ]";
    }

    /**
     * @return the subject
     */
    public Resource getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Resource subject) {
        this.subject = subject;
    }

    /**
     * @return the kind
     */
    public IssueKind getKind() {
        return kind;
    }

    /**
     * @param kind the kind to set
     */
    public void setKind(IssueKind kind) {
        this.kind = kind;
    }

    /**
     * @return the ideas
     */
    public List<Idea> getIdeas() {
        return ideas;
    }

    
}
