package com.venkatesh.foodapp.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.pojos.DataModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Restaurants extends Fragment implements RestaurantsAdapter.ItemListener{
    View mv;
    Activity activity;
    CarouselView carouselView;
    public String TAG = Restaurants.class.getSimpleName();
    int[] sampleImages = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
    private String userName;
    private TextView wish_id;
    GridView androidGridView;

    RecyclerView recyclerView;
    ArrayList arrayList;

    Integer[] imageIDs = {
            R.drawable.food1, R.drawable.food2, R.drawable.food3,
            R.drawable.food4, R.drawable.food1, R.drawable.food2,
            R.drawable.food3, R.drawable.food4, R.drawable.food1,
            R.drawable.food2
    };

    public Restaurants() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            userName =  getArguments().getString("userName");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mv = inflater.inflate(R.layout.fragment_first_page, null);
        carouselView = (CarouselView)mv. findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };
        carouselView.setImageListener(imageListener);
        activity = getActivity();



        androidGridView = (GridView)mv. findViewById(R.id.gridview);
//        androidGridView.setAdapter(new ImageAdapterGridView(getActivity()));

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                Toast.makeText(getActivity(), "Grid Item " + (position ) + " Selected", Toast.LENGTH_LONG).show();
                Intent intent =  new Intent(getActivity(), FoodCategories.class);
                intent.putExtra("pos", position);
                getActivity().startActivity(intent);

            }
        });
        return mv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView name = mv.findViewById(R.id.tv_user);
        wish_id = mv.findViewById(R.id.wish_id);



        recyclerView =  mv.findViewById(R.id.recyclerView);
        arrayList = new ArrayList();
        arrayList.add(new DataModel("Paradise", R.drawable.food1, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Barbeque", R.drawable.food3, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Helapuri", R.drawable.food2, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Alpha", R.drawable.food4, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Multi Cushine", R.drawable.food1, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Jeshan", R.drawable.food2, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Avakai", R.drawable.food3, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Vivana", R.drawable.food4, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Raju's Kitchen", R.drawable.food1, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Satyam", R.drawable.food3, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Punjabi Dhaba", R.drawable.food2, "#ffffff", 220, 220));
        arrayList.add(new DataModel("Food House", R.drawable.food4, "#ffffff", 220, 220));

        RestaurantsAdapter adapter = new RestaurantsAdapter(getActivity(), arrayList, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


//        name.setText(userName);
//        getCurrentTime();
    }

    private void getCurrentTime() {
        Calendar calendar= Calendar.getInstance();
        Date dat = calendar.getTime();
//        Log.d(TAG, dat+"");

        String[] list = dat.toString().split(" ");
        String mnth = list[1];
        String date = list[2];

        String mnth_date = mnth+date;

//       Log.d(TAG, mnth+", "+date);

        Time time = new Time(System.currentTimeMillis());
        int hours = time.getHours();

//        Log.d(TAG, hours+", ");
        if (hours<12){
            wish_id.setText("Good Morning!");
        }else if (hours>=12 && hours<5){
            wish_id.setText("Good AfterNoon!");
        }else {
            wish_id.setText("Good Evening!");
        }

        if (mnth_date.equalsIgnoreCase("Dec25")){
            wish_id.setText("Happy Christmas!");
        }else if (mnth_date.equalsIgnoreCase("Jan01")){
            wish_id.setText("Happy NewYear!");
        }else if (mnth_date.equalsIgnoreCase("Dec19")){
            wish_id.setText("Happy BirthDay!");
        }

    }

    @Override
    public void onItemClick(DataModel item) {
        Toast.makeText(getActivity(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }


//    public class ImageAdapterGridView extends BaseAdapter {
//        private Context mContext;
//
//        public ImageAdapterGridView(Context c) {
//            mContext = c;
//        }
//
//        public int getCount() {
//            return imageIDs.length;
//        }
//
//        public Object getItem(int position) {
//            return null;
//        }
//
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView mImageView;
//
//            if (convertView == null) {
//                mImageView = new ImageView(mContext);
//                mImageView.setLayoutParams(new GridView.LayoutParams(400, 400));
//                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                mImageView.setPadding(16, 16, 16, 16);
//            } else {
//                mImageView = (ImageView) convertView;
//            }
//            mImageView.setImageResource(imageIDs[position]);
//            return mImageView;
//        }
//    }


}
