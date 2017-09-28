package com.sist.blog.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.sist.blog.domain.Channel;
import com.sist.blog.domain.Item;
import com.sist.common.ComConst;
import com.sist.common.DTO;
import com.sist.common.StringUtil;


@Service
public class BlogSvcImpl implements BlogSvc {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Override
	public List<?> do_naverBlogSearch(DTO search) {
		List<Item> list= new ArrayList<Item>();
        try {
    		Hashtable<String,String> inParam= search.getParam();
    		
    		
    		String requestUrl  = ComConst.NAVER_BLOG_URL; 
    		String search_word = URLEncoder.encode(inParam.get("search_word"), "UTF-8");
    		String page_size   = inParam.get("page_size");
    		
            String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ search_word; // json 결과
            apiURL += "&display=" + page_size; //출력될 결과수
            
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", ComConst.NAVER_CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", ComConst.NAVER_CLIENT_SECRET);
            
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
               
               Gson gson=new Gson();   
               BufferedReader br =
                       new BufferedReader(new InputStreamReader(con.getInputStream()));
               String inputLine;
               StringBuffer response = new StringBuffer();
               while ((inputLine = br.readLine()) != null) {
            	   
                   response.append(inputLine);
               }
               br.close();
         
               Channel channel=gson.fromJson(response.toString(),Channel.class);
               
    	       list=channel.getItems();
    	       
    	       
    	       for(Item item:list){
    	    	   log.debug("Title: "+item.getTitle() 
    	        	+":: Link: "+item.getLink()
    	        	+":: Description: "+item.getDescription()
    	        			);
    	       }
    	       
            }else{
            	log.debug("====================================");
            	log.debug("error "+responseCode);
            	log.debug("====================================");  
            }
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
        log.debug("====================================");
        log.debug("list: "+list.size() );
        log.debug("====================================");
        
        return list;
	}

	@Override
	public List<?> do_daumBlogSearch(DTO search)  
			throws IOException, ParserConfigurationException, SAXException, JAXBException{ 
		List<Item> list= new ArrayList<Item>();
		
		Hashtable<String,String> inParam= search.getParam();
		
		
		String requestUrl  = ComConst.DAUM_BLOG_URL; 
		String search_word = URLEncoder.encode(inParam.get("search_word"), "UTF-8");
		String page_size   = inParam.get("page_size");
		
		requestUrl += "?apikey=" + ComConst.DAUM_API_KEY; //발급된 키
        requestUrl += "&q="      + search_word; //검색어
        requestUrl += "&result=" + page_size; //출력될 결과수
        requestUrl += "&pageno=" + "1"; //페이지 번호
        requestUrl += "&output=" + "xml";
        
        log.debug("====================================");
        log.debug("requestUrl: "+requestUrl);
        log.debug("====================================");
        URL url = new URL(requestUrl);
        
      //API 요청 및 반환
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        
        int responseCode = con.getResponseCode();
        log.debug("responseCode: "+responseCode);
        
        if(responseCode==200) { // 정상 호출
           JAXBContext  jc= JAXBContext.newInstance(Channel.class);//RootElement
           Unmarshaller us= jc.createUnmarshaller();;
           Channel channel= (Channel)us.unmarshal(con.getInputStream());
	       list=channel.getItem();
	       log.debug("====================================");
	       log.debug("list: "+list.size());
	       log.debug("====================================");
	       if(list !=null && list.size()>0)list.get(0).setTotalCount(channel.getTotalCount());
    	       
        }else {
        	log.debug("error "+responseCode);
        }
        
		return list;
	}

}
