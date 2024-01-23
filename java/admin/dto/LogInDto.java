package admin.dto;

import lombok.Getter;
import lombok.Setter;

public class LogInDto {

	@Getter
	@Setter
	public static class Request {
		private String mid;
		private String mpass;
	}

}