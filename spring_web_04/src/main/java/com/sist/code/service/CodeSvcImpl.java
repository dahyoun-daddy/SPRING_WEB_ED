package com.sist.code.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.code.dao.CodeDao;
import com.sist.common.DTO;
import com.sist.user.dao.UserDao;

@Service
public class CodeSvcImpl implements CodeSvc {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private CodeDao codeDao;
	
	/**
	 * 전체조회
	 * @param dto
	 * @return  List<UserVO>
	 */
	public List<?> do_search(DTO dto){
		log.debug("2=======================");
		log.debug(dto.toString());
		log.debug("2=======================");	
		return codeDao.do_search(dto);
		
	}	
}
