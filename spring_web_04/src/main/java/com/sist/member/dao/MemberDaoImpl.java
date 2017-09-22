package com.sist.member.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sist.common.DTO;
import com.sist.member.domain.MemberVO;

/**
 * MemberDaoImpl
 * Dao : DB Access Object
 * @author sist_
 *
 */
@Repository
public class MemberDaoImpl implements MemberDao {
	//log class
	private Logger log=LoggerFactory.getLogger(this.getClass()); 
	
	@Override
	public DTO do_searchOne(DTO dto) {
		log.debug("3========================");
		log.debug(dto.toString());
		log.debug("3========================");
		
        MemberVO vo=new MemberVO();
        vo.setId("sist");
        vo.setEmail("git@sist.com");
        vo.setPasswd("1234");
        vo.setName("이상무");
        
		return vo;
	}
	
	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DTO> do_search(DTO dto) {
		// TODO Auto-generated method stub
		return null;
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
