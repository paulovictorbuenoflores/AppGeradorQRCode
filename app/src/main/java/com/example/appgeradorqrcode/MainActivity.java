package com.example.appgeradorqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class MainActivity extends AppCompatActivity {

    EditText edit_main_nome_qrcode;
    ImageView img_main_qrcode;
    Button bt_main_gerar_qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initformularios();



        bt_main_gerar_qrcode.setOnClickListener(view -> {
            isformulario();
        });
    }

    private void isformulario() {
        if(TextUtils.isEmpty(edit_main_nome_qrcode.getText().toString())){
            edit_main_nome_qrcode.setError("*");
            edit_main_nome_qrcode.requestFocus();
        }else{

            gerarQRCode(edit_main_nome_qrcode.getText().toString());
        }
    }

    private void gerarQRCode(String conteudoDoQRCode) {

        QRCodeWriter qrCodeWriter =new QRCodeWriter();

        try {
        //1 criar uma matriz que vai gerar o pixels do qrcode
            BitMatrix bitMatrix=qrCodeWriter.encode(conteudoDoQRCode, BarcodeFormat.QR_CODE, 196,196);

            //criar dois atributos, 1para altura, e o 2 para tamanho
            int tamanho=bitMatrix.getWidth();
            int altura=bitMatrix.getHeight();

            //a imagem vai ficar em um bitmap
            Bitmap bitmap=Bitmap.createBitmap(tamanho,altura,Bitmap.Config.RGB_565);

            //para desenhar o bitmap do qrcode vou precisar de for
            for(int i=0;i<tamanho;i++){
                for (int j = 0; j <altura ; j++) {
                 //vou setar a imagem, vou contruir a imagem
                 bitmap.setPixel(i,j, bitMatrix.get(i,j)? Color.BLACK : Color.WHITE);

                }
            }
            //agorar é só passar a imagem do bitmap para imagemview
            img_main_qrcode.setImageBitmap(bitmap);

        }catch (WriterException e){
        e.printStackTrace();
        }

    }

    private void initformularios() {
        edit_main_nome_qrcode=findViewById(R.id.edit_main_nome_qrcode);
        img_main_qrcode=findViewById(R.id.img_main_qrcode);
        bt_main_gerar_qrcode=findViewById(R.id.bt_main_gerar_qrcode);

    }


}