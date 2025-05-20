package org.example;
public class LinkAndChild {
    private final String link;
    private final ListLinks child;
    public LinkAndChild(String link, ListLinks child) {
        this.link = link;
        this.child = child;
    }
    public String getLink() { return link; }
    public ListLinks getChild() { return child; }
}