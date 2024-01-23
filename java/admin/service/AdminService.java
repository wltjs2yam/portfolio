package admin.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.Data;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import admin.security;
import admin.domain.AdminVo;
import admin.dto.AdminMainDto;
import admin.dto.ApproveAdminDto;
import admin.dto.IdCheckDto;
import admin.dto.LogInDto;
import admin.dto.SignUpDto;
import admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminMapper adminMapper;
	private final ModelMapper modelMapper;


	public boolean isLogin(HttpSession session) {
		return session.getAttribute("midx") != null;
	}

	public int getMidx(HttpSession session) {
		return (int) session.getAttribute("midx");
	}

	public IdCheckDto.Response idCheck(final IdCheckDto.Request request) {

		AdminVo adminVo = adminMapper.getAdminByMid(request.getMid());

		boolean isDuplicated;

		if (adminVo == null) {
			isDuplicated = false;
		} else {
			isDuplicated = true;
		}

		IdCheckDto.Response response = new IdCheckDto.Response();
		response.setDuplicated(isDuplicated);
		return response;
	}

	public int signUpDto(SignUpDto.Request request) {
		AdminVo adminVo = modelMapper.map(request, AdminVo.class);
		adminVo.setMpass(new security(adminVo.getMpass()).md5_se());
		adminVo.setMhp(adminVo.getMhp().replace(",", ""));
		return adminMapper.signUp(adminVo);
	}

	public LoginStatus login(LogInDto.Request request, HttpSession session) {
		AdminVo adminVo = adminMapper.getAdminByMid(request.getMid());
		security sc = new security(request.getMpass());
		String md5Pass = sc.md5_se();

		if (adminVo == null) { // 아이디가 없음
			return LoginStatus.NOT_EXIST_ID;
		}
		if (adminVo.getMpass().equals(md5Pass)) { // 비밀번호 일치

			// 승인여부 확인
			if (adminVo.getMcheck().equals("N")) {
				return LoginStatus.UNAUTHORIZED_ACCOUNT;
			}

			// 5번 이상 비밀번호 틀림
			if (adminVo.getMcount() > 4) {
				return LoginStatus.NUMBER_OF_LOGINS_EXCEEDED;
			}

			session.setAttribute("midx", adminVo.getMidx());
			session.setAttribute("adminName", adminVo.getMname());
			return LoginStatus.SUCCESS;

		} else { // 비밀번호 불일치 index로 다시 보냄
			adminMapper.plusAdminMcount(adminVo.getMidx());
			return LoginStatus.PASSWORD_MISMATCH;
		}
	}

	public AdminMainDto.Response adminMain(HttpSession session) {

		AdminMainDto.Response response = null;

		if (isLogin(session)) {
			response = new AdminMainDto.Response();

			int midx = getMidx(session);

			AdminVo adminVo = adminMapper.getAdminByMidx(midx);
			response.setAdminName(adminVo.getMname());

			List<AdminVo> adminVoList = adminMapper.getAdminList();
			response.setAdminVoList(adminVoList);
		}
		return response;
	}

	public void approveAdmin(ApproveAdminDto.Request request, HttpSession session) {
		AdminVo adminVo = adminMapper.getAdminByMidx(request.getMidx());

		if (adminVo.getMcheck().equals("Y")) {
			adminMapper.updateMcheck(request.getMidx(), "N");
		} else {
			adminMapper.updateMcheck(request.getMidx(), "Y");
		}
	}


	@RequiredArgsConstructor
	@Getter
	public enum LoginStatus {
		PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다."),
		NOT_EXIST_ID("존재하지 않는 아이디입니다."),
		UNAUTHORIZED_ACCOUNT("승인되지 않은 계정입니다."),
		NUMBER_OF_LOGINS_EXCEEDED("로그인 횟수 5회를 초과했습니다"),
		SUCCESS("로그인 성공");

		private final String message;
	}


}
