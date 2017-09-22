package com.sist.user.dao;

import com.sist.common.DTO;
import com.sist.common.WorkDiv;

public interface UserDao extends WorkDiv {
	/**
	 * 단건조회
	 * @param dto
	 * @return 
	 */
	public DTO do_searchOne(DTO dto);
}
