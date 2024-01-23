package admin.dto;

import lombok.Getter;
import lombok.Setter;

public class IdCheckDto {

	@Getter
	@Setter
	public static class Request {
		private String mid;
	}

	@Getter
	@Setter
	public static class Response {
		private boolean duplicated;
	}

}