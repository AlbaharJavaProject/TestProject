package com.db2.sfdc.workorder;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Entity
public class WorkOrderCost{
	
	String WONO;
	String IVDT8; //Invoice Date
	String LABORCOST;
	String MISCCOST;
	String LABORSELL;
	String MISCSELL;
	String PARTSSELL;
	public String getWONO() {
		return WONO;
	}
	public void setWONO(String wONO) {
		WONO = wONO;
	}
	public String getIVDT8() {
		return IVDT8;
	}
	public void setIVDT8(String iVDT8) {
		IVDT8 = iVDT8;
	}
	public String getLABORCOST() {
		return LABORCOST;
	}
	public void setLABORCOST(String lABORCOST) {
		LABORCOST = lABORCOST;
	}
	public String getMISCCOST() {
		return MISCCOST;
	}
	public void setMISCCOST(String mISCCOST) {
		MISCCOST = mISCCOST;
	}
	public String getLABORSELL() {
		return LABORSELL;
	}
	public void setLABORSELL(String lABORSELL) {
		LABORSELL = lABORSELL;
	}
	public String getMISCSELL() {
		return MISCSELL;
	}
	public void setMISCSELL(String mISCSELL) {
		MISCSELL = mISCSELL;
	}
	public String getPARTSSELL() {
		return PARTSSELL;
	}
	public void setPARTSSELL(String pARTSSELL) {
		PARTSSELL = pARTSSELL;
	}
	
	
		
		

}
