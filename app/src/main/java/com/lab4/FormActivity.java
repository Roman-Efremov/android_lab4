package com.lab4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class FormActivity extends AppCompatActivity implements ConfirmationDialogFragment.ConfirmationListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_cancel:
                showConfirmationDialog();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void showConfirmationDialog() {
        DialogFragment dialog = new ConfirmationDialogFragment();
        dialog.show(getSupportFragmentManager(), "ConfirmationDialogFragment");
    }

    @Override
    public void confirmButtonClicked() {
        this.setResult(Activity.RESULT_CANCELED);
        this.finish();
    }

    @Override
    public void cancelButtonClicked() {}
}
