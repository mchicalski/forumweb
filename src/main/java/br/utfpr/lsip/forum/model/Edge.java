/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author mauricio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Edge.findBySourceTarget",query = "SELECT e FROM Edge e WHERE e.source = :source AND e.target = :target")
})
public class Edge implements Serializable {
    @ManyToMany(mappedBy = "edges")
    private List<Idea> ideas;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Resource source;
    @ManyToOne
    private Resource target;
    private boolean warning;
    @Column(length=16000)
    private String text;
    private String metaproperties;
    


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
        if (!(object instanceof Edge)) {
            return false;
        }
        Edge other = (Edge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.utfpr.lsip.forum.model.Edge[ id=" + id + " ]";
    }


    /**
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * @return the source
     */
    public Resource getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(Resource source) {
        this.source = source;
    }

    /**
     * @return the target
     */
    public Resource getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Resource target) {
        this.target = target;
    }

    /**
     * @return the ideas
     */
    public List<Idea> getIdeas() {
        return ideas;
    }

    /**
     * @return the warning
     */
    public boolean isWarning() {
        return warning;
    }

    /**
     * @param warning the warning to set
     */
    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the metaproperties
     */
    public String getMetaproperties() {
        return metaproperties;
    }

    /**
     * @param metaproperties the metaproperties to set
     */
    public void setMetaproperties(String metaproperties) {
        this.metaproperties = metaproperties;
    }
    
}
