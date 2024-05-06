package com.green.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.dto.ArticleDto;
import com.green.entity.Article;
import com.green.repository.ArticleRepository;

@Controller
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/articles/WriteForm")
	public String writeForm() {
		
		return "articles/write";
		
	}
	
	//===================================================================================================
	
	// @GetMapping("articles/Write")
	// 405 error : method="post" 인데, 받는놈이 @GetMapping 으로 받아서 나는 에러
	// Post 로 던지는 놈은 @PostMapping 으로 받자
	// .mustache 에서 보내는 name="title" , name="content" 를 Controller 에 보냄(form tag, submit)
	// Controller 에서는 변수 두개를 써서 받지 않고 ArticleDto 를 생성해서 ArticleDto 하나로만 받음
	@PostMapping("/articles/Write")
	//public String write( @RequestParam(value="title") String title, @RequestParam(value="content") String content ) {
	public String write( ArticleDto articleDto ) {
		
		// 넘어온 data 확인
		  //System.out.println( "title: " + title + ", " + "content: " + content );
		  // Console → title: bbb, content: bbb
		System.out.println( "articleDto: " + articleDto );
		  // Console → articleDto: ArticleDto(title=aaa, content=aaa) → 값 전달받고 있음
		
		//---------------------------------------------------------------------------------------
		
		// 1. http://localhost:9090/articles/WriteForm 에서 입력한 것이 ArticleDto 에 저장
		// 1-2. ArticleDto : private String title, private String content + Getter/Setter + toString
		// 2. Controller 에서 ArticleDto 를 받음 / pubilc String write( ArticleDto articleDto )
		// 3. sysout 로 Console 확인해보니 http://localhost:9090/articles/WriteForm 에서 입력한 것이 ArticleDto 에 저장
		
		//---------------------------------------------------------------------------------------		

		// db 에 저장 = h2 console 테이블에 저장
		// 1. Dto -> Entity 로 보냄
		Article article = articleDto.toEntity();
		
		// 2. Entity -> Repository 로 보내서 저장
		  // Entity : db 의 테이블이다 → mapper.xml 과 같은 역할
		  // 위에서 입력받은 data (article : title, content) 를 ArticleRepository 에 저장(.save)
		Article saved = articleRepository.save( article );
		
		return "redirect:/articles/List";
	}
	
	//===================================================================================================
	
		// 목록 조회
		@GetMapping("/articles/List")
		public String list( Model model ) {
			
			// List<Article> articleEntityList = articleRepository.findAll();  <-  오류 발생
			// 1. 오류처리 1번 방법
			//List<Article> articleEntityList = (List<Article>) articleRepository.findAll();	// F2 -> add cast
			// 복습1) List<MenuVo> menuList = new ArrayList<>();
			// 자료체크) if( menuList.size() == 0 )  ->  자료가 없으면
					
			// 복습2) MenuVo menuVo = null;
			//              menuVo = new MenuVo();  ->  new : null 이 없음
			// 자료체크) if( menuVo == null )  ->  조회한 자료가 없으면
					
			// .findById : 한개만 조회할땐 null 있을 수 있음
			// .findAll : 그 안에 이미 new ArrayList<>() 과정이 있음 -> null 이 없음 -> .orElse() 필요없음
					
			// 2. 오류처리 2번 방법
			// ArticleRepository interface 에 함수를 등록
			List<Article> articleEntityList = articleRepository.findAll();
			System.out.println("전체목록: " + articleEntityList);
					
			model.addAttribute("articleList", articleEntityList);
			
			return "articles/List";
			
		}
	
	//===================================================================================================
	
	// data 조회 : 1번 data
	// PathVariable -> Get 방식
	// 1번 방법Error : java.lang.IllegalArgumentException: Name for argument of type [java.lang.Long] not specified
		// 해결 1 : @PathVariable Long id  ->  @PathVariable(value="id") Long id
		// 해결 2 : STS 방식 : 설정 추가 = Ensure that the compiler uses the '-parameters' flag.
		//   프로젝트 -> properties -> Java Compiler -> 
		//   ✅ Enable project specific settings 체크
		//   ✅ 아래 Store information ~ 체크
		// 참고) Article 에 기본 생성자 생성 안되어 있으면 에러, @NoArgsConstructor 추가 
	@GetMapping("/articles/{id}")
	public String view( @PathVariable(value="id") Long id, Model model ) {
		
		// 1번 방법
		//Article articleEntity = articleRepository.findById(id);
		  // Error : Type mismatch -> F2 : Optional<Article> 로 전환
		  // 해석 : 값이 있으면 Article 을 리턴, 값이 없으면 null 리턴
		//Optional<Article> articleEntity = articleRepository.findById(id);
		  // Article Type 으로 articleEntity 변수 생성
		//System.out.println( "articleEntity: " + articleEntity );
		
		//model.addAttribute("article", articleEntity);
		
		// 2번 방법
		Article articleEntity = articleRepository.findById(id).orElse(null);
		System.out.println( "articleEntity: " + articleEntity );
		
		model.addAttribute("article", articleEntity);		
		
		return "articles/view";
		
	}
	
	//===================================================================================================
	
	// 데이터 수정 페이지로 이동
	@GetMapping("/articles/{id}/EditForm")
	public String editForm( @PathVariable(value="id") Long id ) {
		// Mapping 주소에 {id} 가 있으니 editForm() 안에 넘겨받을 값 있어야 함 -> @PathVariable(value="id") Long id
		
		return "articles/edit";
		
	}
	
	//===================================================================================================

	// 데이터 수정
	@GetMapping("/articles/{id}/Edit")
	public String edit() {
		
		return "redirect:/articles/List";
		
	}

	
	
}














