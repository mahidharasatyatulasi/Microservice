package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Community;

@Repository
public interface ICommunityRepository extends JpaRepository<Community, Integer> {
	
	//JPQL Query to get all communities which are having same community description
	@Query(value="select * from community where community_description LIKE :searchString",nativeQuery=true)
	public List<Community> listAllCommunitiesByDescription(@Param("searchString") String searchString);

	// JPQL Query to get all communities by blogger
	@Query(value = "SELECT c.* from blogger b join blogger_and_communities bc on b.blogger_id = bc.blogger_id join community c on bc.community_id = c.community_id where b.blogger_id = :bloggerId", nativeQuery = true)
	public List<Community> listAllCommunitiesByBloggerId(@Param("bloggerId") int bloggerId);

	//JPQL Query to get community by using postId
	@Query(value=" select c.* from community c join post p on c.community_id=p.community_id where p.post_id=:postId",nativeQuery=true)
	public Community getCommunityByPostId(@Param("postId") int postId);
	
}
	