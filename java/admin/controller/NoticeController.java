package admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.domain.NoticeVo;
import admin.dto.AirCodeDto;
import admin.service.NoticeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	String script = "";
	
	
	
	
	
	
	//공지사항 리스트 페이지
	@GetMapping("/notice_list.do")
	public String noticeList(HttpSession session, Model model) {
		List<NoticeVo> noticeVoList = noticeService.noticeList(session);
		model.addAttribute("noticeList", noticeVoList);
		return "admin_notice";
	}
	
	
	//항공편 및 등록 페이지
	@GetMapping("/product_list.do")
	public String productList() {
		return "admin_product";
	}
	
	
	
	//항공 코드 등록
    @GetMapping("/air_newcode.do")
    public String airNewcode() {
		return "admin_category_write";
	}
    
    //항공 코드 승인
    @RequestMapping("/air_newcodeok.do")
    public String newcodeok(AirCodeDto.Request request, Model model) {
    	System.out.println(request.getAiruse());
    	int success = noticeService.airCodeDto(request);
    	
    	String script;
    	if(success > 0) {
    		script = "<script>alert('항공 코드가 정상적으로 생성 되었습니다.');" + "location.href='./air_list.do';</script>";
    	} else {
    		script = "<script>alert('항공 코드 생성에 문제가 발생하였습니다.');" + "location.href='./air_newcode.do';</script>";
    	}
    	model.addAttribute("script", script);
    	
    	return "air_newcodeok";
    }
    
   
}