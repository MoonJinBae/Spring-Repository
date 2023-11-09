package com.moon.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moon.www.domain.FileVO;
import com.moon.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileSweeper {
	
	private final String BASE_PATH = "C:\\Users\\UserK\\_myweb\\_java\\fileupload\\";
	
	private final FileDAO fdao;
	
	// 초 분 시 일 월 요일 년도(생략가능)
	@Scheduled(cron = "0 10 17 * * *")
	public void fileSweeper() {
		log.info("FileSweeper Running Start >>>>> " + LocalDateTime.now());
		
		// DB에 등록된 파일 목록 가져오기
		List<FileVO> dbList = fdao.getAllFiles();
		// 저장소를 검색할 때 필요한 파일 경로 리트스(실제 존재해야 할 리스트)
		List<String> currFiles = new ArrayList<>();
		for(FileVO fvo : dbList) {
			String filePath = fvo.getSaveDir() + "\\" + fvo.getUuid();
			String fileName = fvo.getFileName();
			
			// 파일에 존재해야 하는 리스트 추가
			currFiles.add(BASE_PATH + filePath + "_" + fileName);
			// 이미지 파일 type은 1 이므로 이미지 파일이면 경로 추가
			if(fvo.getFileType() > 0) {
				currFiles.add(BASE_PATH + filePath + "_th_" + fileName);
			}
		}
		
		// 날짜를 반영한 폴더 구조 경로 만들기
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		
		// 경로를 기반으로 저장되어 있는 파일을 검색
		File dir = Paths.get(BASE_PATH + today).toFile();
		File[] allFileObjects = dir.listFiles();
		
		// 실제 저장되어 있는 파일과 DB에 존재하는 파일을 비교하여 없는 파일 삭제 진행
		for(File file : allFileObjects) {
			String storedFileName = file.toPath().toString();
			// 해당 파일이 DB에 없다면..
			if(!currFiles.contains(storedFileName)) {
				file.delete();
				log.info("Delete File >>>>> " + storedFileName);
			}
			log.info("FileSweeper Running Finish >>>>> " + LocalDateTime.now());
		}
		
	}
	
	
}
























