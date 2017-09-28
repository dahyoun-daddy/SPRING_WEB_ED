package com.sist.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.sist.blog.domain.Channel;
import com.sist.blog.domain.Item;
import com.sist.blog.service.BlogSvc;
import com.sist.common.DTO;
import com.sist.common.StringUtil;
import com.sist.user.domain.UserVO;

@Controller
public class BlogController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private BlogSvc blogSvc;
	
	@RequestMapping(value="blog/blogList.do",method=RequestMethod.GET)
	public ModelAndView do_daum_blog(HttpServletRequest req) 
			throws IOException, ParserConfigurationException, SAXException, JAXBException {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("blog/blogList");
		
		return modelAndView;
	}
	
	@RequestMapping(value="blog/blogList.do",method=RequestMethod.POST)
	public ModelAndView do_daum_blog_exec(HttpServletRequest req) 
			throws IOException, ParserConfigurationException, SAXException, JAXBException {
		ModelAndView modelAndView=new ModelAndView();
		DTO dto=new DTO();
		String search_div = "";
		List<Item> daumList = new ArrayList<Item>();
		//request 이름 read
		Enumeration<String> params = req.getParameterNames();
		Hashtable<String, String> 
		sParam = new Hashtable<String, String>();	
		while(params.hasMoreElements()){
		  String name = (String)params.nextElement();
		  req.getParameter(name);
		  sParam.put(name, StringUtil.nvl(req.getParameter(name),""));
		}  
        log.debug("sParam:"+sParam.toString());
        dto.setParam(sParam);
        search_div = StringUtil.nvl(sParam.get("search_div"),"10");
        if("10".equals(search_div)) {//daum blog검색
        	daumList = (List<Item>)blogSvc.do_daumBlogSearch(dto);
        }else if("20".equals(search_div)) {//naver blog검색
        	daumList = (List<Item>)blogSvc.do_naverBlogSearch(dto); 
        }
        
   	    String totalCnt   = "0";
   	    if(null!=daumList && daumList.size()>0)totalCnt = daumList.get(0).getTotalCount();
   	    
   	    //총글수
   		modelAndView.addObject("totalCnt",StringUtil.nvl(totalCnt,"0"));
    	modelAndView.addObject("daumList", daumList);
		modelAndView.setViewName("blog/blogList");
		return modelAndView;
	}	
}
