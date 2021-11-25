package com.come.in;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "frequency")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Frequency {

	@Id
	private String _id;
	private String userId;
	private int myWhite;
	private int myRed;
	private int wantWhite;
	private int wantRed;
	
}
