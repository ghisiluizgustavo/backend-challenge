package com.spaceflightnews.scheduling;

import com.spaceflightnews.model.Article;
import com.spaceflightnews.model.Launch;
import com.spaceflightnews.model.dto.ArticleDTO;
import com.spaceflightnews.respository.ArticleRepository;
import com.spaceflightnews.respository.LaunchRespository;
import com.spaceflightnews.service.ArticleService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ScheduledCronSync {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private LaunchRespository launchRespository;

    private List<Article> articlesApi, articlesDb;
    private List<Launch> launches = new ArrayList<>();
    private Map<Integer, Article> articlesApiHmp, articlesDbHmp;
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledCronSync.class);

    @Scheduled(cron = "0 0 9 * * *")
    public void scheduledSync() {
        if(verificationInformations()){
            sychronizeDatas();
        }
    }

    public Boolean verificationInformations(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Article[]> response = restTemplate.getForEntity("https://api.spaceflightnewsapi.net/v3/articles/", Article[].class);
        this.articlesApi = Arrays.asList(response.getBody());
        this.articlesDb = repository.findAll();
        return articlesApi.size() > articlesDb.size();
    }

    private void sychronizeDatas() {
        verifyHaveLaunchs();
        LOGGER.info("starting sync between API and Database...");
        articlesApiHmp = articlesApi.stream()
                .collect(Collectors.toMap(Article::getId, Function.identity()));

        articlesDbHmp = articlesDb.stream()
                .collect(Collectors.toMap(Article::getId, Function.identity()));

        articlesDbHmp.forEach((integer, article) -> verificationHaveInMap(integer));

        repository.saveAll(articlesApiHmp.values());
        launchRespository.saveAll(launches);
        LOGGER.info("the following articles have been synced with the database: {} \n",
                articlesApiHmp.values().toString());
    }

    private void verifyHaveLaunchs() {
        for(Article article : articlesApi){
            if(article.getLaunches().size() > 0){
                launches.addAll(article.getLaunches());
                launches.forEach(launch -> launch.setArticle(article));
                article.setLaunches(new ArrayList<>());
            }
        }
        System.out.println(articlesApi.toString());
    }

    private void verificationHaveInMap(Integer integer) {
        if (articlesApiHmp.containsKey(integer)){
            articlesApiHmp.remove(integer);
        }
    }
}
