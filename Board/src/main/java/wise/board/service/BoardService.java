package wise.board.service;

import java.util.List;

import wise.board.domain.BoardDTO;

public interface BoardService {
	// 게시글 등록
	public boolean registerBoard(BoardDTO params);
	
	// 게시글 목록 (+ 게시글 총 개수)
	public List<BoardDTO> getBoardList();
	
	//게시글 상세보기
	public BoardDTO getBoardDetail(Long idx);
	
}
