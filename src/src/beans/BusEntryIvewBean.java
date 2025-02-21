package src.beans;

import org.primefaces.model.UploadedFile;

public class BusEntryIvewBean {
	private String busType;
	private String busRemarks;
	private int busNo;
	private UploadedFile rcBook;
	private String rcBookName;
	private String rcBookType;
	private UploadedFile busImage;
	private String busImageName;
	private String busImageType;
	private String busDriver;
	private String entryDate;
	public BusEntryIvewBean(){
		
	}
	public BusEntryIvewBean(BusEntryIvewBean busEntryIvewBean )
			{
		this.busType = busEntryIvewBean.busType;
		this.busDriver = busEntryIvewBean.busDriver;
		this.busNo = busEntryIvewBean.busNo;
		this.busRemarks= busEntryIvewBean.busRemarks;
		
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getBusDriver() {
		return busDriver;
	}
	public void setBusDriver(String busDriver) {
		this.busDriver = busDriver;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getBusRemarks() {
		return busRemarks;
	}
	public void setBusRemarks(String busRemarks) {
		this.busRemarks = busRemarks;
	}
	public int getBusNo() {
		return busNo;
	}
	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}
	public UploadedFile getRcBook() {
		return rcBook;
	}
	public void setRcBook(UploadedFile rcBook) {
		this.rcBook = rcBook;
	}
	public String getRcBookName() {
		return rcBookName;
	}
	public void setRcBookName(String rcBookName) {
		this.rcBookName = rcBookName;
	}
	public String getRcBookType() {
		return rcBookType;
	}
	public void setRcBookType(String rcBookType) {
		this.rcBookType = rcBookType;
	}
	public UploadedFile getBusImage() {
		return busImage;
	}
	public void setBusImage(UploadedFile busImage) {
		this.busImage = busImage;
	}
	public String getBusImageName() {
		return busImageName;
	}
	public void setBusImageName(String busImageName) {
		this.busImageName = busImageName;
	}
	public String getBusImageType() {
		return busImageType;
	}
	public void setBusImageType(String busImageType) {
		this.busImageType = busImageType;
	}
	
	
}
