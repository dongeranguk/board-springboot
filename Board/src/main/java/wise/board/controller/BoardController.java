package wise.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wise.board.domain.BoardDTO;
import wise.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) {

		model.addAttribute("board", new BoardDTO());

		return "board/write";
	}

	@PostMapping(value = "/board/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if (isRegistered == false) {
				// TODO => 게시글 등록에 실패하였다는 메시지 전달

			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지 전달
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지 전달
		}

		return "redirect:/borad/list.do";
	}
	
	@GetMapping(value = "/board/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	@GetMapping(value = "/board/view.do")
	public String openBoardDetail(@RequestParam(value="idx", required=false) Long idx, Model model) {
		
		if(idx == null) {
			return "redirect/board/list.do";
		}
		
		BoardDTO board = boardService.getBoardDetail(idx);
		
		//없는 게시글이거나, 이미 삭제된 게시글일 경우
		if(board == null || "Y".equals(board.getDeleteYn())) {
			return "redirect/board/list.do";
		}
		
		model.addAttribute("board", board);
		return "board/view";
	}
}
