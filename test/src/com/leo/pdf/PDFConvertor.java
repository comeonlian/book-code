package com.leo.pdf;
/**
 * @description: 
 * @author lianliang
 * @date 2018/12/7 17:01
 */
public class PDFConvertor {

    public static void main(String[] args) throws Exception {
        String a = "D:/Temp/Dev/pdf/test.pdf";
        PDFBox pdfBox = new PDFBox();
        pdfToDoc(a);
    }
    
}
