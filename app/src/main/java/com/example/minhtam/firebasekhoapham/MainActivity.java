package com.example.minhtam.firebasekhoapham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    EditText edt;
    Button btnSetValue;
    TextView txt;
    FirebaseDatabase database;
    DatabaseReference myData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = (EditText) findViewById(R.id.edt);
        btnSetValue = (Button) findViewById(R.id.btnSetValue);
        txt = (TextView) findViewById(R.id.txt);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myData = database.getReference();

        btnSetValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValue();
            }
        });
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                txt.setText(dataSnapshot.getValue().toString());//lấy dữ liệu về
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Toast.makeText(MainActivity.this,"onCancelled",Toast.LENGTH_SHORT).show();
                // ...
            }
        };
        myData.child("Hovaten").addValueEventListener(postListener);
    }
    public void SetValue(){
        String name;
        name = edt.getText().toString();
        //lưu dữ liệu lên database
        myData.child("Hovaten").setValue(name, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError==null){
                    Toast.makeText(MainActivity.this,"Luu thanh cong",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Luu that bai",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
