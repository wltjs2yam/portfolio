package admin.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoticeDto {
  String midx,mcheck,mtitle,mname,mfile,mtext,mdate,file_Name,mviews;
}
