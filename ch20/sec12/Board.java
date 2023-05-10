package ch20.sec12;

import java.sql.Date;
import java.util.Objects;

public class Board {
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	public int getBno() {
		return bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public String getBwriter() {
		return bwriter;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bcontent, bdate, bno, btitle, bwriter);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return Objects.equals(bcontent, other.bcontent) && Objects.equals(bdate, other.bdate) && bno == other.bno
				&& Objects.equals(btitle, other.btitle) && Objects.equals(bwriter, other.bwriter);
	}
	
	public Board() {
		
	}
	public Board(int bno, String btitle, String bcontent, String bwriter, Date bdate) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bdate = bdate;
	}
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter=" + bwriter
				+ ", bdate=" + bdate + "]";
	}
	
	
}
