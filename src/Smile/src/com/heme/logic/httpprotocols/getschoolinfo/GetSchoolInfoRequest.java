package com.heme.logic.httpprotocols.getschoolinfo;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.RegGetSchoolReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class GetSchoolInfoRequest extends BaseBusinessRequest {
	
	RegGetSchoolReq.Builder mRegGetSchoolReqBuilder;
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mRegGetSchoolReqBuilder.setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mRegGetSchoolReqBuilder = RegGetSchoolReq.newBuilder();
		
	}
	
	//省、市、县
	public void setArea(AreaInfo areaInfo)
	{
		mRegGetSchoolReqBuilder.setProvinceCode(areaInfo.getmProvinceCode());
		mRegGetSchoolReqBuilder.setCityCode(areaInfo.getmCityCode());
		mRegGetSchoolReqBuilder.setCountyCode(areaInfo.getmCountryCode());
		mDataSvrProtoBuilder.setRegGetSchoolReqInfo(mRegGetSchoolReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.RegGetSchool);
		super.setBody(mRegGetSchoolReqBuilder.build().toByteString());
	}
}
