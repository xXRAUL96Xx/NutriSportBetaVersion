package com.example.aaron.nutrisportbetaversion.Objetos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.aaron.nutrisportbetaversion.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by aaron on 09/05/2018.
 */

public class AdapterEntrenamiento extends RecyclerView.Adapter<AdapterEntrenamiento.EntrenamientosViewHolder>{
    List<Entrenamiento>entrenamientos;
    static Entrenamiento ar;

    public AdapterEntrenamiento(List<Entrenamiento> entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    @Override
    public EntrenamientosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_entrenamiento, parent, false);
        EntrenamientosViewHolder holder = new EntrenamientosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EntrenamientosViewHolder holder, int position) {
        holder.bindNumber(entrenamientos.get(position));
    }

    public void delete(int position){
        entrenamientos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {return entrenamientos.size();}

    public class EntrenamientosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewdia, textViewtipo_entrenamiento, textViewtime_Duration, textViewobservaciones;
        public EntrenamientosViewHolder(View itemView) {
            super(itemView);
            textViewdia = itemView.findViewById(R.id.diaRecyTra);
            textViewtipo_entrenamiento = itemView.findViewById(R.id.tipo_rutinaRecyTra);
            textViewtime_Duration = itemView.findViewById(R.id.time_durationRecyTra);
            textViewobservaciones = itemView.findViewById(R.id.observacionesRecyTra);
            itemView.setOnClickListener(this);

            final View finalConvertView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(finalConvertView.getContext());
                    alertDialogBuilder.setMessage("Desea borrar el entrenamiento?").setCancelable(false).setPositiveButton("si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ar = entrenamientos.get(getAdapterPosition());
                            delete_data();
                            delete(getAdapterPosition());
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        }

        public void bindNumber(Entrenamiento entrenamiento){
            textViewdia.setText(entrenamiento.getDia());
            textViewtipo_entrenamiento.setText(""+entrenamiento.getTipo_entrenamiento());
            textViewtime_Duration.setText(""+entrenamiento.getTime_duration());
            textViewobservaciones.setText(""+entrenamiento.getObservaciones());
        }

        @Override
        public void onClick(View view) {
            delete(getAdapterPosition());
        }
    }
    private void delete_data(){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("entrenamientos").child(ar.getKey()).removeValue();
    }
}
