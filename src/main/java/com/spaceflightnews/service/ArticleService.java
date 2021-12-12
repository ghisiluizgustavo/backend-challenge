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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    private ModelMapper modelMapper = new ModelMapper();

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
        ArticleDTO articleDTO = modelMapper.map(article.get(), ArticleDTO.class);
        return ResponseEntity.ok().body(articleDTO);
    }

    public ResponseEntity<ArticleDTO> postNewArticle(ArticleDTO articleDTO, UriComponentsBuilder uriBuilder) {
        if(articleDTO == null){
            return ResponseEntity.badRequest().build();
        }
        Article article = modelMapper.map(articleDTO, Article.class);
        Article articleResponse = articleRepository.save(article);
        ArticleDTO articleDTOResponse = modelMapper.map(articleResponse, ArticleDTO.class);
        URI uri = uriBuilder.path("/articles/{id}").buildAndExpand(articleDTOResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(articleDTOResponse);
    }

    public ResponseEntity<ArticleDTO> updateArticle(ArticleDTO articleDTO, Integer id) {
        if(!articleDTO.getId().equals(id)){
            return ResponseEntity.badRequest().build();
        }

        Optional<Article> article = articleRepository.findById(id);
        if(article.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Article articleToSave = modelMapper.map(articleDTO, Article.class);
        Article articleSaved = articleRepository.save(articleToSave);
        ArticleDTO articleSavedDTO = modelMapper.map(articleSaved, ArticleDTO.class);
        return ResponseEntity.ok().body(articleSavedDTO);
    }

    public ResponseEntity<ArticleDTO> deleteArticle(Integer id) {
        Optional<Article> article = articleRepository.findById(id);
        if(article.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        articleRepository.delete(article.get());
        return ResponseEntity.ok().build();
    }
}
