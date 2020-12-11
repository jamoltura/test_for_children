package com.example.test_for_children.classes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;

import com.example.test_for_children.Test.ControlTest;
import com.example.test_for_children.Test.OnlyTest;
import com.example.test_for_children.Test.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyPrinter extends PrintDocumentAdapter {

    private static final String TAG = "myLogs";

    Context context;
    private int pageHeight;
    private int pageWidth;
    public PdfDocument myPdfDocument;
    public int totalpages = 1;
    private Test test;


    public MyPrinter(Context context, Test test) {
        Log.d(TAG,"MyPrintDocumentAdapter Constructor");
        this.context = context;
        this.test = test;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal, LayoutResultCallback callback,
                         Bundle extras) {

        myPdfDocument = new PrintedPdfDocument(context, newAttributes);

        pageHeight =
                newAttributes.getMediaSize().getHeightMils()/1000 * 72;
        pageWidth =
                newAttributes.getMediaSize().getWidthMils()/1000 * 72;

        if (cancellationSignal.isCanceled() ) {
            callback.onLayoutCancelled();
            return;
        }

        if (totalpages > 0) {
            PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                    .Builder("test.pdf")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(totalpages);

            PrintDocumentInfo info = builder.build();
            callback.onLayoutFinished(info, true);
        } else {
            callback.onLayoutFailed("Page count is zero.");
        }

    }

    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination,
                        CancellationSignal cancellationSignal, WriteResultCallback callback) {


        Log.d(TAG,"MyPrintDocumentAdapter onWrite ");

        for (int i = 0; i < totalpages; i++) {
            if (pageInRange(pages, i))
            {
                PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                        pageHeight, i).create();

                PdfDocument.Page page =
                        myPdfDocument.startPage(newPage);

                if (cancellationSignal.isCanceled()) {
                    callback.onWriteCancelled();
                    myPdfDocument.close();
                    myPdfDocument = null;
                    return;
                }
                drawPage(page);
                myPdfDocument.finishPage(page);
            }
        }

        try {
            myPdfDocument.writeTo(new FileOutputStream(
                    destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            myPdfDocument.close();
            myPdfDocument = null;
        }

        callback.onWriteFinished(pages);
    }

    private boolean pageInRange(PageRange[] pageRanges, int page)
    {
        for (int i = 0; i<pageRanges.length; i++)
        {
            if ((page >= pageRanges[i].getStart()) &&
                    (page <= pageRanges[i].getEnd()))
                return true;
        }
        return false;
    }

    private void drawPage(PdfDocument.Page page) {
        Canvas canvas = page.getCanvas();

        int titleBaseLine = 54;
        int leftMargin = 54;

        int OFSET_GlOBAL_Y = 12;

        Paint paint = new Paint();

        paint.setTypeface(Typeface.DEFAULT);


        paint.setColor(Color.BLACK);
        paint.setTextSize(28);
        canvas.drawText(
                "Intel Soft",
                leftMargin,
                titleBaseLine,
                paint);

        paint.setTextSize(13);

        ArrayList<String> list;

        int _pageWidth = page.getInfo().getPageWidth();

        String value = "Результаты тестов";
        list = getList(paint, value, _pageWidth, leftMargin);
        titleBaseLine = DrawText(canvas, paint, list, leftMargin, titleBaseLine, 20);

        paint.setTextSize(11);
        paint.setFakeBoldText(true);
        value = "Правильных ответов";
        paint.setColor(Color.GREEN);
        list = getList(paint, value, _pageWidth, leftMargin);
        titleBaseLine = DrawText(canvas, paint, list, leftMargin, titleBaseLine, 18);


        for (int i = 0; i < ControlTest.COUNT_TESTS; i++){

            OnlyTest onlyTest = test.getOnlyTests()[i];

            if (onlyTest.getIsAnswer() == onlyTest.getAnswerForUser()) {

                paint.setTextSize(9);
                String defaultValue = (i + 1) +" Вопрос:  ";

                Rect rect = new Rect();
                paint.getTextBounds(defaultValue, 0, defaultValue.length(), rect);
                int len = rect.right + 2;

                paint.setColor(Color.BLACK);
                paint.setFakeBoldText(true);
                canvas.drawText(defaultValue, leftMargin, titleBaseLine + 10 + OFSET_GlOBAL_Y, paint);

                paint.setFakeBoldText(false);
                String mQuestion = onlyTest.getQuestion();

                list = getList(paint, mQuestion, _pageWidth, leftMargin + len);
                titleBaseLine = DrawText(canvas, paint, list, leftMargin + len, titleBaseLine + 10, OFSET_GlOBAL_Y);

                defaultValue = "Вы ответили:";
                paint.setFakeBoldText(true);
                paint.setColor(Color.GREEN);
                canvas.drawText(defaultValue, leftMargin, titleBaseLine + OFSET_GlOBAL_Y, paint);

                rect = new Rect();
                paint.getTextBounds(defaultValue, 0, defaultValue.length(), rect);
                len = rect.right + 2;

                paint.setFakeBoldText(false);
                paint.setColor(Color.BLACK);
                String mAnswer = onlyTest.getAnswers()[onlyTest.getIsAnswer()];

                list = getList(paint, mAnswer, _pageWidth, leftMargin + len);
                titleBaseLine = DrawText(canvas, paint, list, leftMargin + len, titleBaseLine, OFSET_GlOBAL_Y);
            }
        }

        paint.setTextSize(11);
        value = "Неправильных ответов";
        paint.setColor(Color.RED);
        paint.setFakeBoldText(true);
        list = getList(paint, value, _pageWidth, leftMargin);
        titleBaseLine = DrawText(canvas, paint, list, leftMargin, titleBaseLine, 18);

        for (int i = 0; i < ControlTest.COUNT_TESTS; i++){

            OnlyTest onlyTest = test.getOnlyTests()[i];

            if (onlyTest.getIsAnswer() != onlyTest.getAnswerForUser()) {

                paint.setTextSize(9);
                String defaultValue = (i + 1) +" Вопрос:  ";
                paint.setColor(Color.BLACK);
                paint.setFakeBoldText(true);
                canvas.drawText(defaultValue, leftMargin, titleBaseLine + 10 + OFSET_GlOBAL_Y, paint);

                Rect rect = new Rect();
                paint.getTextBounds(defaultValue, 0, defaultValue.length(), rect);
                int len = rect.right + 2;

                paint.setFakeBoldText(false);
                String mQuestion = onlyTest.getQuestion();

                list = getList(paint, mQuestion, _pageWidth, leftMargin + len);
                titleBaseLine = DrawText(canvas, paint, list, leftMargin + len, titleBaseLine + 10, OFSET_GlOBAL_Y);

                defaultValue = "Вы ответили:";
                paint.setFakeBoldText(true);
                paint.setColor(Color.RED);
                canvas.drawText(defaultValue, leftMargin, titleBaseLine + OFSET_GlOBAL_Y, paint);

                rect = new Rect();
                paint.getTextBounds(defaultValue, 0, defaultValue.length(), rect);
                len = rect.right + 2;

                paint.setFakeBoldText(false);
                paint.setColor(Color.BLACK);
                String mAnswer = onlyTest.getAnswers()[onlyTest.getAnswerForUser()];

                list = getList(paint, mAnswer, _pageWidth, leftMargin + len);
                titleBaseLine = DrawText(canvas, paint, list, leftMargin + len, titleBaseLine, OFSET_GlOBAL_Y);

                defaultValue = "Правильный ответ:";
                paint.setFakeBoldText(true);
                paint.setColor(Color.GREEN);
                canvas.drawText(defaultValue, leftMargin, titleBaseLine + OFSET_GlOBAL_Y, paint);

                rect = new Rect();
                paint.getTextBounds(defaultValue, 0, defaultValue.length(), rect);
                len = rect.right + 2;

                paint.setFakeBoldText(false);
                paint.setColor(Color.BLACK);
                String mAnswerFor = onlyTest.getAnswers()[onlyTest.getIsAnswer()];

                list = getList(paint, mAnswerFor, _pageWidth, leftMargin + len);
                titleBaseLine = DrawText(canvas, paint, list, leftMargin + len, titleBaseLine, OFSET_GlOBAL_Y);
            }
        }
    }

    private ArrayList<String> getList(Paint paint, String value, int pageWidth, int leftMargin){
        Rect rect = new Rect();
        paint.getTextBounds(value, 0, value.length(), rect);
        int valueLength = rect.right;

        int width = pageWidth - leftMargin - 27;
        ArrayList<String> list = new ArrayList<>();

        if (width < valueLength){
            String[] str = value.split(" ");
            int strCount = str.length;

            if (strCount > 0){
                String newLine = "";

                for (int i = 0; i < strCount; i++){

                    Rect rectLine = new Rect();
                    paint.getTextBounds(newLine, 0, newLine.length(), rectLine);
                    int lineLength = rectLine.right;

                    Rect rectStr = new Rect();
                    paint.getTextBounds(str[i], 0, str[i].length(), rectStr);
                    int strLength = rectStr.right;

                    if (width > (lineLength + strLength)) {

                            if (newLine.isEmpty()) {
                                newLine = newLine.concat(str[i]);
                            } else {
                                newLine = newLine.concat(" ").concat(str[i]);
                            }

                            if (i == strCount - 1){
                                list.add(newLine);
                            }
                    }else {
                        list.add(newLine);
                        newLine = "";
                    }
                }
            }
        }else {
            list.add(value);
        }
        return list;
    }

    private int DrawText(Canvas canvas, Paint paint, ArrayList<String> list, int leftMargin, int titleBaseLine, int ofset){
        int listCount = list.size();
        if (listCount > 0){
            for (int i = 0; i < listCount; i++){
                titleBaseLine = titleBaseLine + ofset;
                canvas.drawText(list.get(i), leftMargin, titleBaseLine, paint);
            }
        }
        return titleBaseLine;
    }

    /**
    private boolean isPageJumpTrue(OnlyTest onlyTest, int pageHeight, Paint paint, String value, int pageWidth, int leftMargin){

        ArrayList<String> listQuestion;
        ArrayList<String> listAnswerForUser;

        String mQuestion = onlyTest.getQuestion();
        String mAnswerForUser = onlyTest.getAnswers()[onlyTest.getAnswerForUser()];

        listQuestion = getList(paint, mQuestion, pageWidth, leftMargin);
        listAnswerForUser = getList(paint, mAnswerForUser, pageWidth, leftMargin);


        return false;
    }

    private boolean isPageJumpFalse(OnlyTest onlyTest, int pageHeight, Paint paint, String value, int pageWidth, int leftMargin){

        ArrayList<String> listQuestion;
        ArrayList<String> listAnswer;
        ArrayList<String> listAnswerForUser;

        String mQuestion = onlyTest.getQuestion();
        String mIsAnswer = onlyTest.getAnswers()[onlyTest.getIsAnswer()];
        String mAnswerForUser = onlyTest.getAnswers()[onlyTest.getAnswerForUser()];

        listQuestion = getList(paint, mQuestion, pageWidth, leftMargin);
        listAnswer = getList(paint, mIsAnswer, pageWidth, leftMargin);
        listAnswerForUser = getList(paint, mAnswerForUser, pageWidth, leftMargin);




        return false;
    }
     */
}
