package dev.nelson.template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.nelson.template.bariers.BarriersTestActivity;
import dev.nelson.template.groups.GroupsTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_groups)
    void onClickGroups(){
        startActivity(new Intent(this, GroupsTestActivity.class));
    }

    @OnClick(R.id.btn_barriers)
    void onClickBarriers(){
        startActivity(new Intent(this, BarriersTestActivity.class));
    }
}
