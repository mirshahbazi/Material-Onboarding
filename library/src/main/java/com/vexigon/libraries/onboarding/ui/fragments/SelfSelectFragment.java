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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vexigon.libraries.onboarding.R;
import com.vexigon.libraries.onboarding.obj.selfselect.BundledListItem;
import com.vexigon.libraries.onboarding.obj.selfselect.GridViewItem;
import com.vexigon.libraries.onboarding.obj.selfselect.ListItem;
import com.vexigon.libraries.onboarding.obj.selfselect.User;
import com.vexigon.libraries.onboarding.ui.activity.SelfSelectActivity;
import com.vexigon.libraries.onboarding.ui.interfaces.SelfSelectFragmentInterface;
import com.vexigon.libraries.onboarding.util.SelfSelectKeys;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Andrew Quebe on 3/2/2017.
 */

@SuppressWarnings("ConstantConditions")
public class SelfSelectFragment extends Fragment implements SelfSelectFragmentInterface {

    private ListView selfSelectItems;
    private int position;

    public SelfSelectFragment() {
    }

    @SuppressLint("ValidFragment")
    public SelfSelectFragment(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_self_select, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Fragment view containers
        LinearLayout userView = getView().findViewById(R.id.userScreenView);
        LinearLayout selectionView = getView().findViewById(R.id.selectionScreenView);

        // Fragment 1 views
        ImageView userScreenImage = getView().findViewById(R.id.userScreenImage);
        final Spinner userDropdown = getView().findViewById(R.id.userScreenDropdown);
        final Button confirmButton = getView().findViewById(R.id.userScreenConfirmButton);

        // Fragment 2 views
        TextView selectionViewTitle = getView().findViewById(R.id.selectionScreenTitle);
        TextView selectionViewSubtitle = getView().findViewById(R.id.selectionScreenSubtitle);
        selfSelectItems = getView().findViewById(R.id.selectionScreenItems);

        switch (position) {
            case 0:
                userView.setVisibility(View.VISIBLE);

                Glide.with(getActivity()).load(getUserScreenImage(position)).dontAnimate().into(userScreenImage);

                userDropdown.setAdapter(new CustomUserAdapter(getLoggedInUsers()));
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
                        ((SelfSelectActivity) getActivity()).viewPager.setCurrentItem(1, true);
                    }
                });
                break;
            case 1:
                selectionView.setVisibility(View.VISIBLE);

                selectionViewTitle.setText(getSelfSelectTitle());
                selectionViewSubtitle.setText(getSelfSelectSubtitle());

                configureSelfSelectionList();
                break;
        }
    }

    @Override
    public int getUserScreenImage(int position) {
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
    public ArrayList<GridViewItem> getGridViewItems() {
        return (ArrayList<GridViewItem>) getActivity().getIntent().getSerializableExtra(SelfSelectKeys.SELF_SELECT_GRIDVIEW_ITEMS);
    }

    @Override
    public ArrayList<ListItem> getListItems() {
        return (ArrayList<ListItem>) getActivity().getIntent().getSerializableExtra(SelfSelectKeys.SELF_SELECT_LIST_ITEMS);
    }

    private boolean hasBundledItems() {
        return !getBundledListItems().isEmpty();
    }

    private boolean hasGridViewItems() {
        return !getGridViewItems().isEmpty();
    }

    private boolean hasListItems() {
        return !getListItems().isEmpty();
    }

    private void configureSelfSelectionList() {
        if (hasBundledItems()) {
            selfSelectItems.setAdapter(new CustomBundledListItemAdapter(getContext(), R.layout.util_bundled_list_item, getBundledListItems()));
        } else if (hasGridViewItems()) {
            Toast.makeText(getContext(), "Has GridView Items", Toast.LENGTH_SHORT).show();
        } else if (hasListItems()) {
            Toast.makeText(getContext(), "Has List Items", Toast.LENGTH_SHORT).show();
        } else {
            throw new RuntimeException("No self selectable items were specified.");
        }
    }

    @SuppressWarnings("unchecked")
    private class CustomUserAdapter extends BaseAdapter {

        private ArrayList<User> users;

        CustomUserAdapter(@NonNull ArrayList<User> users) {
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
            convertView = lf.inflate(R.layout.util_user_dropdown_item, null);

            CircleImageView profileImage = convertView.findViewById(R.id.profileImage);
            TextView name = convertView.findViewById(R.id.tV_name),
                    email = convertView.findViewById(R.id.tV_email);

            Glide.with(getActivity()).load(users.get(position).getDrawableRes()).dontAnimate().into(profileImage);
            name.setText(users.get(position).getName());
            email.setText(users.get(position).getEmail());

            return convertView;
        }
    }

    private class CustomBundledListItemAdapter extends ArrayAdapter<BundledListItem> {

        ArrayList<BundledListItem> bundledListItems;

        CustomBundledListItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<BundledListItem> objects) {
            super(context, resource, objects);
            this.bundledListItems = objects;
        }

        @SuppressLint("InflateParams")
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.util_bundled_list_item, null);
            }

            final BundledListItem item = getItem(position);

            if (item != null) {
                ImageView itemImage = v.findViewById(R.id.bundledItemImage);
                final TextView itemTitle = v.findViewById(R.id.bundledItemTitle),
                        itemSubtitle = v.findViewById(R.id.bundledItemSubtitle);
                Switch itemToggle = v.findViewById(R.id.bundledItemToggle);

                if (itemImage != null)
                    Glide.with(getContext()).load(item.getDrawableRes()).into(itemImage);

                if (itemTitle != null)
                    itemTitle.setText(item.getItemName());

                if (itemSubtitle != null)
                    itemSubtitle.setText(item.getItemDesc());

                itemToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            if (item.getToggledItemDesc() != null)
                                itemSubtitle.setText(item.getToggledItemDesc());
                        } else {
                            itemSubtitle.setText(item.getItemDesc());
                        }
                    }
                });
            }
            return v;
        }

        @Override
        public int getCount() {
            return bundledListItems.size();
        }
    }

    private class CustomGridViewItemAdapter extends ArrayAdapter<BundledListItem> {

        ArrayList<BundledListItem> gridViewItems;

        CustomGridViewItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<BundledListItem> objects) {
            super(context, resource, objects);
            this.gridViewItems = objects;
        }

        @SuppressLint("InflateParams")
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.util_bundled_list_item, null);
            }

            BundledListItem item = getItem(position);

            if (item != null) {
                ImageView itemImage = v.findViewById(R.id.bundledItemImage);
                TextView itemTitle = v.findViewById(R.id.bundledItemTitle),
                        itemSubtitle = v.findViewById(R.id.bundledItemSubtitle);
                Switch itemToggle = v.findViewById(R.id.bundledItemToggle);

                if (itemImage != null)
                    Glide.with(getContext()).load(item.getDrawableRes()).into(itemImage);

                if (itemTitle != null)
                    itemTitle.setText(item.getItemName());

                if (itemSubtitle != null)
                    itemSubtitle.setText(item.getItemDesc());

                itemToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Toast.makeText(getContext(), "Toggle #" + position + ": " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return v;
        }

        @Override
        public int getCount() {
            return gridViewItems.size();
        }
    }
}
