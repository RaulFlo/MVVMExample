package com.example.android.mvvmexample;

import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {



    private static OnItemClickListener listener;
    void setOnItemClickListener(OnItemClickListener listener) {
        NoteAdapter.listener = listener;
    }

    NoteAdapter() {

        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));

        Context context = holder.itemView.getContext();


        //change color of background based on priority . also different ways to get int color
        switch (currentNote.getPriority()) {
            case 1:
            case 2:
            case 3:
                holder.colorBackground.setBackgroundColor(context.getColor(R.color.colorGreen));
                return;
            case 4:
            case 5:
            case 6:
            case 7:
                holder.colorBackground.setBackgroundColor(Color.parseColor("#FFFF00"));
                return;
            case 8:
            case 9:
            case 10:
                holder.colorBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorMaroon));
                return;
            default:
                holder.colorBackground.setBackgroundColor(Color.GRAY);
        }


    }


    //To get a note from the adapter to the outside. Added for swipe delete.
    Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final TextView textViewPriority;
        private final View colorBackground;

        NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            colorBackground = itemView.findViewById(R.id.relative_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    //interface to be able to click on notes in recycle view
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }




}
