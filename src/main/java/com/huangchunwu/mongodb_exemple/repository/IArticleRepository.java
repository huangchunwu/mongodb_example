package com.huangchunwu.mongodb_exemple.repository;

import com.huangchunwu.mongodb_exemple.core.page.PageRequestVo;
import com.huangchunwu.mongodb_exemple.core.page.PageResult;
import com.huangchunwu.mongodb_exemple.entity.Article;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface IArticleRepository{

    Article save(Article article);

    UpdateResult updateArticle(Article article);

    void deleteAll();

    List<Article> findAll();

    void delete(Article article);

    List<Article> findById(String id);

    PageResult<Article> queryPageList(PageRequestVo pageRequest);
}
