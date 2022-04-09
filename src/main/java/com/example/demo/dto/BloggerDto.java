package com.example.demo.dto;

import java.util.List;

import com.example.demo.bean.Community;
import com.example.demo.bean.UserEntity;

import lombok.Data;

@Data
public class BloggerDto {

	private int bloggerId;
	private String bloggerName;
	private int karma;
	private List<Community> communities;
	//private List<Award> awards;
	private UserEntity user;
}
