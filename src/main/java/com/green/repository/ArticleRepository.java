package com.green.repository;

import org.springframework.data.repository.CrudRepository;

import com.green.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
