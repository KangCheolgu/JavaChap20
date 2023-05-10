package ch20.sec09.exam02;

import java.sql.Blob;
import java.sql.Date;
import java.util.Objects;

public class Board {
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private String bfilename;
	private Blob bfiledata;
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
	public String getBfilename() {
		return bfilename;
	}
	public Blob getBfiledata() {
		return bfiledata;
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
	public void setBfilename(String bfilename) {
		this.bfilename = bfilename;
	}
	public void setBfiledata(Blob bfiledate) {
		this.bfiledata = bfiledate;
	}
	
	
	public Board() {
		
	}
	public Board(int bno, String btitle, String bcontent, String bwriter, Date bdate, String bfilename,
			Blob bfiledata) {
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bdate = bdate;
		this.bfilename = bfilename;
		this.bfiledata = bfiledata;
	}
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter=" + bwriter
				+ ", bdate=" + bdate + ", bfilename=" + bfilename + ", bfiledata=" + bfiledata + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(bcontent, bdate, bfiledata, bfilename, bno, btitle, bwriter);
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
		return Objects.equals(bcontent, other.bcontent) && Objects.equals(bdate, other.bdate)
				&& Objects.equals(bfiledata, other.bfiledata) && Objects.equals(bfilename, other.bfilename)
				&& bno == other.bno && Objects.equals(btitle, other.btitle) && Objects.equals(bwriter, other.bwriter);
	}
	
	
	
}
