package com.example.billy.weekone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

/**
 * Created by billy on 2017-07-21.
 */

public class MainReviseActivity extends AppCompatActivity {
    private EditText mTitleEditText2;
    private EditText mContentEditText2;
    private Button mButton4;
    private Button mButton5;
    private String mTitle;
    private String mContent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_revise);

        mTitleEditText2 = (EditText) findViewById(R.id.titleEditText2);
        mContentEditText2 = (EditText) findViewById(R.id.contentEditText2);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);

        View.OnClickListener thirdOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Article Addition
                String titleText = mTitleEditText2.getText().toString();
                String contentText = mContentEditText2.getText().toString();
                // mTextView.setText(text);
                mTitleEditText2.setText("");
                mContentEditText2.setText("");

                // Add New Data
                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();

                // transaction
                realm.beginTransaction();
                Article article = realm.where(Article.class).equalTo("title", mTitle).findFirst();
                article.setTitle(titleText);
                article.setContent(contentText);
                realm.commitTransaction();

                finish();
            }
        };

        View.OnClickListener FourthOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();

                realm.beginTransaction();
                final Article article = realm.where(Article.class).equalTo("title", mTitle).findFirst();
                article.deleteFromRealm();
                realm.commitTransaction();

                finish();
            }
        };
        mButton4.setOnClickListener(FourthOnClickListener);
        mButton5.setOnClickListener(thirdOnClickListener);

        if( savedInstanceState == null ){
//            String text = getIntent().getStringExtra("Item");
//            mTextView.setText(text);
            String title =  getIntent().getStringExtra("title");
            String content =  getIntent().getStringExtra("content");
            mTitle = title;
            mContent = content;
            mTitleEditText2.setText(title);
            mContentEditText2.setText(content);

        }
    }
}
