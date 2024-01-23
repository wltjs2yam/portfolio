package admin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqVo {
	private int fidx;
	private String ftitle;
	private String fwriter;
	private String ftext;
	private String fdate;
}
