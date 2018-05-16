package com.cipherscriptdevs.thedocxreader;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cipherscriptdevs.Adapters.RecyclerViewAdapterFileList;
import com.cipherscriptdevs.Utils.Utils;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDocX extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_x, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerViewAdapterFileList adapterFileList = new RecyclerViewAdapterFileList(Utils.docXFilePaths,R.drawable.ic_docx,getContext(),"docx");
        RecyclerView myView = view.findViewById(R.id.recyclerView);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapterFileList);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);
    }
}
