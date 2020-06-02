package web.assets.controller;

import web.assets.model.Banner;
import web.assets.repository.FileStorage;
import web.assets.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DownloadFileController {

	final
	FileStorage fileStorage;

	public DownloadFileController(FileStorage fileStorage) {
		this.fileStorage = fileStorage;
	}

	/*
	 * Retrieve Files' Information
	 */
	@GetMapping("/files")
	public String getListFiles(Model model) {
		List<FileInfo> fileInfos = fileStorage.loadFiles().map(
					path ->	{
						String filename = path.getFileName().toString();
						String url = MvcUriComponentsBuilder.fromMethodName(web.assets.controller.DownloadFileController.class,
		                        "downloadFile", path.getFileName().toString()).build().toString();

//						String preview = path.getFileName().toString();
//						String type = Banner.getType();
//						Boolean status = Banner.getStatus();
//						String drm = Banner.getDRM();
						return new FileInfo(filename, url); //, preview, type, status, drm
					}
				)
				.collect(Collectors.toList());

		model.addAttribute("files", fileInfos);
		return "main/listfiles";
	}

    /*
     * Download Files
     */
	@GetMapping("/files/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		Resource file = fileStorage.loadFile(filename);
		return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
	}
}