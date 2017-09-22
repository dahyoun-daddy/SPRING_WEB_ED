package com.sist.member.dao;

import com.sist.common.DTO;
import com.sist.common.WorkDiv;
/**
 * 
 * @author sist_
 *
 */
public interface MemberDao extends WorkDiv {
	//단건조회
	public DTO do_searchOne(DTO dto);
}
