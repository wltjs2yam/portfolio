package admin.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import admin.domain.AdminVo;
import admin.domain.AirCodeVo;
import admin.domain.FaqVo;
import admin.domain.NoticeVo;
import admin.dto.AdminMainDto;
import admin.dto.AirCodeDto;
import admin.dto.NoticeListDto;
import admin.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeMapper noticeMapper;
	private final ModelMapper modelMapper;

	public boolean isLogin(HttpSession session) {
		return session.getAttribute("midx") != null;
	}

	public int getMidx(HttpSession session) {
		return (int) session.getAttribute("midx");
	}

	public List<NoticeVo> noticeList(HttpSession session) {
		List<NoticeVo> noticeVoList = noticeMapper.getNoticeList();
        return noticeVoList;
	}
	
	public List<NoticeVo> noticeView(HttpSession session) {
		List<NoticeVo> noticeList = noticeMapper.getNoticeList();
        return noticeList;
	}
	
	
	//항공 코드 입력
	public int airCodeDto(AirCodeDto.Request request) {
		AirCodeVo airCodeVo = modelMapper.map(request, AirCodeVo.class);	
		return noticeMapper.newCode(airCodeVo);
		
	
	}

	
}
