package com.example.project.adapters;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Comment;
import com.example.project.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

private Context mcontext;
private List<Comment> mdata;

    public CommentAdapter(Context mcontext, List<Comment> mdata) {
        this.mcontext = mcontext;
        this.mdata = mdata;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mcontext).inflate(R.layout.rowcomment,parent,false);
        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        //Glide.with(mcontext).load(mdata.get(position).ge()).into(holder.img_user);



        holder.tv_name.setText(mdata.get(position).getUsername());
        holder.tv_content.setText(mdata.get(position).getContent());
        holder.tv_date.setText(timestampToString((Long)mdata.get(position).getTimestamp()));
        holder.tv_rate.setText(String.valueOf(mdata.get(position).getRating()));


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        ImageView img_user;
        TextView tv_name,tv_content,tv_date,tv_rate;


        public CommentViewHolder(@NonNull  View itemView) {
            super(itemView);
            img_user=itemView.findViewById(R.id.imageView);
            tv_name=itemView.findViewById(R.id.doctorname);
            tv_content= itemView.findViewById(R.id.commentdata);
            tv_date=itemView.findViewById(R.id.comdate);
            tv_rate=itemView.findViewById(R.id.textView7);
        }
    }
    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("hh:mm",calendar).toString();
        return date;


    }

}
