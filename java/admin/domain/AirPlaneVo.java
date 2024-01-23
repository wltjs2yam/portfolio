package admin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirPlaneVo {
	private String pidx;
	private String aidx;
	private String aircode;
	private String start_point;
	private String end_point;
	private String seat_text;
	private String seat;
	private String air_use;
	private String indate;
}
