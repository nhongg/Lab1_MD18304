package com.anhntk429.myapp429;

import static com.google.firebase.firestore.FieldValue.delete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {
    TextView tvKQ;

    FirebaseFirestore database;

    Context context=this;

    String strKQ="";

    Todo toDo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvKQ=findViewById(R.id.tvKQ);
        database=FirebaseFirestore.getInstance();
//        insert();
//        select();
//        update();
        Delete();
    }
    void insert(){
        String id= UUID.randomUUID().toString();
        toDo=new Todo(id,"tille1","content1");
        database.collection("TODO")
                .document(id)
                .set(toDo.convertToHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                       Toast.makeText(context,"thành công",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"thất bại",Toast.LENGTH_SHORT).show();

                    }
                });
    }
    void update(){
        String id="cb6551b0-eaae-463e-9dc6-aea7a8ec84f4";
        toDo=new Todo(id,"tittle 1 update","content 1 update");//noi dung can update
        database.collection("TODO")
                .document(id)
                .update(toDo.convertToHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context,"ud thành công",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"ud thất bại",Toast.LENGTH_SHORT).show();

                    }
                });
    }
    void Delete(){
        String id="cb6551b0-eaae-463e-9dc6-aea7a8ec84f4";
        database.collection("TODO")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"thất bại",Toast.LENGTH_SHORT).show();

                    }
                });

    }
    ArrayList <Todo> select(){
       ArrayList<Todo> list = new ArrayList<>();
       database.collection("TODO")
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if(task.isSuccessful()){
                           strKQ="";
                           for(QueryDocumentSnapshot doc: task.getResult()){
                               Todo t=doc.toObject(Todo.class);
                               list.add(t);
                               strKQ += "id:"+t.getId()+"\n";
                               strKQ += "title: "+t.getTitle()+"\n";
                               strKQ += "content: "+t.getContent()+"\n";
                           }
                           tvKQ.setText(strKQ);
                       }
                       else{
                           Toast.makeText(context,"select thất bại",Toast.LENGTH_SHORT).show();
                       }
                   }
               });
       return list;

    }
}
