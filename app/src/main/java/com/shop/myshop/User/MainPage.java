package com.shop.myshop.User;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.shop.myshop.AdsModel;
import com.shop.myshop.ProductsModel;
import com.shop.myshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainPage extends Fragment {
public Button allPro, allDeals;
RecyclerView ads, lastPro, lastDeals;
LinearLayout imageGallery;
    AdsAdapter adapter;
    LastProductAdapter ProAdapter;
Gson gson;
ProductsModel product;
ArrayList<ProductsModel> lastProList, lastDealsProducts;
ArrayList<AdsModel> adsList;
     Bundle bundle;
    ArrayList<ImageView> im= new ArrayList<>();
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    private ArrayList<String> id= new ArrayList<String>();
    public MainPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_page,
                container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  bundle = new Bundle();
        adsList = new ArrayList<>();
        lastDealsProducts = new ArrayList<>();
        lastProList = new ArrayList<>();
        allDeals = view.findViewById(R.id.button6);
        lastPro = view.findViewById(R.id.recyclerView);
        lastDeals = view.findViewById(R.id.recyclerView2);
        allPro = view.findViewById(R.id.all);
        ads = view.findViewById(R.id.switchImage);
        allPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Navigation.findNavController(getView()).navigate(MainPageDirections.actionMainPageToProducts(null,"lastProduct",null));
            }
        });
        allDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(MainPageDirections.actionMainPageToProducts(null,"lastDeals",null));
            }
        });
        FirebaseFirestore.getInstance().collection("Ads").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                   if(task.isSuccessful()){
                       for (QueryDocumentSnapshot document : task.getResult()) {
                           adsList.add(document.toObject(AdsModel.class));
                       }
                  adapter = new AdsAdapter(adsList, getContext());
                       RecyclerView.LayoutManager manager =new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                       ads.setLayoutManager(manager);
                       ads.setHasFixedSize(false);
                       ads.setAdapter(adapter);
                   }
            }
        });
        FirebaseFirestore.getInstance().collection("Products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        lastProList.add(document.toObject(ProductsModel.class));
                    }
                    ProAdapter = new LastProductAdapter(getContext(),lastProList);
                   // RecyclerView.LayoutManager manager =new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                    lastPro.setLayoutManager(layoutManager);
                    lastPro.setHasFixedSize(false);
                    lastPro.setAdapter(ProAdapter);
                }
            }
        });
        FirebaseFirestore.getInstance().collection("Products").whereGreaterThan("discount",0).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        lastDealsProducts.add(document.toObject(ProductsModel.class));
                    }
                    ProAdapter = new LastProductAdapter(getContext(),lastDealsProducts);
                  RecyclerView.LayoutManager manager =new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                //    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                    lastDeals.setLayoutManager(manager);
                    lastDeals.setHasFixedSize(false);
                    lastDeals.setAdapter(ProAdapter);
                }
            }
        });

  }

}