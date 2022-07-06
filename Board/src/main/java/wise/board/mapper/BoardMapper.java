package wise.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import wise.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	// 게시글 작성
	public int insertBoard(BoardDTO params);
	
	public List<BoardDTO> selectBoardList();
	
	public int selectBoardTotalCount();
	
}
