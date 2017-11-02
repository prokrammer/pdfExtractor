package com.kch.pdfEx;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.MissingOperandException;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.util.StopWatch;

public class PdfImageCounter extends PDFStreamEngine {
    protected int documentImageCount = 0;

    public int getDocumentImageCount() {
        return documentImageCount;
    }

    public PdfImageCounter() {
        addOperator(new OperatorProcessor() {
            @Override
            public void process(Operator operator, List<COSBase> arguments) throws IOException {
                if (arguments.size() < 1) {
                    throw new MissingOperandException(operator, arguments);
                }
                if (isImage(arguments.get(0))) {
                    documentImageCount++;
                }
            }

            protected Boolean isImage(COSBase base) {
                return (base instanceof COSName) &&
                        context.getResources().isImageXObject((COSName)base);
            }

            @Override
            public String getName() {
                return "Do";
            }
        });
    }
    protected int countImagesWithProcessor(PDDocument pdf) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        PdfImageCounter counter = new PdfImageCounter();
        for (PDPage pdPage : pdf.getPages()) {
        	
            counter.processPage(pdPage);
        }

        stopWatch.stop();
        int imageCount = counter.getDocumentImageCount();
        System.err.println("Images counted: time={}s,imageCount={}"+ (stopWatch.getTotalTimeMillis() / 1000) + imageCount);
        return imageCount;
    }
}