package model.dto;

import java.sql.Timestamp;

public class BuyInfoDTO {
    private int BID;
    private String MID;
    private int PID;
    private String CPID;
    private int orderNum;
    private String deliState;
    private int bQty;
    private int paymentPrice;
    private Timestamp buyTime;
    private String searchCondition;
	public int getBID() {
		return BID;
	}
	public void setBID(int bID) {
		BID = bID;
	}
	public String getMID() {
		return MID;
	}
	public void setMID(String mID) {
		MID = mID;
	}
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public String getCPID() {
		return CPID;
	}
	public void setCPID(String cPID) {
		CPID = cPID;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getDeliState() {
		return deliState;
	}
	public void setDeliState(String deliState) {
		this.deliState = deliState;
	}
	public int getbQty() {
		return bQty;
	}
	public void setbQty(int bQty) {
		this.bQty = bQty;
	}
	public int getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	public Timestamp getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	@Override
	public String toString() {
		return "BuyInfoDTO [BID=" + BID + ", MID=" + MID + ", PID=" + PID + ", CPID=" + CPID + ", orderNum=" + orderNum
				+ ", deliState=" + deliState + ", bQty=" + bQty + ", paymentPrice=" + paymentPrice + ", buyTime="
				+ buyTime + ", searchCondition=" + searchCondition + "]";
	}
    
    
    

}
