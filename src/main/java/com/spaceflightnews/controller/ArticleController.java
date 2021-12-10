package com.spaceflightnews.controller;

import com.spaceflightnews.model.Article;
import com.spaceflightnews.respository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/all")
    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }
}
