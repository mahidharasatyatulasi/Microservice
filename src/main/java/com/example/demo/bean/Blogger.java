package com.example.demo.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blogger{
	@Id
	@GeneratedValue
	private int bloggerId;
	
	@NotEmpty(message="Name shouldn't be empty")
	@Size(min=3, max=50, message="Min 3 characters required")
	private String bloggerName;
	
	private int karma = 50;
	
	public Blogger(int userId,@NotEmpty(message = "Name shouldn't be empty") @Size(min = 3, max = 50, message = "Min 3 characters required") String bloggerName,int karma) {
		super();
		this.bloggerId = userId;
		this.bloggerName = bloggerName;
		this.karma = karma;
	}
	
	//ManyToMany -Many Bloggers can be a part of many communities
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name = "blogger_and_communities",
			joinColumns = { @JoinColumn(name="bloggerId")},
			inverseJoinColumns = { @JoinColumn(name="communityId")})
	private List<Community> communities;
	
//	// Many to many - many bloggers can receive many awards
//	@ManyToMany(cascade = CascadeType.MERGE)
//	@JoinTable(
//			name = "blogger_and_award", 
//			joinColumns = { @JoinColumn(name = "bloggerId") }, 
//			inverseJoinColumns = { @JoinColumn(name = "awardId")})
//	private List<Award> awards;
	
	// OneToOne between blogger and user - A user can be a blogger
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
}
