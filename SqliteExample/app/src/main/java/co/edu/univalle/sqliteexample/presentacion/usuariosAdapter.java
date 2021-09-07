package co.edu.univalle.sqliteexample.presentacion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import co.edu.univalle.sqliteexample.R;
import co.edu.univalle.sqliteexample.dominio.entidades.Usuario;

public class usuariosAdapter extends RecyclerView.Adapter<usuariosAdapter.ViewHolder> {

    private ArrayList<Usuario> data;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre, tvApellido, tvTipoUsuario, tvCedula;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCedula = (TextView) itemView.findViewById(R.id.cedula);
            tvNombre = (TextView) itemView.findViewById(R.id.nombre);
            tvApellido = (TextView) itemView.findViewById(R.id.apellido);
            tvTipoUsuario = (TextView) itemView.findViewById(R.id.tipoUsuario);
        }
    }

    public usuariosAdapter(ArrayList<Usuario> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_lista_usuarios, viewGroup, false);
        ViewHolder vholder = new ViewHolder(v);
        return vholder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvCedula.setText(data.get(i).getCedula());
        viewHolder.tvNombre.setText(data.get(i).getNombre());
        viewHolder.tvApellido.setText(data.get(i).getApellido());
        viewHolder.tvTipoUsuario.setText(data.get(i).getIdTipoUsuario().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
