package model.dto;

public class CartDTO {
	
	private int CID;
	private String MID;
	private int PID;
	private int cQty;
	private String pName;
	private String searchCondition;
	private int sellingPrice;
	private String imagePath;
	
	public int getCID() {
		return CID;
	}
	public void setCID(int cID) {
		CID = cID;
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
	public int getcQty() {
		return cQty;
	}
	public void setcQty(int cQty) {
		this.cQty = cQty;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "CartDTO [CID=" + CID + ", MID=" + MID + ", PID=" + PID + ", cQty=" + cQty + ", pName=" + pName
				+ ", searchCondition=" + searchCondition + ", sellingPrice=" + sellingPrice + ", imagePath=" + imagePath
				+ "]";
	}
	 
	

}
