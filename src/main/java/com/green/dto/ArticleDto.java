package com.green.dto;

import com.green.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

	// Field
	private String title;
	private String content;
	
	//------------------------------------------
	
	// ArticleController 中 @PostMapping("/articles/Write") 의 
	//   → Article article = articleDto.toEntity(); 메소드
	// ArticleDto 에 담겨있는 data(write.mustache 에서 input 속성 中
	//   → name=title & name=content 인 data) 를
	//   → Article.java (@Entity) 곧 h2 database 에 담는 함수
	// DB : 데이터 없으면 null 들어가야 함
	public Article toEntity() {
		
		Article article = new Article( null, title, content );
		
		return article;
	}
	
}
