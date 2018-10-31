package com.skx.tomike.activity;

import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.skx.tomike.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenericTestActivity extends SkxBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_test);

        initializeData();
    }

    @Override
    public void initializeData() {
        super.initializeData();


        List<String> strings = new ArrayList<String>();
//        strings.add(new Integer((42)));
//        unsafeAdd(strings, new Integer(42));
        String s = strings.get(0);
        Log.e("a","s=" + s);


//        List<String> strings = new ArrayList<>();
//        strings.add("aa");
//        String s = strings.get(0);
//
//
//        List<Object> O = new ArrayList<>();
//        O.add("aa");
//        String O1 = strings.get(0);

        Set<PdfRenderer.Page> exaltation = new HashSet<>();


        Object[] o = new Long[1];
        o[0] = "111";

//        List<Object> array = new ArrayList<Long>();

    }

    private static void unsafeAdd(List<Object> list, Object o) {
        list.add(o);
    }
}
