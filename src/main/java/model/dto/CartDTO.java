package model.dto;

public class CartDTO {
	
	private int cid;
	private String mid;
	private int pid;
	private int cQty;
	
	private String pName;
	private String searchCondition;
	private int sellingPrice;
	private String imagePath;
	 
	 
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
		return "CartDTO [cid=" + cid + ", mid=" + mid + ", pid=" + pid + ", cQty=" + cQty + ", pName=" + pName
				+ ", searchCondition=" + searchCondition + ", sellingPrice=" + sellingPrice + ", imagePath=" + imagePath
				+ "]";
	}
	
	
	
	
	
	

}
