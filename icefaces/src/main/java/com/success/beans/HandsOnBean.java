package com.success.beans;

import java.io.File;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;

@ManagedBean
@SessionScoped
public class HandsOnBean {

	private File uploadedFile = null;

	public void sampleListener(FileEntryEvent e) {
		System.out.println("Inside file upload listener " + e.getPhaseId());
		FileEntry fe = (FileEntry) e.getComponent();
		FileEntryResults results = fe.getResults();

		for (FileEntryResults.FileInfo i : results.getFiles()) {
			if (i.isSaved()) {
				uploadedFile = i.getFile();
			}
		}
	}

	public void submitAction() {
		System.out.println("Inside submit action");
		if(uploadedFile!=null){
			System.out.println("Hello " + uploadedFile.getAbsolutePath());
			System.out.println("Uploaded file name" + uploadedFile.getName());
			//call webservices and send the uploaded file... 
		}
	}

	public void submitActionIce() {
		System.out.println("Hello ice");
	}

	public void submitActionIceAE(ActionEvent ae) {

		System.out.println("Hello ae " + ae.getPhaseId());
	}
}
