package co.edu.univalle.realtimedatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public void crearUsuario(UsuarioDTO usuarioDTO) throws Exception {

            try{
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("Usuarios");
                databaseReference.child(usuarioDTO.getCedula()+"").setValue(usuarioDTO);

            }catch (Exception exception){
                throw new Exception("Error Creando un Usuario:"+ exception.getMessage());
            }
    }
}
