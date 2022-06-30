package wise.board.service;

import wise.board.domain.BoardDTO;

public interface BoardService {
	// 게시글 등록
	public boolean registerBoard(BoardDTO params);
}
