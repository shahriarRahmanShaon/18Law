package com.ovi.a16flawbd.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ovi.a16flawbd.Adapters.UsersAdapter;
import com.ovi.a16flawbd.ModelClasses.ChatList;
import com.ovi.a16flawbd.ModelClasses.UserModel;
import com.ovi.a16flawbd.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private List<UserModel>  userModelList;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    private List<ChatList> chatListList;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.recyclerChat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // private List<ChatList> chatList; Declaration at top
        chatListList = new ArrayList<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase
                .getInstance()
                .getReference("BaatCheet/ChatList")
                .child(firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chatListList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ChatList chatList = snapshot.getValue(ChatList.class);
                        chatListList.add(chatList);
                    }
                    myChatList();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

       return view;
    }

    private void myChatList() {

        userModelList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("BaatCheet/Users/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UserModel userModel = snapshot.getValue(UserModel.class);
                    for (ChatList chatList : chatListList){
                        if (userModel.getId().equals(chatList.getId())){
                            userModelList.add(userModel);
                        }
                    }
                }
                usersAdapter = new UsersAdapter(userModelList,true);
                recyclerView.setAdapter(usersAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    

}//  end of class
