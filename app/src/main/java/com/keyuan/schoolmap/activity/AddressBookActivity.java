package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;

/**
 * 通讯录
 */
public class AddressBookActivity extends BaseActivity {
    private CardView cardTeacher;
    private CardView cardStudent;
    private CardView cardOtherPerson;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_book;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        cardTeacher = (CardView) findViewById(R.id.card_teacher);
        cardStudent = (CardView) findViewById(R.id.card_student);
        cardOtherPerson = (CardView) findViewById(R.id.card_other_person);
    }

    @Override
    public void initEvent() {
        cardTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressBookActivity.this, TeacherOrStudentAdreessActivity.class).putExtra("isTeacher", true));
            }
        });
        cardStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressBookActivity.this, TeacherOrStudentAdreessActivity.class).putExtra("isTeacher", false));
            }
        });
        cardOtherPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressBookActivity.this, AddressDetailsListActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }
}
