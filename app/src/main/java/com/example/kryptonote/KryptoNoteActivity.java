package com.example.kryptonote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class KryptoNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    public void onEncrypt(View v)
    {
        try {
            String key = ((EditText) findViewById(R.id.key)).getText().toString();
            if (!key.equals("")){
                String note = ((EditText) findViewById(R.id.data)).getText().toString();
                Cipher c = new Cipher(key);
                ((EditText) findViewById(R.id.data)).setText(c.encrypt(note));
            }
        } catch (Exception e) {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onDecrypt(View v){
        try{
            String key = ((EditText) findViewById(R.id.key)).getText().toString();
            if (!key.equals("")) {
                String note = ((EditText) findViewById(R.id.data)).getText().toString();
                Cipher c = new Cipher(key);
                ((EditText) findViewById(R.id.data)).setText(c.decrypt(note));
            }
        } catch (Exception e){
                Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
                label.show();
        }
    }

    public void onSave(View v) {
        try {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast label = Toast.makeText(this, "Note Saved.", Toast.LENGTH_LONG);
            label.show();
        } catch (Exception e) {
            Toast label = Toast.makeText(this, "Enter a file name to save!", Toast.LENGTH_LONG);
            label.show();

        }
    }

    public void onLoad(View v) {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();

            try {
                File dir = this.getFilesDir();
                File file = new File(dir, name);
                FileReader fr = new FileReader(file);
                // given a FileReader instance named fr:

                String show = "";
                for (int c = fr.read(); c != -1; c = fr.read()) {
                    show += (char) c;
                }

                fr.close();
                ((EditText) findViewById(R.id.data)).setText(show);
                Toast label = Toast.makeText(this, "Note Loaded.", Toast.LENGTH_LONG);
                label.show();
            } catch (Exception e) {
                if (name.equals("")) {
                    Toast label = Toast.makeText(this, "Enter a file name to load!", Toast.LENGTH_LONG);
                    label.show();
                } else {
                    Toast label = Toast.makeText(this, "File name not found", Toast.LENGTH_LONG);
                    label.show();

                }
            }

    }

}