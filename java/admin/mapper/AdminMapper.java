package admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import admin.domain.AdminVo;

public interface AdminMapper {

	AdminVo getAdminByMid(String mid);

	int signUp(AdminVo adminVo);

	int plusAdminMcount(int midx);

	AdminVo getAdminByMidx(int midx);

	List<AdminVo> getAdminList();

	int updateMcheck(@Param("midx") int midx, @Param("mcheck") String mcheck);
}
