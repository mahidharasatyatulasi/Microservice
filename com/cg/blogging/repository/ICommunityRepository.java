package com.cg.blogging.repository;

import java.util.List;

import com.cg.blogging.entities.Blogger;
import com.cg.blogging.entities.Community;

public interface ICommunityRepository {
	public Community addCommunity(Community community);
	public Community updateCommunity(Community community);
	public Community deleteCommunity(Community community);
	public List<Community> listAllCommunities(String searchString);
	public List<Community> listAllCommunitiesByBlogger(Blogger blogger);
}
