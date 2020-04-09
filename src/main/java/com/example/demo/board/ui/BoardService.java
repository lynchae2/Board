package com.example.demo.board.ui;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.Reply;
import com.example.demo.board.infra.BoardRepository;
import com.example.demo.board.infra.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, ReplyRepository replyRepository) {
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
    }

    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }

    public Board findBoardById(int id) {
        return boardRepository.findById(id).orElse(new Board());
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public List<Reply> findReplyList(int boardId){
        return replyRepository.findAllByParentId(boardId);
    }

}