package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Blogger;

@Repository
public interface IBloggerRepository extends JpaRepository<Blogger, Integer> {

	@Query(value = "SELECT b.* from blogger b join blogger_and_communities bc on b.blogger_id = bc.blogger_id join community c on bc.community_id = c.community_id where c.community_id = :communityId", nativeQuery = true)
	public List<Blogger> viewBloggerListByCommunityId(@Param("communityId") int communityId);
	
	@Query(value = "select b.* from blogger b join user_entity u on u.user_id=b.user_id where u.user_id=:user_id", nativeQuery = true)
	public Blogger getBloggerByUserId(@Param("user_id") int id);
}
