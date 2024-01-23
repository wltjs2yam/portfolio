package admin;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class NoticePage {
    String script = "";
	SqlSession se = null;
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;

	@Resource
	private SqlSessionTemplate sqlSession;
	
	public boolean isLogin(HttpSession session) {
		return session.getAttribute("midx") != null;
	}
	
	
}
