package com.example.firebaseapp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


//    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference(); //Salvar dados no Firebase
//    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    private ImageView imageFoto;
    private Button buttonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUpload = findViewById(R.id.buttonUpload);
        imageFoto = findViewById(R.id.imageFoto);

        // Evento de click
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Configurar para  imagem ser salva em memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

                //Recuperar bitmap da image, (imagem a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

                //Comprimir bitmap para um formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                //converter o boas para pixel brutos em uma matriz de bytes
                //(dados da imagem)
                byte[] dadosImagem = baos.toByteArray();

                //Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens"); //pasta
                StorageReference imagemRef = imagens.child("celular.jpeg"); //arquivo


                /* Download do arquivo */
                Glide.with(MainActivity.this)
                        .using(new FirebaseImageLoader())
                        .load(imagemRef)
                        .into(imageFoto);



                /*Deletar arquivo
                imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this, "Erro ao deletar o arquivo: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();

                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Sucesso ao deletar o arquivo: ",
                                Toast.LENGTH_LONG).show();
                    }
                });*/


                //Nome da imagem
//                String nomeArquivo = UUID.randomUUID().toString();

//                StorageReference imagemRef = imagens.child(nomeArquivo + ".jpeg"); //arquivo
//
//                //Retorna objeto que irá controlar o upload
              /*  UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this, "Upload da imagem falhou: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Uri url = taskSnapshot.getDownloadUrl();
                        Toast.makeText(MainActivity.this, "Sucesso ao fazer upload: " + url.toString(),
                                Toast.LENGTH_LONG).show();

                    }
                }); */

            }
        });

//        DatabaseReference usuarios = referencia.child("usuarios");

//        DatabaseReference usuarioPesquisa = usuarios.child("-MLeO0sMgtpUof72JY8b");

//        Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Rafael");
//         Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(3);

//        Query usuarioPesquisa = usuarios.orderByKey().limitToLast(3);

        //Maior ou igual (>=)
//        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(35);

        //Menor ou iguar (<=)
//        Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(22);

        // Entre dois valores
//        Query usuarioPesquisa = usuarios.orderByChild("idade")
//                                        .startAt(18)
//                                        .endAt(30);


        // filtrar palavras
//        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("Raf")
//                                        .endAt("Raf" + "\uf8ff");


//        usuarioPesquisa.addValueEventListener(new ValueEventListener() {


//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                Usuario dadosUsuario = dataSnapshot.getValue(Usuario.class);
////
//                Log.i("Dados do usuário: ", dataSnapshot.getValue().toString());
////                Log.i("Dados do usuário: ", "nome: " + dadosUsuario.getNome() +  " sobrenome: " + dadosUsuario.getSobrenome() + " idade: " + dadosUsuario.getIdade()   );
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


//        Usuario usuario = new Usuario();
//        usuario.setNome("Rodrigo");
//        usuario.setSobrenome("Matos");
//        usuario.setIdade(35);
//
//        usuarios.push().setValue(usuario); // identificador único


        //Deslogar usuario
//        usuario.signOut();


        //logar usuário
//        usuario.signInWithEmailAndPassword("rafaelmadakis2@gmail.com", "ra123456" )
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.i("SIGNUSER: ", "Sucesso ao logar o usuário" );
//                       }else{
//                            Log.i("SIGNUSER: ", "Erro ao logar o usuário" );
//                        }
//                    }
//                });


        //verificar usuario logado
//        if (usuario.getCurrentUser() != null) {
//            Log.i("CREATEDUSER: ", "usuario logado" );
//        }else{
//            Log.i("CREATEDUSER: ", "usuario não logado" );
//        }


        //Cadastrar um usuario
//        usuario.createUserWithEmailAndPassword("rafaelmadakis2@gmail.com", "ra123456")
//                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.i("CREATEDUSER: ", "Sucesso ao cadastrar o usuário" );
//                        }else{
//                            Log.i("CREATEDUSER: ", "Erro ao cadastrar o usuário" );
//                        }
//                    }
//                });


//
//        DatabaseReference usuarios =  referencia.child("usuarios");
//        DatabaseReference produtos = referencia.child("produtos");
//
//        //recuperar dados firebase
//        usuarios.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.i("FIREBASE: ", dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        //Salvar dados no firebase
//        Usuario usuario = new Usuario();
//        usuario.setNome("Maria");
//        usuario.setSobrenome("Silva");
//        usuario.setIdade(45);

//        Produto produto = new Produto();
//        produto.setDescricao("Acer Aspire");
//        produto.setMarca("Acer");
//        produto.setPreco(999.99);
//
//        produtos.child("002").setValue(produto);
//        usuarios.child("002").setValue(usuario);

    }


}




