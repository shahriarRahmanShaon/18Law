package com.ovi.a16flawbd.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ovi.a16flawbd.LawList.showingLawDetails;
import com.ovi.a16flawbd.R;


public class LawListFragment extends Fragment {

    ListView listView;
    final String[] items = new String[] { "Fundamental rights in Bangladesh", "Case law", "Codification and language",
            "Freedom of information", "Criminal law", "Company law", "Contact law", "Religious law",
            "Tax law", "Labour law", "Property law", "Intellectual property law", "Judiciary", "Judicial review", "Alternative dispute revolution", "Legal profession" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_law_list, container, false);

        listView = view.findViewById(R.id.list_View);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    startActivity(new Intent(getActivity(), showingLawDetails.class));

            }
        });
        return view;
    }
}