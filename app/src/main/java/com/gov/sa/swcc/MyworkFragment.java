package com.gov.sa.swcc;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.model.GetToken.GetToken;
import com.gov.sa.swcc.model.GridItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyworkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyworkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyworkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyworkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyworkFragment newInstance(String param1, String param2) {
        MyworkFragment fragment = new MyworkFragment();
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
    public void onResume() {
        super.onResume();
        CheckFlag();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        CheckFlag();

    }

 static    Global global;
    int height,width;
    GridAdapter adapter,adapter1;
    ArrayList<GridItem> birdList,birdList1;

CardView mytask;
   static CardView fab;
static LinearLayout emptask,taskaproval;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mywork, container, false);

        global=new Global(getContext());
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        height = displayMetrics.heightPixels;
//        width = displayMetrics.widthPixels;
//
//
//        //global.SaveValue("Lan","en");
//
//        birdList = new ArrayList<GridItem>();
//        if(global.GetValue("Lan").equals("en")) {
//            birdList.add(new GridItem("employees evaluation", R.drawable.ratecon, "SH1"));
//            birdList.add(new GridItem("daily visits", R.drawable.dailyvist, "SH2"));
//            birdList.add(new GridItem("employees attendance", R.drawable.signoncon, "SH3"));
//            birdList.add(new GridItem("export report", R.drawable.report, "SH4"));
//            ((TextView)view.findViewById(R.id.sharektitle)).setText("Shareek Service");
//            ((LinearLayout)view.findViewById(R.id.sline)).setGravity(Gravity.LEFT);
//
//
//
//        }else{
//            birdList.add(new GridItem("تقييم العمالة", R.drawable.ratecon, "SH1"));
//            birdList.add(new GridItem("زيارات يومية", R.drawable.dailyvist, "SH2"));
//            birdList.add(new GridItem("حضور العمال", R.drawable.signoncon, "SH3"));
//
//            birdList.add(new GridItem("اصدار التقارير", R.drawable.report, "SH4"));
//        }
//        adapter=new GridAdapter(getContext(),R.layout.griditem,birdList,width,height,0);
//        GridView gridView=(GridView)view.findViewById(R.id.servicegrid1);
//        gridView.setAdapter(adapter);
//        getHeight(adapter,gridView);
//
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Log.d("fav----","index------"+birdList.get(i).getType());
//
//
//
////                if(i==2) {
////                    Intent ie =new Intent(getActivity(),SharekProjectsActivity.class);
////                    ie.putExtra("SSID","1");
////                    startActivity(ie);                }
//
//                if(i==1) {
//                    Intent ie =new Intent(getActivity(),SharekProjectsActivity.class);
//                    ie.putExtra("HT",birdList.get(i).getServiceName());
//                    ie.putExtra("SSID","3");
//                    startActivity(ie);
//                }
//
//                if(i==2) {
//                    Intent ie =new Intent(getActivity(),SharekProjectsActivity.class);
//                    ie.putExtra("SSID","1");
//                    ie.putExtra("HT",birdList.get(i).getServiceName());
//
//                    startActivity(ie);                }
//
//                if(i==0) {
//                    Intent ie =new Intent(getActivity(),SharekProjectsActivity.class);
//                    ie.putExtra("SSID","2");
//                    ie.putExtra("HT",birdList.get(i).getServiceName());
//                    startActivity(ie);
//                }
//
//                if(i==3) {
//                    Intent ie =new Intent(getActivity(),ShareakReportNActivity.class);
//                    ie.putExtra("HT",birdList.get(i).getServiceName());
//                    startActivity(ie);
//                }
//                if(birdList.get(i).getType().contains("HR2")) {
//                    startActivity(new Intent(getActivity(),PayslipActivity.class));
//                }
//                if(birdList.get(i).getType().contains("HR1")) {
//                    startActivity(new Intent(getActivity(),LeaveActivity.class));
//                }
//                if(birdList.get(i).getType().contains("HR6")) {
//                    startActivity(new Intent(getActivity(),EmployeeIdentificationActivity.class));
//                }
//                if(birdList.get(i).getType().contains("HR5")) {
//                    startActivity(new Intent(getActivity(),EmpSearchActivity.class));
//                }
//                if(birdList.get(i).getType().contains("HR4")) {
//                    startActivity(new Intent(getActivity(),InsuranceInfoActivity.class));
//                }
//
//            }
//        });


         mytask=(CardView)view.findViewById(R.id.mytask);
        CardView sharek=(CardView)view.findViewById(R.id.sharek);
         emptask=(LinearLayout) view.findViewById(R.id.emptask);
         taskaproval=(LinearLayout) view.findViewById(R.id.taskaproval);

        sharek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SharekActivity.class));
            }
        });

        mytask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MyTaskActivity.class));
            }
        });
        emptask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),EmpTaskActivity.class));
            }
        });
        taskaproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ApprovalActivity.class));
            }
        });

         fab=(CardView)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Emp_Add_TaskActivity.class);
                startActivity(intent);
            }
        });


        TextView approvaltask=(TextView) view.findViewById(R.id.approvaltask);

        String text = "<font color=#ffffff>في انتظار الموافقة</font> <br/> <font color=#BBF912>"+ global.GetValue("ApprovalWaiting")+"</font> طلب";
        approvaltask.setText(Html.fromHtml(text));
        TextView emptasks=(TextView) view.findViewById(R.id.emptasks);

         text = "<font color=#ffffff>مهام العاملين</font> <br/> <font color=#BBF912>"+ global.GetValue("EmpTaskCount")+"</font> مهمة";
        emptasks.setText(Html.fromHtml(text));


        //GetToken();

        return view;
    }


    public  static void CheckFlag(){

//       String IsSuper= global.GetValue("IsSuper");
//       if(!IsSuper.contains("rue")){
//           emptask.setVisibility(View.GONE);
//           taskaproval.setVisibility(View.GONE);
//           fab.setVisibility(View.GONE);
//
//       }

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