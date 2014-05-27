package com.example.testhttpclient;

public class BreakingNews {
	
	
	private String id;
	private String  news;
	private String news_type;
	private String entry_date;
	
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	
	public String getNews(){
		return this.news;
	}
	
	public void setNews(String news){
		this.news = news;
	}
	
	
	public String getNews_type(){
		return this.news_type;
	}
	
	public void setNews_Type(String newstype){
		this.news_type = newstype;
	}
	
	
	public String getEntry_Date(){
		return this.entry_date;
	}
	
	public void setEntry_Date(String entry_date){
		this.entry_date = entry_date;
	}

}
