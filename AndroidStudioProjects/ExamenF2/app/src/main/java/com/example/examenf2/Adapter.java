package com.example.examenf2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Modelo> lista=new ArrayList<Modelo>(); //Es el listado de datos. En forma de arreglo de modelo
    public Adapter(Context context, int layout, ArrayList<Modelo> lista) { //Constructor del adaptador
        //parametros de inicializaciòn
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }
//Sobrecarga del metodo getcount para retornar la lista
    @Override
    public int getCount() {
        return lista.size();
    }

    //Sobrecarga del metodo getItem que retorna un elemento del arreglo segun un indice de posiciòn
    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

// sobrecarga de de getItemId para obtener el id de un elemento segun la posicion de este
    @Override
    public long getItemId(int position) {
        return position;
    }

//Declaracion del viewholder
    static class ViewHolder {
        public TextView textViewNombres;
        public TextView textViewApellidos;
        public TextView textViewIdentificacion;
        public TextView textViewCargo;
        public TextView textViewArea;
        public TextView textViewTelefono;
        public ImageView imageViewList;
    }
//Sobrecarga del metodo getView para asignar un layout y establecer los datos necesarios
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder myholder;
        if(convertView == null || convertView.getTag() == null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(R.layout.item_list,null);
            myholder= new ViewHolder();
            myholder.textViewNombres = (TextView)convertView.findViewById(R.id.textViewNombres);
            myholder.textViewApellidos = (TextView)convertView.findViewById(R.id.textViewApellidos);
            myholder.textViewIdentificacion = (TextView)convertView.findViewById(R.id.textViewIdentificacion);
            myholder.textViewCargo = (TextView)convertView.findViewById(R.id.textViewCargo);
            myholder.textViewArea = (TextView)convertView.findViewById(R.id.textViewArea);
            myholder.textViewTelefono = (TextView)convertView.findViewById(R.id.textViewTelefono);
            myholder.imageViewList = (ImageView)convertView.findViewById(R.id.imageViewList);
        }else{
            myholder=(ViewHolder)convertView.getTag();
        }
        Modelo current_Item= lista.get(position);
        myholder.textViewNombres.setText(current_Item.getNombres());
        myholder.textViewApellidos.setText(current_Item.getApellidos());
        myholder.textViewIdentificacion.setText(current_Item.getIdentificacion());
        myholder.textViewCargo.setText(current_Item.getCargo());
        myholder.textViewArea.setText(current_Item.getArea());
        myholder.textViewTelefono.setText(current_Item.getTelefono());
        myholder.imageViewList.setImageResource(current_Item.getImagen());
        return convertView;
    }
}
