package com.venkatesh.businessoffers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.venkatesh.businessoffers.R;
import com.venkatesh.businessoffers.adapters.OffersAdapter;
import com.venkatesh.businessoffers.pojos.OffersPojo;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllOffersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllOffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllOffersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayList<OffersPojo> list_offers;
    private OffersAdapter adapter;
    private View main_view;
    private RecyclerView my_recycler_view;
    LinearLayoutManager layoutManager;
    public AllOffersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllOffersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllOffersFragment newInstance(String param1, String param2) {
        AllOffersFragment fragment = new AllOffersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        main_view = inflater.inflate(R.layout.fragment_all_offers, container, false);
        return main_view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        my_recycler_view  = main_view.findViewById(R.id.my_recycler_view);

       ArrayList<OffersPojo> list = makeAList();
        adapter = new OffersAdapter(list);

        layoutManager = new LinearLayoutManager(getActivity());
        my_recycler_view.setLayoutManager(layoutManager);
        my_recycler_view.setItemAnimator(new DefaultItemAnimator());
        my_recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));

        my_recycler_view.setAdapter(adapter);


    }

    private ArrayList<OffersPojo> makeAList() {


        list_offers = new ArrayList<>();
        for (int i=0; i<12; i++){

            OffersPojo pojo = new OffersPojo();
            pojo.title = "Ramboo";
            pojo.description = "fsdkhgsjghjskghkjshgkjshgkjhsjkghjksghjshgjhjkshgjhjkgh";
            list_offers.add(pojo);
        }

        return list_offers;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
