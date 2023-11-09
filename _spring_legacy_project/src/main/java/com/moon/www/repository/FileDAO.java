package com.moon.www.repository;

import java.util.List;

import com.moon.www.domain.FileVO;

public interface FileDAO {

	int uploadFile(FileVO fvo);

	List<FileVO> getFiles(int bno);

	int deleteFile(String uuid);

	List<FileVO> getAllFiles();

}
