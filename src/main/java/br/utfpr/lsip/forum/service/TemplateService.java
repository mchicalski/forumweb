/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.lsip.forum.service;

import br.utfpr.lsip.forum.dao.IdeaFacade;
import br.utfpr.lsip.forum.dao.TemplateFacade;
import br.utfpr.lsip.forum.model.Idea;
import br.utfpr.lsip.forum.model.IssueKind;
import br.utfpr.lsip.forum.model.Resource;
import br.utfpr.lsip.forum.model.Template;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import sun.management.counter.Units;

/**
 *
 * @author mauricio
 */
@Singleton
public class TemplateService {



    @EJB
    private TemplateFacade templateFacade;
    @EJB
    private IdeaFacade ideaFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public String getTemplateFor(Idea idea) {
        List<Template> templates = templateFacade.findAllByType(idea.getDataProperty());
        idea = ideaFacade.find(idea.getId());
        String template = "nada";
        if (templates.size() > 0) {
            template = templates.get(0).getText();
            template = template.replace("<s>", idea.getIssue().getSubject().getName());
            String objects = "";
            for (Resource r : idea.getObjectProperties()) {
                objects += r.getName() + " ";
            }
            template = template.replace("<o>", objects);
        }
        return template;
    }

    public String justify(IssueKind issueKind, Resource source, Resource target) {
        String sourceValue = "";
        String targetValue = "";
        switch(issueKind) {
            case UNITY:
                sourceValue = source.getUnity();  
                targetValue = target.getUnity();  
                break;
            case RIGIDITY:
                sourceValue = source.getRigidity();  
                targetValue = target.getRigidity();  
                break;
            case DEPENDENCE:
                sourceValue = source.getDependence();    
                targetValue = target.getDependence();    
                break;
            case IDENTITY:
                sourceValue = source.getIdentityy();
                targetValue = target.getIdentityy();
                break;
        }
        List<Template> templates = templateFacade.findAllByType(targetValue);
        String template = "nada";
        if (templates.size() > 0) {
            template = templates.get(0).getText();
            template = template.replace("<s>", target.getName());
        }
        
        templates = templateFacade.findAllByType(sourceValue);
        if (templates.size() > 0) {
            template += ", MAS, " + templates.get(0).getText();
            template = template.replace("<s>", source.getName());
        }
        System.out.println("returning TEMPLATE = " + template);
        return template;    
    }
    
    public List<Template> findAll() {
        return templateFacade.findAll();
    }
    public void insert() {
        Template t;
        t = new Template();
        t.setText("Todo indivíduo que é <s>, é sempre <s> em todos os mundos possíveis.");
        t.setType("+R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo indivíduo que é <s>, quando existe é <s> em todos os mundos possíveis.");
        t.setType("+R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo individuo que é <s>, é obrigatoriamente <s> em todos os mundos possíveis.");
        t.setType("+R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo individuo que é <s>, não pode deixar de ser <s> em todos os mundos possíveis.");
        t.setType("+R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo indivíduo que é <s> não pode existir sem ser <s> em todos os mundos possíveis.");
        t.setType("+R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo indivíduo que é <s> deixa de existir se deixar de ser <s>, em todos os mundos possíveis.");
        t.setType("+R");
        templateFacade.create(t);

        t = new Template();
        t.setText("Pelo menos um indivíduo que é <s>, é sempre <s> em todos os mundos possíveis, enquanto os outros indivíduos que são <s>  podem deixar de o ser em algum mundo possível.");
        t.setType("-R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Pelo menos um indivíduo que é <s>, é obrigatoriamente <s>em todos os mundos possíveis, enquanto os outros indivíduos podem deixar de ser <s> dependendo do estado das coisas.");
        t.setType("-R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Há alguns indivíduos que são <s> e podem deixar de ser <s>  em algum mundo possível, enquanto outros indivíduos serão sempre <s> em todos os mundos possíveis.");
        t.setType("-R");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Todo indivíduo que é <s> pode não ser <s> em algum mundo possível, de acordo com alguma condição ou ação.");
        t.setType("~R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo indivíduo que é <s> pode deixar de ser <s> em algum mundo possível, embora ainda possa continuar a existir.");
        t.setType("~R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo indivíduo que é <s> não é obrigatoriamente <s> em todos os mundos possíveis.");
        t.setType("~R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo indivíduo que é <s> pode existir sem ser <s>, em algum mundo possível.");
        t.setType("~R");
        templateFacade.create(t);
        t = new Template();
        t.setText("Todo indivíduo que é <s> pode não ser <s> em algum mundo possível, dependendo do estado das coisas.");
        t.setType("~R");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Para um indivíduo do conceito <s> existir é necessário que tenha um relacionamento com indivíduos de outros conceitos, que não são partes, nem constituintes de <s>.");
        t.setType("+D");
        templateFacade.create(t);
        t = new Template();
        t.setText("A existência dos indivíduos do conceito <s> está condicionada ao relacionamento com indivíduos de outros conceitos, que não são partes, nem constituintes de <s>.");
        t.setType("+D");
        templateFacade.create(t);
        t = new Template();
        t.setText("A existência dos indivíduos de <s> está condicionada à existência de indivíduos de outros conceitos, que não são partes, nem constituintes de <s>.");
        t.setType("+D");
        templateFacade.create(t);
                t = new Template();
        t.setText("A existência dos indivíduos de <s> é dependente da existência de indivíduos de outros conceitos, que não são partes, nem constituintes de <s>.");
        t.setType("+D");
        templateFacade.create(t);
                t = new Template();
        t.setText("Para um indivíduo de <s> existir é necessário que existam indivíduos de outros conceitos a ele relacionados, que não são suas partes, nem seus constituintes.");
        t.setType("+D");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Para um indivíduo do conceito <s> existir NÃO é necessário que tenha um relacionamento com indivíduos de outros conceitos, que não são partes, nem constituintes de <s>.");
        t.setType("-D");
        templateFacade.create(t);
        t = new Template();
        t.setText("A existência dos indivíduos do conceito <s> NÃO está condicionada ao relacionamento com indivíduos de outros conceitos, que não são partes, nem constituintes de <s>.");
        t.setType("-D");
        templateFacade.create(t);
        t = new Template();
        t.setText("A existência dos indivíduos de <s> NÃO está condicionada à existência de indivíduos de outros conceitos, que não são partes, nem constituintes de <s>.");
        t.setType("-D");
        templateFacade.create(t);
                t = new Template();
        t.setText("A existência dos indivíduos de <s> NÃO é dependente da existência de indivíduos de outros conceitos, que não são partes, nem constituintes de <s>.");
        t.setType("-D");
        templateFacade.create(t);
                t = new Template();
        t.setText("Para um indivíduo de <s> existir NÃO é necessário que existam indivíduos de outros conceitos a ele relacionados, que não são suas partes, nem seus constituintes.");
        t.setType("-D");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Os indivíduos de <s> compartilham de um critério de identificação que cujos valores permitem diferenciar um indivíduo dos demais, sendo que este critério È HERDADO de seus superconceitos.");
        t.setType("+I");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Os indivíduos de <s> representam VALORES DE UM ATRIBUTO de outros conceitos, OU os indivíduos de <s> possuem DIFERENTES critérios de identificação, que não são comuns a todos(as) <s>.");
        t.setType("-I");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Os indivíduos de <s> compartilham de um critério de identificação cujos valores permitem diferenciar um indivíduo dos demais, sendo que este critério é PRÓPRIO de <s>, ou seja, NÃO É HERDADO de seus superconceitos.");
        t.setType("*I");
        templateFacade.create(t);

        
        t = new Template();
        t.setText("Os indivíduos de <s> SÃO elementos inteiros (sob um critério COMUM a todos os indivíduos), ou seja, é possível definir quais são suas partes componentes, o quê faz e o quê não faz parte de cada indivíduo e sob quais condições cada indivíduo é considerado completo.");
        t.setType("+U");
        templateFacade.create(t);
        t = new Template();
        t.setText("Os indivíduos de <s> SÃO delimitáveis (sob um critério COMUM a todos os indivíduos), porque possuem limites exatos que permitem separar um indivíduo dentre os demais, dessa forma podem ser enumerados ou contados como unidades individuais.");
        t.setType("+U");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Os indivíduos de <s> NÃO são delimitáveis, pois NÃO existem critérios exatos para determinar quais são os limites e partes de cada indivíduo de <s>, por se tratar de algo disperso e espalhado, que NÃO possui um formato padrão ou uma estrutura pré-definida.");
        t.setType("~U");
        templateFacade.create(t);
        t = new Template();
        t.setText("Os indivíduos de <s> NÃO são elementos inteiros ou completos em si mesmos, pois NÃO é possível determinar exatamente quais são suas partes componentes: o quê faz parte e o quê não faz parte de cada indivíduo de <s>.");
        t.setType("~U");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Os indivíduos de <s> são delimitáveis, podendo ser enumerados ou contados como unidades individuais, porém existem DIFERENTES critérios para definir os limites exatos que permitem separar um indivíduo dos demais.");
        t.setType("-U");
        templateFacade.create(t);
        t = new Template();
        t.setText("Os indivíduos de <s> são elementos inteiros, completos, porém existem DIFERENTES critérios para definir quais são as partes componentes, o quê faz e o quê não faz parte de cada indivíduo.");
        t.setType("-U");
        templateFacade.create(t);
        
        t = new Template();
        t.setText("Todo(a) <s> é um tipo de <o>.");
        t.setType("Tx");
        templateFacade.create(t);
        t = new Template();
        t.setText("Para ser <s> é necessário ser <o>.");
        t.setType("Tx");
        templateFacade.create(t);
                t = new Template();
        t.setText("Todo(a) <s> é também <o>.");
        t.setType("Tx");
        templateFacade.create(t);
        t = new Template();
        t.setText("<s> herda todas as características de <o>, ou seja, todo(a) <s> é também <o>.");
        t.setType("Tx");
        templateFacade.create(t);
                t = new Template();
        t.setText("<s>  é um(a) <o>.");
        t.setType("Tx");
        templateFacade.create(t);
    }

}
