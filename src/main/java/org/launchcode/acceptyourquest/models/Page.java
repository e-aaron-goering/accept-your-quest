package org.launchcode.acceptyourquest.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Page {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=80)
    private String pageTitle;

    @Size(max=5000)
    private String pageText;

    @OneToMany
    @JoinColumn(name = "page_id")
    private List<Choice> choices = new ArrayList<>();

    @ManyToOne
    private Novel novel;

    public Page() {
    }

    public Page(String choiceDescription) {
        this.pageTitle = choiceDescription;
        this.pageText = "";
    }

    public Page(String pageTitle, String pageText) {
        this.pageTitle = pageTitle;
        this.pageText = pageText;
    }

    public void addChoice (Choice choice){
        this.choices.add(choice);
    }

    public void deleteChoice(Choice choice){
        this.choices.remove(choice);
        //delete the page created by the choice?
    }

    public int getId() {
        return id;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageText() {
        return pageText;
    }

    public void setPageText(String pageText) {
        this.pageText = pageText;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}
