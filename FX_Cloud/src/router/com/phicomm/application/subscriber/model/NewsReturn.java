package com.phicomm.application.subscriber.model;

import javax.annotation.Resource;

import com.phicomm.application.subscriber.service.INewsService;


public class NewsReturn {
	private INewsService newsService;
	
	
	public INewsService getnewsService() {
		return newsService;
	}


	@Resource
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	 
	public int retState;
	public int ifSuport;
	

	
	public String Setit(java.util.List<News> l){
		String temp="";
		String temp1="";
		for(News news : l){
			if(news.getRouterVer().equals(""))
				temp1 = "sale";
			else
				temp1 = "router";
			
			if(l.lastIndexOf(news)+1 == l.size())
				temp += "{\"newsTyp\":\""+temp1+"\",\"newsDetail\":\""+news.getNewsTheme()+"\",\"newsDetail2\":\""+news.getNewsContext()+"\"},";
			else
				temp += "{\"newsTyp\":\""+temp1+"\",\"newsDetail\":\""+news.getNewsTheme()+"\",\"newsDetail2\":\""+news.getNewsContext()+"\"},"+"\n";
		}
		return temp;
	}
	
	public String Return(String temp) {
		return "{\"deviceList\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"newsList\":"+"\n"
				+"["+"\n"
				+temp+"\n"
				+"]"+"\n"
				+"}}";
			
	}



	public int getRetState() {
		return retState;
	}



	public void setRetState(int retState) {
		this.retState = retState;
	}



	public int getIfSuport() {
		return ifSuport;
	}



	public void setIfSuport(int ifSuport) {
		this.ifSuport = ifSuport;
	}




	
	
	
}
