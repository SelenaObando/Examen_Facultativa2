package com.example.examenf2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView myList; //declaracion de listview
    Modelo modelo; //declaracion de modelo
    Adapter adaptador; //declaracion del adapter
    ArrayList<Modelo> lista; //declaraciòn del listado de datos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList=(ListView)findViewById(R.id.list1); //selecciona list1 como interfaz del objeto myList
        myList.setOnCreateContextMenuListener(this); //establece el menu contextual de myList que serà una implementaciòn sobrecargada de nuestra clase mainactivity
        llenarLista(); //llama a la funciòn llenar lista

    }
    void llenarLista(){
        lista =new ArrayList<Modelo>(); //inicializamos una instancia para nuestra lista
//creamos 3 objetos y los añadimos
        modelo =new Modelo("Selena","Obando", "1245", "Gerente general", "Administraciòn", "86185955", R.drawable.descarga);
        lista.add(modelo);

        modelo =new Modelo("Heyler", "Obando", "5472", "Secretario", "Administraciòn", "89153248", R.drawable.hombre_negocio);
        lista.add(modelo);

        modelo =new Modelo("Napoleòn", "Gòmez", "1524", "Contador", "Administraciòn", "85203147", R.drawable.vector);
        lista.add(modelo);

        adaptador =new Adapter(this,R.layout.item_list, lista); //inicializamos una instancia del adapter, le pasamos los parametros necesarios
        myList.setAdapter(adaptador); //le asignamos el adapter al listview

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menú"); //le asignamos un tìtulo
        getMenuInflater().inflate(R.menu.menu, menu); //infla un menu que serà el que tiene las opciones
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //obtenemos informaciòn del menuitem que se selecciono y que esta relacionada al elemento del adaptador

        switch (item.getItemId()) {
            case R.id.Salir:
                this.finish(); //cerramos la aplicaciòn
                break;
            case R.id.Agregar:
                //definimos un cuadro de dialogo en donde mostraremos nuestro layout para guardar
                final Dialog dlg = new Dialog(this);
                dlg.setContentView(R.layout.agregar_nuevo);
                dlg.setTitle("Nuevo Empleado...");
                dlg.setCancelable(false);
//definimos los botones agregar y cancelar
                Button buttonAgregar = (Button) dlg.findViewById(R.id.btAdd);
                Button buttonCancelar = (Button) dlg.findViewById(R.id.btCancel);
//asignamos el evento click al boton agregar
                buttonAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        //creamos los objetos de edittext para obtener los valores que ingreso el usuario
                        EditText edNombre = (EditText) dlg.findViewById(R.id.editText_Nombre);
                        EditText edApellidos = (EditText) dlg.findViewById(R.id.editText_Apellidos);
                        EditText edIdentificacion = (EditText) dlg.findViewById(R.id.editText_Identif);
                        EditText edCargo = (EditText) dlg.findViewById(R.id.editText_Carg);
                        EditText edArea = (EditText) dlg.findViewById(R.id.editText_Area);
                        EditText edTelefono = (EditText) dlg.findViewById(R.id.editText_Telef);

                        //se valida que no se añadieran datos vacìos a los edit text
                        if (edNombre.getText().toString().isEmpty()
                                || edApellidos.getText().toString().isEmpty()
                                || edIdentificacion.getText().toString().isEmpty()
                                || edCargo.getText().toString().isEmpty()
                                || edArea.getText().toString().isEmpty()
                                || edTelefono.getText().toString().isEmpty()
                        ) {
                            Toast.makeText(MainActivity.this, "Campos vacíos", Toast.LENGTH_SHORT).show();
                        }else{
                            //se crea el modelo y se le añade los datos de los edit texts
                            Modelo empleado = new Modelo();
                            empleado.setNombres(edNombre.getText().toString());
                            empleado.setApellidos(edApellidos.getText().toString());
                            empleado.setIdentificacion(edIdentificacion.getText().toString());
                            empleado.setCargo(edCargo.getText().toString());
                            empleado.setArea(edArea.getText().toString());
                            empleado.setTelefono(edTelefono.getText().toString());
                            empleado.setImagen(R.drawable.empleadonuevo);

                            //se añade el modelo a la lista de datos
                            lista.add(empleado);
                            adaptador.notifyDataSetChanged();//notifica al adapter que hay cambios, para que actalice el listview

                            //se limpia los edit text
                            edNombre.setText("");
                            edApellidos.setText("");
                            edIdentificacion.setText("");
                            edCargo.setText("");
                            edArea.setText("");
                            edTelefono.setText("");
                            dlg.cancel();
                        }
                    }

                });
                buttonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //se añade el evento click al botòn cancelar para cerra el dialogo
                        dlg.cancel();
                    }
                });//
                dlg.show();
                break;
            case R.id.Eliminar:
                this.lista.remove(info.position); //se elimina un elemento de la lista segun la posicion del elemento que mostro el menu contextual
                this.adaptador.notifyDataSetChanged(); //se notifica al adaptador que se removio un elemento para que actualice el listview
                break;
        }

        return super.onContextItemSelected(item);
    }

}
