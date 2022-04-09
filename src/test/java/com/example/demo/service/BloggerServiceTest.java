package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BloggerDto;
import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;

@SpringBootTest
class BloggerServiceTest {

	@Autowired
	IBloggerService bloggerSer;

	@Test
	@Disabled
	void addBloggerTest(){
		
		// Creating blogger object and setting values
		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setBloggerName("TestBlogger");
		
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(10);

		blogger.setCommunityIds(communityIds);
		
		List<Integer> awards = new ArrayList<>();
		awards.add(8);
		blogger.setAwardsIds(awards);
		
		blogger.setUserId(15);
		
		// Calling addBlogger function in bloggerservice
		BloggerDto newBlog = bloggerSer.addBloggerDto(blogger);
		
		// Comparing both the blogger values
		assertEquals("TestBlogger", newBlog.getBloggerName());
		assertEquals(1, newBlog.getCommunities().size());
		assertEquals(15, newBlog.getUser().getUserId());
	}

	@Test
	void updateBloggerTest(){
		
		BloggerInputDto blogger = new BloggerInputDto();
		blogger.setBloggerId(14);
		blogger.setBloggerName("Blogger_3");
		
		// Storing community ids in a list of integers
		List<Integer> communityIds = new ArrayList<>();
		communityIds.add(10);
		
		blogger.setCommunityIds(communityIds);
		
		List<Integer> awards = new ArrayList<>();
		awards.add(2);
		blogger.setAwardsIds(awards);
		
		blogger.setUserId(6);
		
		BloggerDto updatedBlog = bloggerSer.updateBlogger(blogger);
		
		assertEquals(14, updatedBlog.getBloggerId());
		assertEquals("Blogger_3", updatedBlog.getBloggerName());
		assertEquals(1, updatedBlog.getCommunities().size());
		assertEquals(6, updatedBlog.getUser().getUserId());

	}

	@Test
	void viewBloggerTest(){
		BloggerOutputDto blogger = bloggerSer.viewBlogger(13);
		assertEquals("Blogger2", blogger.getBloggerName());
		assertEquals(50, blogger.getKarma());

	}

	@Test
	void viewAllBloggersTest() {
		List<BloggerOutputDto> bloggers = bloggerSer.viewAllBloggers();
		int noOfBloggers = bloggers.size();
		assertEquals(3, noOfBloggers);
	}

	@Test
	void viewBloggerListByCommunityIdTest(){
		
		List<BloggerOutputDto> bloggers = bloggerSer.viewBloggerListByCommunityId(11);
		
		assertEquals(2, bloggers.size());
	}
	
//	@Test
//	void getBloggerByAwardIdTest(){
//
//		List<BloggerOutputDto> bloggers = bloggerSer.getBloggerByAwardId(2);
//
//		assertEquals(2, bloggers.size());
//
//	}
	
	@Test
	void getBloggerByUserIdTest() {
		BloggerOutputDto  blogger = bloggerSer.getBloggerByUserId(4);
		assertEquals(12, blogger.getBloggerId());
		assertEquals("Blogger1", blogger.getBloggerName());
		assertEquals(50, blogger.getKarma());
	}
}