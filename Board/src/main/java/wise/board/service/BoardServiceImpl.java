package wise.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wise.board.domain.BoardDTO;
import wise.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		
		if(params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} 
		else {
			// 쿼리가 정상적으로 실행되면 1을 반환함.
			queryResult = boardMapper.updateBoard(params);
		} 
		//정상적으로 실행되면 true, 아니면 false;
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = Collections.emptyList();
		
		// 게시글 총 개수를 가져와 저장
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		
		// 게시글이 있을 경우에만, 게시글 목록을 가져오는 Mapper 실행
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}
		
		return boardList;
	}
	
	@Override
	public BoardDTO getBoardDetail(Long idx) {
		return boardMapper.selectBoardDetail(idx);
	}
	
	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		
		if(board != null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
		} 
			
		return (queryResult == 1) ? true : false;
	}
}
