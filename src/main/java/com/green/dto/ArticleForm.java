package com.green.dto;

import com.green.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForm {

	private Long id;	// 수정을 위해 추가
	private String title;
	private String content;
	
	// Method
	// 클래스 쓰는 방법 : 데이터 중심의 클래스(Vo etc), 기능 중심의 클래스(프로레스, 컨트롤러 etc)
	public Article toEntity() {		// Article 을 return 하는 toEntity
		
		return new Article(id, title, content);
		
	}
	
}
