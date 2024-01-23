package admin;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoticeDto {
	private String midx;
	private boolean mcheck;
	private String mtitle;
	private String mname;
	private MultipartFile userfile;
	private String mtext;
	private String mfile;
	private String filePath;
}