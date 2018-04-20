package com.fit.i_kit.Fragments;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fit.i_kit.R;
import com.joanzapata.pdfview.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.fit.i_kit.R.string.app_name;

public class StudymaterialFragment extends Fragment  {
PDFView pdfView;
    public StudymaterialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_studymaterial, container, false);
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String course = sharedpreferences.getString("title", "I-KIT");
        String fname = course.toLowerCase()+"_tutorial.pdf";

pdfView=view.findViewById(R.id.pdfview);
        pdfView.fromAsset(fname)
                .defaultPage(1)
                .showMinimap(false)
                .swipeVertical(true)
                 .load();

        return view;

    }


}


