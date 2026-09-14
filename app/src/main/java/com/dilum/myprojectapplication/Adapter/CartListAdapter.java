package com.dilum.myprojectapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.dilum.myprojectapplication.Domain.PopularDomain;
import com.dilum.myprojectapplication.Helper.ChangeNumberItemListener;
import com.dilum.myprojectapplication.Helper.ManagmentCart;
import com.dilum.myprojectapplication.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<PopularDomain> listItemSelected;
    private ManagmentCart managmentCart;
    ChangeNumberItemListener changeNumberItemListener;

    public CartListAdapter(ArrayList<PopularDomain>listItemSelected,Context context, ChangeNumberItemListener changeNumberItemListener){
        this.listItemSelected = listItemSelected;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }


    @NonNull
    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(listItemSelected.get(position).getTitle());
        holder.feeEachItem.setText("Rs"+listItemSelected.get(position).getPrice());
        holder.totalEachItem.setText("Rs"+Math.round((listItemSelected.get(position).getNumberInCart()*listItemSelected.get(position).getPrice())));
        holder.num.setText(String.valueOf(listItemSelected.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(listItemSelected.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.plusNumberItem(listItemSelected, position, new ChangeNumberItemListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemListener.change();

                    }
                });

            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.minusNumberItem(listItemSelected, position, new ChangeNumberItemListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemListener.change();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, feeEachItem, plusItem, minusItem;
        ImageView pic;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachitem);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberitemTxt);
        }
    }
}
