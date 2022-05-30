package com.application.todoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.application.todoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NoteViewModels noteViewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModels= new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider
                .AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModels.class);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DataInsertActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            assert data != null;
            String title=data.getStringExtra("title");
            String desc=data.getStringExtra("desc");

            Note note=new Note(title, desc);
            noteViewModels.insert(note);
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        }
    }
}