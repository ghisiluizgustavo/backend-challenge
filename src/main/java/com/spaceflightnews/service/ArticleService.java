package com.spaceflightnews.service;

import com.spaceflightnews.model.Article;
import com.spaceflightnews.model.dto.ArticleDTO;
import com.spaceflightnews.respository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    private ModelMapper modelMapper;

    public Page<ArticleDTO> findAll(Pageable pageable){
        modelMapper = new ModelMapper();
        Page<Article> articles = articleRepository.findAll(pageable);
        Page<ArticleDTO> articlesPageDto = articles.map(
                article -> modelMapper.map(article, ArticleDTO.class));
        return articlesPageDto;
    }

    public ResponseEntity<ArticleDTO> findOneArticle(Integer id) {
        modelMapper = new ModelMapper();
        Optional<Article> article = articleRepository.findById(id);
        if(article.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(modelMapper.map(article.get(), ArticleDTO.class));
    }
}
