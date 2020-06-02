package web.assets.model;

public class FileInfo {
	private String filename;
	private String url;

	private String preview;
	private String type;
	private Boolean status;
	private String drm;

	public FileInfo(String filename, String url) { //, String preview, String type, Boolean status, String drm
		this.filename = filename;
		this.url = url;

//		this.preview = preview;
//		this.type = type;
//		this.status = status;
//		this.drm = drm;
	}

	public String getFilename() {
		return this.filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUrl() { return this.url; }
	public void setUrl(String url) {
		this.url = url;
	}

//	public String getPreview() {
//		return this.preview;
//	}
//	public void setPreview(String preview) {
//		this.preview = preview;
//	}
//
//	public String getType() {
//		return this.type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public Boolean getStatus() {
//		return this.status;
//	}
//	public void setStatus(Boolean status) {
//		this.status = status;
//	}
//
//	public String getDRM() {
//		return this.drm;
//	}
//	public void setDRM(String drm) {
//		this.drm = drm;
//	}

}
