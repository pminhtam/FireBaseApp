package com.example.minhtam.firebasekhoapham.appli;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.minhtam.firebasekhoapham.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChupAnhFragment extends Fragment {


    public ChupAnhFragment() {
        // Required empty public constructor
    }
    Button btnChupAnh,btnGuiAnh;
    ImageView imgChupAnh;
    int REQUEST_CODE = 1;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chup_anh, container, false);
        btnChupAnh = (Button) view.findViewById(R.id.btnChupAnh);
        imgChupAnh = (ImageView) view.findViewById(R.id.imgChupAnh);
        btnGuiAnh = (Button) view.findViewById(R.id.btnGuiAnh);
        btnChupAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        btnGuiAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpImg();
            }
        });
        return view;
    }
    public void UpImg(){
        Calendar calendar = Calendar.getInstance();
        StorageReference imgRef = storageReference.child("image"+calendar.getTimeInMillis()+".png");
        imgChupAnh.setDrawingCacheEnabled(true);
        imgChupAnh.buildDrawingCache();
        Bitmap bitmap = imgChupAnh.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = imgRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(getActivity(),"thất bại",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(getActivity(),"Thành công",Toast.LENGTH_SHORT).show();
                mData.child("hinhanh").push().setValue(new ItemImage(downloadUrl.toString()));
                Log.e("Up Ìmg",downloadUrl.toString());

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgChupAnh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
