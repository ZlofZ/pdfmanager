package model;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PDF extends Document{
	private PDDocument PDF;
	private final String fileName;
	
	public PDF(PDDocument file, String fileName) throws IOException{
		this.PDF = file;
		this.fileName = fileName;
	}
	
	public PDF(File file, String fileName) throws IOException{
		PDF = PDDocument.load(file);
		this.fileName = fileName;
	}
	
	public PDF(String fileName){
		this.fileName = fileName;
	}
	
	public void setPDF(PDDocument PDF){
		this.PDF = PDF;
	}
	
	public PDDocument loadPDF(File file) throws IOException{
		this.PDF = PDDocument.load(file);
		return this.PDF;
	}
	
	public String getName(){
		return fileName;
	}
	
	public PDDocument getPDF(){
		return PDF;
	}
	
	public void savePDF(String path) throws IOException{
		this.PDF.save(path+fileName);
	}
	public void savePDF() throws IOException{
		this.PDF.save(fileName);
	}
}
