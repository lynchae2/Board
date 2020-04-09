package com.example.demo.board.ui;

import java.util.Optional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.example.demo.board.domain.Board;
import com.example.demo.board.infra.BoardRepository;

//rest API 를 설계할 것이다.
//json, xml...

@RestController
@RequestMapping("/api/board")
public class BoardRestController {

	// 멤버필드에다가 @autowired 하면 좋지 않음. 그래서 생성자에 해줌
	final private BoardRepository boardRepository;
	final private ModelMapper modelMapper;
	final private BoardService boardService;

	@Autowired
	public BoardRestController(BoardRepository boardRepository, ModelMapper modelMapper, BoardService boardService) {
		this.boardRepository = boardRepository;
		this.modelMapper = modelMapper;
		this.boardService = boardService;
	}

	// board 데이터 가져오기
	// 이름 바꾸기
//	@GetMapping("/board")
//	ResponseEntity board(@Param("id") int id) { // localhost:8080/board?id=2
//		Optional<Board> optionalBoard = boardRepository.findByDeletedAndId(false, id);
//
//		optionalBoard.orElseThrow(() -> new BadRequestException("데이터가 없습니다")); //객체가 null 이면 이벤트 실행
//
//		return new ResponseEntity<>(modelMapper.map(optionalBoard.get(), BoardDto.class), HttpStatus.OK);	//아니면 성공
//	}


	//board 리스트 가져오기
//	@GetMapping("/boards")	// @PageableDefault 내부적으로 파라미터를 page번호를 받음 /boards?page=1
//	ResponseEntity boards(@PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<Board> boards = boardRepository.findAll(pageable);
//		return new ResponseEntity<>(boards, HttpStatus.OK);
//	}

	//@PostMapping("/board")
	@PostMapping
	public ResponseEntity createBoard(@Valid @RequestBody Board board, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {	//에러를 가지고 있으면
			StringBuilder builder = new StringBuilder();

			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				builder.append("[");
				builder.append(fieldError.getField());
				builder.append("](은)는 ");
				builder.append(fieldError.getDefaultMessage());
				builder.append(" 입력된 값 : [");
				builder.append(fieldError.getRejectedValue());
				builder.append("]");
			}
			throw new BadRequestException(builder.toString());
		}
		return new ResponseEntity<>(boardRepository.save(board), HttpStatus.CREATED);
	}

	//@PutMapping("/board")
	//requestBody 로 수정한 값을 받아온다.
//	@PutMapping("/{id}")
//	public ResponseEntity updateBoard(@PathVariable("id") int id, @RequestBody Board board) {
//		Board persistBoard = boardService.findBoardById(id);
//
//		persistBoard.setTitle(board.getTitle());
//		persistBoard.setEmail(board.getEmail());
//		persistBoard.setContent(board.getContent());
//
//		System.out.println(persistBoard.toString());
//
//		boardRepository.save(persistBoard);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}


	// @RequestBody : json 정보 받기
	// @ResponseBody : json 정보 전달하기
	//@DeleteMapping("/board")
//	@DeleteMapping("/{id}")
//	public ResponseEntity deleteBoard(@PathVariable("id") int id) {
//		Optional<Board> optionalBoard = boardRepository.findById(id);
//
//		if (optionalBoard.isPresent()) {
//			boardRepository.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} else {
//			ErrorResponse errorResponse = new ErrorResponse("잘못된 요청", "데이터가 존재하지 않아 삭제할 수 없습니다.");
//			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
//		}
//	}


	//에러처리
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity badRequestExceptionHandler(BadRequestException e) {
		ErrorResponse errorResponse = new ErrorResponse("잘못된 요청", e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}