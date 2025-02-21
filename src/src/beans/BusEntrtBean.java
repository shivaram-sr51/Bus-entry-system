package src.beans;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

@RequestScoped
public class BusEntrtBean {
	private String busType;
	private String busRemarks;
	private int busNo;
	private UploadedFile rcBook;
	private String rcBookName;
	private String rcBookType;
	private UploadedFile busImage;
	private String busImageName;
	private String busImageType;
	private String imagePath;

	private String rcBookPath;
	private String busDriver;
	private List<BusEntryIvewBean> consolidatedList;
	private List<BusEntryIvewBean> filteredList;
	private BusEntryIvewBean busEntryIvewBean;
	
	public BusEntryIvewBean getBusEntryIvewBean() {
		return busEntryIvewBean;
	}
	public void setBusEntryIvewBean(BusEntryIvewBean busEntryIvewBean) {
		this.busEntryIvewBean = busEntryIvewBean;
	}
	
	
	public List<BusEntryIvewBean> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<BusEntryIvewBean> filteredList) {
		this.filteredList = filteredList;
	}

	public List<BusEntryIvewBean> getConsolidatedList() {
		return consolidatedList;
	}

	public void setConsolidatedList(List<BusEntryIvewBean> consolidatedList) {
		this.consolidatedList = consolidatedList;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getRcBookPath() {
		return rcBookPath;
	}

	public void setRcBookPath(String rcBookPath) {
		this.rcBookPath = rcBookPath;
	}

	public void dmlOperations(String request) {
		System.out.println(" Request =>" + request);
		if ("N".equals(request)) {
			if (getBusType() == null || getBusType().trim().length() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Bus Type is Manatory."));
				return;
			}
			System.out.println("getRcBookPath() " + getRcBookPath());
			/*
			 * if(getRcBookPath() == null || getRcBookPath().trim().length()
			 * <=0){ FacesContext.getCurrentInstance().addMessage(null, new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
			 * "RC Image is Manatory.")); return; }
			 * 
			 * System.out.println ("getImagePath() "+ getImagePath());
			 * if(getImagePath() == null || getImagePath().trim().length() <=0){
			 * FacesContext.getCurrentInstance().addMessage(null, new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
			 * "Bus Image is Manatory.")); return; }
			 */
			if (getBusRemarks() == null || getBusRemarks().trim().length() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Bus Comments / Remarks is Manatory."));
				return;
			}
			/* Starting Insert Block */
			try {

				// java.io.File f=new java.io.File(strRcBookName);
				// java.io.FileReader fr=new java.io.FileReader(f);

				PreparedStatement objPreparedStatement = getConnection()
						.prepareStatement("INSERT INTO BUSENTRY(BusNo,BusType, comments, drivername) "
								+ "	values(busentryseqno.nextVAL, ?, ?, ?)");
				objPreparedStatement.setString(1, getBusType().trim());
				objPreparedStatement.setString(2, getBusRemarks().trim());
				objPreparedStatement.setString(3, getBusDriver());
				int successCount = objPreparedStatement.executeUpdate();
				objPreparedStatement = getConnection().prepareStatement("SELECT MAX(BusNo) as BUSNUMBER from BUSENTRY");
				ResultSet objResultSet = objPreparedStatement.executeQuery();
				String busNUmber = null;
				if (objResultSet.next()) {
					busNUmber = objResultSet.getString("BUSNUMBER");
				}
				if (busNUmber != null && busNUmber.trim().length() > 0) {
					clearRecords();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"INFO", "New Bus Entry is completed as SUCCESS. Bus Number as follows :" + busNUmber));
					return;
				}

			} catch (Exception objEx) {
				objEx.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to Save Your Records"));
				return;
			}
			/* Ending Insert Block */
			setBusDriver(null);
			setBusType(null);
			setBusRemarks(null);
		}
	}

	public void uploadRcBook() {
		setRcBookPath(null);
		if (getRcBook() != null && getRcBook().getSize() > 0) {
			System.out.println(" This is UploadRcBook Alert ");
			String filePath = System.getProperty("user.dir") + "\\" + getRcBook().getFileName();
			System.out.println(" filePath => " + filePath);
			setRcBookPath(filePath);
			System.out.println(" getRcBookPath() = " + getRcBookPath());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "RC Book is Uploaded. Please contiue "));
			return;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "RC Book is Upload is Manatory."));
			return;
		}
	}

	public void uploadBusImage() {
		setImagePath(null);
		if (getBusImage() != null && getBusImage().getSize() > 0) {
			System.out.println(" This is uploadBusImage Alert ");
			String filePath = System.getProperty("user.dir") + "\\" + getBusImage().getFileName();
			System.out.println(" filePath => " + filePath);
			setImagePath(filePath);
			System.out.println(" getRcBookPath() = " + getRcBookPath());
			System.out.println(" setImagePath() = " + getImagePath());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Bus Image is Uploaded. Please contiue "));
			return;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Bus Image is Upload is Manatory."));
			return;
		}

	}

	public void clearRecords() {
		setBusType(null);
		setBusDriver(null);
		setBusType(null);
		setBusRemarks(null);
		setBusNo(0);
		consolidatedList = new ArrayList();
		filteredList = new ArrayList();
	}

	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection objConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
				"disys");
		return objConnection;
	}
 public void searchEntry(){
 try{
	/* if(getBusNo() <= 0 ){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Enter the Bus Number to Search."));
			return;	 
	 }*/
	 StringBuilder objStringBuilder = new StringBuilder(256);
	 objStringBuilder.append(" SELECT BUSNO, BUSTYPE, COMMENTS, ENTRYDATE, DRIVERNAME FROM BUSENTRY");
	 if(getBusNo() >0 ){
		 objStringBuilder.append(" WHERE BUSNO = ").append(getBusNo());
		 
	 }
	String finalQuery = objStringBuilder.toString();
	System.out.println(" final Query => "+ finalQuery);
	PreparedStatement objPreparedStaement = getConnection().prepareStatement(finalQuery);
	ResultSet objResultSet = objPreparedStaement.executeQuery();
	BusEntryIvewBean objBusEntryIvewBean = null;
	consolidatedList =  new ArrayList();
	while(objResultSet.next()){
		objBusEntryIvewBean = new BusEntryIvewBean();
        objBusEntryIvewBean.setBusDriver(objResultSet.getString("DRIVERNAME"));
        objBusEntryIvewBean.setBusNo(objResultSet.getInt("BUSNO"));
        objBusEntryIvewBean.setBusRemarks(objResultSet.getString("COMMENTS"));
        objBusEntryIvewBean.setBusType(objResultSet.getString("BUSTYPE"));
        objBusEntryIvewBean.setEntryDate(objResultSet.getString("ENTRYDATE"));
        consolidatedList.add(objBusEntryIvewBean);
	}
	if(consolidatedList.size() == 0 ){
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Records are not Available."));
		return;
	}
	 
 }catch(Exception objEx){
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Unable to Search Bus Entry."));
		return; 
 }
 }
	public void dmlOpearions(BusEntryIvewBean busEntryIvewBean) {
		this.busEntryIvewBean = new BusEntryIvewBean(busEntryIvewBean);

			
	} 
}
