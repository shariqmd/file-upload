package com.sharique.fileupload;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.MalformedURLException;


import javax.print.attribute.standard.Media;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class Fileuploadcontroller {
	
	
  Logger log=LoggerFactory.getLogger("Fileupload controller");
	
	@Autowired
	FileUploadUtility fileuploadUtility;
	
	@PostMapping("/upload")
	public ResponseEntity<FileResonse> uploadfile(@RequestParam MultipartFile file) throws IOException
	{
		log.info("file upload rest controller called  ");
		String filename=fileuploadUtility.storeFile(file);
		
		
	
		String url=ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(filename).toUriString();
		FileResonse fileresponse=new FileResonse(filename,url,file.getContentType());
		
		return new ResponseEntity(fileresponse,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> downloadfile(@PathVariable String filename) throws MalformedURLException
	{
		Resource resources=(Resource) fileuploadUtility.download(filename);
		org.springframework.http.MediaType contentType=org.springframework.http.MediaType.IMAGE_JPEG;
		return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename).body(resources);
	}


	
}
