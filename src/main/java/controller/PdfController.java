package controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class PdfController{
	//Crops away the unneccesary area of every page
	private static void cropPage(PDPage page){
		PDRectangle currentRect = page.getCropBox();
		PDRectangle rightwayup = new PDRectangle(currentRect.getWidth()/2, currentRect.getHeight()/2, currentRect.getWidth()/2, currentRect.getHeight()/2-150);
		PDRectangle upsidedown = new PDRectangle(0, 0, currentRect.getWidth()/2, currentRect.getHeight()/2-150);
		page.setCropBox(rightwayup);
		System.out.print("-");
	}
	
	//Resets the cropbox to contain the entire page.
	private static void uncropPage(PDPage page){
		PDRectangle upsidedown = new PDRectangle(0, 0, page.getCropBox().getWidth()*2, (page.getCropBox().getHeight()+150)*2);
		page.setCropBox(upsidedown);
		System.out.print("-");
	}
	
	public static void uncropPDF(PDDocument pdf){
		System.out.print("Uncropping PDF, Page [");
		for(int i = 0; i < pdf.getNumberOfPages(); i++){
			uncropPage(pdf.getPage(i));
		}
		System.out.println("]");
	}
	//Loops through the pdf pages and executes cropping on every one.
	public static void cropPDF(PDDocument pdf){
		System.out.print("Cropping PDF, Page [");
		for(int i = 0; i < pdf.getNumberOfPages(); i++){
			cropPage(pdf.getPage(i));
		}
		System.out.println("]");
	}
	
	public static PDDocument mergeWaybills(ArrayList<PDDocument> bills){
		PDDocument total = new PDDocument();
		for(PDDocument d : bills){
			total.addPage(d.getPage(0));
		}
		return total;
	}
	
	public static void checkRotation(){
		PDDocument doc = new PDDocument();
		doc.getPage(0);
	}
	
	//Saves the pdf to disk
	public static void savePDF(PDDocument pdf){
		savePDF(pdf,"cropped");
	}
	public static void savePDF(PDDocument pdf, String name){
		try{
			Date d = new Date();
			pdf.save("pdfout/"+d.getTime()+"-"+name+".pdf");
			System.out.println("PDF Saved...");
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
}
