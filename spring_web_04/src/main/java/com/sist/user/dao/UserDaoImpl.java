package com.sist.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sist.common.DTO;
import com.sist.common.DublicateIdException;
import com.sist.user.domain.Level;
import com.sist.user.domain.UserVO;
@Repository
public class UserDaoImpl implements UserDao {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String namespace
	  ="com.sist.user.repository.mappers.user";
	
	@Override
	public int do_save(DTO dto)throws DataAccessException {
		int flag = 0;
		try {
			String statement = namespace +".do_save";
			UserVO inUserVo = (UserVO)dto;
			flag = sqlSession.update(statement, inUserVo);
		}catch(DataAccessException e) {
			throw e;
		}	
		
		return flag;
	}

	@Override
	public List<?> do_search(DTO dto) {
		//String statement = namespace +".do_search";
		String statement = namespace +".do_search_dynamic";
		//Param
		UserVO param=(UserVO)dto;
		
		Hashtable<String, String> searchParam = null;//검색조건
		searchParam = param.getParam();
		
		int page_size  = 10;
		int page_num   = 1;
		
		if(searchParam.get("pageSize")!=null)//page_size: 10,50,100 
			page_size = Integer.parseInt(searchParam.get("pageSize").toString());
		
		if(searchParam.get("pageNo") !=null)//page_num:1,2,3,....
			page_num = Integer.parseInt(searchParam.get("pageNo").toString());
		
		searchParam.put("PAGE_SIZE", page_size+"");
		searchParam.put("PAGE_NUM", page_num+"");
		
		
		String searchWord  = searchParam.get("searchWord").toString();
		String searchDiv   = searchParam.get("searchDiv").toString();
		
		searchParam.put("SEARCH_DIV", searchDiv);
		searchParam.put("SEARCH_WORD", searchWord);
		return sqlSession.selectList(statement, searchParam);

	}

	@Override
	public int do_delete(DTO dto)throws DataAccessException {
		int flag = 0;

		String statement = namespace +".do_delete";
		UserVO inUserVo = (UserVO)dto;
		flag = sqlSession.delete(statement, inUserVo);

		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		String statement = namespace +".do_update";
		UserVO inUserVo = (UserVO)dto;
		return sqlSession.update(statement, inUserVo);
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
	/**
	 * mybatis sql연결
	 */
	@Override
	public DTO do_searchOne(DTO dto) {
		String statement = namespace +".do_searchOne";
		log.debug("***********************");
		log.debug("statement"+statement);
		log.debug("dto.toString() = "+dto.toString());
		log.debug("***********************");
		
		UserVO inUserVo = (UserVO)dto;
		return sqlSession.selectOne(statement, inUserVo);
	}
	

	
	
	
    private JdbcTemplate jdbcTemplate;
    
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
//	
//	/**
//	 * 단건조회
//	 * @param dto
//	 * @return 
//	 */
//	public DTO do_searchOne(DTO dto) {
//		StringBuilder sb=new StringBuilder();
//		sb.append("\n");
//		sb.append("SELECT ID,    \n");
//		sb.append("  NAME,       \n");
//		sb.append("  PASSWORD,   \n");
//		sb.append("  U_LEVEL,    \n");
//		sb.append("  LOGIN,      \n");
//		sb.append("  RECOMMEND,  \n");
//		sb.append("  MAIL,       \n");
//		sb.append("  REG_DT,     \n");
//		sb.append("  1 no,       \n");
//		sb.append("  1 total_cnt \n");
//		sb.append("FROM USERS 	 \n");
//		sb.append("where id = ?  \n");
//		log.debug(sb.toString());
//		
//		log.debug("3=======================");
//		log.debug(dto.toString());
//		log.debug("3=======================");
//		
//		UserVO inUserVo = (UserVO)dto;
//		String id = inUserVo.getId();
//		
//		UserVO user = this.jdbcTemplate.queryForObject(sb.toString(), 
//										new Object[] {id}, 
//										userMapper);
//		
//		return user;		
//	}
//	
//	
//	
	private RowMapper<UserVO> userMapper = new RowMapper<UserVO>() {
		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO user = new UserVO();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setUlevel(rs.getInt("u_level"));
			user.setLevelIntValue(rs.getInt("u_level"));
			user.setRegDt(rs.getString("reg_dt"));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));
			user.setMail(rs.getString("mail"));
			user.setNo(rs.getInt("no"));
			user.setTotalNo(rs.getInt("total_cnt"));
			return user;
		}
		
		
	};
	
//	@Override
//	public List<?> do_search(DTO dto) {
//		log.debug("3=======================");
//		log.debug(dto.toString());
//		log.debug("3=======================");
//		/**
//		 * 	-조회구분: searchDiv,
//		 *  -조회Data: searchWord
//		 *  -조회Page 사이즈: pageSize
//		 */
//		//Param
//		UserVO param=(UserVO)dto;
//		StringBuilder  	  sbSearch = new StringBuilder();//조건SQL
//		
//		Hashtable<String, String> searchParam = null;//검색조건
//		searchParam = param.getParam();
//		
//		int page_size  = 10;
//		int page_num   = 1;
//		
//		if(searchParam.get("pageSize")!=null)//page_size: 10,50,100 
//			page_size = Integer.parseInt(searchParam.get("pageSize").toString());
//		
//		if(searchParam.get("pageNo") !=null)//page_num:1,2,3,....
//			page_num = Integer.parseInt(searchParam.get("pageNo").toString());
//			
//		String searchWord   = searchParam.get("searchWord").toString();
//		
//		if(searchParam.get("searchDiv").equals("10")){//id
//			sbSearch.append("AND  id LIKE '"+searchWord+"' || '%' \n");
//		}else if(searchParam.get("searchDiv").equals("20")){
//			sbSearch.append("AND  name LIKE '"+searchWord+"' || '%' \n");
//		}else if(searchParam.get("searchDiv").equals("30")){
//			sbSearch.append("AND  u_Level LIKE '"+searchWord+"' || '%' \n");
//		}
//		
//		
//		StringBuilder sb=new StringBuilder();
//		sb.append("\n");
//		sb.append(" select T1.id                                                \n");
//		sb.append("       ,T1.name                                              \n");
//		sb.append("       ,T1.password                                          \n");
//		sb.append("       ,T1.u_Level                                           \n");
//		sb.append("       ,T1.login                                             \n");
//		sb.append("       ,T1.recommend                                         \n");
//		sb.append("       ,T1.mail                                              \n");
//		sb.append("       ,TO_CHAR(T1.reg_dt,'YYYY-MM-DD') AS reg_dt            \n");
//		sb.append("       ,T1.total_cnt                                         \n");
//		sb.append("       ,T1.RNUM as no                                        \n");
//		sb.append("   from(                                                     \n");
//		sb.append("   select A.*                                                \n");
//		sb.append("          ,ROW_NUMBER() OVER(ORDER BY A.reg_dt DESC) as RNUM \n");
//		sb.append("          ,COUNT(*) OVER () AS TOTAL_CNT                     \n");
//		sb.append("     from USERS A                                            \n");
//		sb.append("    where 1=1                                                \n");
//		//search condtion
//		sb.append(sbSearch.toString()); 
//		//--search condtion
//		sb.append("    order by reg_dt desc                                     \n");
//		sb.append("   )T1                                                       \n");
//		sb.append("WHERE RNUM BETWEEN (? * (?-1)+1) AND (? * (?-1)+?)           \n");
//
//		log.debug(sb.toString());
//		List<UserVO> list = 
//				this.jdbcTemplate.query(sb.toString()
//						,new Object[] {page_size
//								,page_num
//								,page_size
//								,page_num
//								,page_size} 
//				        ,userMapper);
//		log.debug("3-1=======================");
//		log.debug("list: "+list.size());
//		log.debug("3-1=======================");		
//		return list;	
//	}
//	
//	
//	@Override
//	public int do_save(DTO dto)throws DataAccessException {
//		log.debug("3=======================");
//		log.debug(dto.toString());
//		log.debug("3=======================");
//		int flag = -1;
//		try {
//			UserVO user = (UserVO)dto;
//			StringBuilder sb=new StringBuilder();
//			sb.append(" insert into users ");
//			sb.append("(id,name,password,u_level,login,RECOMMEND,mail,reg_dt) \n");
//			sb.append(" values (?,?,?,?,?,?,?,SYSDATE) \n");
//			log.debug(sb.toString());
//			
//			flag = this.jdbcTemplate.update(sb.toString(),
//										user.getId(),
//										user.getName(),
//										user.getPassword(),
//										user.getLevel().getValue(),
//										user.getLogin(),
//										user.getRecommend(),
//										user.getMail()
//										);
//		}catch(DataAccessException e) {
//			throw new DublicateIdException(e.getCause());
//		}
//		
//		return flag;
//	}
//
//
//	/**
//	 * 삭제
//	 */
//	@Override
//	public int do_delete(DTO dto) {
//		int flag = 0;
//		try {
//			UserVO user=(UserVO)dto;
//			log.debug("3=======================");
//			log.debug(dto.toString());
//			log.debug("3=======================");
//			
//			StringBuilder sb=new StringBuilder();
//			sb.append("\n");
//			sb.append("DELETE FROM USERS    \n");
//			sb.append(" WHERE ID        = ? \n");
//			log.debug(sb.toString());
//			
//			flag = this.jdbcTemplate.update(sb.toString(),
//										user.getId()
//										);
//		}catch(DataAccessException e) {
//			throw e;
//		}		
//		return flag;
//	}
//
//	@Override
//	public int do_update(DTO dto) {
//		int flag = 0;
//		try {
//			UserVO user=(UserVO)dto;
//			log.debug("3=======================");
//			log.debug(dto.toString());
//			log.debug("3=======================");
//			
//			
//			StringBuilder sb=new StringBuilder();
//			sb.append("\n");
//			sb.append("UPDATE USERS         \n");
//			sb.append("   SET NAME      = ? \n");
//			sb.append("      ,PASSWORD  = ? \n");
//			sb.append("      ,U_LEVEL   = ? \n");
//			sb.append("      ,LOGIN     = ? \n");
//			sb.append("      ,RECOMMEND = ? \n");  
//			sb.append("      ,mail      = ? \n");
//			sb.append("      ,reg_dt    = sysdate \n");
//			sb.append(" WHERE ID        = ? \n");
//			log.debug(sb.toString());
//			
//			flag = this.jdbcTemplate.update(sb.toString(),
//										user.getName(),
//										user.getPassword(),
//										user.getLevel().getValue(),
//										user.getLogin(),
//										user.getRecommend(),
//										user.getMail(),
//										user.getId()
//										);
//		}catch(DataAccessException e) {
//			throw new DublicateIdException(e.getCause());
//		}		
//		return flag;
//	}
//
//	@Override
//	public List<?> do_excelDown() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int do_excelUp(List<?> list) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
