package com.esprit.workspace_wellbeing.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esprit.workspace_wellbeing.entity.Article;

@RepositoryRestResource(path = "article", collectionResourceRel = "article")
public interface ArticleRepository extends JpaRepository<Article,Long> {	

}
