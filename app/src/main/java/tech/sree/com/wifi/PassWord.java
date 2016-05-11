package tech.sree.com.wifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PassWord extends AppCompatActivity {
    EditText currPwd, newPwd, confirmPwd, email;
    Button passButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        currPwd = (EditText) findViewById(R.id.curPass);
        newPwd = (EditText) findViewById(R.id.newPass1);
        confirmPwd = (EditText) findViewById(R.id.newPass2);
        email = (EditText) findViewById(R.id.email);

    }

    public void validatePassWord(View V) {
        if (email.getText().toString() == "") {
            Toast.makeText(this, "Enter the proper Email Id ", Toast.LENGTH_LONG).show();
        } else if (currPwd.getText().toString().equals(MainActivity.getInstance().getpresentPassword())
                && newPwd.getText().toString().equals(confirmPwd.getText().toString())) {
            MainActivity.getInstance().setNewPassWord(confirmPwd.getText().toString());
            String[] Emails = {email.getText().toString(),""};
            sendEmail(confirmPwd.getText().toString(), Emails);
            finish();
            // return true;
        } else {
            Toast.makeText(this, "Entered Wrong Password...\n Plz ... Enter Proper Details", Toast.LENGTH_LONG).show();
            //return false;
        }
    }

    private void sendEmail(String newPwd,   String[] emails) {
       // String[] email = new String[emails];
        String Subject = "Wifi Controller password is Changed ... ";
        String MessageBody = "Wifi Controller : New password = " + newPwd;
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emails);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, MessageBody);


        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent,
                    "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "No email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }

    }

}