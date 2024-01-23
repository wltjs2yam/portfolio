package admin.dto;

import lombok.Getter;
import lombok.Setter;

public class FaqDto {
  
	@Getter
	@Setter
	public static class Request {
		private String fidx;
		private String ftitle;
		private String fwriter;
		private String ftext;
		private String fdate;
	}
}
