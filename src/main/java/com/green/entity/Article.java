package com.green.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// 실제 database 의 table 구조를 만든다
// import : jakarta 로 → import jakarta.persistence.Entity;
// persistence : 영속성 → 영구적으로 보관, pk(primary key) 를 영구적으로 만들어라
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

	// primary key : @Id
	// 값을 자동으로 채워라 : @GeneratedValue
	// @GeneratedValue() 에 ctrl+space : stategy = + 선택가능
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String content;

	
	public Article() {}
	public Article(Long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
