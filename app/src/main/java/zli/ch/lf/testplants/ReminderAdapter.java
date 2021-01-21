package zli.ch.lf.testplants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zli.ch.lf.testplants.Data.EntityKlasse;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 *
 * Reminder Klasse von Plants for Dummies
 *
 * Beinhaltet vor allem den ViewHolder(RecycleView), der ViewHolder beschreibt eine Element-Ansicht innerhalb der RecyclerView.
 *
 * @quelle: https://projectnotes.org/it-projects/simple-reminder-android-application-with-source-code/
 *          https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder
 */
public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder>
{
    List<EntityKlasse> entityKlassen;
    Context context;

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView plantReminderText, zeitUndDatumPlantReminder;
        private LinearLayout linearLayout;

        public ViewHolder( @NonNull View view)
        {
            super(view);
            plantReminderText = (TextView) itemView.findViewById(R.id.reminder);
            zeitUndDatumPlantReminder = (TextView) itemView.findViewById(R.id.tV_zeit_und_datum);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);
        }
    }

    public ReminderAdapter(Context context, List<EntityKlasse> entityKlassen)
    {
        this.context = context;
        this.entityKlassen = entityKlassen;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos)
    {
        holder.plantReminderText.setText(entityKlassen.get(pos).getPlantname());
        holder.zeitUndDatumPlantReminder.setText(entityKlassen.get(pos).getPlantdate() + " " + entityKlassen.get(pos).getPlanttime());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false));
    }

    @Override
    public int getItemCount()
    {
        return entityKlassen.size();
    }
}
