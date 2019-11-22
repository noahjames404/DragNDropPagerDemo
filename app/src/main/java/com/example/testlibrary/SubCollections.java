package com.example.testlibrary;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dndp.DND.DNDItem;
import com.example.dndp.DND.DNDUtils;
import com.example.dndp.DND.IDNDPager;
import com.example.dndp.Fragment.FCollectionAdapter;
import com.example.dndp.Fragment.FPage;

import java.util.ArrayList;
import java.util.List;

public class SubCollections extends FCollectionAdapter {

    List<DNDItem> item_list = new ArrayList<>();

    public SubCollections(@NonNull FragmentManager fm, int row_num, int col_num, List<DNDItem> item_list, IDNDPager.AutoSwipe auto_swipe) {
        super(fm, row_num, col_num, item_list, auto_swipe);
        this.item_list = item_list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            Log.d(TAG, "getItem: wwwwww" );
            DNDUtils.resetItems(item_list);
        }
        return super.getItem(position);
    }

    
}
