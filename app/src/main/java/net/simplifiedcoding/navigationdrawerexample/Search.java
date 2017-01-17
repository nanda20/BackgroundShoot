package net.simplifiedcoding.navigationdrawerexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Belal on 18/09/16.
 */


public class Search extends Fragment {

    private EditText category;
    private Button btnSearch;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        category = (EditText) view.findViewById(R.id.inputCategory);
        btnSearch= (Button) view.findViewById(R.id.btnCategory);
//        btnSearch.setOnClickListener((View.OnClickListener) this);

       btnSearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               category = (EditText) v.findViewById(R.id.categori);
               String val = category.getText().toString();
//               Log.d("ISI"," = "+val);
//               Log.d("ISI"," = "+category);
               Intent intent= new Intent(getContext(), CategoryList.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("page","2");
               intent.putExtra("category",val);
               getActivity().startActivity(intent);
           }
       });
        return view;
    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCategory:
                Toast.makeText(getContext(),category.toString(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Search");
    }
}
