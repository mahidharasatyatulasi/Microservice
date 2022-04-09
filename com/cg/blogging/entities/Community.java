package com.cg.blogging.entities;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Community {

	private int communityId;
	private String communityDescription;
	private int totalMembers;
	private int onlineMembers;
	private File image;
	private LocalDate createdOn;
	private List<String> postRulesAllowed;
	private List<String> postRulesDisAllowed;
	private List<String> banningPolicy;
    private List<String> flairs;
    

	
	
}
