package com.sist.user.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sist.common.DTO;
import com.sist.common.ExcelUtil;
import com.sist.common.StringUtil;
import com.sist.user.dao.UserDao;
import com.sist.user.domain.UserVO;
/**
 * UserSvcImpl.java
 * @author sist_
 *
 */
@Service
public class UserSvcImpl implements UserSvc {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	private UserDao userDao;
	
	private String path = "c:\\file\\excel\\";

	@Override
	public String do_excelDown(DTO dto) 
			throws IOException {
		String fileName = null;
		log.debug("0===================");
		log.debug("//do_excelDown:dto"+dto.toString());
		log.debug("0===================");
		
		List<UserVO> list =(List<UserVO>)userDao.do_search(dto);
		log.debug("1===================");
		log.debug("//list.size():"+list.size());
		log.debug("1===================");
		ExcelUtil excelUtil=new ExcelUtil();
		fileName = excelUtil.writeExcel(path, "users.xls", list);
		
		log.debug("2===================");
		log.debug("fileName="+fileName);
		log.debug("2===================");
		return path+fileName;
	}
	
	
	/**
	 * 다건삭제
	 * @param dto
	 * @return int (1:성공,1이 아니면 실패)
	 */
	@Transactional
	public int do_checkedDelete(List<String> list)throws DataAccessException {
		log.debug("2=======================");
		log.debug(list.toString());
		log.debug("2=======================");	
		int flag = 0;
		try {
			for(String id:list) {
				UserVO vo=new UserVO();
				vo.setId(id);
				int one = userDao.do_delete(vo);
				flag+=one;
			}
		}catch(DataAccessException e) {
			log.debug("2DataAccessException=======================");
			log.debug(e.toString());
			log.debug("2=======================");				
			throw e;
		}
		return flag;		
	}
	
	
	/**
	 * 삭제
	 * @param dto
	 * @return int (1:성공,1이 아니면 실패)
	 */
	public int do_delete(DTO dto) {
		log.debug("2=======================");
		log.debug(dto.toString());
		log.debug("2=======================");	
		return userDao.do_delete(dto);		
	}
	
	/**
	 * 수정
	 * @param dto
	 * @return int (1:성공,1이 아니면 실패)
	 */
	public int do_update(DTO dto) {
		log.debug("2=======================");
		log.debug(dto.toString());
		log.debug("2=======================");	
		return userDao.do_update(dto);		
	}
	

	/**
	 * 단건조회
	 * @param dto(id=?)
	 * @return UserVO
	 */
	public DTO do_searchOne(DTO dto) {
		log.debug("2=======================");
		log.debug(dto.toString());
		log.debug("2=======================");	
		return userDao.do_searchOne(dto);	
	}
	
	/**
	 * 전체조회
	 * @param dto
	 * @return  List<UserVO>
	 */
	public List<?> do_search(DTO dto){
		log.debug("2=======================");
		log.debug(dto.toString());
		log.debug("2=======================");	
		return userDao.do_search(dto);
		
	}
	
	@Override
	/**
	 * 사용자 등록 
	 */
	public int do_save(DTO dto) throws DataAccessException {
		log.debug("2=======================");
		log.debug(dto.toString());
		log.debug("2=======================");
		return userDao.do_save(dto);
	}


	@Override
	public int do_saveAll(List<DTO> list) throws DataAccessException {
		log.debug("2=======================");
		log.debug(list.toString());
		log.debug("2=======================");	
		int flag = 0;
		try {
			for(DTO vo: list) {
				UserVO userVO=(UserVO)vo;
				int one = userDao.do_delete(userVO);
				flag+=one;				
			}
			
		}catch(DataAccessException e) {
			log.debug("2DataAccessException=======================");
			log.debug(e.toString());
			log.debug("2=======================");				
			throw e;
		}
		return flag;	
	}

	/**
	 * excelUp
	 * @param dto
	 * @return String
	 * @throws Exception 
	 */
	@Override
	public String do_excelUpTx(HttpServletRequest req) 
			throws Exception {
		int flag = 0;
		String fileName   = "";
		List<UserVO> list = new ArrayList<UserVO>(); 
		
		String initPasswd = "1234";
		MultipartHttpServletRequest mhsr =
				(MultipartHttpServletRequest)req;
		
		Iterator<String> iter= mhsr.getFileNames();
		
		MultipartFile mfile = null;
		
		File dir =new File(path);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		while(iter.hasNext()) {
			fileName = iter.next();//File명 Read
			mfile    = mhsr.getFile(fileName);
			String orgName =new String(
				mfile.getOriginalFilename().getBytes("8859_1")
				,"UTF-8");
			if("".equals(orgName))continue;
			
			String ext = orgName.substring(
							orgName.lastIndexOf(".")
							);
			String saveFileNm = StringUtil.getUuid()+ext;
			log.debug("orgName:"+orgName);
			log.debug("saveFileNm:"+saveFileNm);
			
			File saveFile=
					new File(path+File.separator+saveFileNm);
			mfile.transferTo(saveFile);
			
			ExcelUtil excelUtil=new ExcelUtil();
			log.debug("saveFile:"+saveFile.getAbsolutePath());
			List<UserVO> excelList = excelUtil.parseTableExcel(saveFile.getAbsolutePath());
			
			log.debug("excelList:"+excelList.size());
			//--Data입력
			for(UserVO vo :excelList) {
				vo.setPassword(initPasswd);
				int one = this.userDao.do_save(vo);
				flag+=one;
			}
			log.debug("flag:"+flag);
		}
		
		
		
		return flag+"";
	}


	@Override
	public String doExcelDownload2(DTO dto) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String fileName = null;
		log.debug("======================================");
		log.debug("do excel_down: dto: "+dto.toString());
		log.debug("======================================");
		List<UserVO> list = (List<UserVO>) userDao.do_search(dto);
		log.debug("======================================");
		log.debug("list.size(): "+list.size());
		log.debug("======================================");
		
		ExcelUtil excelUtil = new ExcelUtil();
//		fileName = excelUtil.writeExcel(path, "users.xls", list);
		try {
			fileName = excelUtil.writeExcelEX(path, "users.xls", list,UserVO.class);
		} catch (IllegalAccessException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (SecurityException e) {
			throw e;
		}
		
		log.debug("======================================");
		log.debug("fileName: "+fileName);
		log.debug("======================================");
		
		return path+fileName;
	}



}
