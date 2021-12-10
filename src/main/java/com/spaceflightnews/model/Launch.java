package com.spaceflightnews.model;

import javax.persistence.*;

@Entity
public class Launch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    /**
     * getters and setters
     */

    public Integer getId() {
        return id;
    }

    public void Integer(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}