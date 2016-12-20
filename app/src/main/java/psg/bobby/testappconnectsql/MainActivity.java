package psg.bobby.testappconnectsql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    TextView mcondtionTextView;
    Button sunnyButton;
    Button foggyButton;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conRef = mRootRef.child("condition");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //get ui element
        mcondtionTextView = (TextView) findViewById(R.id.textView1);
        sunnyButton = (Button) findViewById(R.id.button2);
        foggyButton = (Button) findViewById(R.id.button3);

    }

    @Override
    protected void onStart() {
        super.onStart();

        conRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String _data = dataSnapshot.getValue(String.class);
                mcondtionTextView.setText(_data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sunnyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conRef.setValue("Sunny");
            }
        });

        foggyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conRef.setValue("Foggy");
            }
        });
    }
}
