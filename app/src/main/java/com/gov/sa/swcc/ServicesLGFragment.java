package com.gov.sa.swcc;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.Adapter.GridFavAdapter;
import com.gov.sa.swcc.model.GridItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesLGFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesLGFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Global global;
    int height,width;
    GridAdapter adapter,adapter1;
    ArrayList<GridItem> birdList,birdList1;
    public ServicesLGFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesLGFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesLGFragment newInstance(String param1, String param2) {
        ServicesLGFragment fragment = new ServicesLGFragment();
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
        View view=inflater.inflate(R.layout.fragment_services_l_g, container, false);

        global=new Global(getContext());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        birdList = new ArrayList<GridItem>();
        birdList.add(new GridItem("الحضور و الإنصراف",R.drawable.checkin,"HR3"));
        birdList.add(new GridItem("مسيّر الراتب",R.drawable.profile,"HR2"));
        birdList.add(new GridItem("الاجازات",R.drawable.leave,"HR1"));


        birdList.add(new GridItem("التعريف بالراتب",R.drawable.salary,"HR6"));
        birdList.add(new GridItem("البحث عن العاملين",R.drawable.searchicon,"HR5"));
        birdList.add(new GridItem("التأمين الصحي",R.drawable.insur,"HR4"));

        adapter=new GridAdapter(getContext(),R.layout.griditem,birdList,width,height,0);
        GridView gridView=(GridView)view.findViewById(R.id.servicegrid1);
        gridView.setAdapter(adapter);
        getHeight(adapter,gridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("fav----","index------"+birdList.get(i).getType());


                if(birdList.get(i).getType().contains("HR3")) {
                    startActivity(new Intent(getActivity(),TransactionsActivity.class));
                }
                if(birdList.get(i).getType().contains("HR2")) {
                    startActivity(new Intent(getActivity(),PayslipActivity.class));
                }
                if(birdList.get(i).getType().contains("HR1")) {
                    startActivity(new Intent(getActivity(),LeaveActivity.class));
                }
                if(birdList.get(i).getType().contains("HR6")) {
                    startActivity(new Intent(getActivity(),EmployeeIdentificationActivity.class));
                }
                if(birdList.get(i).getType().contains("HR5")) {
                    startActivity(new Intent(getActivity(),EmpSearchActivity.class));
                }
                if(birdList.get(i).getType().contains("HR4")) {
                    startActivity(new Intent(getActivity(),InsuranceInfoActivity.class));
                }

            }
        });




        birdList1 = new ArrayList<GridItem>();
        birdList1.add(new GridItem("تقنية المعلومات",R.drawable.iticon,"TE1"));
        birdList1.add(new GridItem("العناية بالعاملين",R.drawable.hricon,"TE2"));
        birdList1.add(new GridItem("الملاحظات والبلاغات",R.drawable.complintnote,"TE3"));

        adapter1=new GridAdapter(getContext(),R.layout.griditem,birdList1,width,height,0);
        GridView gridView1=(GridView)view.findViewById(R.id.servicegrid2);
        gridView1.setAdapter(adapter1);
        getHeight(adapter1,gridView1);

        Log.d("fav----",global.GetValue("HRFav"));
        Log.d("fav----","ALI TEST");
        Log.d("fav----",global.GetValue("TEFav"));


        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if(birdList1.get(i).getType().contains("TE3")) {

                    startActivity(new Intent(getActivity(),OpenTeqActivity.class));

                    // birdList1.add(new GridItem("الملاحظات والبلاغات",R.drawable.complintnote,"TE3"));
                }
                if(birdList1.get(i).getType().contains("TE2")) {
                    startActivity(new Intent(getActivity(),HrRequestActivity.class));
                }
                if(birdList1.get(i).getType().contains("TE1")) {
                    startActivity(new Intent(getActivity(),ITComActivity.class));
                }
            }
        });




        return view;

    }



    public void getHeight(GridAdapter listadp, GridView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            i=i+2;
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getVerticalSpacing() * ((int)(listadp.getCount()/3)));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }


}