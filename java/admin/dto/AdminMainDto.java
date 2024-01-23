package admin.dto;

import java.util.List;

import admin.domain.AdminVo;
import lombok.Getter;
import lombok.Setter;

public class AdminMainDto {

	@Getter
	@Setter
	public static class Response {
		private String adminName;
		private List<AdminVo> adminVoList;
	}
}