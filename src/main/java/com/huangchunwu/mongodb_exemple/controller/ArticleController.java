package com.huangchunwu.mongodb_exemple.controller;


import com.huangchunwu.mongodb_exemple.core.page.PageRequestVo;
import com.huangchunwu.mongodb_exemple.core.page.PageResult;
import com.huangchunwu.mongodb_exemple.entity.Article;
import com.huangchunwu.mongodb_exemple.repository.IArticleRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleRepository articleRepository;

    @RequestMapping(value = "/add")
    @ResponseBody
    public String saveArtical() {
        Article article = new Article();
        article.setTitle("Spring-data-mongo-example");
        article.setAddTime(new Date());
        article.setAuthor("黄春伍");
        article.setTags(Arrays.asList("mongodb","spring"));
        article.setVisitCount(1l);
        articleRepository.save(article);
        return "YES";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteArtical() {
        Article article = new Article();
        article.setTitle("Spring-data-mongo-example");
        article.setAddTime(new Date());
        article.setAuthor("黄春伍");
        article.setTags(Arrays.asList("mongodb","spring"));
        article.setVisitCount(1l);
        articleRepository.delete(article);
        return "YES";
    }

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<Article> findAllArtical() {
        return articleRepository.findAll();
    }



    @RequestMapping(value = "/deleteAll")
    @ResponseBody
    public void deleteAllArtical() {
         articleRepository.deleteAll();
    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public UpdateResult updateArticle(Article article){
        return articleRepository.updateArticle(article);
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    public List<Article> queryById(String id){
        return articleRepository.findById(id);
    }

    @RequestMapping(value = "/queryPageList")
    @ResponseBody
    public PageResult<Article> queryPageList(@RequestBody  PageRequestVo pageRequest){
        return articleRepository.queryPageList(pageRequest);
    }
}
