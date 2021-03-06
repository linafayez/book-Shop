package com.shop.myshop.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.myshop.Models.shopModel;
import com.shop.myshop.ProductsModel;
import com.shop.myshop.R;
import com.shop.myshop.SharedPreference;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyCart extends Fragment {
    RecyclerView cart;
    CartAdapter adapter;
    ArrayList<ProductsModel> data, shopProduct;
    SharedPreference sharedPreference;
    Button checkout;
static TextView total;
shopModel shop;
    public MyCart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        total = view.findViewById(R.id.total);
        cart = view.findViewById(R.id.cartRe);
        sharedPreference = new SharedPreference(getContext());
        shop = MyCartArgs.fromBundle(getArguments()).getShop();
        shopProduct = new ArrayList<>();
        data = new ArrayList<>();
        data = sharedPreference.getCartData();
        for(int i =0;i<data.size();i++){
            if(data.get(i).getShop().getId().equals(shop.getId())){
                shopProduct.add(data.get(i));
            }
        }
       // Toast.makeText(getContext(), data.size() + "", Toast.LENGTH_LONG).show();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        changed.setTotal(shopProduct);
        adapter = new CartAdapter(getContext(), data);
        cart.setLayoutManager(manager);
        cart.setHasFixedSize(false);
        cart.setAdapter(adapter);
        checkout = view.findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Navigation.findNavController(getView()).navigate(MyCartDirections.actionMyCartToCheckOut(shop));
            }
        });
    }

    public static class changed {
        static DecimalFormat df2 = new DecimalFormat("#.##");
        public static void setTotal(ArrayList<ProductsModel> data){
            Double sum= 0.0;
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    ProductsModel P = data.get(i);
                    double price = P.getPrice() / 100.0;
                    if(P.getDiscount()!=0){
                        price-=price*(P.getDiscount()/100.0);
                    }
                    int number = P.getItemNumberInCart();
                    sum += Double.parseDouble(df2.format(price*number));

                }
            }
            total.setText(df2.format(sum)+"JD");
        }
    }
}