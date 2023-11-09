package com.moon.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.junrar.volume.FileVolume;
import com.moon.www.domain.FileVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileHandler {
	private final String UP_DIR = "C:\\Users\\UserK\\_myweb\\_java\\fileupload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		
		List<FileVO> flist = new ArrayList<FileVO>();
		
		LocalDate date = LocalDate.now(); // 현재 날짜 객체
		String today = date.toString(); // 현재 날짜를 문자열로 변환 2023-11-06
		today = today.replace("-", File.separator); // 2023-10-24 -> 2023\10\24{window) | 2023/10/24(리눅스,맥)
		
		File folders = new File(UP_DIR, today); // 설정경로에 현재 날짜 경로 추가
		
		if(!folders.exists()) { // 해당 날짜 경로의 폴더가 없다면 생성
			folders.mkdirs(); // 년\월\일 별로 폴더 생성
		}
		
		// 파일 객체에 대한 설정
		for (MultipartFile file : files) {
			// 들어온 첨부파일 1개씩 for문 처리
			String uuid = UUID.randomUUID().toString(); // uuid 랜덤으로 생성 후 toString()으로 변환해서 문자열로 저장
			String originalFileName = file.getOriginalFilename();
			// 가끔 오리지날 파일네임이 다르게 들어오는 경우가 있어서, \이후로 잘라서 저장
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			FileVO fvo = new FileVO();
			fvo.setUuid(uuid);
			fvo.setSaveDir(today);
			fvo.setFileName(fileName);
			fvo.setFileSize(file.getSize());
			// -------------- FileVO 객체 생성 완료 -------------
			
			// 이제 실제 디스크에 저장할 파일 객체 생성
			// 파일이름 uuid_fileName / uuid_th_fileName(썸네일용)
			String fullFileName = uuid.toString() + "_" + fileName;
			File storeFile = new File(folders, fullFileName);
			
			// file 객체가 저장이 되려면 첫 경로부터 다 설정되어 있어야 함
			// C:\\Users\\UserK\\_myweb\\_java\\fileupload\\2023\\11\06\\uuid_fileName.jpg
			
			try {
				file.transferTo(storeFile); // 폴더에 파일 저장
				
				if(isImageFile(storeFile)) { // 이미지 파일 tika로 확인
					fvo.setFileType(1);; // 이미지면 type 1
					
					// 이미지 파일이므로 썸네일 생성
					File thumbnail = new File(folders, uuid + "_th_" + fileName);
					Thumbnails.of(storeFile).size(50, 50).toFile(thumbnail);
				}
				
			} catch (Exception e) {
				log.info("uploadFiles >>>>> 파일 생성 오류");
				e.printStackTrace();
			}
			
			// List<FileVO> fvo 에 추가
			flist.add(fvo);
		}
		// flist 리턴
		return flist;
		
	}
	// tika 라이브러리로 이미지 파일인지 확인
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); //  image/jpg
		return mimeType.startsWith("image") ? true : false;
	}
	
}
