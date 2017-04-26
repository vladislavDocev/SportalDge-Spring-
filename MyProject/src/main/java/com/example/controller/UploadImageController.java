package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Media;
import com.example.model.dao.MediaDAO;

@Controller
@SessionAttributes("filename")
@MultipartConfig
public class UploadImageController {
		
	private String image;

	private static final String FILE_LOCATION = "/Users/vladi/Desktop/UploadedPics/";
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String prepareForUpload() {
		return "upload";
	}
	
	@RequestMapping(value="/image/{fileName}", method=RequestMethod.GET)
	@ResponseBody
	public void prepareForUpload(@PathVariable("fileName") String fileName, HttpServletResponse resp, Model model) throws IOException {
		File file = new File(FILE_LOCATION + image);
		Files.copy(file.toPath(), resp.getOutputStream());
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String receiveUpload(@RequestParam("file") MultipartFile multiPartFile, Model model, HttpSession s) throws IOException{
		String location = "";
		//check if logged
		if(s.isNew() || s.getAttribute("user") == null){
			location = "index";
		}
		else{
			File fileOnDisk = new File(FILE_LOCATION + multiPartFile.getOriginalFilename());
			Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
			image = multiPartFile.getOriginalFilename();
			model.addAttribute("filename", multiPartFile.getOriginalFilename());

			try {
				Media m = null;
				MediaDAO.getInstance().addMedia(m);
			} catch (SQLException e) {
				//show error page
				location = "";
			}
			location = "upload";
		}
		return location;
	}
}