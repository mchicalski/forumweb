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
    @NamedQuery(name = "Resource.findByTeamURI",query = "SELECT r FROM Resource r WHERE r.team = :team AND r.uri = :uri"),
    @NamedQuery(name = "Resource.countAllByTeam",query = "SELECT COUNT(r) FROM Resource r WHERE r.team = :team"),
    @NamedQuery(name = "Resource.findThingByTeam",query = "SELECT r FROM Resource r WHERE r.team = :team AND r.uri = 'owl:Thing'"),
    @NamedQuery(name = "Resource.findByNameTeam",query = "SELECT r FROM Resource r WHERE r.team = :team AND r.name = :name")
})
public class Resource implements Serializable {
    @OneToMany(mappedBy = "target")
    private List<Edge> edgesAsTarget;
    @OneToMany(mappedBy = "source")
    private List<Edge> edgesAsSource;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uri;
    private String name;
    @ManyToOne
    private Team team;
    @ManyToMany(mappedBy = "subClasses")
    private List<Resource> subClassOf;
    @ManyToMany
    private List<Resource> subClasses = new ArrayList<Resource>();
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Issue> issues = new ArrayList<Issue>();
    private String rigidity="";
    private String identityy="";
    private String dependence="";
    private String unity="";
    
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
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "br.utfpr.lsip.forum.model.Resource[ id=" + id + " ]";
        //return XMLUtils.getNCNameSuffix(uri.replace("<", "").replace(">", ""));
        return name;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the subClassOf
     */
    public List<Resource> getSubClassOf() {
        return subClassOf;
    }

    /**
     * @return the subClasses
     */
    public List<Resource> getSubClasses() {
        return subClasses;
    }

    /**
     * @return the edgesAsTarget
     */
    public List<Edge> getEdgesAsTarget() {
        return edgesAsTarget;
    }

    /**
     * @return the edgesAsSource
     */
    public List<Edge> getEdgesAsSource() {
        return edgesAsSource;
    }

    /**
     * @return the issues
     */
    public List<Issue> getIssues() {
        return issues;
    }

    /**
     * @return the rigidity
     */
    public String getRigidity() {
        return rigidity;
    }

    /**
     * @param rigidity the rigidity to set
     */
    public void setRigidity(String rigidity) {
        this.rigidity = rigidity;
    }

    /**
     * @return the identityy
     */
    public String getIdentityy() {
        return identityy;
    }

    /**
     * @param identityy the identityy to set
     */
    public void setIdentityy(String identityy) {
        this.identityy = identityy;
    }

    /**
     * @return the dependence
     */
    public String getDependence() {
        return dependence;
    }

    /**
     * @param dependence the dependence to set
     */
    public void setDependence(String dependence) {
        this.dependence = dependence;
    }

    /**
     * @return the unity
     */
    public String getUnity() {
        return unity;
    }

    /**
     * @param unity the unity to set
     */
    public void setUnity(String unity) {
        this.unity = unity;
    }


    
}
