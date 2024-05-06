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
	
	// Article article = articleDto.toEntity(); 메소드
	public Article toEntity() {
		
		Article article = new Article( null, title, content );
		
		return article;
	}
	
}
