package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
	/* create table file(
			uuid varchar(256) primary key,
			save_dir varchar(256) not null,
			file_name varchar(256) not null,
			file_type tinyint(1) default 0,
			bno bigint,
			file_size int,
			reg_date datetime default now(),
			constraint fk_bno foreign key(bno) references board(bno) on delete cascade); */
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private long bno;
	private long fileSize;
	private String regDate;
}
