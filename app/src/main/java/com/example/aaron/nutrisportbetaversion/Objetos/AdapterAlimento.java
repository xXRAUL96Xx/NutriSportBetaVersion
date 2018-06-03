package com.example.aaron.nutrisportbetaversion.Objetos;

import android.app.AlertDialog;
import android.content.Context;
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
 * Created by aaron on 06/05/2018.
 */

public class AdapterAlimento extends RecyclerView.Adapter<AdapterAlimento.AlimentosViewHolder>{
    private List<Alimento> alimentos;
    private Context mContext;
    static Alimento ap;
    public AdapterAlimento(List<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    @Override
    public AlimentosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_alimento, parent,false);
        AlimentosViewHolder holder = new AlimentosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlimentosViewHolder holder, int position) {
        holder.bindNumber(alimentos.get(position));
    }

    @Override
    public int getItemCount() { return alimentos.size();}

    public void delete(int position){
        alimentos.remove(position);
        notifyItemRemoved(position);
    }

    public class AlimentosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewProducto, textViewProteinas, textViewHidratos, textViewGrasas, textViewFibra, textViewEnergia;
        public AlimentosViewHolder(View itemView) {
            super(itemView);
            textViewProducto = itemView.findViewById(R.id.producto);
            textViewHidratos = itemView.findViewById(R.id.hidratos);
            textViewProteinas = itemView.findViewById(R.id.proteinas);
            textViewGrasas =  itemView.findViewById(R.id.grasas);
            textViewFibra = itemView.findViewById(R.id.fibra);
            textViewEnergia =  itemView.findViewById(R.id.energia);
            itemView.setOnClickListener(this);

            final View finalConvertView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(finalConvertView.getContext());
                    alertDialogBuilder.setMessage("Desea borrar el producto?").setCancelable(false).setPositiveButton("si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ap = alimentos.get(getAdapterPosition());
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
        public void bindNumber(Alimento alimento){
            textViewProducto.setText(alimento.getProducto());
            textViewHidratos.setText( ""+alimento.getHidratos());
            textViewProteinas.setText( ""+alimento.getProteinas());
            textViewGrasas.setText(""+alimento.getGrasas());
            textViewFibra.setText(""+alimento.getFibra());
            textViewEnergia.setText(""+alimento.getEnergia());
        }

        @Override
        public void onClick(View v) {
            delete(getAdapterPosition());
            //final Dialog dialog = new Dialog();
        }

    }
    private void delete_data(){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("alimentos").child(ap.getKey()).removeValue();
    }
}
