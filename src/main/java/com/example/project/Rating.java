package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.project.adapters.CommentAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Rating extends AppCompatActivity {
    private RatingBar rBar;
    private Button btn;
    private EditText edt;
    FirebaseDatabase firebaseDatabase;
    RecyclerView commentv;
    CommentAdapter commentAdapter;
    List<Comment> commentList;
    static String Comment_key="Comment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_main
        );
        rBar= (RatingBar) findViewById(R.id.ratingBar);
        btn=(Button) findViewById(R.id.b1);
        edt=(EditText) findViewById(R.id.post_detail_comment);
        commentv=findViewById(R.id.rv);

        final  String[] doctorname = new String[1];
        Intent intent = getIntent();
        ArrayList<String> str = intent.getStringArrayListExtra("data_of_docotr");
        String username=str.get(0);
        doctorname[0] =str.get(1);
        firebaseDatabase=FirebaseDatabase.getInstance();
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {   btn.setVisibility(View.INVISIBLE);
                int noofstart=rBar.getNumStars();
                float getrating=rBar.getRating();
                DatabaseReference commentref=firebaseDatabase.getReference(Comment_key).child("Comments").push();
                String comment_content =edt.getText().toString();



                Log.d("doctorr",doctorname[0]);
                if(!comment_content.isEmpty())
                {
                Comment comment=new Comment(comment_content,username,getrating, doctorname[0]);
                commentref.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showMessage("Comment has been added");
                        edt.setText("");
                        btn.setVisibility(View.VISIBLE);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Fail to add comment"+e.getMessage());
                        btn.setVisibility(View.VISIBLE);
                    }
                });}
                else
                {
                    String mess="Cannot Add Empty Review";
                    showMessage(mess);
                    btn.setVisibility(View.VISIBLE);
                }


            }


        });

        iniRvComment(doctorname[0]);

    }
    public void iniRvComment(String doc)
    {


        Log.d("doc1",doc);
        commentv.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference commentRef =firebaseDatabase.getReference(Comment_key).child("Comments");
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                commentList=new ArrayList<>();
                for(DataSnapshot snap:dataSnapshot.getChildren())
                {

                    Comment comment= snap.getValue(Comment.class);
                    assert comment != null;
                    if(comment.getDoctorname().equals(doc))
                    {
                        commentList.add(comment);
                    }

                }
                commentAdapter = new CommentAdapter(getApplicationContext(),commentList);
                commentv.setAdapter(commentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
        return date;

    }
}

