package com.sist.blog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sist.blog.domain.Item;
import com.sist.common.DTO;


@XmlRootElement
public class Channel extends DTO {
	//daum
	private List<Item> item=new ArrayList<Item>();

	private String totalCount;//총글수
	
	
	public String getTotalCount() {
		return totalCount;
	}

	@XmlElement
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public List<Item> getItem() {
		return item;
	}

    @XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}

    public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	//naver
    private List<Item> items=new ArrayList<Item>();    
}
