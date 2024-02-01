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
    // 컬럼에 없는 멤버변수
    private int ancPID;
    private String ancPName;
    private String ancMName;
    private String ancEmail;
    private String ancCreateTime;
    
    
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
	public int getAncPID() {
		return ancPID;
	}
	public void setAncPID(int ancPID) {
		this.ancPID = ancPID;
	}
	public String getAncPName() {
		return ancPName;
	}
	public void setAncPName(String ancPName) {
		this.ancPName = ancPName;
	}
	public String getAncMName() {
		return ancMName;
	}
	public void setAncMName(String ancMName) {
		this.ancMName = ancMName;
	}
	public String getAncEmail() {
		return ancEmail;
	}
	public void setAncEmail(String ancEmail) {
		this.ancEmail = ancEmail;
	}
	
	public String getAncCreateTime() {
		return ancCreateTime;
	}
	public void setAncCreateTime(String ancCreateTime) {
		this.ancCreateTime = ancCreateTime;
	}
	@Override
	public String toString() {
		return "ReviewDTO [RID=" + RID + ", MID=" + MID + ", BID=" + BID + ", score=" + score + ", contents=" + contents
				+ ", createTime=" + createTime + ", searchCondition=" + searchCondition + ", ancPID=" + ancPID
				+ ", ancPName=" + ancPName + ", ancMName=" + ancMName + ", ancEmail=" + ancEmail + ", ancCreateTime="
				+ ancCreateTime + "]";
	}

    

}