package com.example.demo.bean;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
	
	@Id
	@GeneratedValue
	private int communityId;
	//Community Description
	private String communityDescription;
	//Total members in Community
	private int totalMembers;
	//Total online members in community
	private int onlineMembers;
	//Community Images
	private File image;
	
	//Date when community is created
	//@Convert(converter = LocalDateConverter.class)
	private LocalDate createdOn;
	
	//Post Rules Allowed
	@ElementCollection
	@CollectionTable(name="Rules_Allowed")
	private List<String> postRulesAllowed;
	
	//Post Rules Disallowed
	@ElementCollection
	@CollectionTable(name="Rules_DisAllowed")
	private List<String> postRulesDisAllowed;
	
	//Posts Banning policy
	@ElementCollection
	@CollectionTable(name="Banning_policy")
	private List<String> banningPolicy;
	
	//Posts Flairs
	@ElementCollection
	@CollectionTable(name="flairs")
	private List<String> flairs;

}

