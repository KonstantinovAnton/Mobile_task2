package com.example.mobile_task1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button btn, btnGoToAdd;

    Connection connection;
    String ConnectionResult = "";

    TextView tvID, tvFname, tvLname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnNext);
        btn.setOnClickListener(this);

        btnGoToAdd = findViewById(R.id.btnGoToAdd);
        btnGoToAdd.setOnClickListener(this);

    }

    public void GetTextFromSql(View v) {

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();

            if (connection != null) {
                String query = "Select * from Persons";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next())
                {
                    tvID.setText(resultSet.getString(1));
                    tvFname.setText(resultSet.getString(2));
                    tvLname.setText(resultSet.getString(3));

                }

                connection.close();

            } else {
                ConnectionResult = "Check connection";
            }

        } catch (Exception ex) {

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnNext:
                startActivity(new Intent(this, ShowData.class));
                break;
            case R.id.btnGoToAdd:
                startActivity(new Intent(this, AddPerson.class));
                break;
        }


        }


}