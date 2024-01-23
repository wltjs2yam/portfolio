package admin.dto;

import lombok.Getter;
import lombok.Setter;

public class AirCodeDto {

	@Getter
	@Setter
	public static class Request {
		private String aidx;
		private String airplane;
		private String airname;
		private String aircode;
		private String airflight;
		private String airuse;
		private String airdate;
	}
}
