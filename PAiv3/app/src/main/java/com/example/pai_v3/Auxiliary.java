package com.example.pai_v3;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Response;

public class Auxiliary {

    public static void onViewConnectionPolicy()
    //required to make the connection for policy building
    {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
    }



    public static ArrayList<String> responseToJSON(Response response, String mapKey)
    //convert response to JSON Object
    {
        try{
        String jsonData = response.body().string();
        JSONObject Jobject = new JSONObject(jsonData);
        JSONArray Jarray = Jobject.getJSONArray("news");

        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < Jarray.length(); i++) {
            JSONObject object = Jarray.getJSONObject(i);
            //Log.i("data", object.toString());
            data.add(object.toString());
        }
        Log.i("Success - Auxiliary.responseToJSON", "..................................." );
        return data;

        }catch(Exception e){
            Log.i("Failed Start  - Auxiliary.responseToJSON", "...................................");
            e.printStackTrace();
            Log.i("Failed End  - Auxiliary.responseToJSON", "...................................");
            return null;
        }
    }


    public static class OnSwipeTouchListener implements OnTouchListener {



        private final GestureDetector gestureDetector;
        private Context context;

        /* (non-Javadoc)
         * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
         */
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }

        /**
         * Gets the gesture detector.
         *
         * @return the gesture detector
         */
        public GestureDetector getGestureDetector(){
            return  gestureDetector;
        }

        /**
         * Instantiates a new on swipe touch listener.
         *
         * @param context
         *            the context
         */
        public OnSwipeTouchListener(Context context) {
            super();
            this.context = context;
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            /* (non-Javadoc)
             * @see android.view.GestureDetector.SimpleOnGestureListener#onDown(android.view.MotionEvent)
             */
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            /* (non-Javadoc)
             * @see android.view.GestureDetector.SimpleOnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
             */

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getRawY() - e1.getRawY();
                    float diffX = e2.getRawX() - e1.getRawX();
                    if ((Math.abs(diffX) - Math.abs(diffY)) > SWIPE_THRESHOLD) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                        }
                    } else {
                        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                onSwipeBottom();
                            } else {
                                onSwipeTop();
                            }
                        }
                    }
                } catch (Exception e) {

                }
                return result;
            }
        }

        /**
         * On swipe right.
         */
        public void onSwipeRight() {
        }

        /**
         * On swipe left.
         */
        public void onSwipeLeft() {
        }

        /**
         * On swipe top.
         */
        public void onSwipeTop() {
        }

        /**
         * On swipe bottom.
         */
        public void onSwipeBottom() {
        }
    }
}
