package com.huangchunwu.mongodb_exemple.repository;

import com.huangchunwu.mongodb_exemple.core.constants.FieldName;
import com.huangchunwu.mongodb_exemple.core.page.PageRequestVo;
import com.huangchunwu.mongodb_exemple.core.page.PageResult;
import com.huangchunwu.mongodb_exemple.entity.Article;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.LongSupplier;


@Component
public class ArticleRepository implements IArticleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Article save(Article article) {
        return mongoTemplate.save(article);
    }

    @Override
    public UpdateResult updateArticle(Article article) {
        Query query = Query.query(Criteria.where(FieldName.TITLE).is(article.getTitle()));
        Update update = Update.update(FieldName.AUTHOR, article.getAuthor());
        return mongoTemplate.updateMulti(query,
                update,
                Article.class);
    }


    @Override
    public void deleteAll() {
        mongoTemplate.dropCollection(Article.class);
    }

    @Override
    public List<Article> findAll() {
        return mongoTemplate.findAll(Article.class);
    }

    @Override
    public void delete(Article article) {

    }

    @Override
    public List<Article> findById(String id) {
        Query query = Query.query(Criteria.where(FieldName.ID).is(id));
        return mongoTemplate.find(query,Article.class);
    }

    @Override
    public PageResult<Article> queryPageList(PageRequestVo pageRequest) {
        Query query = Query.query(Criteria.where(FieldName.AUTHOR).is("jay"));
        Pageable pageable =  PageRequest.of(pageRequest.getPageNo(), pageRequest.getPageSize());
        //分页
        query.with(pageable);
        // 排序
        query.with(Sort.by( Sort.Direction.DESC,FieldName.ADDTIME));

        List<Article> items = mongoTemplate.find(query, Article.class);

        Page<Article> page = PageableExecutionUtils.getPage(items,pageable, new LongSupplier() {
            @Override
            public long getAsLong() {
                return mongoTemplate.count(query,Article.class);
            }
        });
        return PageResult.newBuilder().data(page.getContent()).pageNo(page.getNumber()).pageTotalSize(page.getTotalPages()).build();
    }

    @Override
    public List<Article> findByTag(String tag) {
        Query query = Query.query(Criteria.where(FieldName.TAGS).in(tag));
        return mongoTemplate.find(query,Article.class);
    }


}
