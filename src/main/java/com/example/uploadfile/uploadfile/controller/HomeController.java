package com.example.uploadfile.uploadfile.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.uploadfile.uploadfile.model.Image;
import com.example.uploadfile.uploadfile.repository.ImageRepository;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Image> image = imageRepository.findAll();
		model.addAttribute("list", image);
		return "index";
	}
	
	@PostMapping("/imageUpload")
	public String imageUpload(@RequestParam MultipartFile img,HttpSession httpSession) throws Exception {
		//System.out.println(img.getOriginalFilename());
		Image image = new Image();
		image.setImgName(img.getOriginalFilename());
		
		Image uploadImages = imageRepository.save(image);
		
		if (uploadImages!=null) {
			
			File saveFile = new ClassPathResource("static/image").getFile();
			
			Path path =Paths.get(saveFile.getAbsolutePath()+File.separator+img.getOriginalFilename());
			//System.out.println(path);			
			Files.copy(img.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
		}
		
		
		return "redirect:index";
	}

}
