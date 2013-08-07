package com.heme.logic.httpprotocols.push;

import com.google.protobuf.ByteString;
import com.heme.commonlogic.servermanager.BasePbRequest;

public class PushMsgResponse extends BasePbRequest
{
	public PushMsgResponse(ByteString bytes_context)
	{
		super();
		buildAccessReq(ByteString.EMPTY);
		setCmd(bytes_context.toString());
	}
}
