package com.example.blw13.chatclient;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;



/**
 * A simple {@link Fragment} subclass.
 */
public class OneConversation extends Fragment {


    public OneConversation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one_conversation, container, false);


        //gets arguments from Bundle and retrieves email to display.
        Bundle args = getArguments();
        if(args != null) {


            // JSONObject result = (JSONObject) getArguments().get("result");

            try{

                JSONObject root = new JSONObject((String) getArguments().get("result"));

                if (root.has("messages")) {
                    JSONArray data = root.getJSONArray("messages");


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                            , ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(10, 10, 10, 50);
//                    params.height = 200;
//                    params.width = 600;

                    //LinearLayout mlayout = v.findViewById(R.id.one_conv_scroll_layout);
                    LinearLayout mlayout = (LinearLayout) v.findViewById(R.id.oneconversation_scroll_view);

                    Random rnd = new Random();


                    for (int i = data.length()-1; i >=0; i--) {
                        JSONObject jsonBlog = data.getJSONObject(i);
                        TextView textView = new TextView(v.getContext());
                        textView.setText( jsonBlog.getString("email")+ ": " + jsonBlog.getString("message"));
                        textView.setHeight(100);
//                        int color = Color.rgb(227, 232, 227);
                        textView.setBackground(getResources().getDrawable(R.drawable.rounded_corner));

                        textView.setTextSize(18);
                        textView.setLayoutParams(params);
                        mlayout.addView(textView);
                    }

//                    ScrollView sv = (ScrollView)v.findViewById(R.id.scrollView_One_Conversation_Viewer);
//                    sv.fullScroll(ScrollView.FOCUS_DOWN);
                    Runnable runnable= new Runnable() {
                        @Override
                        public void run() {
                            ((ScrollView)v.findViewById(R.id.scrollView_One_Conversation_Viewer)).fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    };
                    ((ScrollView)v.findViewById(R.id.scrollView_One_Conversation_Viewer)).post(runnable);

                    

                }

            }   catch (JSONException e) {
                e.printStackTrace(); Log.e("ERROR!", e.getMessage()); //notify user onWaitFragmentInteractionHide();
            }

        }
        return v;
    }




}
