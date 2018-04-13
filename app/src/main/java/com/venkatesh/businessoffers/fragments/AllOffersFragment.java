package com.venkatesh.businessoffers.fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.ResponseBody;
import com.venkatesh.businessoffers.BaseActivity;
import com.venkatesh.businessoffers.Listener;
import com.venkatesh.businessoffers.MyApplication;
import com.venkatesh.businessoffers.PreferencesData;
import com.venkatesh.businessoffers.R;
import com.venkatesh.businessoffers.RetrofitService;
import com.venkatesh.businessoffers.superList.OnMoreListener;
import com.venkatesh.businessoffers.superList.SuperRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;

public class AllOffersFragment extends Fragment  {

    public boolean isListEmpty = false;
    SuperRecyclerView mList;
//    OffersAdapter adap;
    View main;
//    private List<NotificationsPojo> notifications;
    private int PAGE = 1;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        setListLayout();

//        notifications = new ArrayList<>();
        getNotofications();
        setHasOptionsMenu(true);

        getOffers();

//        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.cancelAll();
        //PreferencesData.putNumNotifications(getActivity(), "0");

       /* Activity act = getActivity();
        if (act instanceof InterViewer) {
            ((InterViewer) act).setNotificationText();
        } else if (act instanceof Recruiter) {
            ((Recruiter) act).setNotificationText();
        }
        if (act instanceof Candidate) {
            ((Candidate) act).setNotificationText();
        }*/
        //((RadioButton)getActivity().findViewById(R.id.notifications)).setText(builder);

    }

    private void getOffers(){

        String business_id = "1";
        Call<ResponseBody> call = MyApplication.getSerivce().getOffers_(business_id);
        call.enqueue(new Listener(new RetrofitService() {
            @Override
            public void onSuccess(String result, int pos, Throwable t) {

                if (pos==0){


                }


            }
        }, "loading..", true,getActivity() ));


    }

    /**
     * Initializing UI list with grid layout manager
     */
    public void setListLayout() {

        mList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mList.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mList.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
             /*   if(adap!=null)
                    adap.clearAll();
                adap.notifyDataSetChanged();
                PAGE = 1;
                getNotofications();*/

            }
        });
//        mList.setupMoreListener(new OnMoreListener() {
//            @Override
//            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
//                if (!isListEmpty)
//                    getNotofications();
//                else
//                    mList.hideMoreProgress();
//            }
//        }, 2);
//        adap = new OffersAdapter(getActivity(), this);
//        mList.setAdapter(adap);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 8.
        3333Bundle savedInstanceState) {
        main = inflater.inflate(R.layout.frag_offer_items, null);
        //	view=(ListView) main.findViewById(R.id.list);
        mList = (SuperRecyclerView) main.findViewById(R.id.list);
        //LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //view.setVerticalScrollBarEnabled(false);
        //view.setCacheColorHint(Color.TRANSPARENT);
        //view.setLayoutParams(lp);
        //	view.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        return main;
    }




    /**
     * Empty text changes will done here
     *
     * @param id operation to perform
     */
   /* public void changeEmptyText(int id) {
        switch (id) {
            case 0:
                mList.changeEmptyView(R.drawable.ic_launcher,getString(R.string.notifications));
                break;
            case 1:
                mList.changeEmptyView(R.drawable.ic_launcher, R.string.nothing_found_notifications);

                break;
            case 2:
                mList.changeEmptyView(R.drawable.ic_launcher, R.string.something_wentwrong);
                break;
            case 3:
                mList.changeEmptyView(R.drawable.ic_launcher, R.string.no_matching_found);
                break;
            case 4:
                mList.changeEmptyView(R.drawable.ic_launcher,R.string.no_network_connection_found);
                break;
        }
        setEmptyViewClick(id);

    }
*/
    public void setEmptyViewClick(int id) {
        mList.getEmptyView().setOnClickListener(null);
        if (id == 2 || id==4) {
            mList.getEmptyView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PAGE = 1;
                    getNotofications();
                }
            });
        }
    }

  /*  public void setmAdapter() {
        //List<JobPojo> jobs=adapter.getItems();
        adap = new OffersAdapter(getActivity(), this);
        //adap.setItems(engagedlist);
        mList.setAdapter(adap);
    }
*/
   /* public void notiFyDataSetChanged() {
        if (PAGE == 1)
            setmAdapter();
        else
            adap.notifyDataSetChanged();

    }*/

    protected void parseJsonFeed(String res) {

        try {
            JSONObject response = new JSONObject(res);


            Gson gson = new Gson();
            Type type = new TypeToken<List<NotificationsPojo>>() {
            }.getType();

            notifications = gson.fromJson(response.getJSONArray("notifications").toString(), type);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.edit);
        if (item != null)
            item.setVisible(false);
        menu.removeItem(R.id.filter);
    }

    public void getNotofications() {
        if(getActivity() instanceof BaseActivity) {
            if (((BaseActivity) getActivity()).checkConnection()) {
                if (PAGE == 1)
                    changeEmptyText(0);
                Call<ResponseBody> cl = MyApplication.getSerivce().getNotifications(PreferencesData.getRole(getActivity()), PAGE, 15);
                cl.enqueue(new Listener(new RetrofitService() {
                    @Override
                    public void onSuccess(String result, int pos, Throwable t) {
                        if (pos == 0) {
                            parseJsonFeed(result);

                            if (notifications.size() > 0) {
                                if (PAGE == 1)
                                    adap.clearAll();
                                PAGE++;
                                adap.addItems(notifications);
                                isListEmpty = notifications.size() < 15;
                                notiFyDataSetChanged();
                            } else if (PAGE == 1) {
                                adap.clearAll();
                                adap.notifyDataSetChanged();
                                changeEmptyText(1);
                            } else {
                                isListEmpty = true;
                                mList.hideMoreProgress();
                                //changeEmptyText(1);
                            }
                        }
                    }
                }, null, false, getActivity()));
            }
            else{
                changeEmptyText(4);
            }
        }
    }

 /*   @Override
    public void conformed() {
        adap.clearAll();
        adap.notifyDataSetChanged();
        getNotofications();
    }*/
}
