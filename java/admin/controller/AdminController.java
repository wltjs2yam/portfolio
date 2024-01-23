package admin.controller;

import javax.servlet.http.HttpSession;

import admin.Dbselect;
import admin.member_dto;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import admin.dto.AdminMainDto;
import admin.dto.ApproveAdminDto;
import admin.dto.IdCheckDto;
import admin.dto.LogInDto;
import admin.dto.SignUpDto;
import admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;
	@Autowired
	BasicDataSource dataSource;
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/add_master")
	public String addMaster() {
		return "add_master";
	}

	@ResponseBody
	@GetMapping("/idcheck.do")
	public HttpEntity<IdCheckDto.Response> idCheck(IdCheckDto.Request request) {
		IdCheckDto.Response response = adminService.idCheck(request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	@ResponseBody
	@GetMapping("/footer")
	public member_dto getFooterData() throws SQLException {
		Dbselect dbselect = new Dbselect(dataSource);
		return dbselect.getFooter();
	}
	@RequestMapping("/joinok.do")
	public String joinok(SignUpDto.Request request, Model model) {
		int success = adminService.signUpDto(request);

		String script;
		if (success > 0) {
			script = "<script>alert('회원가입이 정상적으로 완료 되었습니다.');" + "location.href='./index';</script>";
		} else {
			script = "<script>alert('회원가입에 문제가 발생하였습니다.');" + "location.href='./add_master';</script>";
		}
		model.addAttribute("script", script);

		return "joinok";
	}

	@PostMapping("/login.do")
	public String login(LogInDto.Request request, HttpSession session, RedirectAttributes model) {
		AdminService.LoginStatus login = adminService.login(request, session);
		if (AdminService.LoginStatus.SUCCESS.equals(login)) {
			return "redirect:/admin_main.do";
		} else {
			model.addFlashAttribute("msg",login.getMessage());
			return "redirect:/index";
		}
	}

	@GetMapping("/admin_main.do")
	public String adminMain(HttpSession session, Model model) {
		AdminMainDto.Response response = adminService.adminMain(session);
		if (response == null) {
			return "redirect:/index";
		} else {
			model.addAttribute("adminList", response.getAdminVoList());
			return "admin_main";
		}
	}

	@ResponseBody
	@PostMapping("/approval")
	public HttpEntity<Void> approveAdmin(@RequestBody ApproveAdminDto.Request request, HttpSession session) {
		adminService.approveAdmin(request, session);
		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	
}