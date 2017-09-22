package com.sist.code.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.code.domain.CodeVO;
import com.sist.common.DTO;
import com.sist.user.domain.UserVO;

@Repository
public class CodeDaoImpl implements CodeDao {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String namespace ="com.sist.code.repository.mappers.code";
	
	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<?> do_search(DTO dto) {
		String statement = namespace +".do_search";
		log.debug("***********************");
		log.debug("statement"+statement);
		log.debug("dto.toString() = "+dto.toString());
		log.debug("***********************");
		
		CodeVO codeVO = (CodeVO)dto;
		return sqlSession.selectList(statement, codeVO);
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> do_excelDown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_excelUp(List<?> list) {
		// TODO Auto-generated method stub
		return 0;
	}

}
