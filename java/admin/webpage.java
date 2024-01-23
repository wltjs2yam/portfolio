package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import admin.domain.AirCodeVo;
import admin.domain.FaqVo;
import admin.domain.NoticeVo;
import admin.domain.PayListVo;
import admin.service.NoticeService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class webpage {

	String script = "";
	SqlSession se = null;

	private final NoticeService noticeService = null;

	@Autowired
	BasicDataSource dataSource;

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	@Resource
	private SqlSessionTemplate sqlSession;

	private MultipartFile mfile;


	// 공지사항 쓸 수 있는 파트
	@GetMapping("/admin_notice_write_page.do")
	public String admin_notice_write(HttpSession session, Model model) throws SQLException {

		if (isLogin(session)) { // 로그인 안 함
			Dbselect select = null;

			select = new Dbselect(dataSource);
			int midx = (int) session.getAttribute("midx");
			AdminVo adminVo = select.getAdmin(midx);
			model.addAttribute("adminName", adminVo.getMname());

			return "admin_notice_write";
		} else {
			return "redirect:/index.do";
		}

	}

	@PostMapping("/notice_writeok.do")
	public String notice_write(@ModelAttribute("board") dao dao, @RequestParam(value = "userfile", required = false) MultipartFile files, HttpServletRequest req, Model model) throws Exception {

		String filename = null;
	
		if (files != null && !files.isEmpty()) {
			long filesize = files.getSize();

			if (filesize > 3145728) {
				System.out.println("용량초과");
				return "redirect:/admin_notice_write_page.do";
			}

			FTPClient ftp = new FTPClient();
			ftp.setControlEncoding("utf-8");
			FTPClientConfig cf = new FTPClientConfig();
			try {
				String filenameExtension = StringUtils.getFilenameExtension(files.getOriginalFilename());
				filename = UUID.randomUUID() + "." + filenameExtension;
				String host = "";
				String user = "";
				String pass = "";
				int port = 21;
				ftp.configure(cf);
				ftp.connect(host, port);

				if (ftp.login(user, pass)) {
					ftp.setFileType(FTP.BINARY_FILE_TYPE); //이미지, 동영상, ppt..
					boolean ok = ftp.storeFile("/www/" + filename, files.getInputStream());

					if (ok == true) {
						System.out.println("정상적으로 업로드 되었습니다.");
						model.addAttribute("imgs", filename);
					} else {
						System.out.println("FTP 경로 오류 발생 되었습니다.");
					}
				} else {
					System.out.println("FTP 정보가 올바르지 않습니다.");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FTP 접속 정보 오류 및 접속 사용자 아이디/패스워드 오류!");
			} finally {
				try {
					ftp.disconnect();
				} catch (Exception ee) {
					System.out.println("서버 루프로 인하여 접속종료 장애 발생!");
				}
			}
		}

		// 글 작성 처리
		SqlSession ss = sqlSessionFactory.openSession();
		dao.setFile_name(filename);
		ss.insert("admin.mapper.NoticeMapper.noticeWrite", dao);
		return "redirect:/notice_view.do?midx=" + dao.getMidx();
	}


	//사이트 기본 설정
	@GetMapping("/admin_siteinfo.do")
	public String admin_siteinfo(HttpSession session, Model model) throws SQLException {

		if (isLogin(session)) {
			Dbselect selectDao = new Dbselect(dataSource);
			member_dto member = selectDao.getsiteinfo();

			model.addAttribute("siteinfo", member);

			return "admin_siteinfo";
		} else {
			return "redirect:/index.do";
		}
	}

	// 정보저장
	@PostMapping("/save.do")
	public String save(HttpSession session, member_dto memberDto) throws Exception {
		if (isLogin(session)) {


			Dbselect selectDao = new Dbselect(dataSource);
			member_dto member = selectDao.getsiteinfo();
			memberDto.setSidx(member.getSidx());
			if (member == null) { //  db에 아무정보가 없음. (insert)
				db_insert insertDao = new db_insert(dataSource);
				insertDao.saveSiteInfoUseMeberDto(memberDto);
			} else { // 기존에 이미 입력된 상태이므로 업데이트 (update)
				db_update updateDao = new db_update(dataSource);
				updateDao.updateSiteinfo(memberDto);
			}

			return "redirect:/admin_siteinfo.do";
		} else {
			return "redirect:/index.do";
		}

	}

	public boolean isLogin(HttpSession session) {
		return session.getAttribute("midx") != null;
	}


	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}


	// 사이트 정보 Insert 처리
	@GetMapping("/insert_admin_siteinfo.do")
	public String insert_admin_siteinfo() {

		
		try {
			member_dto mdto = new member_dto();
			
			mdto.setSaddress(null);

			db_insert insertDao = new db_insert(dataSource);
			insertDao.saveSiteInfoUseMeberDto(mdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return "redirect:/admin_siteinfo.do";
	}

	// 사이트 정보 Up 처리
	@GetMapping("/update_admin_siteinfo.do")
	public String update_admin_siteinfo() {

		return "admin_siteinfo";
	}


	// 공지사항 내용 볼 수 있는 페이지
	@GetMapping("/notice_view.do")
	public String notice_view(@RequestParam(defaultValue = "", required = false) String midx, Model model) {


		if (midx.equals("") || midx == "") { 
			this.script = "<script>"
					+ "alert('정상적인 접근이 아닙니다.');"
					+ "location.href='./admin_notice.do';"
					+ "</script>";
		} else {

			try {

				this.script = "";
				this.se = sqlSessionFactory.openSession();

				NoticeVo d = this.se.selectOne("admin.mapper.NoticeMapper.noticeView", midx);


				ArrayList<Object> onedata = new ArrayList<Object>();
				onedata.add(d.getMidx()); //0
				onedata.add(d.getMcheck()); //1
				onedata.add(d.getMtitle()); //2
				onedata.add(d.getMname()); //3
				onedata.add(d.getFileName()); //4
				onedata.add(d.getMtext()); //5
				model.addAttribute("one", onedata);
				
				this.se.update("admin.mapper.NoticeMapper.updateView",midx);


			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Database 연결 오류 발생!");
			} finally {
				this.se.close();
			}
		}
		model.addAttribute("script", this.script);

		return "admin_notice_view";
	}

	//공지사항 수정 페이지
	@GetMapping("/notice_modify.do")
	public String noticeModify(@RequestParam(defaultValue = "", required = false) String midx, Model model) {
		if (midx.equals("") || midx == "") {
			this.script = "<script>"
					+ "alert('정상적인 접근이 아닙니다.');"
					+ "location.href='./notice_list.do';"
					+ "</script>";
		} else {
			try {
				this.se = sqlSessionFactory.openSession();
				NoticeVo d = this.se.selectOne("admin.mapper.NoticeMapper.noticeView", midx);
				ArrayList<Object> onedata = new ArrayList<>();
				onedata.add(d.getMidx());       // 0
				onedata.add(d.getMcheck());   // 1
				onedata.add(d.getMtitle());   // 2
				onedata.add(d.getFileName());      // 3
				onedata.add(d.getMtext());   // 4
				model.addAttribute("one", onedata);
			} catch (Exception e) {
				System.out.println("Database 접속 오류 발생");
			} finally {
				this.se.close();
			}
		}
		model.addAttribute("script", this.script);
		return "admin_notice_modify";
	}

	// 공지사항 수정 완료
	@PostMapping("/notice_modifyok.do")
	public String noticeModifyok(@ModelAttribute("board") dao dao, HttpServletRequest req, Model model, RedirectAttributes redirectAttributes) throws Exception {
		this.se = sqlSessionFactory.openSession();
		MultipartFile userfile = dao.getUserfile();
		if (userfile != null && !userfile.isEmpty()) {

			String filename = null;

			long filesize = userfile.getSize();

			if (filesize > 3145728) {
				System.out.println("용량초과");
				model.addAttribute("msg","첨부파일은 3MB 이하로 사용하셔야 합니다.");
				model.addAttribute("location","back");
				return "admin_notice_modify.do";
			}

			FTPClient ftp = new FTPClient();
			ftp.setControlEncoding("utf-8");
			FTPClientConfig cf = new FTPClientConfig();
			try {
				String filenameExtension = StringUtils.getFilenameExtension(userfile.getOriginalFilename());
				filename = UUID.randomUUID() + "." + filenameExtension;
				String host = "";
				String user = "";
				String pass = "";
				int port = 21;
				ftp.configure(cf);
				ftp.connect(host, port);

				if (ftp.login(user, pass)) {
					ftp.setFileType(FTP.BINARY_FILE_TYPE); //이미지, 동영상, ppt..
					boolean ok = ftp.storeFile("/www/" + filename, userfile.getInputStream());

					if (ok == true) {
						System.out.println("정상적으로 업로드 되었습니다.");
					} else {
						System.out.println("FTP 경로 오류 발생 되었습니다.");
					}
				} else {
					System.out.println("FTP 정보가 올바르지 않습니다.");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FTP 접속 정보 오류 및 접속 사용자 아이디/패스워드 오류!");
			} finally {
				try {
					ftp.disconnect();
				} catch (Exception ee) {
					System.out.println("서버 루프로 인하여 접속종료 장애 발생!");
				}
			}
			dao.setMfile(filename);
			this.se.update("admin.mapper.NoticeMapper.noticeFile", dao);
			redirectAttributes.addFlashAttribute("msg","정상적으로 수정되었습니다.");
			return "redirect:/notice_view.do?midx=" + dao.getMidx();
		} else {
			try {

				int result = this.se.update("admin.mapper.NoticeMapper.noticeFile", dao);
				if (result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 게시물이 수정 완료 되었습니다.');"
							+ "location.href='./notice_list.do';"
							+ "</script>";
				} else {
					this.script = "<script>"
							+ "alert('데이터에 오류로 인하여 수정이 되지 않았습니다.');"
							+ "history.go(-1);"
							+ "</script>";
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Database 접속 오류!");
			} finally {
				this.se.close();
			}
		}
		model.addAttribute("script", this.script);
		return "script_page";

	}

	// 공지사항 삭제 페이지
	@GetMapping("/notice_deleteok.do")
	public String noticeDelete(@RequestParam(defaultValue = "", required = false) String midx, Model model,
			@RequestParam(defaultValue = "", required = false) String url) {
		System.out.println(url);
		FTPClient ftp = new FTPClient();
	      ftp.setControlEncoding("utf-8");
	      FTPClientConfig cf = new FTPClientConfig();
	      try {
	         String host = ""; // cafe24 넣어도 가능
	         String user = "";
	         String pass = "";
	         int port = 21;
	         ftp.configure(cf);
	         ftp.connect(host,port);
	         
	         if(ftp.login(user, pass)) {
	            // 파일 삭제 메소드, ("/웹경로/"+삭제할 파일명);
	            
	            boolean delok = ftp.deleteFile("/www/"+url);   
	            
	            if (delok == true) {
	               System.out.println("정상적으로 파일이 삭제 되었어요.");
	            }
	            else {
	               System.out.println("경로 또는 파일문제로 인하여 삭제되지 않았어요.");
	            }
	         }
	         else {
	            System.out.println("FTP 접속오류 발생");
	         }
	      }catch (Exception e) {
	         System.out.println("FTP 정보오류 발생");
	      }
	         finally {
	            try {
	               ftp.disconnect();
	         }
	            catch(Exception ee) {
	               System.out.println("CDN 서버 지연으로 접속이 해제되지 않아요.");
	         }
	      }
		
		System.out.println(midx);
		if (midx.equals("") || midx == null) { 
			String script = "<script>" +
					"alert('정상적인 접근이 아닙니다.');" +
					"location.href='./notice_list.do';" +
					"</script>";
			model.addAttribute("script", script);
			return "script_page";
		}

		try {
			SqlSession se = sqlSessionFactory.openSession();
			int result = se.delete("admin.mapper.NoticeMapper.noticeDelete", midx);
			if (result > 0) {
				String script = "<script>" +
						"alert('정상적으로 게시물이 삭제되었습니다.');" +
						"location.href='./notice_list.do';" +
						"</script>";
				model.addAttribute("script", script);
				se.commit();
			} else {
				String script = "<script>" +
						"alert('데이터가 비정상으로 활용되었습니다.');" +
						"location.href='./notice_list.do';" +
						"</script>";
				model.addAttribute("script", script);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database 접속 오류!!!!");
		}

		return "script_page";
	}

	// 공지사항 복수 삭제
	@GetMapping("/notice_deleteokay.do")
	public String noticeDeleteOkay(@RequestParam String midx[], Model model,
			@RequestParam String url[]) {
		FTPClient ftp = new FTPClient();
	      ftp.setControlEncoding("utf-8");
	      FTPClientConfig cf = new FTPClientConfig();
	      try {
	         String host = ""; // cafe24 넣어도 가능
	         String user = "";
	         String pass = "";
	         int port = 21;
	         ftp.configure(cf);
	         ftp.connect(host,port);
	         boolean delok= true;
	         if(ftp.login(user, pass)) {
	        	
	            // 파일 삭제 메소드, ("/웹경로/"+삭제할 파일명);
	            for(int a=0; a<url.length; a++) {
	            delok = ftp.deleteFile("/www/"+url[a]);   //boolean => true 나 false 시 사용
	            }
	            if (delok == true) {
	               System.out.println("정상적으로 파일이 삭제 되었어요.");
	            }
	            else {
	               System.out.println("경로 또는 파일문제로 인하여 삭제되지 않았어요.");
	            }
	         }
	         else {
	            System.out.println("FTP 접속오류 발생");
	         }
	      }catch (Exception e) {
	         System.out.println("FTP 정보오류 발생");
	      }
	         finally {
	            try {
	               ftp.disconnect();
	         }
	            catch(Exception ee) {
	               System.out.println("CDN 서버 지연으로 접속이 해제되지 않아요.");
	         }
	      }
		
		if (midx.equals("") || midx == null) { 
			String script = "<script>" +
					"alert('정상적인 접근이 아닙니다.');" +
					"location.href='./notice_list.do';" +
					"</script>";
			model.addAttribute("script", script);
			return "script_page";
		}
		try {
			SqlSession se = sqlSessionFactory.openSession();
			int ea = midx.length;
			int w = 0;
			int result = 0;
			while (ea > w) {
				result = se.delete("admin.mapper.NoticeMapper.noticeDelete", midx[w]);

				w++;
			}

			if (result > 0) {
				String script = "<script>" +
						"alert('정상적으로 게시물이 삭제되었습니다.');" +
						"location.href='./notice_list.do';" +
						"</script>";
				model.addAttribute("script", script);
				se.commit();
			} else {
				String script = "<script>" +
						"alert('데이터가 비정상으로 활용되었습니다.');" +
						"location.href='./notice_list.do';" +
						"</script>";
				model.addAttribute("script", script);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database 접속 오류!!!!");
		}


		return "notice_deleteokay";
	}


	// 항공 코드 리스트 출력
	@RequestMapping("/air_list.do")
	public String airList(HttpSession session, HttpServletRequest req, Model model,
						  @RequestParam(defaultValue = "", required = false) String search,
						  @RequestParam(defaultValue = "1", required = false) String part) {
		List<AirCodeVo> ac = null;
		Map<String, String> m = new HashMap<String, String>();
		this.se = sqlSessionFactory.openSession();
		ac = this.se.selectList("admin.mapper.NoticeMapper.codeList");   // 여러개의 데이터를 가져올 때 사용
		try {
			if (search == "" || search == null) {
				ac = this.se.selectList("admin.mapper.NoticeMapper.codeList");   // 여러개의 데이터를 가져올 때 사용
			} else {
				if (part.equals("1")) {
					m.put("part", part);
					m.put("search", search);
					ac = this.se.selectList("admin.mapper.NoticeMapper.airCodeSearch", m);

				} else {
					m.put("part", part);
					m.put("search", search);
					ac = this.se.selectList("admin.mapper.NoticeMapper.airCodeSearch", m);
				}
			}

			model.addAttribute("adata", ac);
			model.addAttribute("search", search);
			model.addAttribute("part", part);
		} catch (Exception e) {
			System.out.println("Database 접속 오류발생");
		} finally {
			this.se.close();
		}
		return "admin_category";
	}



	//신규 항공편 등록
	@GetMapping("/product_new.do")
	public String productNew(HttpSession session, @RequestParam(defaultValue = "", required = false) String aidx, HttpServletRequest request, Model model) {
		this.se = sqlSessionFactory.openSession();
		List<AirCodeVo> a = null;
		a = this.se.selectList("admin.mapper.NoticeMapper.codeListOption");
		model.addAttribute("aa", a);

		return "admin_product_write";
	}


	//좌석 및 예매등록
	@GetMapping("/seat_list.do")
	public String seatList(HttpSession session) {

		return "admin_seat";
	}

	//고객관리 FAQ
	@RequestMapping("/faq_list.do")
	public String faqList(HttpSession session, HttpServletRequest req, Model model, @RequestParam(defaultValue = "", required = false) String search) {
		List<FaqVo> fa = null;
		try {
			this.se = sqlSessionFactory.openSession();
			if (search == "" || search == null) {

				fa = this.se.selectList("admin.mapper.NoticeMapper.faqWriteList");
			} else {
				fa = this.se.selectList("admin.mapper.NoticeMapper.faqSearch", search);
			}
			model.addAttribute("fdata", fa);
		} catch (Exception e) {
			System.out.println("Database 접속 오류 발생");
			e.printStackTrace();
		} finally {
			this.se.close();
		}

		return "admin_faq";
	}
		


	// FAQ  글쓰는 파트
	@GetMapping("/faq_write.do")
	public String faqWrite(HttpSession session) {

		return "admin_faq_write";
	}


	// FAQ 글 저장
	@PostMapping("/notice_faq_writeok.do")
	public String faqWrite(HttpSession session, FaqVo faq, HttpServletRequest req, Model model) throws Exception {
		SqlSession ss = sqlSessionFactory.openSession();
		this.script = "";

		try {
			int result = ss.insert("admin.mapper.NoticeMapper.faqWrite", faq);
			if (result > 0) {
				this.script = "<script>"
						+ "alert('정상적으로 게시글이 등록 되었습니다');"
						+ "location.href='./faq_list.do';"
						+ "</script>";
			} else {
				this.script = "<script>"
						+ "alert('데이터에 오류로 인하여 게시글이 등록 되지 않았습니다.');"
						+ "history.go(-1);"
						+ "</script>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database 접속 오류 발생!!");
		} finally {
			ss.close();
		}
		model.addAttribute("script", this.script);
		return "notice_faq_writeok";

	}

	// FAQ 글 내용 보기 및 수정
	@GetMapping("/faq_view.do")
	public String faqView(@RequestParam(defaultValue = "", required = false) String fidx, Model model) {

		if (fidx.equals("") || fidx == "") {
			this.script = "<script>"
					+ "alert('정상적인 접근이 아닙니다.');"
					+ "location.href='./faq_list.do';"
					+ "</script>";
		} else {
			try {
				this.se = sqlSessionFactory.openSession();
				FaqVo f = this.se.selectOne("admin.mapper.NoticeMapper.faqView", fidx);

				ArrayList<Object> fdata = new ArrayList<Object>();

				fdata.add(f.getFidx());   // 0
				fdata.add(f.getFtitle());
				fdata.add(f.getFwriter());
				fdata.add(f.getFtext());
				fdata.add(f.getFdate()); //4
				model.addAttribute("ff", fdata);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Database 접속 오류 발생");
			} finally {
				this.se.close();
			}
		}

		model.addAttribute("script", this.script);
		return "admin_faq_modify";
	}


	// FAQ 수정완료
	@PostMapping("/faq_modifyok.do")
	public String faqModifyok(@ModelAttribute("board") FaqVo faqvo, HttpServletRequest req, Model model) {
		this.script = "";

		try {
			this.se = sqlSessionFactory.openSession();
			int result = this.se.update("admin.mapper.NoticeMapper.faqUpdate", faqvo);
			if (result > 0) {
				this.script = "<script>"
						+ "alert('정상적으로 게시물이 수정 완료 되었습니다.');"
						+ "location.href='./faq_list.do';"
						+ "</script>";
			} else {
				this.script = "<script>"
						+ "alert('데이터에 오류로 인하여 수정이 되지 않았습니다.');"
						+ "history.go(-1);"
						+ "</script>";
			}
		} catch (Exception e) {
			System.out.println("Database 접속 오류!");
		} finally {
			this.se.close();
		}
		model.addAttribute("script", this.script);
		return "faq_modifyok";
	}

	// FAQ 삭제
	@GetMapping("/faq_delete.do")
	public String faqDelete(@RequestParam(defaultValue = "", required = false) String fidx, Model model) {

		if (fidx.equals("") || fidx == "") { 
			this.script = "<script>"
					+ "alert('정상적인 접근이 아닙니다.');"
					+ "location.href='./faq_list.do';"
					+ "</script>";
		} else { 
			try {

				this.se = sqlSessionFactory.openSession();
				this.script = "";
				
				int result = this.se.delete("admin.mapper.NoticeMapper.faqDelete", fidx);
				if (result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 게시물이 삭제 되었습니다.');"
							+ "location.href='./faq_list.do';"
							+ "</script>";
				} else {
					this.script = "<script>"
							+ "alert('데이터가 비정상으로 활용 되었습니다.');"
							+ "location.href='./faq_list.do';"
							+ "</script>";
				}

			} catch (Exception e) {
				System.out.println("Database 접속 오류!");
			} finally {
				this.se.close();
			}
		}
		model.addAttribute("script", this.script);
		return "faq_delete";
	}


	//예매리스트 페이지
	@RequestMapping("/ticketing_list.do")
	public String ticketing(HttpServletRequest req, Model model, @RequestParam(defaultValue = "", required = false) String search,
							@RequestParam(defaultValue = "1", required = false) String part) {
		List<PayListVo> pa = null;
		Map<String, String> m = new HashMap<String, String>();
		this.se = sqlSessionFactory.openSession();
		try {
	
			if (search.equals("") || search.equals(null)) {
				pa = this.se.selectList("admin.mapper.NoticeMapper.payList");
				
			} else {
				if (part.equals("1")) {
					m.put("part", part);
					m.put("search", search);
					pa = this.se.selectList("admin.mapper.NoticeMapper.payListSearch", m);
				} else {
					m.put("part", part);
					m.put("search", search);
					pa = this.se.selectList("admin.mapper.NoticeMapper.payListSearch", m);
				}
			}
			model.addAttribute("pdata", pa);
			model.addAttribute("search",search);
			model.addAttribute("part",part);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database 접속 오류!");
		} finally {
			this.se.close();
		}

		return "admin_ticketing";
	}


	//항공코드 중복확인
	@GetMapping("/aircodecheck.do")
	public String aircodeCheck(@RequestParam String aircode, Model model) {
		String msg = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) as abc from air_code where aircode=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aircode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("abc").equals("0")) {
					msg = "0";
				} else {
					msg = "1";
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			msg = "error";
		}
		model.addAttribute("msg", msg);
		return null;
	}

	//항공코드 수정
	@GetMapping("/air_modifycode.do")
	public String modifyCode(@RequestParam(defaultValue = "", required = false) String aidx, Model model) {
		this.script = "";
		if (aidx.equals("") || aidx == "") {
			this.script = "<script>"
					+ "alert('정상적인 접근이 아닙니다.');"
					+ "location.href='./air_list.do';"
					+ "</script>";
		} else {
			try {
				this.se = sqlSessionFactory.openSession();
				AirCodeVo ac = this.se.selectOne("admin.mapper.NoticeMapper.airCodeModify", aidx);
				ArrayList<Object> adata = new ArrayList<Object>();
				adata.add(ac.getAidx()); //0
				adata.add(ac.getAirplane());
				adata.add(ac.getAirname());
				adata.add(ac.getAircode());
				adata.add(ac.getAirflight());
				adata.add(ac.getAiruse());
				adata.add(ac.getAirdate()); //6
				model.addAttribute("air", adata);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Database 접속 오류 발생");
			} finally {
				this.se.close();
			}
		}
		model.addAttribute("script", this.script);
		return "admin_category_modify";
	}

	//항공코드 수정완료
	@PostMapping("/air_code_modifyok.do")
	public String airCodeModifyOk(@ModelAttribute("board") AirCodeVo aircodevo, HttpServletRequest req, Model model) {
		this.script = "";
        System.out.println(aircodevo.getAiruse());
		try {
			this.se = sqlSessionFactory.openSession();
			int result = this.se.update("admin.mapper.NoticeMapper.airCodeModifyOk", aircodevo);
			if (result > 0) {
				this.script = "<script>"
						+ "alert('정상적으로 항공코드가 수정 되었습니다.');"
						+ "location.href='./air_list.do';"
						+ "</script>";
			} else {
				this.script = "<script>"
						+ "alert('데이터에 오류로 인하여 수정이 되지 않았습니다.');"
						+ "history.go(-1);"
						+ "</script>";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database 접속 오류!!!!!!!!");
		} finally {
			this.se.close();
		}
		model.addAttribute("script", this.script);
		return "air_code_modifyok";
	}

	// 항공코드 삭제페이지
	@GetMapping("/category_deleteok.do")
	public String categoryDeleteok(@RequestParam(defaultValue = "", required = false) String aidx, Model model) {
		if (aidx.equals("") || aidx == null) { 
			String script = "<script>" +
					"alert('정상적인 접근이 아닙니다.');" +
					"location.href='./air_list.do';" +
					"</script>";
			model.addAttribute("script", script);
			return "script_page";
		}
		try {
			SqlSession se = sqlSessionFactory.openSession();
			int result = se.delete("admin.mapper.NoticeMapper.airCodeDelete", aidx);
			if (result > 0) {
				String script = "<script>" +
						"alert('정상적으로 게시물이 삭제되었습니다.');" +
						"location.href='./air_list.do';" +
						"</script>";
				model.addAttribute("script", script);
				se.commit();
			} else {
				String script = "<script>" +
						"alert('데이터가 비정상으로 활용되었습니다.');" +
						"location.href='./air_list.do';" +
						"</script>";
				model.addAttribute("script", script);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database 접속 오류!!!!");
		}
		return "script_page";
	}


	// 항공코드 복수삭제
	@GetMapping("/category_deleteokay.do")
	public String categoryDeleteOkay(@RequestParam String aidx[], Model model) {
		if (aidx.equals("") || aidx == null) { 
			String script = "<script>" +
					"alert('정상적인 접근이 아닙니다.');" +
					"location.href='./air_list.do';" +
					"</script>";
			model.addAttribute("script", script);
			return "script_page";
		}
		try {
			SqlSession se = sqlSessionFactory.openSession();
			int ea = aidx.length;
			int w = 0;
			int result = 0;
			while (ea > w) {
				result = se.delete("admin.mapper.NoticeMapper.airCodeDelete", aidx[w]);

				w++;
			}
			if (result > 0) {
				String script = "<script>" +
						"alert('정상적으로 게시물이 삭제되었습니다.');" +
						"location.href='./air_list.do';" +
						"</script>";
				model.addAttribute("script", script);
				se.commit();
			} else {
				String script = "<script>" +
						"alert('데이터가 비정상으로 활용되었습니다.');" +
						"location.href='./air_list.do';" +
						"</script>";
				model.addAttribute("script", script);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database 접속 오류!!!!");
		}


		return "category_deleteokay";
	}

	//예매리스트 취소
	@GetMapping("/pay_deleteok.do")
	public String payDelete(@RequestParam(defaultValue = "", required = false) String aidx, Model model) {
		if (aidx.equals("") || aidx == "") { 
			this.script = "<script>"
					+ "alert('정상적인 접근이 아닙니다.');"
					+ "location.href='./ticketing_list.do';"
					+ "</script>";
			
		} else { 
			try {

				this.se = sqlSessionFactory.openSession();
				this.script = "";
				System.out.println("testtest");
				int result = this.se.delete("admin.mapper.NoticeMapper.payDelete", aidx);
				if (result > 0) {
					this.script = "<script>"
							+ "alert('정상적으로 예매 취소 되었습니다.');"
							+ "location.href='./ticketing_list.do';"
							+ "</script>";
				} else {
					this.script = "<script>"
							+ "alert('데이터가 비정상으로 활용 되었습니다.');"
							+ "location.href='./ticketing_list.do';"
							+ "</script>";
				}

			} catch (Exception e) {
				System.out.println("Database 접속 오류!");
			} finally {
				this.se.close();
			}
		}
		model.addAttribute("script", this.script);
		return "pay_deleteok";
	}
}
