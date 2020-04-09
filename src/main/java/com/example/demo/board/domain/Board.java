package com.example.demo.board.domain;

import java.util.BitSet;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.example.demo.board.infra.BoardRepository;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;

@Entity	//entity 클래스 //db와 연결된 클래스 //반드시 pk가져야함 
@Table(name = "BOARD") //이 이름으로 테이블이 생성될것임
public class Board {

	//pk
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	//자동으로 증가하는 키 
	private int id;
	
	//@NotNull	//validation constraint
	//@NotBlank	//validation constraint
	@Size(min=5,max=20)	//값 사이즈 제한 
	private String title;

	private String password;

	//@NotNull
	//@NotBlank
	private String content;


	@Email
	@Column(name="email", unique=false)	//DB 에 들어가는 값 //unique 한 값이다.
	private String email;
	
	@OneToMany(fetch = FetchType.EAGER) // 1:N 관계
	@JoinColumn(name="parentId", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)	//Reply의  parentId와 조인
	@OrderBy("id DESC")
	@Where(clause="deleted=false")
	private List<Reply> reply;

	@ManyToOne
	private Brand brand;


	//참조
	//주소 데이터를 내장함 
	//@Embedded
	//private Address address;

	@Where(clause="deleted=0")
	private boolean deleted;

	public Board(String title, String email, String content, Brand brand) {
		this.title = title;
		this.email = email;
		this.content = content;
		this.brand = brand;
	}

	public Board(){
		super();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Board{" +
				"id=" + id +
				", title='" + title + '\'' +
				", password='" + password + '\'' +
				", content='" + content + '\'' +
				", email='" + email + '\'' +
				", reply=" + reply +
				", deleted=" + deleted +
				'}';
	}

}
