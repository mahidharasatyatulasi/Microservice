package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloggerDto;
import com.example.demo.dto.BloggerInputDto;
import com.example.demo.dto.BloggerOutputDto;
import com.example.demo.bean.Blogger;
import com.example.demo.bean.Community;
import com.example.demo.bean.UserEntity;
import com.example.demo.exception.BloggerIdNotFoundException;
import com.example.demo.repository.IBloggerRepository;
import com.example.demo.repository.ICommunityRepository;
import com.example.demo.repository.IUserRepository;

@Service
public class BloggerServiceImpl implements IBloggerService {

	@Autowired
	IBloggerRepository blogRepo;
	
	@Autowired
	ICommunityRepository commRepo;
	
//	@Autowired
//	IAwardRepository awardRepo;
	
	@Autowired
	IUserRepository userRepo;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		return blogRepo.save(blogger);
	}

	@Override
	public BloggerDto addBloggerDto(BloggerInputDto bloggerInputDto) {
		
		// Creating blogger object
		Blogger blog = new Blogger();
		
		//Setting blogger variables by bloggerInputDto values
		blog.setBloggerName(bloggerInputDto.getBloggerName());
			
		// List to store communities
		List<Community> communities = new ArrayList<>();
		
		// Getting community IDs
		List<Integer> communityIds = bloggerInputDto.getCommunityIds();
		
		for(Integer id : communityIds) {
			
			// Getting community by ID
			Optional<Community> opt = commRepo.findById(id);
			// Adding community to list
			communities.add(opt.get());
		}
		// Setting the communities to blog
		blog.setCommunities(communities);
		
		
		// Getting the user by ID
		Optional<UserEntity> user = userRepo.findById(bloggerInputDto.getUserId());
		
		
		// Setting the user to blogger
		blog.setUser(user.get());
		
		// Saving the blogger in database
		Blogger newBlogger = blogRepo.save(blog);	
		
		BloggerDto bloggerDto = new BloggerDto();
		bloggerDto.setBloggerId(newBlogger.getBloggerId());
		bloggerDto.setBloggerName(newBlogger.getBloggerName());
		bloggerDto.setKarma(50);
		
		List<Community> newCommunities = new ArrayList<>();
		
		for(Community community : newBlogger.getCommunities()) {
			
			//Creating communityOutputDto object
			Community com = new Community();
			
			//Set values to community object
			com.setCommunityId(community.getCommunityId());
			com.setCommunityDescription(community.getCommunityDescription());
			com.setTotalMembers(community.getTotalMembers());
			com.setOnlineMembers(community.getOnlineMembers());
			com.setImage(community.getImage());
			com.setCreatedOn(community.getCreatedOn());
			com.setPostRulesAllowed(community.getPostRulesAllowed());
			com.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
			com.setBanningPolicy(community.getBanningPolicy());
			com.setFlairs(community.getFlairs());
			
			// Adding com to communities
			newCommunities.add(com);
		}
		
		UserEntity blogUser = newBlogger.getUser();
		// Creating a userOutputDto
		UserEntity userOutput = new UserEntity();
		userOutput.setUserId(blogUser.getUserId());
		userOutput.setEmail(blogUser.getEmail());
		userOutput.setLoginStatus(blogUser.isLoginStatus());
		userOutput.setRole(blogUser.getRole());
		
		bloggerDto.setCommunities(newCommunities);
		
		bloggerDto.setUser(userOutput);

		return bloggerDto;
		
	}

	@Override
	public BloggerDto updateBlogger(BloggerInputDto blogger) {
		
		Optional<Blogger> opt1 = blogRepo.findById(blogger.getBloggerId());
		if (!opt1.isPresent()) {
			throw new BloggerIdNotFoundException("Blogger not found with the given id:" + blogger.getUserId());
		}
		Blogger updateBlogger = opt1.get();
		
		// Setting values to updateBlogger
		updateBlogger.setBloggerName(blogger.getBloggerName());
		

		// List to store communities
		List<Community> communities = new ArrayList<>();
		
		List<Integer> communityIds = blogger.getCommunityIds();
		
		for(Integer id : communityIds) {
			Optional<Community> opt = commRepo.findById(id);
			
			communities.add(opt.get());
		}
		updateBlogger.setCommunities(communities);
		
		
		// Getting the user by ID
		Optional<UserEntity> user = userRepo.findById(blogger.getUserId());
		
		
		// Setting the user to blogger
		updateBlogger.setUser(user.get());
		
		Blogger newBlogger = blogRepo.save(updateBlogger);
		
		BloggerDto bloggerDto = new BloggerDto();
		bloggerDto.setBloggerId(newBlogger.getBloggerId());
		bloggerDto.setBloggerName(newBlogger.getBloggerName());
		bloggerDto.setKarma(newBlogger.getKarma());
		
		List<Community> newCommunities = new ArrayList<>();
		
		for(Community community : newBlogger.getCommunities()) {
			
			//Creating communityOutputDto object
			Community com = new Community();
			
			//Set values to community object
			com.setCommunityId(community.getCommunityId());
			com.setCommunityDescription(community.getCommunityDescription());
			com.setTotalMembers(community.getTotalMembers());
			com.setOnlineMembers(community.getOnlineMembers());
			com.setImage(community.getImage());
			com.setCreatedOn(community.getCreatedOn());
			com.setPostRulesAllowed(community.getPostRulesAllowed());
			com.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
			com.setBanningPolicy(community.getBanningPolicy());
			com.setFlairs(community.getFlairs());
			
			// Adding com to communities
			newCommunities.add(com);
		}
		
		UserEntity blogUser = newBlogger.getUser();
		// Creating a userOutputDto
		UserEntity userOutput = new UserEntity();
		userOutput.setUserId(blogUser.getUserId());
		userOutput.setEmail(blogUser.getEmail());
		userOutput.setLoginStatus(blogUser.isLoginStatus());
		userOutput.setRole(blogUser.getRole());
		
		bloggerDto.setCommunities(newCommunities);
		bloggerDto.setUser(userOutput);
		
		return bloggerDto;

	}

	@Override
	public void deleteBlogger(int bloggerId) {
		
		Optional<Blogger> opt = blogRepo.findById(bloggerId);
		if (!opt.isPresent()) {
			throw new BloggerIdNotFoundException("Blogger not found with the given id:" + bloggerId);
		}
		
		Blogger blogger = opt.get();
		blogRepo.delete(blogger);
	}

	@Override
	public BloggerOutputDto viewBlogger(int bloggerId) {

		Optional<Blogger> opt = blogRepo.findById(bloggerId);
		if (!opt.isPresent()) {
			throw new BloggerIdNotFoundException("Blogger not found with the given id:" + bloggerId);
		}

		Blogger blogger = opt.get();
		
		BloggerOutputDto blog = new BloggerOutputDto();
		
		blog.setBloggerName(blogger.getBloggerName());
		blog.setBloggerId(blogger.getBloggerId());
		blog.setKarma(blogger.getKarma());
		
		return blog;
	}

	@Override
	public List<BloggerOutputDto> viewAllBloggers() {
		List<BloggerOutputDto> bloggers = new ArrayList<>();
		
		for(Blogger blogger : blogRepo.findAll()) {
			BloggerOutputDto blog = new BloggerOutputDto();
			
			blog.setBloggerName(blogger.getBloggerName());
			blog.setBloggerId(blogger.getBloggerId());
			blog.setKarma(blogger.getKarma());
			
			bloggers.add(blog);
		}
		
		return bloggers;
	}

	
	public List<BloggerOutputDto> viewBloggerListByCommunityId(int communityId) {
		
		List<Blogger> bloggers = blogRepo.viewBloggerListByCommunityId(communityId);
		if(bloggers.isEmpty()) {
			throw new BloggerIdNotFoundException("Bloggers not found with the community id:" + communityId);
		}
		List<BloggerOutputDto> allBloggers = new ArrayList<>();
		
		for(Blogger blogger : bloggers) {
			// Creating bloggerOutputDto and setting values
			BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();
			
			bloggerOutputDto.setBloggerId(blogger.getBloggerId());
			bloggerOutputDto.setBloggerName(blogger.getBloggerName());
			bloggerOutputDto.setKarma(blogger.getKarma());			
			
			allBloggers.add(bloggerOutputDto);
		}
		
		return allBloggers;
	}


	@Override
	public BloggerOutputDto getBloggerByUserId(int userId) {
		
		Blogger blogger = blogRepo.getBloggerByUserId(userId);
		BloggerOutputDto bloggerOutputDto = new BloggerOutputDto();

		bloggerOutputDto.setBloggerId(blogger.getBloggerId());
		bloggerOutputDto.setBloggerName(blogger.getBloggerName());
		bloggerOutputDto.setKarma(blogger.getKarma());
		return bloggerOutputDto;
	}

}
