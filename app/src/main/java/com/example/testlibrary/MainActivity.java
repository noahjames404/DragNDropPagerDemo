package com.example.testlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.dndp.DND.DNDButton;
import com.example.dndp.DND.DNDItem;
import com.example.dndp.DND.DNDPager;
import com.example.dndp.DND.DNDUtils;
import com.example.dndp.DND.IDNDPager;
import com.example.dndp.FCustomizePanel;
import com.example.dndp.Fragment.FCollectionAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {



    static final String TAG = "dndp";
    FCollectionAdapter adapter;
    ViewPager view_pager;
    DNDPager pager1,pager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: pow");


    }

    @Override
    protected void onStart() {
        super.onStart();
        updateViewPager();
        updatePager();
    }

    /**
     * View Pager Example
     * generates a drag n drop draggable views inside a set of relative layouts of a ViewPager.
     */
    void updateViewPager(){
        final List<DNDItem> view_pager_item_list = new ArrayList<>();

        view_pager = findViewById(R.id.view_pager);
        view_pager.setOffscreenPageLimit(1000);
        /**
         * create a list of DNDItems
         */
        view_pager_item_list.add(new DNDItem("Lion",null,Color.parseColor("#e67e22"),"1"));
        view_pager_item_list.add(new DNDItem("Wolf",null,Color.parseColor("#8e44ad"),"2"));
        view_pager_item_list.add(new DNDItem("Monkey",null,Color.parseColor("#e74c3c"),"3"));
        view_pager_item_list.add(new DNDItem("Wow",null,Color.parseColor("#95a5a6"),"4"));
        view_pager_item_list.add(new DNDItem("Sample",null,Color.parseColor("#d35400"),"5"));
        view_pager_item_list.add(new DNDItem("No",null,Color.parseColor("#bdc3c7"),"6"));
        view_pager_item_list.add(new DNDItem("Much Wow",null,Color.parseColor("#7f8c8d"),"7"));
        view_pager_item_list.add(new DNDItem("Yare Yare",null,Color.parseColor("#1abc9c"),"8"));
        view_pager_item_list.add(new DNDItem("Daze",null,Color.parseColor("#1abc9c"),"9"));
        view_pager_item_list.add(new DNDItem("HA",null,Color.parseColor("#1abc9c"),"10"));

        view_pager_item_list.add(new DNDItem("WA",null,Color.parseColor("#e67e22"),"11"));
        view_pager_item_list.add(new DNDItem("WA",null,Color.parseColor("#8e44ad"),"12"));
        view_pager_item_list.add(new DNDItem("DO",null,Color.parseColor("#e74c3c"),"13"));
        view_pager_item_list.add(new DNDItem("A",null,Color.parseColor("#95a5a6"),"14"));
        view_pager_item_list.add(new DNDItem("FLIP",null,Color.parseColor("#d35400"),"15"));
        view_pager_item_list.add(new DNDItem("Eipstein",null,Color.parseColor("#bdc3c7"),"16"));
        view_pager_item_list.add(new DNDItem("Didn't",null,Color.parseColor("#7f8c8d"),"17"));
        view_pager_item_list.add(new DNDItem("Kill",null,Color.parseColor("#1abc9c"),"18"));
        view_pager_item_list.add(new DNDItem("Himself",null,Color.parseColor("#1abc9c"),"19"));

        /**
         * The DNDItem uses a much complex constructor
         * cell_heigh_ratio & cell_width_ratio defines the size of the view
         * x & y are the coordinates of the view from a layout's row_num & col_num, setting the coordinates to -1 will automatically assign a new coordinates.
         * background_image can be null, if not the background_color is used instead.
         * page_num is used which page to preview the view.
         * tag are used in onclick listeners
         *
         * note: if both item has the same page_num & coordinates it is possible that they overlap.
         */
        view_pager_item_list.add(new DNDItem("hello",1,1,1,1,null,Color.parseColor("#1abc9c"),5,"20"));
        view_pager_item_list.add(new DNDItem("hello",1,1,1,1,null,Color.parseColor("#1abc9c"),1,"21"));

        adapter = new FCollectionAdapter(
                getSupportFragmentManager(),
                3,3,
                view_pager_item_list,
                DNDUtils.defaultAutoSwipe(view_pager)
        );

        /**
         * settings the editable to true, will enable views to be drag & customized
         */
        adapter.setEditable(true);

        /**
         * works only if editable is set to true.
         * Double click on fragment to customize, opens a fragment to customize properties
         */
        adapter.setCustomizeFragment(new IDNDPager.ItemView() {
            @Override
            public View onCustomize(DNDPager pager1, View view) {
                DNDButton btn = (DNDButton) view;
                FCustomizePanel
                        .getInstance(btn)
                        .show(getSupportFragmentManager(),"customize");
                return null;
            }
        });

        /**
         * works only if editable is set to false.
         * onClick listener applied to all buttons, to distinguish each view, you can use View.tags.
         * To apply tags on Views set tag value on DNDItem.tag (also available on constructor)
         */
        adapter.setOnClickBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DNDButton btn = (DNDButton) view;
                Toast.makeText(getApplicationContext(),"clicking tag " + btn.getTag(),Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * apply the adapter to view pager
         */
        view_pager.setAdapter(adapter);
        view_pager.setBackgroundColor(Color.GRAY);

        /**
         * The Timer is only a demonstration when the viewpager updates content.
         */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * always ensure that the array list is empty or the cache is clear
                         * to clear cache use DNDPager.clearCache()
                         */
                        view_pager_item_list.clear();
                        view_pager_item_list.add(new DNDItem("updated!",null,Color.parseColor("#1abc9c"),"20"));
                        /**
                         * To update the contents of a viewpager, create a new instance of FCollectionAdapter.
                         * Since notifyDataChanged doesn't work, this is the current work around.
                         */
                        adapter = new FCollectionAdapter(
                                getSupportFragmentManager(),
                                3,3,
                                view_pager_item_list,
                                DNDUtils.defaultAutoSwipe(view_pager));

                        view_pager.setAdapter(adapter);

                    }
                });
            }
        },20000);

    }

    /**
     * Single page example
     * generates a drag n drop draggable views inside a relative layout
     */
    void updatePager(){
        final RelativeLayout rl_grid1, rl_grid2;

        final List<DNDItem> rl_item_list = new ArrayList<>();
        rl_item_list.add(new DNDItem("POWER",null,Color.parseColor("#e67e22"),"11"));
        rl_item_list.add(new DNDItem("hello",null,Color.parseColor("#8e44ad"),"12"));
        rl_item_list.add(new DNDItem("hello",null,Color.parseColor("#e74c3c"),"13"));
        rl_item_list.add(new DNDItem("hello",null,Color.parseColor("#95a5a6"),"14"));
        rl_item_list.add(new DNDItem("hello",null,Color.parseColor("#d35400"),"15"));
        rl_item_list.add(new DNDItem("hello",null,Color.parseColor("#bdc3c7"),"16"));
        rl_item_list.add(new DNDItem("hello",null,Color.parseColor("#7f8c8d"),"17"));
        rl_item_list.add(new DNDItem("hello",null,Color.parseColor("#1abc9c"),"18"));
        rl_item_list.add(new DNDItem("helelo",null,Color.parseColor("#1abc9c"),"19"));
        rl_item_list.add(new DNDItem("hello",null,Color.parseColor("#1abc9c"),"20"));


        rl_grid1 = findViewById(R.id.rl_grid1);
        rl_grid2 = findViewById(R.id.rl_grid2);

        pager1 = new DNDPager(rl_grid1,4,2,"power_id",getApplicationContext());
        pager2 = new DNDPager(rl_grid2,4,2,"power_id",getApplicationContext());
        pager1.setBackgroundColor(Color.BLACK);
        pager2.render();


        pager1.render(new IDNDPager.ActionEvent() {
            @Override
            public void onExecute() {
                /**
                 * single pages only requires page_num -1
                 */
                pager1.addButtonToLayout(rl_item_list,-1);
            }
        });

        /**
         * The Timer is only a demonstration when the layout updates content.
         */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       /**
                        * when updating the contents ensure that you have cleared the list
                        */
                       rl_item_list.clear();
                       rl_item_list.add(new DNDItem("UPDATE",null,Color.parseColor("#e67e22"),"11"));
                       pager1.updateButtons(rl_item_list,-1);


                   }
               });
            }
        },5000);

        pager1.setIsEditable(true);
        pager2.setIsEditable(true);


    }

    /**
     * extracting values in DNDPagers
     */
    public List<DNDItem> extractValues(List<DNDItem> items){
        for(DNDItem i : items){
            //updates the values of DNDItems
            i.validatedProperties();
        }
        return items;
    }




}
