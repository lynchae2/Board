package com.example.demo.board.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
@Table(name = "REPLY") //이 이름으로 테이블이 생성될것임
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


	private int parentId;
	
	@Column(length = 255)
	@NotBlank
	@NotNull
	private String content;

	private String writer;

	private boolean deleted;

	public Reply(){

	}

	public Reply(int parentId, String content, String writer){
		this.parentId = parentId;
		this.content = content;
		this.writer = writer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Reply{" +
				"id=" + id +
				", parentId=" + parentId +
				", content='" + content + '\'' +
				", writer='" + writer + '\'' +
				", deleted=" + deleted +
				'}';
	}
}
