package com.sharique.fileupload;

public class FileResonse {
	
	private String filename;

	private String url;
	private String contentType;
	
	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public FileResonse(String filename, String url, String contentType) {
		super();
		this.filename = filename;
		this.url = url;
		this.contentType = contentType;
	}
	
	
	
}
