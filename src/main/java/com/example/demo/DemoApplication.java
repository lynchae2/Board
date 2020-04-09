package com.example.demo;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.Brand;
import com.example.demo.board.domain.Reply;
import com.example.demo.board.infra.BoardRepository;
import com.example.demo.board.infra.BrandRepository;
import com.example.demo.board.infra.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private BrandRepository brandRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("##########Spring Boot 초기화#############");

		Brand brand = new Brand("미니멀레시피", ".btn-create{background-color:#88b5bf;color:#fff;}\n" +
				".btn-update{background-color:#88b5bf;color:#fff;}\n" +
				"a{color:#0f9fb0;text-decoration:none;}\n" +
				".pagination>.active>a,.pagination>.active>a:focus,.pagination>.active>a:hover,.pagination>.active>span,.pagination>.active>span:focus,.pagination>.active>span:hover{\n" +
				"z-index:3;color:#fff;cursor:default;background-color:#0f9fb0;border-color:#0f9fb0;}\n" +
				".pagination>li>a,.pagination>li>span{position:relative;float:left;padding:6px 12px;margin-left:-1px;line-height:1.42857143;color:#0f9fb0;text-decoration:none;background-color:#fff;border:1px solid #ddd;\n" +
				"}");

		brand = brandRepository.save(brand);

		for(int i=0; i <50; i++){
			boardRepository.save(new Board("제목입니다" + i, "leebokeum@hanmail.net", "내용"+ i, brand));
		}



		for (int i=0;i<5;i++){
			Reply reply = new Reply(2,"댓글내용입니다아아앙ㅇㅇㅇㅇㅇㅇㅇㅇ"+i,"작성자"+i);
			reply = replyRepository.save(reply);

		}

	}
}
