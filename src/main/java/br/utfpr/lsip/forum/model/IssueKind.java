/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.model;

/**
 *
 * @author mauricio
 */
public enum IssueKind {
    TAXONOMY("rdfs:subClassOf"),
    RIGIDITY("oc:rigidity"), 
    IDENTITY("oc:identity"), 
    UNITY("oc:unity"), 
    DEPENDENCE("oc:dependence");//,
    //DOMAIN("rdfs:domain"),
    //RANGE("rdfs:rage");
    
    private IssueKind(final String value) {
        this.value = value;
    }
    
    private final String value;

    @Override
    public String toString() {
        return value;
    }
    
    
}
