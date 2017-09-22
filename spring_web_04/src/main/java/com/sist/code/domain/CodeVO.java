package com.sist.code.domain;

import com.sist.common.DTO;
/**
 * CodeVO
 * 코드테이블 
 * @author sist_
 *
 */
public class CodeVO extends DTO {
	private String mstCdId	 ;   //	마스터코드ID
	private String dtlCdId	 ;   //	상세코드_ID
	private String mstCdNm	 ;   //	마스터코드명
	private String dtlCdNm	 ;   //	상세코드명
	private String seq	     ;   //	정열순서
	private String supMstCdId;	 //	상위마스터코드ID
	private String useYn	 ;   //	사용여부
	
	public CodeVO() {
		
	}
	
	@Override
	public String toString() {
		return "CodeVO [mstCdId=" + mstCdId + ", dtlCdId=" + dtlCdId + ", mstCdNm=" + mstCdNm + ", dtlCdNm=" + dtlCdNm
				+ ", seq=" + seq + ", supMstCdId=" + supMstCdId + ", useYn=" + useYn + ", getNo()=" + getNo()
				+ ", getTotalNo()=" + getTotalNo() + ", getParam()=" + getParam() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getMstCdId() {
		return mstCdId;
	}
	public void setMstCdId(String mstCdId) {
		this.mstCdId = mstCdId;
	}
	public String getDtlCdId() {
		return dtlCdId;
	}
	public void setDtlCdId(String dtlCdId) {
		this.dtlCdId = dtlCdId;
	}
	public String getMstCdNm() {
		return mstCdNm;
	}
	public void setMstCdNm(String mstCdNm) {
		this.mstCdNm = mstCdNm;
	}
	public String getDtlCdNm() {
		return dtlCdNm;
	}
	public void setDtlCdNm(String dtlCdNm) {
		this.dtlCdNm = dtlCdNm;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSupMstCdId() {
		return supMstCdId;
	}
	public void setSupMstCdId(String supMstCdId) {
		this.supMstCdId = supMstCdId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public CodeVO(String mstCdId, String dtlCdId, String mstCdNm, String dtlCdNm, String seq, String supMstCdId,
			String useYn) {
		super();
		this.mstCdId = mstCdId;
		this.dtlCdId = dtlCdId;
		this.mstCdNm = mstCdNm;
		this.dtlCdNm = dtlCdNm;
		this.seq = seq;
		this.supMstCdId = supMstCdId;
		this.useYn = useYn;
	}
}
