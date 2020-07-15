package com.example.database_project_dataentryoperator.ProfileActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.database_project_dataentryoperator.R;
import com.example.database_project_dataentryoperator.main_dashboard_activity;
import com.google.android.material.textfield.TextInputEditText;

public class setting_activity extends AppCompatActivity {
TextInputEditText username_tf_setting,current_password_tf_setting,new_password_tf_setting,confirm_password_tf_setting;
    RadioGroup Radio_Group_setting_font,Radio_Group_setting_text_size;
    int select_font,select_text_size;
    RadioButton font_radioButton,textSize_radioButton;
    Button back_button_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //connecting TextInputEditText
        username_tf_setting=findViewById(R.id.username_tf_setting);
        current_password_tf_setting=findViewById(R.id.current_password_tf_setting);
        new_password_tf_setting=findViewById(R.id.new_password_tf_setting);
        confirm_password_tf_setting=findViewById(R.id.confirm_password_tf_setting);
        //connecting radio group
        Radio_Group_setting_font=findViewById(R.id.Radio_Group_setting_font);
        Radio_Group_setting_text_size=findViewById(R.id.Radio_Group_setting_text_size);
        //conecting button
        back_button_setting=findViewById(R.id.back_button_setting);
        back_button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setting_activity.this, main_dashboard_activity.class));
            }
        });

        Radio_Group_setting_font.clearCheck();
        Radio_Group_setting_text_size.clearCheck();
        Radio_Group_setting_font.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                font_radioButton = Radio_Group_setting_font.findViewById(checkedId);
            }
        });
        Radio_Group_setting_text_size.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               textSize_radioButton = Radio_Group_setting_text_size.findViewById(checkedId);
            }
        });
    }
}