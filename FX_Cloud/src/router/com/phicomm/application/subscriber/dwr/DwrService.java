package com.phicomm.application.subscriber.dwr;

import javax.inject.Inject;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.phicomm.application.subscriber.service.IAttachmentService;


@RemoteProxy(name="dwrService")
public class DwrService implements IDwrService{
	private IAttachmentService attachmentService;
	
	
	
	
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	@Inject
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@Override
	@RemoteMethod
	public void deleteAttach(int id) {
		attachmentService.delete(id);
	}

}
