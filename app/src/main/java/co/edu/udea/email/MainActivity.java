package co.edu.udea.email;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText to, subject, message;
    Button send;
    View snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        to = (EditText) findViewById(R.id.et_to);
        subject = (EditText) findViewById(R.id.et_subject);
        message = (EditText) findViewById(R.id.et_message);
        send = (Button) findViewById(R.id.bt_send);
        snackbar = findViewById(R.id.snackbar);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!to.getText().toString().equals("") && !subject.getText().toString().equals("") && !message.getText().toString().equals("")){
                    sendEmail();
                }else{
                    Snackbar.make(snackbar, "Informacion Incompleta", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendEmail(){
        Intent intentGmail = new Intent(Intent.ACTION_SEND);
        intentGmail.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");
        intentGmail.putExtra(Intent.EXTRA_EMAIL, new String[] { to.getText().toString() });
        intentGmail.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        intentGmail.setType("plain/text");
        intentGmail.putExtra(Intent.EXTRA_TEXT, message.getText());
        startActivity(intentGmail);
    }
}
