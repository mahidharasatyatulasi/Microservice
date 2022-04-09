package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
	
	@Query(value = "select u.* from user_entity u join blogger b on u.user_id = b.user_id where b.blogger_id= :bloggerId", nativeQuery = true)
	public UserEntity getUserByBloggerId(@Param("bloggerId") int id);
}

