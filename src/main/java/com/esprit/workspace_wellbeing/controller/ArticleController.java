package com.esprit.workspace_wellbeing.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.workspace_wellbeing.entity.Activity;
import com.esprit.workspace_wellbeing.entity.Article;
import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.repository.ArticleRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;
import com.esprit.workspace_wellbeing.service.ArticleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/application")
public class ArticleController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleService articleService;
	
	
	@PostMapping("/articles/{user_Id}")
    public Article addArticle(@Valid @RequestBody Article article,@PathVariable long user_Id){
       return articleService.addArticle(article, user_Id);
    }

	@GetMapping("/articles")
	public ResponseEntity<List<Article>> GetAllArticle() {
		List<Article> listArticle = null;
		listArticle = articleRepository.findAll();
		if (listArticle.isEmpty())
			return ResponseEntity.noContent().build();

		return new ResponseEntity<>(listArticle, HttpStatus.OK);
	}

	@GetMapping("/articles/{article_Id}")
	public ResponseEntity<Optional<Article>> GetArticleById(@PathVariable long article_Id) {
		Optional<Article> article;
		article = articleService.getArticleById(article_Id);

		if (!article.isPresent())
			return ResponseEntity.noContent().build();
		return new ResponseEntity<>(article, HttpStatus.OK);
	}

	@GetMapping("/user/articles/{user_Id}")
	public ResponseEntity<List<Article>> GetAllArticleByUserId(@PathVariable long user_Id) {
		List<Article> listArticle = null;
		listArticle = articleRepository.findAll().stream()
				.filter(a -> a.getUserWriterId().getId().equals(user_Id)).collect(Collectors.toList());;
		if (listArticle.isEmpty())
			return ResponseEntity.noContent().build();

		return new ResponseEntity<>(listArticle, HttpStatus.OK);
	}
	
	@PutMapping("/articles/{article_Id}")
    public Article updateArticle(@PathVariable long article_Id, @Valid @RequestBody Article article) {
	 		return articleService.updateArticle(article_Id, article);
    }
	
	@DeleteMapping("/articles/{article_Id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable long article_Id) {
		articleRepository.deleteById(article_Id);
        return ResponseEntity.accepted().build();
    }

}
