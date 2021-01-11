package com.sharique.fileupload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FileUploadUtility {
	
	
	private Path storageLocation;
	/*@ConfigurationProperties(prefix="") is used to read value from properties file*/
	public FileUploadUtility(@Value("${file.storage.location}") String location) throws IOException {
		
		this.storageLocation=Paths.get(location).toAbsolutePath().normalize();
		/*c:\\image  create folder like this*/
		Files.createDirectories(storageLocation);
	
	}

	public String storeFile(MultipartFile file) throws IOException {
		

		
		Path path = Paths.get(storageLocation+"\\"+StringUtils.cleanPath(file.getOriginalFilename()));
	/*	c:\\image\file.jpg  above line of code will give this line*/
		
		System.out.println("file save location "+path);
		Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		
		return StringUtils.cleanPath(file.getOriginalFilename());
	}

	public Resource download(String filename) throws MalformedURLException {
		
		Path path = Paths.get(storageLocation.toUri()).toAbsolutePath().resolve(filename);
		Resource urlResource = (Resource) new UrlResource(path.toUri());
		
		return urlResource;
		
		
	}
	
	
	

}
