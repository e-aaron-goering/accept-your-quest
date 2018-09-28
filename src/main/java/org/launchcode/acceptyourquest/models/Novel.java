package org.launchcode.acceptyourquest.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Novel {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=100)
    private String title;

    @Size(max=100)
    private String subtitle;

    @OneToMany
    @JoinColumn(name = "novel_id")
    private List<Page> pages = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "novel_id")
    private List<CustomOption> customOptions = new ArrayList<>();

    private boolean fnlnpn = false; // determines whether the author is using first name/last name/pronoun parameters

    private boolean published = false;

    public Novel() {
    }

    public Novel(@NotNull @Size(min = 3, max = 100) String title, @Size(max = 100) String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void addPage(Page newPage) {
        this.pages.add(newPage);
    }

    public Page getPage (int pageId) {
        return this.pages.get(pageId);
    }

    public List<CustomOption> getCustomOptions() {
        return customOptions;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isFnlnpn() {
        return fnlnpn;
    }

    public void setFnlnpn(boolean fnlnpn) {
        this.fnlnpn = fnlnpn;
    }
}
