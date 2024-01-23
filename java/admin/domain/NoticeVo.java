package admin.domain;

import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class NoticeVo {
	private int midx;
	private String mcheck;
	private String mtitle;
	private String mname;
	private MultipartFile mfile;
	private String mtext;
	private String mdate;
	private String fileName;
	private int mviews;
}