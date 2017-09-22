package com.sist.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.member.domain.MemberVO;
import com.sist.member.service.MemberSvc;

@Controller
public class MemberController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	MemberSvc memberSvc;
	
	@RequestMapping(value="member/member.do")
	public String member() {
		log.debug("0=========================");
		log.debug("0=========================");
		return "member/member";
	}
	
	
	//doSelectOne.do
	@RequestMapping(value="member/doSelectOne.do"
			,method=RequestMethod.POST)
	public ModelAndView doSelectOne(HttpServletRequest request) {
		MemberVO inVO=new MemberVO();
		inVO.setEmail(request.getParameter("email"));
		inVO.setId(request.getParameter("id"));
		inVO.setName(request.getParameter("name"));
		inVO.setPasswd(request.getParameter("passwd"));
		
		log.debug("1=========================");
		log.debug(inVO.toString());
		log.debug("1=========================");
		
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("member/memberForm");
		
		log.debug("=========================");
		log.debug(""+memberSvc.do_searchOne(inVO));
		log.debug("=========================");
		
		modelAndView.addObject("memberVO",memberSvc.do_searchOne(inVO));
		
		return modelAndView;
	}
	
	
}
