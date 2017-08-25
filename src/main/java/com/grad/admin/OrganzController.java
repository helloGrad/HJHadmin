package com.grad.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.grad.admin.security.Auth;
import com.grad.admin.service.ApndngFileService;
import com.grad.admin.service.OrganzService;
import com.grad.admin.vo.ApndngFileVo;
import com.grad.admin.vo.OrganzVo;
import com.grad.admin.vo.ResrchAcrsltVo;


@Controller
@RequestMapping("/organz")
public class OrganzController {

	/*
	 * 정예린 : admin으로 옮김
	 */
	@Autowired
	OrganzService organzService;

	@Autowired
	ApndngFileService apndngFileService;

	/*
	 * 허주한
	 */
	@Auth(role=Auth.Role.ADMIN) 
	@RequestMapping({"/list", "/gradlist"})
	public String gradList(Model model) {

		model.addAttribute("list", organzService.getOrgnzList("대학원"));
		return "/organz/gradlist";
	}

	/*
	 * 허주한
	 */
	@Auth(role=Auth.Role.ADMIN) 
	@RequestMapping("/unilist")
	public String uniList(Model model) {

		model.addAttribute("list", organzService.getOrgnzList("대학교"));
		return "/organz/gradlist";
	}

	/*
	 * 허주한
	 */
	@Auth(role=Auth.Role.ADMIN) 
	@RequestMapping("/deptlist")
	public String deptList(Model model) {

		model.addAttribute("list", organzService.getOrgnzList("학과"));
		return "/organz/gradlist";
	}

	/*
	 * 박가혜
	 */
	@Auth(role = Auth.Role.ADMIN)
	@RequestMapping("/lablist") // 연구실 리스트
	public String lablist(Model model) {

		model.addAttribute("list", organzService.getOrganzLabList());

		return "/organz/gradlist";
	}

	/*
	 * 허주한
	 */
	@Auth(role=Auth.Role.ADMIN) 
	@RequestMapping("/updateform")
	public String updateGradForm(Model model, @RequestParam int no, @RequestParam String type) {


		/*
		 * 박가혜
		 */
		if (type.equals("연구실")) {

			model.addAttribute("organzLabList", organzService.getOrganz(no));
			return "organz/labupdatedetail";

		}else {

			model.addAttribute("vo", organzService.getOrgnzByNo(no, type));

			System.out.println(apndngFileService.getFileList(no,type));
			model.addAttribute("fileList", apndngFileService.getFileList(no,type));
			return "organz/updategrad";

		}

	}



	/*
	 * 허주한
	 */
	@Auth(role=Auth.Role.ADMIN) 
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateGrad(Model model, @ModelAttribute OrganzVo organzVo, @RequestParam String type, @RequestParam(value = "prntsOrgnzStr", required = true, defaultValue = "") String prntsOrgnzStr) {

		/*
		 * 박가혜
		 */
		if (type.equals("연구실")) {

			organzService.updateOrganz(organzVo);
			return "redirect:/organz/lablist";

		} else {
			organzService.update(organzVo, type, prntsOrgnzStr);
			return "redirect:/organz/list";
		}




	}



	/*
	 * 허주한
	 */
	@Auth(role=Auth.Role.ADMIN) 
	@RequestMapping("/insertform")
	public String insertGradForm(Model model, @RequestParam String type) {

		if(type.equals("대학원")||type.equals("대학교")||type.equals("학과")) {
			return "/organz/insertgrad";
		}
		else {
			return "organz/labdetail";
		}

	}


	/*
	 * 허주한
	 */
	@Auth(role=Auth.Role.ADMIN) 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertGrad(Model model, @ModelAttribute OrganzVo organzVo, @RequestParam(value = "prntsOrgnzStr", required = true, defaultValue = "") String prntsOrgnzStr,
			@RequestParam String type, @ModelAttribute ResrchAcrsltVo resrchAcrsltVo,
			@RequestParam(value="attachFile", required=false) MultipartFile[] attachFile) {

		int lastId=0;
		ApndngFileVo vo = null;

		if (type.equals("연구실")) {

			if (resrchAcrsltVo.getResrchText() == null) { // 연구실입력인 경우
				organzService.insertLab(organzVo);
				lastId=organzService.lastInsertId();
			}

			if (organzVo.getOrgnzNm() == null) { // 연구실적입력인 경우
				organzService.insertResrch(resrchAcrsltVo);
			}

		} else {
			organzService.insert(organzVo, prntsOrgnzStr);
			lastId=organzService.lastInsertId();

		}

		for(int i=0;i<attachFile.length;i++) {

			if(!attachFile[i].isEmpty()) {
				apndngFileService.restore(attachFile[i]);
				vo = apndngFileService.getFileVo();
				vo.setPrntsDstnct(type);
				vo.setPrntsNo(lastId);
				apndngFileService.insert(vo);
			}
		}

		return "redirect:/organz/list";





	}





	/***********************************************************************/
















	/*
	 * 박가혜
	 */
	@Auth(role = Auth.Role.ADMIN)
	@RequestMapping("/resrchlist") //연구실적리스트
	public String resrchlist(Model model, @RequestParam(value = "no", required = true, defaultValue = "") int orgnzNo) {

		List<ResrchAcrsltVo> resrchAcrsltList = organzService.getResrchList(orgnzNo);
		model.addAttribute("resrchAcrsltList", resrchAcrsltList);

		return "organz/resrchlist";
	}




	/*
	 * 박가혜
	 */
	@Auth(role = Auth.Role.ADMIN)
	@RequestMapping("/resrchdetail") //연구실적 수정폼
	public String resrchdetail(Model model, @RequestParam(value = "no", required = true, defaultValue = "") int resrchAcrsltNo) {



		ResrchAcrsltVo resrchAcrsltVo = organzService.getResrchNo(resrchAcrsltNo);
		model.addAttribute("resrchAcrsltVo", resrchAcrsltVo);


		return "organz/resrchupdate";
	}

	/*
	 * 박가혜
	 */
	@Auth(role = Auth.Role.ADMIN)
	@RequestMapping(value = "/resrchupdate" ,method = RequestMethod.POST)//연구실적 수정폼
	public String resrchupdate(Model model, @ModelAttribute ResrchAcrsltVo resrchAcrsltVo) {



		organzService.updateResrch(resrchAcrsltVo);


		return "redirect:/organz/lablist";
	}



}
