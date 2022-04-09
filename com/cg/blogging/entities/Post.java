package com.cg.blogging.entities;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

public class Post {
	private int postId;
	private String title;
	private Blogger createdBy;
	private PostType content;
	private List<Files> data;
	private Award awardsReceived;
	private LocalDateTime createdDateTime;
	private List<Comment> comments;
	private int votes;
	private boolean voteUp;
    private boolean notSafeForWork;
    private boolean spoiler;
    private boolean originalContent;
    private String flair;
    private Community community;
    
	
	



}
