package com.example.jetsoftpro.recyclertest.feature;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jetsoftpro.recyclertest.feature.Models.Person;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    final private ListItemClickListener onClickListener;
    private Person[] personData;

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.person_list_item, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personData[position];
        holder.nameTextView.setText(new StringBuilder().append(person.getFirstName()).append(" ").append(person.getLastName()).toString());
        holder.cityTextView.setText(new StringBuilder().append(person.getCity()));
    }

    @Override
    public int getItemCount() {
        if(personData == null) return 0;
        return personData.length;
    }


    public interface ListItemClickListener {
        void OnClick(Person selectedPerson);
    }

    public RecyclerAdapter(ListItemClickListener listener){

        onClickListener = listener;
    }


    public void setPersonData(Person[] data){
        personData = data;
        notifyDataSetChanged();
    }


    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView nameTextView;
        public final TextView cityTextView;

        public PersonViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            cityTextView = (TextView) itemView.findViewById(R.id.city_text_view);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Person selectedPerson = personData[clickedPosition];
            onClickListener.OnClick(selectedPerson);
        }
    }
}
