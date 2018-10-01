package com.yavin.afficheca.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yavin.afficheca.R;
import com.yavin.afficheca.presentation.model.EventModel;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adaptar that manages a collection of {@link EventModel}.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    public interface OnItemClickListener {
        void onEventItemClicked(EventModel eventModel);
    }

    private List<EventModel> eventsCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    @Inject
    EventsAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.eventsCollection = Collections.emptyList();
    }

    @Override public int getItemCount() {
        return (this.eventsCollection != null) ? this.eventsCollection.size() : 0;
    }

    @Override public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override public void onBindViewHolder(EventViewHolder holder, final int position) {
        final EventModel eventModel = this.eventsCollection.get(position);
        holder.textViewTitle.setText(eventModel.getTitle());
        if(eventModel.getDetail().isEmpty()) {
            holder.textViewDetail.setVisibility(View.GONE);
        } else {
            holder.textViewDetail.setVisibility(View.VISIBLE);
            holder.textViewDetail.setText(eventModel.getDetail());
        }

        holder.itemView.setOnClickListener(v -> {
            if (EventsAdapter.this.onItemClickListener != null) {
                EventsAdapter.this.onItemClickListener.onEventItemClicked(eventModel);
            }
        });
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setEventsCollection(Collection<EventModel> eventsCollection) {
        this.validateEventsCollection(eventsCollection);
        this.eventsCollection = (List<EventModel>) eventsCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateEventsCollection(Collection<EventModel> eventsCollection) {
        if (eventsCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView textViewTitle;

        @BindView(R.id.detail)
        TextView textViewDetail;

        EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}