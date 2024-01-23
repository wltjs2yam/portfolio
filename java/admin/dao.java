package admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class dao {
    String midx,mcheck,mtitle,mname,mfile,mtext,mdate,file_name,mviews;
    MultipartFile userfile;
}
