package com.example.demo.board.ui;

import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.Brand;
import com.example.demo.board.infra.BoardRepository;
import com.example.demo.board.infra.BrandRepository;
import com.example.demo.board.infra.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

//view 를 주고받을 컨트롤러
@Controller
@RequestMapping(value = "/board")
public class BoardViewController {
	final private BoardRepository boardRepository;
	final private BoardService boardService;
	final private BrandRepository brandRepository;
	final private ReplyRepository replyRepository;

	@Autowired
	public BoardViewController(BoardRepository boardRepository, BoardService boardService, BrandRepository brandRepository, ReplyRepository replyRepository){
		this.boardRepository = boardRepository;
		this.boardService = boardService;
		this.brandRepository = brandRepository;
		this.replyRepository = replyRepository;
	}

	@GetMapping({"", "/{brand_id}"})//localhost:8080/board/1?id= 로 이동
	public String board(@RequestParam(value="id", defaultValue = "0")int id, @PathVariable int brand_id, Model model){
		model.addAttribute("board", boardService.findBoardById(id));
		model.addAttribute("brand",brandRepository.findById(brand_id).get());

		if(id == 0)
			return "/board/form-save";

		model.addAttribute("replyList", replyRepository.findAllByParentId(id));
		return "/board/form-update";
	}

	@GetMapping("/list/{brand_id}") //localhost:8080/board/list 로 이동
	public String list(Model model, @PageableDefault Pageable pageable, @PathVariable int brand_id){
		model.addAttribute("boardList", boardService.findBoardList(pageable));
		model.addAttribute("brand", brandRepository.findById(brand_id).orElse(new Brand()));
		return "/board/list";
	}

	@PostMapping("/{brand_id}")
	public String saveBoard(@PathVariable int brand_id, Board board,  Model model){
		model.addAttribute("brand", brandRepository.findById(brand_id).orElse(new Brand()));

		boardService.save(board);
		return "redirect:" + "/board/list/"+ brand_id;
	}

	@PostMapping("/{brand_id}/{id}")
	public String updateBoard(@PathVariable int brand_id, @PathVariable int id, Board board, Model model) {
		Brand brand = brandRepository.findById(brand_id).get();
		model.addAttribute("brand", brand);
		board.setBrand(brand);
		boardService.save(board);

		return "redirect:" + "/board/list/"+brand_id;
	}

	@DeleteMapping("/{brand_id}/{id}")
	public String deleteBoard(@PathVariable int brand_id, @PathVariable int id,  Model model) {
		Board board = boardService.findBoardById(id);
		Brand brand = brandRepository.findById(brand_id).get();

		model.addAttribute("brand", brand);
		model.addAttribute("board", board);

		int replySize = boardService.findReplyList(id).size();
		for (int i = 0; i < replySize; i++) {
			boardService.findReplyList(id).get(i).setDeleted(true);
		}

		board.setDeleted(true);
		boardService.save(board);

		return "redirect:" + "/board/list/" + brand_id;
	}
}


