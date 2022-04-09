package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.repository.IBloggerRepository;

import org.mockito.BDDMockito;

import com.example.demo.bean.Community;
import com.example.demo.bean.UserEntity;
import com.example.demo.repository.ICommunityRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class BloggerServiceMockitoTest {
	
	// @InjectMocks - Creates instance of a class and injects mocks that are created with @Mock
	@InjectMocks
	BloggerServiceImpl blogSer;

	// @MockBean - Creates Mock of a class or interface
	@MockBean
	IBloggerRepository blogRepo;
	
	@MockBean
	ICommunityRepository comRepo;
	
//	@MockBean
//	IPostRepository postRepo;
//
//	@MockBean
//	ICommentRepository commentRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addBloggerTest() {
		// Creating blogger object
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setBloggerId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		// Community 
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		
		// Creating community using constructor
		Community com = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
	
		List<Community> communities = new ArrayList<>();
		communities.add(com);
		
		blogger.setCommunities(communities);
		
		// Creating and setting the user
		UserEntity user = new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
		blogger.setUser(user);
		
		// Sending post object when save function is called
		Mockito.when(blogRepo.save(blogger)).thenReturn(blogger);
		
		Blogger newBlog = blogSer.addBlogger(blogger);

		// checking if the added blogger values are equal to the newBlog or not
		assertEquals(1, newBlog.getBloggerId());
		assertEquals("Abc", newBlog.getBloggerName());
		assertEquals(20,newBlog.getKarma());
		assertEquals(1, newBlog.getCommunities().size());
		assertEquals(5, newBlog.getUser().getUserId());
	}

	@Test
	void viewBloggerTest(){
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setBloggerId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		// Community 
		File fw = new File("abc.jpg");
		
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		
		Community com = new Community(12,"Dogs",400,123,fw,LocalDate.parse("2019-02-07"),glist,galist,bp,f);
		
		List<Community> communities = new ArrayList<>();
		communities.add(com);
		
		blogger.setCommunities(communities);
		
		// Creating and setting the user
		UserEntity user = new UserEntity(5,"ram@gmail.com","ram@1234","Trader",false);
		blogger.setUser(user);
		
		// Sending post object when save function is called
		Mockito.when(blogRepo.findById(1)).thenReturn(Optional.of(blogger));
		
		BloggerOutputDto newBlog = blogSer.viewBlogger(1);

		// checking if the added blogger values are equal to the newBlog or not
		assertEquals(1, newBlog.getBloggerId());
		assertEquals("Abc", newBlog.getBloggerName());
		assertEquals(20,newBlog.getKarma());
	}
	
	@Test
	public void viewAllBloggersTest() {
		List<Blogger> bloggers = new ArrayList<>();
		bloggers.add(new Blogger());
		
		List<BloggerOutputDto> allBloggers = new ArrayList<>();
		allBloggers.add(new BloggerOutputDto());

		BDDMockito.given(blogRepo.findAll()).willReturn(bloggers);

		List<BloggerOutputDto> expected = blogSer.viewAllBloggers();

		assertEquals(expected.size(), allBloggers.size());
		verify(blogRepo).findAll();
	}

	@Test
	public void getBloggerByUserIdTest() {
		
		Blogger blogger = new Blogger();
		
		// Setting the values
		blogger.setBloggerId(1);
		blogger.setBloggerName("Abc");
		blogger.setKarma(20);
		
		Mockito.when(blogRepo.getBloggerByUserId(10)).thenReturn(blogger);
		
		BloggerOutputDto blog = blogSer.getBloggerByUserId(10);
		
		// checking if the blogger values are equal to the blog or not
		assertEquals(1, blog.getBloggerId());
		assertEquals("Abc", blog.getBloggerName());
		assertEquals(20, blog.getKarma());
	}
	
}
