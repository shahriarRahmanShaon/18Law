package com.ovi.a16flawbd.Adapters;


import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ovi.a16flawbd.MessageActivity;
import com.ovi.a16flawbd.ModelClasses.ChatList;
import com.ovi.a16flawbd.ModelClasses.MessageModel;
import com.ovi.a16flawbd.ModelClasses.UserModel;
import com.ovi.a16flawbd.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

// create inner view holder class before extending
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersAdapterViewHolder> {

    private List<UserModel> userModelList;
    private boolean isChat;
    String theLastMsg;

    public UsersAdapter(List<UserModel> userModelList,boolean isChat) {
        this.userModelList = userModelList;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public UsersAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.useritem,null);
        UsersAdapterViewHolder usersAdapterViewHolder = new UsersAdapterViewHolder(view);
        return usersAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UsersAdapterViewHolder holder, final int i) {

        final UserModel user = userModelList.get(i);

        holder.textViewProfileName.setText(userModelList.get(i).getUsername());

        if (userModelList.get(i).getImageURL().equals("default")){
            holder.circleImageViewProfileImage.setImageResource(R.mipmap.ic_launcher);
        }
        else {
         //   Picasso.get().load(userModelList.get(i).getImageURL()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.circleImageViewProfileImage, new Callback() {
         //       @Override
         //       public void onSuccess() {
         //       }
          //      @Override
           //     public void onError(Exception e) {
                    Picasso.get().load(userModelList.get(i).getImageURL()).into(holder.circleImageViewProfileImage);
          //      }
          //  });
        }

        if (isChat){
            lastMessage(user.getId(),holder.lastMsg);
        }else {
            holder.lastMsg.setVisibility(View.GONE);
        }

        if (isChat){
            if (userModelList.get(i).getStatus().equals("online")){
                holder.circleImageViewStatus.setImageResource(R.color.statusOnline);
            }else{
                holder.circleImageViewStatus.setImageResource(R.color.statusOffline);
            }
        }else {
            holder.circleImageViewStatus.setImageResource(R.color.statusOffline);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),MessageActivity.class);
                intent.putExtra("userId",userModelList.get(i).getId());
                intent.putExtra("imageURL",userModelList.get(i).getImageURL());
                intent.putExtra("userName",userModelList.get(i).getUsername());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public void filterList(ArrayList<UserModel> userFilteredList) {
        userModelList = userFilteredList;
        notifyDataSetChanged();
    }


    public static class UsersAdapterViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageViewProfileImage,circleImageViewStatus;
        TextView textViewProfileName;
        private TextView lastMsg;

        public UsersAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

             circleImageViewProfileImage = itemView.findViewById(R.id.recyProImage);
             textViewProfileName = itemView.findViewById(R.id.recyProName);
             circleImageViewStatus = itemView.findViewById(R.id.userStatus);
             lastMsg = itemView.findViewById(R.id.lst_msg);
        }


    }// end of class

    public void lastMessage(final String userid, final TextView lst_msg){
        theLastMsg = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BaatCheet/Chats/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    MessageModel messageModel = snapshot1.getValue(MessageModel.class);
                    if (messageModel.getReceiver().equals(firebaseUser.getUid()) && messageModel.getSender().equals(userid) ||
                            messageModel.getReceiver().equals(userid) && messageModel.getSender().equals(firebaseUser.getUid())){
                                theLastMsg = messageModel.getMessage();
                    }

                }
                switch (theLastMsg){
                    case "default":
                        lst_msg.setText(" ");
                        break;
                    default:
                        lst_msg.setText(theLastMsg);
                        break;
                }
                theLastMsg = "default";

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}// end of class
