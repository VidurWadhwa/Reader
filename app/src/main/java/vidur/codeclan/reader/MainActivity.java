package vidur.codeclan.reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2;
    EditText et1,et2,et3;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button)findViewById(R.id.button2);
        bt2 = (Button)findViewById(R.id.button4);
        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        tv1 = (TextView)findViewById(R.id.textView2);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference ref = firebaseDatabase.getReference();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Library lib = new Library();
                String name = et1.getText().toString();
                String book = et2.getText().toString();
                lib.setaName(name);
                lib.setbName(book);
                ref.child("Library").child("BookInfo").setValue(lib);
                Toast.makeText(getApplicationContext() , "Record Entered" , Toast.LENGTH_LONG).show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.orderByChild("aName").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String author = et3.getText().toString();
                        Library lib = dataSnapshot.getValue(Library.class);
                        int result = author.compareTo(lib.getaName());

                        if (result == 0)
                        {
                            Toast.makeText(getApplicationContext(),"Yes the Data is present in the Database",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Error!!!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        /*final DatabaseReference dinosaursRef = database.getReference("dinosaurs");
        dinosaursRef.orderByChild("height").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Dinosaur dinosaur = dataSnapshot.getValue(Dinosaur.class);
                System.out.println(dataSnapshot.getKey() + " was " + dinosaur.height + " meters tall.");
            }*/





    }
}
