package admin.dto;

import lombok.Getter;
import lombok.Setter;

public class ApproveAdminDto {

	@Getter
	@Setter
	public static class Request {
		private int midx;

		public int getMidx() {
			return midx;
		}

		public void setMidx(int midx) {
			this.midx = midx;
		}
	}
}