package com.sist.user.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sist.code.domain.CodeVO;
import com.sist.code.service.CodeSvc;
import com.sist.common.DTO;
import com.sist.common.StringUtil;
import com.sist.user.domain.Level;
import com.sist.user.domain.UserVO;
import com.sist.user.service.UserSvc;

@Controller
public class UserController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	UserSvc userSvc;
	
	@Autowired 
	CodeSvc codeSvc;
	
    @Resource(name="downloadView")
    private View downloadView;
    
    /**
     * JUnit Controller Test
     * @param req
     * @return
     */
    @RequestMapping(value="test/hello.do",
    		method=RequestMethod.GET)
    public ModelAndView jamesGreeting(HttpServletRequest req) {
    	ModelAndView modelAndView=new ModelAndView();
    	String greeting = StringUtil.nvl(req.getParameter("name"),"없음");
    	
    	modelAndView.addObject("greeting",greeting);
    	modelAndView.setViewName("test/hello");
    	return modelAndView;
    }
    
    @RequestMapping(value="test/hello.do",
    		method=RequestMethod.POST)
    public ModelAndView jamesGreetingPost(HttpServletRequest req) {
    	ModelAndView modelAndView=new ModelAndView();
    	String greeting = StringUtil.nvl(req.getParameter("name"),"없음");
    	
    	modelAndView.addObject("greeting",greeting);
    	modelAndView.setViewName("test/helloResult");
    	return modelAndView;
    }
    
	/**
	 * ExcelUpload
	 * @return
	 */
    @RequestMapping(value="user/do_excelUpload.do",
    		method=RequestMethod.GET)
	public String do_excelUpload() {
		log.debug("===================");
		log.debug("//do_excelUpload===");
		log.debug("===================");
		
		return "user/excelUp";
	}
	
	/**
	 * ExcelUpload
	 * @return
	 * @throws IOException 
	 */
    @RequestMapping(value="user/do_excelUpload.do",
    		method=RequestMethod.POST,produces="application/json;charset=utf8")
	@ResponseBody
	public ModelAndView do_excelUploadExec(HttpServletRequest req)
			throws Exception {
		log.debug("===================");
		log.debug("//do_excelUploadExec===");
		log.debug("===================");
		ModelAndView mav= new ModelAndView();
		String result = userSvc.do_excelUpTx(req);
		mav.addObject("result",result);
	    mav.setViewName("jsonView");
	    
		log.debug("===================");
		log.debug("//result=="+result);
		log.debug("===================");
	    return mav; 
	}
    
    
	/**
	 * Excel_Download
	 * 조회조건을 DB에 전달 
	 * @param req
	 * @return
	 * @throws IOException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value="user/do_excelDown.do"
			,method=RequestMethod.POST)	
	public ModelAndView do_excelDown(HttpServletRequest req)
			throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		UserVO inVO=new UserVO(); 	 
		Hashtable<String, String> 
		searchParam = new Hashtable<String, String>();//검색조건
		String p_pageSize = StringUtil.nvl(req.getParameter("page_size"),"10");
		String p_pageNo  = StringUtil.nvl(req.getParameter("page_num"),"1");
		String p_searchDiv = StringUtil.nvl(req.getParameter("searchDiv"),"");
		String p_searchWord = StringUtil.nvl(req.getParameter("searchWord"),"");
		
		searchParam.put("pageSize", p_pageSize);
		searchParam.put("pageNo", p_pageNo);
		searchParam.put("searchDiv", p_searchDiv);
		searchParam.put("searchWord", p_searchWord);
		
		inVO.setParam(searchParam);
		
		//String fileFullPath = this.userSvc.do_excelDown(inVO);
		String fileFullPath = this.userSvc.doExcelDownload2(inVO);//일반화 ExcelDownload
		ModelAndView modelAndView=new ModelAndView();
		log.debug("=========================");
		log.debug("fileFullPath: "+fileFullPath);
		log.debug("=========================");
		modelAndView.setView(this.downloadView); 
		File  downloadFile=new File(fileFullPath);
		modelAndView.addObject("downloadFile", downloadFile);
		
		return modelAndView;
	}
	
	/**
	 * 수정
	 * @param req
	 * @return
	 */
	public ModelAndView do_update(DTO dto) {
		UserVO inVO=new UserVO();
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("user/userList");//List
		
		return modelAndView;
	}
	
	
	
	
	
	
	@RequestMapping(value="user/userForm.do"
			,method=RequestMethod.POST
			,produces="application/json;charset=utf8")
	@ResponseBody
	public String doSelectOne(HttpServletRequest req) {
		log.debug("0================");
		log.debug("0================");
		UserVO inVO=new UserVO();
		String id = req.getParameter("id");
		
		if(id!=null && id.length()>0) {
			inVO.setId(id);
		}
		
		UserVO outVO=(UserVO)userSvc.do_searchOne(inVO);
		
		Gson gson=new Gson();
		String retString = gson.toJson(outVO);
		log.debug("0===============retString="+retString);
		return retString;
	}
	
	@RequestMapping(value="user/userForm.do"
			,method=RequestMethod.GET)
	public ModelAndView do_add(HttpServletRequest req) {
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("user/userForm");
		return modelAndView;
	}
	
	/**
	 * UI에 
	 *  if(workdDiv==null) do_save()
	 *  else do_update()
	 * 
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="user/do_save.do")
	public String do_save(HttpServletRequest req)
			throws IOException {
		
		UserVO inVO=new UserVO();
		
		inVO.setId(req.getParameter("id"));
		
		int ulevel = Integer.parseInt(req.getParameter("u_level").toString());
		//inVO.setLevelIntValue(u_level);
		inVO.setUlevel(ulevel);
		
		int login = Integer.parseInt(req.getParameter("login").toString());
		inVO.setLogin(login);
		
		int recommend = Integer.parseInt(req.getParameter("recommend").toString());
		inVO.setRecommend(recommend);
		
		inVO.setMail(req.getParameter("mail"));
		inVO.setName(req.getParameter("name"));
		inVO.setPassword(req.getParameter("password"));
		
		String workDiv = req.getParameter("workDiv");
		int flag = 0;
		//workDiv==null?do_save/do_update
		if(workDiv == null || workDiv.trim().equals("") ) {
			flag = userSvc.do_save(inVO);
		}else {
			flag = userSvc.do_update(inVO);
		}
		
		log.debug("flag: "+flag);
		
		return "redirect:do_search.do";
	}
	@RequestMapping(value="user/do_search.do")
	public ModelAndView do_search(HttpServletRequest req) {
        //코드조회 
		CodeVO codeVO=new CodeVO(); 	 
		String mstCdId = "C001";
		codeVO.setMstCdId(mstCdId);
		List<CodeVO> codeList= (List<CodeVO>)codeSvc.do_search(codeVO);
		//-- 코드조회 
		
		
		UserVO inVO=new UserVO(); 	 
		Hashtable<String, String> 
		searchParam = new Hashtable<String, String>();//검색조건
		String p_pageSize = StringUtil.nvl(req.getParameter("page_size"),"10");
		String p_pageNo  = StringUtil.nvl(req.getParameter("page_num"),"1");
		String p_searchDiv = StringUtil.nvl(req.getParameter("searchDiv"),"");
		String p_searchWord = StringUtil.nvl(req.getParameter("searchWord"),"");
		
		searchParam.put("pageSize", p_pageSize);
		searchParam.put("pageNo", p_pageNo);
		searchParam.put("searchDiv", p_searchDiv);
		searchParam.put("searchWord", p_searchWord);

		
//		//request 이름 read
//		Enumeration<String> params = req.getParameterNames();
//		Hashtable<String, String> 
//		sParam = new Hashtable<String, String>();	
//		while(params.hasMoreElements()){
//		  String name = (String)params.nextElement();
//		  req.getParameter(name);
//		  sParam.put(name, StringUtil.nvl(req.getParameter(name),""));
//		}
//        log.debug("sParam:"+sParam.toString());
//		
		inVO.setParam(searchParam);
		
		
   	    List<UserVO> list = (List<UserVO>)userSvc.do_search(inVO);
   	    int totalCnt   = 0;
   	    if(list !=null && list.size()>0)totalCnt = list.get(0).getTotalNo();
   	    
		//TO_DO: pageing처리 할것
		ModelAndView modelAndView =new ModelAndView();
		//코드
		
		modelAndView.addObject("codeList",codeList);
		log.debug("codeList :"+codeList.toString());
		modelAndView.addObject("list",list );
		
		//총글수
		modelAndView.addObject("totalCnt",totalCnt);
		modelAndView.setViewName("user/userList");
		
		return modelAndView;
	}

//	/**
//	 * checked 삭제
//	 * @param req
//	 * @param res
//	 * @return int
//	 * @throws IOException
//	 */
//	@RequestMapping(value="user/do_checkedDelete.do",produces="application/json;charset=utf8")
//	@ResponseBody
//	public String do_checkedDelete(HttpServletRequest req) throws IOException {
//		//in
//		String   ret = req.getParameter("idList");//JSON
//		log.debug("ret :"+ret);
//		
//		Gson gson=new Gson();
//		List<String> idList = gson.fromJson(ret, List.class);
//		log.debug("1.idList size:"+idList.size());

//		
//		int flag = userSvc.do_checkedDelete(idList);
//		log.debug("삭제건수:"+flag);
//		//--in
//		
//		return flag+"";
//	}	
	
	/**
	 * checked 삭제
	 * @param req
	 * @param res
	 * @return int
	 * @throws IOException
	 */
	@RequestMapping(value="user/do_checkedDelete.do",produces="application/json;charset=utf8")
	@ResponseBody
	public String do_checkedDelete(HttpServletRequest req) throws IOException {
		//in
		String   ret = req.getParameter("idList");//JSON
		log.debug("ret :"+ret);
		
		Gson gson=new Gson();
		List<String> idList = gson.fromJson(ret, List.class);
		log.debug("1.idList size:"+idList.size());
		log.debug("1.1 idList :"+idList.toString());
		String firstId = "";
		for(int i=0;i<idList.size();i++) {
			firstId = idList.get(i);
		}
		idList.add(firstId);
		log.debug("2.idList size:"+idList.size());
		
		log.debug("2.1 idList :"+idList.toString());
		
		int flag = userSvc.do_checkedDelete(idList);
		log.debug("삭제건수:"+flag);
		//--in
		
		return flag+"";
	}	
	/**
	 * 삭제
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="user/do_delete.do")
	public String do_delete(HttpServletRequest req) throws IOException {
		UserVO inVO=new UserVO();
		
		inVO.setId(req.getParameter("id"));
		log.debug("inVO : "+inVO.getId()); 
		int flag = userSvc.do_delete(inVO);
		log.debug("flag : "+flag);
		return "redirect:do_search.do";
	}
	
}
