package com.sist.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.common.DTO;
import com.sist.member.dao.MemberDao;

@Service
public class MemberSvcImpl implements MemberSvc {
	
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	private MemberDao memberDao;
//	
//	@Override
	public DTO do_searchOne(DTO dto) {
//		log.debug("2 ================================");
//		log.debug(dto.toString());
//		log.debug("2 ================================");
//		return memberDao.do_searchOne(dto);
		return null;
	}

}
