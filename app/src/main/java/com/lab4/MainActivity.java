package com.lab4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ConfirmationDialogFragment.ConfirmationListener {

    public static final String NAME = "NAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String COMMENT = "COMMENT";

    private TextView nameText;
    private TextView addressText;
    private TextView commentText;

    private ActivityResultLauncher<Intent> formActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent resultData = result.getData();

                    String name = resultData.getStringExtra(NAME);
                    if (name != null)
                        nameText.setText(name);
                    String address = resultData.getStringExtra(ADDRESS);
                    if (address != null)
                        addressText.setText(address);
                    String comment = resultData.getStringExtra(COMMENT);
                    if (comment != null)
                        commentText.setText(comment);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.text_name);
        addressText = findViewById(R.id.text_address);
        commentText = findViewById(R.id.text_comment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_exit:
                showConfirmationDialog();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void editName(View view) {
        Intent intent = new Intent(MainActivity.this, NameFormActivity.class);
        String name = nameText.getText().toString();
        intent.putExtra(NAME, name);
        formActivityResultLauncher.launch(intent);
    }

    public void editAddress(View view) {
        Intent intent = new Intent(MainActivity.this, AddressFormActivity.class);
        String address = addressText.getText().toString();
        intent.putExtra(ADDRESS, address);
        formActivityResultLauncher.launch(intent);
    }

    public void editComment(View view) {
        Intent intent = new Intent(MainActivity.this, CommentFormActivity.class);
        String comment = commentText.getText().toString();
        intent.putExtra(COMMENT, comment);
        formActivityResultLauncher.launch(intent);
    }

    public void showConfirmationDialog() {
        DialogFragment dialog = new ConfirmationDialogFragment();
        dialog.show(getSupportFragmentManager(), "ConfirmationDialogFragment");
    }

    @Override
    public void confirmButtonClicked() {
        finishAndRemoveTask();
        System.exit(0);
    }

    @Override
    public void cancelButtonClicked() {}
}