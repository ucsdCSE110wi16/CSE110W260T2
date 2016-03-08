package com.example.kingd.hello_world;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PopupWindowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_popup_window);

        Button button = (Button)findViewById(R.id.button2);
        final EditText editText = (EditText)findViewById(R.id.editText);
        final EditText editText2 = (EditText)findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBFetch.setName(editText.getText().toString());
                DBFetch.setBalance(Double.parseDouble(editText2.getText().toString()));
                try {
                    MainActivity.dbFetch.writeStoreUser();
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("Writing failed");
                }
                Intent intent = new Intent(PopupWindowActivity.this, MainActivity.class);
                PopupWindowActivity.this.startActivity(intent);
            }
        });
    }

}
