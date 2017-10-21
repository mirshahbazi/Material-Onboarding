package com.vexigon.libraries.onboarding.ui.fragments;

/*
 * Copyright 2017 Vexigon, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vexigon.libraries.onboarding.R;
import com.vexigon.libraries.onboarding.obj.selfselect.BundledListItem;
import com.vexigon.libraries.onboarding.obj.selfselect.GridViewItem;
import com.vexigon.libraries.onboarding.obj.selfselect.ListItem;
import com.vexigon.libraries.onboarding.obj.selfselect.User;
import com.vexigon.libraries.onboarding.ui.interfaces.SelfSelectFragmentInterface;
import com.vexigon.libraries.onboarding.util.SelfSelectKeys;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Andrew Quebe on 3/2/2017.
 */

public class SelfSelectFragment extends Fragment implements SelfSelectFragmentInterface {

    LinearLayout userView, selectionView;
    Spinner userDropdown;
    ImageView userScreenImage;
    Button confirmButton, backButton, doneButton;



    int position;

    public SelfSelectFragment() {
    }

    @SuppressLint("ValidFragment")
    public SelfSelectFragment(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.self_select_fragment, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userView = (LinearLayout) getView().findViewById(R.id.userScreenView);
        selectionView = (LinearLayout) getView().findViewById(R.id.selectionScreenView);
        userScreenImage = (ImageView) getView().findViewById(R.id.userScreenImage);
        userDropdown = (Spinner) getView().findViewById(R.id.userDropdown);
        confirmButton = (Button) getView().findViewById(R.id.confirmButton);

        switch (position) {
            case 0:
                userView.setVisibility(View.VISIBLE);
                selectionView.setVisibility(View.GONE);

                Glide.with(getActivity()).load(getPageImage(position)).dontAnimate().into(userScreenImage);

                userDropdown.setAdapter(new CustomAdapter(getLoggedInUsers()));
                userDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        confirmButton.setText("Continue as " + ((User) userDropdown.getSelectedItem()).getName());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        confirmButton.setText("Continue as " + ((User) userDropdown.getSelectedItem()).getName());
                    }
                });

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //((SelfSelectActivity) getActivity())
                    }
                });
                break;
            case 1:
                selectionView.setVisibility(View.VISIBLE);
                userView.setVisibility(View.GONE);


                break;
        }
    }

    @Override
    public int getPageImage(int position) {
        return getActivity().getIntent().getIntExtra(SelfSelectKeys.USER_PAGE_DRAWABALE_RES, 0);
    }

    @Override
    public ArrayList<User> getLoggedInUsers() {
        return (ArrayList<User>) getActivity().getIntent().getSerializableExtra(SelfSelectKeys.USER_PAGE_USERS);
    }

    @Override
    public String getSelfSelectTitle() {
        return getActivity().getIntent().getStringExtra(SelfSelectKeys.SELF_SELECT_PAGE_TITLE);
    }

    @Override
    public String getSelfSelectSubtitle() {
        return getActivity().getIntent().getStringExtra(SelfSelectKeys.SELF_SELECT_PAGE_SUBTITLE);
    }

    @Override
    public ArrayList<BundledListItem> getBundledListItems() {
        return (ArrayList<BundledListItem>) getActivity().getIntent().getSerializableExtra(SelfSelectKeys.SELF_SELECT_BUNDLED_ITEMS);
    }

    @Override
    public ArrayList<GridViewItem> getGridviewItems() {
        return (ArrayList<GridViewItem>) getActivity().getIntent().getSerializableExtra(SelfSelectKeys.SELF_SELECT_GRIDVIEW_ITEMS);
    }

    @Override
    public ArrayList<ListItem> getListItems() {
        return (ArrayList<ListItem>) getActivity().getIntent().getSerializableExtra(SelfSelectKeys.SELF_SELECT_LIST_ITEMS);
    }

    @SuppressWarnings("unchecked")
    private class CustomAdapter extends BaseAdapter {

        private ArrayList<User> users;

        CustomAdapter(@NonNull ArrayList<User> users) {
            this.users = users;
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public User getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return users.size();
        }

        @SuppressLint({"ViewHolder", "InflateParams"})
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater lf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = lf.inflate(R.layout.spinner_layout, null);

            CircleImageView profileImage = (CircleImageView) convertView.findViewById(R.id.profileImage);
            TextView name = (TextView) convertView.findViewById(R.id.tV_name),
                    email = (TextView) convertView.findViewById(R.id.tV_email);

            Glide.with(getActivity()).load(users.get(position).getDrawableRes()).dontAnimate().into(profileImage);
            name.setText(users.get(position).getName());
            email.setText(users.get(position).getEmail());

            return convertView;
        }
    }
}
