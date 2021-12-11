package com.spaceflightnews.controller;

import com.spaceflightnews.model.Article;
import com.spaceflightnews.model.dto.ArticleDTO;
import com.spaceflightnews.respository.ArticleRepository;
import com.spaceflightnews.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping()
    public ResponseEntity<Page<ArticleDTO>> getAllArticles(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<ArticleDTO> articleDTOPage = articleService.findAll(pageable);
        return ResponseEntity.ok().body(articleDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getOneArticle(@PathVariable Integer id){
        return articleService.findOneArticle(id);
    }
}
