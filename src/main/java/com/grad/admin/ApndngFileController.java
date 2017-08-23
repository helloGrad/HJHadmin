package com.grad.admin;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.grad.admin.service.ApndngFileService;
import com.grad.admin.vo.ApndngFileVo;

@Controller
public class ApndngFileController {
	
	@Autowired
	private ApndngFileService apndngFileService;
	@RequestMapping("/form")
	public String form(){
		return "form";
	}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public String upload(  @RequestParam(value="email", required=true, defaultValue="") String email,
							@RequestParam(value="file1") MultipartFile file1, Model model){
		
		
		String url1 = apndngFileService.restore(file1);
		model.addAttribute("url1",url1);
		
		return "result";
	}
	
	@RequestMapping("/download")
	public void download(@RequestParam Long no, HttpServletResponse response) {
		ApndngFileVo vo = apndngFileService.getFileInfo(no);
		byte fileByte[];
		try {
			fileByte = FileUtils.readFileToByteArray(new File("C:" + vo.getStorgPath()));
			response.setContentType("application/octet-stream");
		    response.setContentLength(fileByte.length);
		    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(vo.getApndngFileNm(),"UTF-8")+"\";");
		    response.setHeader("Content-Transfer-Encoding", "binary");
		    response.getOutputStream().write(fileByte);
		     
		    response.getOutputStream().flush();
		    response.getOutputStream().close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	     
		    

	}
	
}
