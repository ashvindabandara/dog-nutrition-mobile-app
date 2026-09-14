package com.dilum.myprojectapplication.Helper;

import android.content.Context;
import android.widget.Toast;

import com.dilum.myprojectapplication.Domain.PopularDomain;

import java.util.ArrayList;

public class ManagmentCart {
     private Context context;
     private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(PopularDomain item){
        ArrayList<PopularDomain> listPop = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listPop.size(); i++) {
            if (listPop.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready){
            listPop.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listPop.add(item);
        }

        tinyDB.putListObject("CartList", listPop);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void minusNumberItem(ArrayList<PopularDomain>listItem, int position, ChangeNumberItemListener changeNumberItemListener){
        if (listItem.get(position).getNumberInCart()==1) {
            listItem.remove(position);

        }else {
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()-1);
        }

        tinyDB.putListObject("CartList",listItem);
        changeNumberItemListener.change();
    }

    public void plusNumberItem(ArrayList<PopularDomain>listItem, int position, ChangeNumberItemListener changeNumberItemListener){
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()+1);
        tinyDB.putListObject("Cartlist",listItem);
        changeNumberItemListener.change();
    }

    public Double getTotalFee(){
        ArrayList<PopularDomain>listItem = getListCart();
        double fee = 0;
        for (int i = 0; i < listItem.size(); i++){
            fee = fee+(listItem.get(i).getPrice()*listItem.get(i).getNumberInCart());
        }
        return fee;
    }
}
