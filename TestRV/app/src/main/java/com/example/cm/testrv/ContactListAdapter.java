package com.example.cm.testrv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cm on 2017/10/24.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyContactHolder> {

    List<String> contactsList;
    OnItemClickListener onItemClickListener;

    public ContactListAdapter(List<String> listContacts) {
        contactsList = listContacts;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public MyContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_view_holder_layout, parent, false);
        return new MyContactHolder(view);
    }

    // TODO: 2017/10/24  将点击事件甩出去。
    @Override
    public void onBindViewHolder(MyContactHolder holder, final int position) {
        holder.setContacts(contactsList.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class MyContactHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyContactHolder(View itemView) {
            super(itemView);
            mTextView = ((TextView) itemView.findViewById(R.id.my_holder_textview));
        }

        public void setContacts(String contacts) {
            mTextView.setText(contacts);
        }
    }
}

