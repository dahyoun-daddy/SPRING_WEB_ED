package com.sist.user.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.sist.common.DTO;
/**
 * UserSvc.java
 * 
 * @author sist_
 *
 */
@Transactional
public interface UserSvc {
	
	
	/**
	 * 2017-09-18
	 * 엑셀 다운로드2
	 * @param dto
	 * @return int
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 */
	public String doExcelDownload2(DTO dto) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	/**
	 * excelUp
	 * @param dto
	 * @return String
	 */
	public String do_excelUpTx(HttpServletRequest req) 
			throws Exception ;
	
	
	/**
	 * excelDown
	 * @param dto
	 * @return  int
	 */
	public String do_excelDown(DTO dto) 
			throws IOException ;
	
	/**
	 * checked된것 삭제
	 * @param list
	 * @return int
	 */
	@Transactional
	public int do_checkedDelete(List<String> list);
	/**
	 * 삭제
	 * @param dto
	 * @return int (1:성공,1이 아니면 실패)
	 */
	public int do_delete(DTO dto);  
	
	/**
	 * 수정
	 * @param dto
	 * @return int (1:성공,1이 아니면 실패)
	 */
	public int do_update(DTO dto);
	
	/**
	 * 1이면 성공 그렇치 않으면 실패
	 * @param dto
	 * @return 1 
	 * @throws DataAccessException
	 */
	public int do_save(DTO dto)throws DataAccessException;
	
	/**
	 * 1이면 성공 그렇치 않으면 실패
	 * @param dto
	 * @return 1 
	 * @throws DataAccessException
	 */
	public int do_saveAll(List<DTO> list)throws DataAccessException;
	
	/**
	 * 전체조회
	 * @param dto
	 * @return  List<UserVO>
	 */
	public List<?> do_search(DTO dto) ;
	
	/**
	 * 단건조회
	 * @param dto(id=?)
	 * @return UserVO
	 */
	public DTO do_searchOne(DTO dto);
	
}
