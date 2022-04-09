package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BloggerDto;
import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;

public interface IBloggerService {
	
	public Blogger addBlogger(Blogger blogger);
	public BloggerDto updateBlogger(BloggerInputDto blogger);
	public void deleteBlogger(int bloggerId) ;
	public BloggerOutputDto viewBlogger(int bloggerId);
	public List<BloggerOutputDto> viewAllBloggers();
	//public BloggerOutputDto getBloggerByCommentId(int commentId);
	public List<BloggerOutputDto> viewBloggerListByCommunityId(int communityId);
	public BloggerDto addBloggerDto(BloggerInputDto blogger);
	//public BloggerOutputDto getBloggerByPostId(int postId) ;
	//public List<BloggerOutputDto> getBloggerByAwardId(int awardId);
	public BloggerOutputDto getBloggerByUserId(int userId);
}
