package com.db2.sfdc.workorder;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Entity
public class WorkOrder {
	
	String WONO;
	String TERMCD;
	String PACC;
	String LBCC;
	String MCCC;
	String HDCGC1;
	String HDCGC2;
	String CURIND;
	String CPCCD;
	String TXCD1;
	String CPIDNO;
	String IDNO1;
	String IDCD;
	String EQMFPC;
	String HRMII;
	String DTPDL8;
	String LKNSMU;
	
	
	public String getWONO() {
		return WONO;
	}
	public void setWONO(String wONO) {
		WONO = wONO;
	}
	public String getTERMCD() {
		return TERMCD;
	}
	public void setTERMCD(String tERMCD) {
		TERMCD = tERMCD;
	}
	public String getPACC() {
		return PACC;
	}
	public void setPACC(String pACC) {
		PACC = pACC;
	}
	public String getLBCC() {
		return LBCC;
	}
	public void setLBCC(String lBCC) {
		LBCC = lBCC;
	}
	public String getMCCC() {
		return MCCC;
	}
	public void setMCCC(String mCCC) {
		MCCC = mCCC;
	}
	public String getHDCGC1() {
		return HDCGC1;
	}
	public void setHDCGC1(String hDCGC1) {
		HDCGC1 = hDCGC1;
	}
	public String getHDCGC2() {
		return HDCGC2;
	}
	public void setHDCGC2(String hDCGC2) {
		HDCGC2 = hDCGC2;
	}
	public String getCURIND() {
		return CURIND;
	}
	public void setCURIND(String cURIND) {
		CURIND = cURIND;
	}
	public String getCPCCD() {
		return CPCCD;
	}
	public void setCPCCD(String cPCCD) {
		CPCCD = cPCCD;
	}
	public String getTXCD1() {
		return TXCD1;
	}
	public void setTXCD1(String tXCD1) {
		TXCD1 = tXCD1;
	}
	public String getCPIDNO() {
		return CPIDNO;
	}
	public void setCPIDNO(String cPIDNO) {
		CPIDNO = cPIDNO;
	}
	public String getIDNO1() {
		return IDNO1;
	}
	public void setIDNO1(String iDNO1) {
		IDNO1 = iDNO1;
	}
	public String getIDCD() {
		return IDCD;
	}
	public void setIDCD(String iDCD) {
		IDCD = iDCD;
	}	
	public String getEQMFPC() {
		return EQMFPC;
	}
	public void setEQMFPC(String eQMFPC) {
		EQMFPC = eQMFPC;
	}
	public String getHRMII() {
		return HRMII;
	}
	public void setHRMII(String hRMII) {
		HRMII = hRMII;
	}	
	public String getDTPDL8() {
		return DTPDL8;
	}
	public void setDTPDL8(String dTPDL8) {
		DTPDL8 = dTPDL8;
	}
	public String getLKNSMU() {
		return LKNSMU;
	}
	public void setLKNSMU(String lKNSMU) {
		LKNSMU = lKNSMU;
	}
	
	

}
