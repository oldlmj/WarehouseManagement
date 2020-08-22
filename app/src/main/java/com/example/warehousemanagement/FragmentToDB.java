package com.example.warehousemanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentToDB extends Fragment {
    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;
    public FragmentToDB() {
    // Required empty public constructor
    }
    /*Fragment的傳參方式(通過Bundle物件來傳遞)
     *採用這種傳參方式可以保證使用者在橫豎屏切換時所
     * 傳遞的引數不會丟失
     */
    public static FragmentToDB newInstance(int data) {
        FragmentToDB fragment = new FragmentToDB();
        Bundle bundle = new Bundle();
    //將需要傳遞的字串以鍵值對的形式傳入bundle
        bundle.putInt("data",data);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_data, container, false);
        Bundle bundle = getArguments();
        String data = bundle.getString("DATA");
        TextView tViewName = root.findViewById(R.id.Text_Name);

        TextView tViewPhone = root.findViewById(R.id.Text_Phone);
        TextView tViewHobby = root.findViewById(R.id.Text_Hobby);
        TextView tViewElse = root.findViewById(R.id.Text_Else);
        if(data != null){
            tViewName.setText(data);
        }
        tViewName.setText(mParam);
        return root;
    }
    @Override
    public void onPause() {
        super.onPause();
    }

}
