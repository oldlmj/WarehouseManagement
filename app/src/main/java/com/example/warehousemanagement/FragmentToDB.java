package com.example.warehousemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanagement.RoomDataBase.DataBase;
import com.example.warehousemanagement.RoomDataBase.MyData;

import com.facebook.stetho.Stetho;

import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class FragmentToDB extends AppCompatActivity {

    private static String ARG_PARAM = "param_key";
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";


    /*Fragment的傳參方式(通過Bundle物件來傳遞)
     *採用這種傳參方式可以保證使用者在橫豎屏切換時所
     * 傳遞的引數不會丟失
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);
        Stetho.initializeWithDefaults(this);//設置資料庫監視

        EditText edName = findViewById(R.id.editText_Name);
        EditText edPhone = findViewById(R.id.editText_Phone);
        EditText edHobby = findViewById(R.id.editText_Hobby);
        EditText edOther = findViewById(R.id.editText_else);
        Button btnCreate = findViewById(R.id.button_Create);
        Button btnModify=findViewById(R.id.button_Modify);
        Button btnDelete=findViewById(R.id.button_Modify);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(edName.getText())) {
                    //setResult(RESULT_OK, replyIntent);
                    Toast.makeText(getBaseContext(),"姓名是必須輸入~",Toast.LENGTH_SHORT).show();
                } else {
                    String word = edName.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }

            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        //saveStateToArguments();
//    }
}
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            NavHostFragment
//                    .findNavController(FragmentToDB.this)
//                    .popBackStack();
//            getActivity().getSupportFragmentManager().popBackStack();
//            return false;
//        }
//        return super.onOptionsItemSelected(item);
//    }
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
