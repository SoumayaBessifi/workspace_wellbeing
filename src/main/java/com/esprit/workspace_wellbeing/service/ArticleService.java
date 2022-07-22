package com.esprit.workspace_wellbeing.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import com.esprit.workspace_wellbeing.entity.Activity;
import com.esprit.workspace_wellbeing.entity.Article;
import com.esprit.workspace_wellbeing.repository.ArticleRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;

@Component 
public class ArticleService {
	
	
	
	@Autowired
    private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Article addArticle(Article article,Long userWriterId) {
		article.setUserWriterId(userRepository.findById(userWriterId).get());
        return articleRepository.save(article);
    }

	public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id); 
    }
	
	public Article updateArticle(long article_Id, @Valid Article article) {
		Optional<Article> oldArticle = articleRepository.findById(article_Id);
		if (oldArticle.isPresent()) {
			
			Article newArticle = oldArticle.get();
			newArticle.setTitle(article.getTitle());
			newArticle.setDescription(article.getDescription());			
			
			return articleRepository.save(article);
		} else
			throw new ResourceNotFoundException("Article Id " + article_Id + " not found");
	}

}
