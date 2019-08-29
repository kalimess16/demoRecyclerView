package com.example.demorecyclerview.RV;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demorecyclerview.R;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<Hero> mListHero;

    public RecyclerAdapter( ArrayList<Hero> listHero) {
        mListHero = listHero;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        return new  ViewHolder(view);
    }

    public void updateData(ArrayList<Hero> listHero){
        mListHero.clear();
        mListHero.addAll(listHero);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hero hero = mListHero.get(position);
        holder.mTextName.setText(hero.getName());
       // holder.mImageHero.setImageResource(hero.getImage());
        holder.mImageHero.setImageBitmap(hero.getImage()); // set theo BITMAP
    }

    @Override
    public int getItemCount() {
        return mListHero.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageHero;
        TextView mTextName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageHero = itemView.findViewById(R.id.image_recycler);
            mTextName = itemView.findViewById(R.id.text_name);

        }
    }
}
