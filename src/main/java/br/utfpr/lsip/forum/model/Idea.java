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
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Idea.findByIdeaObjects",query = "SELECT i FROM Idea i WHERE i.issue = :issue AND i.objectProperties = :objects")
})
public class Idea implements Serializable {
    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL)
    private List<Argument> arguments = new ArrayList<Argument>();
    @OneToMany(mappedBy = "idea")
    private List<Vote> votes;
    @ManyToOne
    private Issue issue;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer importCount = 0;
    private String dataProperty;
    @OneToMany
    private List<Resource> objectProperties = new ArrayList<Resource>();
    @ManyToMany
    private List<Edge> edges = new ArrayList<Edge>();
    private boolean winning;

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
        if (!(object instanceof Idea)) {
            return false;
        }
        Idea other = (Idea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.utfpr.lsip.forum.model.Idea[ id=" + id + " ]";
    }

    /**
     * @return the votes
     */
    public List<Vote> getVotes() {
        return votes;
    }

    /**
     * @return the issue
     */
    public Issue getIssue() {
        return issue;
    }

    /**
     * @param issue the issue to set
     */
    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    /**
     * @return the importCount
     */
    public Integer getImportCount() {
        return importCount;
    }

    /**
     * @param importCount the importCount to set
     */
    public void setImportCount(Integer importCount) {
        this.importCount = importCount;
    }

    /**
     * @return the dataProperty
     */
    public String getDataProperty() {
        return dataProperty;
    }

    /**
     * @param dataProperty the dataProperty to set
     */
    public void setDataProperty(String dataProperty) {
        this.dataProperty = dataProperty;
    }

    /**
     * @return the objectProperties
     */
    public List<Resource> getObjectProperties() {
        return objectProperties;
    }

    /**
     * @return the arguments
     */
    public List<Argument> getArguments() {
        return arguments;
    }

    /**
     * @return the edges
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * @return the winning
     */
    public boolean isWinning() {
        return winning;
    }

    /**
     * @param winning the winning to set
     */
    public void setWinning(boolean winning) {
        this.winning = winning;
    }

}
