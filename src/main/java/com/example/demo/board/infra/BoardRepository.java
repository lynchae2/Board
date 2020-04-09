package com.example.demo.board.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.board.domain.Board;

//DAO 와 비슷한 인터페이스 //데이터에 접근하기 위함 
//쿼리가 없어도 됨 
//board에 access 하기 위한 인터페이스
//pk가 int 형이기 때문에  Integer로 하기

//JpaRepository가 내부적으로 @Configuration을 가지고 있음
public interface BoardRepository extends JpaRepository<Board, Integer>{
	//Optional<Board> findByDeletedAndId(boolean deleted, int id);//Board의 deleted와 Deleted 같게 !!!
}