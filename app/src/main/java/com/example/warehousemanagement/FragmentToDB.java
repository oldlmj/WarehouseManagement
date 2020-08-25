package com.example.warehousemanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.warehousemanagement.ui.home.HomeFragment;

public class FragmentToDB extends Fragment {
    private Bundle saveState;

    private String tag = FragmentToDB.class.getSimpleName();
    private static String ARG_PARAM = "param_key";
    public FragmentToDB() { }
    /*Fragment的傳參方式(通過Bundle物件來傳遞)
     *採用這種傳參方式可以保證使用者在橫豎屏切換時所
     * 傳遞的引數不會丟失
     */
//    public static FragmentToDB newInstance(int data) {
//        FragmentToDB fragment = new FragmentToDB();
//        Bundle bundle = new Bundle();
//    //將需要傳遞的字串以鍵值對的形式傳入bundle
//        bundle.putInt("data",data);
//        fragment.setArguments(bundle);
//        return fragment;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_data, container, false);
        setHasOptionsMenu(true);  //保證能在Fragment裡面呼叫onCreateOptionsMenu()方法
        TextView tViewName = root.findViewById(R.id.Text_Name);
        TextView tViewPhone = root.findViewById(R.id.Text_Phone);
        TextView tViewHobby = root.findViewById(R.id.Text_Hobby);
        TextView tViewElse = root.findViewById(R.id.Text_Else);
//        if(data != null){
//            //tViewName.setText(data);
//        }
        //tViewName.setText(mParam);
        return root;
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //saveStateToArguments();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavHostFragment
                    .findNavController(FragmentToDB.this)
                    .popBackStack();
            getActivity().getSupportFragmentManager().popBackStack();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        saveStateToArguments();
//    }
//    private void saveStateToArguments(){
//        if(getView() != null){
//            saveState = saveState();
//        }
//        if(saveState != null){
//            Bundle b = getArguments();
//            if(b!=null){
//                b.putBundle("internalSavedViewState8954201239547", saveState);
//            }
//        }
//    }
//
//    private boolean restoreStateFromArguments(){
//        Bundle b = getArguments();
//        if(b == null) return false;
//        saveState = b.getBundle("internalSavedViewState8954201239547");
//        if(saveState != null){
//            restoreState();
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 恢復狀態
//     * */
//    private void restoreState(){
//        if(saveState != null){
//            onRestoreState(saveState);
//        }
//    }
//
//    /**
//     * 子類重寫該方法恢復狀態
//     * */
//    public void onRestoreState(Bundle saveInstanceState){
//
//    }
//
//    /**
//     * 儲存狀態
//     * */
//    private Bundle saveState(){
//        Bundle state = new Bundle();
//        //儲存資料
//        onSaveState(state);
//        return state;
//    }
//
//    /**
//     * 子類重寫該方法實現狀態儲存
//     * */
//    public void onSaveState(Bundle outState){
//
//    }


}
