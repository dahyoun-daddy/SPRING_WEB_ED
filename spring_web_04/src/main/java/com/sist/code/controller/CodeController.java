package com.sist.code.controller;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sist.code.domain.CodeVO;
import com.sist.code.service.CodeSvc;
import com.sist.common.StringUtil;
import com.sist.user.domain.UserVO;
import com.sist.user.service.UserSvc;

@Controller
public class CodeController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	CodeSvc codeSvc;
	
	
//	@RequestMapping(value="code/do_search.do")
//	public ModelAndView do_search(HttpServletRequest req) {
//		CodeVO inVO=new CodeVO(); 	 
//		String mstCdId = StringUtil.nvl(req.getParameter("mst_cd_id"),"");
//		inVO.setMstCdId(mstCdId); 
//        ModelAndView modelAndView =new ModelAndView();
//   	    List<CodeVO> list = (List<CodeVO>)codeSvc.do_search(inVO);		
//   	    modelAndView.addObject("list",list );
//   	    modelAndView.setViewName("code/code List");
//		
//		return modelAndView;
//	}
}
