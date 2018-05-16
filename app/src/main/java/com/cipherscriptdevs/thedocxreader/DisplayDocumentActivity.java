package com.cipherscriptdevs.thedocxreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.cipherscriptdevs.Utils.Utils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;

import java.io.File;

public class DisplayDocumentActivity extends AppCompatActivity{

    private PDFView pdfView;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_doc);
        pdfView = findViewById(R.id.pdfView);
        Intent intent = getIntent();
        webView = findViewById(R.id.webView);
        displayDoc(intent.getIntExtra("selectedPosition", 0), intent.getStringExtra("fileType"));
    }

    private void displayDoc(final int pos, String file) {
        switch (file){
            case "docx":
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String inputPath = Utils.docXFilePaths.get(pos).getAbsolutePath();
                        Document document = null;
                        try {
                            document = new Document(inputPath);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "error while loading", Toast.LENGTH_SHORT).show();
                        }
                        String outputPath = inputPath.substring(0, inputPath.lastIndexOf('.'))+".html";

                        assert document != null;
                        try {
                            document.save(outputPath, SaveFormat.HTML);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        WebSettings webSetting = webView.getSettings();
                        webSetting.setBuiltInZoomControls(false);
                        webSetting.setJavaScriptEnabled(true);

                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl(outputPath);
                    }
                },1000);
                            break;
            case "pdf" :
                pdfView.fromFile(Utils.pdfFilePaths.get(pos).getAbsoluteFile())
                        .enableSwipe(true)
                        .onError(new OnErrorListener() {
                            @Override
                            public void onError(Throwable t) {
                                Toast.makeText(getApplicationContext(),"Error while loading",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onPageError(new OnPageErrorListener() {
                            @Override
                            public void onPageError(int page, Throwable t) {
                                Toast.makeText(getApplicationContext(),"Error while page loading",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .enableAnnotationRendering(false)
                        .enableAntialiasing(true)
                        .load();
                break;
            default:
                break;
        }
    }
}
