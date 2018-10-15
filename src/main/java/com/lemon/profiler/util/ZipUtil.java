package com.lemon.profiler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;

public class ZipUtil {

	private static final Logger log = Logger.getLogger(ZipUtil.class);
	public void main(String file) {
		  try {
	        String fileZip = file;
	        byte[] buffer = new byte[1024];
	        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
	        ZipEntry zipEntry = zis.getNextEntry();
	        while(zipEntry != null){
	            String fileName = zipEntry.getName();
	            File newFile = new File("unzipTest/" + fileName);
	            FileOutputStream fos = new FileOutputStream(newFile);
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	                fos.write(buffer, 0, len);
	            }
	            fos.close();
	            zipEntry = zis.getNextEntry();
	        }
	        zis.closeEntry();
	        zis.close();
	    }catch(IOException e) {
	    	log.error(e.getMessage());
	    }
	}
}
