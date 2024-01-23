package admin.mapper;

import java.util.List;

import admin.domain.AirCodeVo;
import admin.domain.FaqVo;
import admin.domain.NoticeVo;

public interface NoticeMapper {

	List<NoticeVo> getNoticeList();
	
	int newCode (AirCodeVo airCodeVo);

	NoticeVo getNoticeByMidx(int midx);
	
}