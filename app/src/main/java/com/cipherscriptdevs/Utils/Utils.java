package com.cipherscriptdevs.Utils;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<File> docXFilePaths = new ArrayList<>();
    public static ArrayList<File> pdfFilePaths = new ArrayList<>();
    public static ArrayList<File> pptFilePaths = new ArrayList<>();

    private Context _context;
    public Utils(Context context){
        this._context = context;
    }

    public void getFilePaths(File directory) {
        File FileList[] = directory.listFiles();
        if (FileList != null) {
            for (File aFileList : FileList) {
                if (aFileList.isDirectory()) {
                    getFilePaths(aFileList);
                } else {
                    if (aFileList.getName().endsWith(".docx")) {
                        docXFilePaths.add(aFileList);
                    }else if (aFileList.getName().endsWith(".pdf")){
                        pdfFilePaths.add(aFileList);
                    }else if (aFileList.getName().endsWith(".pptx")){
                        pptFilePaths.add(aFileList);
                    }
                }
            }
        }
    }
}
