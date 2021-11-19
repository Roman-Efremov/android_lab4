package com.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CommentFormActivity extends FormActivity {

    private EditText inputComment;

    private String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_form);

        Intent intent = getIntent();
        comment = intent.getStringExtra(MainActivity.COMMENT);

        inputComment = findViewById(R.id.input_comment);
        inputComment.setText(comment);
    }

    public void save(View view) {
        Intent returnIntent = new Intent();
        comment = inputComment.getText().toString();
        returnIntent.putExtra(MainActivity.COMMENT, comment);
        this.setResult(Activity.RESULT_OK, returnIntent);
        this.finish();
    }

    public void cancel(View view) {
        this.setResult(Activity.RESULT_CANCELED);
        this.finish();
    }
}