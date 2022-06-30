package wise.board.service;

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
		
		// 쿼리가 정상적으로 실행되면 1을 반환함.
		queryResult = boardMapper.insertBoard(params);
		
		//정상적으로 실행되면 true, 아니면 false;
		return (queryResult == 1) ? true : false;
	}
}
