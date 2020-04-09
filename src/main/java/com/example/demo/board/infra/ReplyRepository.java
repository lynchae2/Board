package com.example.demo.board.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.board.domain.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
    List<Reply> findAllByParentId(int boardId);
}
