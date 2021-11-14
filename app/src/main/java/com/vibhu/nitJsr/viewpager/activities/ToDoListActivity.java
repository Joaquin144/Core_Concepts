package com.vibhu.nitJsr.viewpager.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.vibhu.nitJsr.viewpager.R;
import com.vibhu.nitJsr.viewpager.adapter.ToDoAdapter;
import com.vibhu.nitJsr.viewpager.models.ToDOModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ToDoListActivity extends AppCompatActivity {

    private ArrayList<ToDOModel> list;
    private ImageView todoact_plusBtn;
    private EditText et;private RecyclerView todoAct_RV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        list=new ArrayList<>();todoAct_RV=findViewById(R.id.todoAct_RV);
        todoact_plusBtn=findViewById(R.id.todoact_plusBtn);et=findViewById(R.id.editText);

        //todo : firebase ke liye plus action set karo
        todoact_plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                String newTask = et.getText().toString().trim();
                DocumentReference totalRef = firestore.collection("tasks").document("total");
                totalRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d("####","getting task was success");
                            DocumentSnapshot document = task.getResult();
                            Log.d("####",""+task.getResult()+"   "+document.getData());
                            long num = Long.parseLong((String) document.get("totalTasks"));
                            Log.d("####","totalTasks = "+num);
//                            Map<String,Long> m =new HashMap<>();
//                            m.put("totalTasks",num+1);
//                            totalRef.set(m);
                            totalRef.update("totalTasks",""+(num+1));
                            Map<String,Object> m =new HashMap<>();
                            m.put("name",newTask);m.put("takeMe",true);
                            firestore.collection("tasks").document("item"+(num+1)).set(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(ToDoListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ToDoListActivity.this, "Failed to add", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            Toast.makeText(ToDoListActivity.this, "Task failed to get total ", Toast.LENGTH_SHORT).show();
                            Log.d("####","Failed task to get total");
                        }
                    }
                });


            }
        });

        //todo : data featch karke show karo RV mein
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("tasks").whereEqualTo("takeMe",true).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String name = (String) document.getString("name");
                        list.add(new ToDOModel(name));
                    }
                    ToDoAdapter adapter=new ToDoAdapter(list);
                    todoAct_RV.setAdapter(adapter);
                    LinearLayoutManager manager=new LinearLayoutManager(ToDoListActivity.this);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    todoAct_RV.setLayoutManager(manager);
                    adapter.notifyDataSetChanged();

                }
                else{
                    Log.d("####","reading all tasks from firestore failed");
                }
            }
        });

    }
}