package com.sist.blog.service;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.sist.common.DTO;


public interface BlogSvc {
	public List<?> do_naverBlogSearch(DTO search);
	public List<?> do_daumBlogSearch(DTO search)
				throws IOException, 
				       ParserConfigurationException, SAXException, JAXBException;
}
