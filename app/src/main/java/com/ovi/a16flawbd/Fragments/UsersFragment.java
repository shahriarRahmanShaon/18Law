package com.ovi.a16flawbd.Fragments;


import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ovi.a16flawbd.Adapters.UsersAdapter;
import com.ovi.a16flawbd.ModelClasses.UserModel;
import com.ovi.a16flawbd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment {

    RecyclerView recyclerViewUsers;
    EditText editTextSearch;
    List<UserModel> userModelList;
    UsersAdapter usersAdapter;


    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_users, container, false);

        // Users Recycler view
        recyclerViewUsers = view.findViewById(R.id.recyclerUsers);
        editTextSearch = view.findViewById(R.id.editTextUserSearch);

        getUsersFromServer();

        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                    myUserFilter(s.toString());
            }
        });


        return  view;
    }

    private void myUserFilter(String s) {

        ArrayList<UserModel> userFilteredList = new ArrayList<>();

        for (UserModel userModel : userModelList){
            if (userModel.getUsername().toLowerCase().contains(s.toLowerCase())){
                    userFilteredList.add(userModel);
            }
        }
        usersAdapter.filterList(userFilteredList);
    }

    private void getUsersFromServer() {

        userModelList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BaatCheet/Users/");
        //databaseReference.keepSynced(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if (!userModelList.isEmpty()){
                        userModelList.clear();
                    }
                  for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                      UserModel userModel = dataSnapshot1.getValue(UserModel.class);
                      userModelList.add(userModel);
                  }
                  usersAdapter = new UsersAdapter(userModelList,true);
                  recyclerViewUsers.setAdapter(usersAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
