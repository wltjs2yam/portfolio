package admin.dto;

import lombok.Getter;
import lombok.Setter;

public class SignUpDto {

	@Getter
	@Setter
	public static class Request {
		private String mid;
		private String mpass;
		private String mname;
		private String memail;
		private String mhp;
		private String mselect1;
		private String mselect2;
	}
}