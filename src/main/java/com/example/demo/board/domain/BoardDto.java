package com.example.demo.board.domain;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/// DTO : Data Transfer O..? 사용자에게 노출될 데이터만 넣는다. 
// Board 객체와 매핑을 해야함 
// 이 객체를 BoardDto 객체에 담아서 ? 매핑해서 ? 사용

public class BoardDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull 
	@NotBlank
	@Size(min = 5, max = 20)
	private String title;

	private List<Reply> reply;

	
	public int getReplyCount() {
		if(this.reply == null)
			return 0;
		return reply.size();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
	
	
}
