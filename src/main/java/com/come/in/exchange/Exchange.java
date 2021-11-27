package com.come.in.exchange;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "frequency")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exchange {

	@Id
	private String _id;
	private String userId;
	private String type;
	private int myR;
	private int myW;
	private int exR;
	private int exW;
	private String fullYn;
	private String useYn;
	private String fromDt;
	private String toDt;
	
}
