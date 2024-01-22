package model.dto;

import java.sql.Timestamp;

public class ReviewDTO {
    private int RID;
    private String MID;
    private int BID;
    private int score;
    private String contents;
    private Timestamp createTime;
    private String searchCondition;
    // 조인
    private int PID;
    private String pNAME;
    private String mName;
    
    
	public int getRID() {
		return RID;
	}
	public void setRID(int rID) {
		RID = rID;
	}
	public String getMID() {
		return MID;
	}
	public void setMID(String mID) {
		MID = mID;
	}
	public int getBID() {
		return BID;
	}
	public void setBID(int bID) {
		BID = bID;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public String getpNAME() {
		return pNAME;
	}
	public void setpNAME(String pNAME) {
		this.pNAME = pNAME;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	@Override
	public String toString() {
		return "ReviewDTO [RID=" + RID + ", MID=" + MID + ", BID=" + BID + ", score=" + score + ", contents=" + contents
				+ ", createTime=" + createTime + ", searchCondition=" + searchCondition + ", PID=" + PID + ", pNAME="
				+ pNAME + ", mName=" + mName + "]";
	}
    

	
}