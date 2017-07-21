package com.example.billy.weekone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListActivity extends AppCompatActivity {

    private ListView mListView;
    private Button mButton2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        final String[] sampleArray = {"hello", "this", "is", "test"};
//        Article[] articles = {
//                new Article("title1", "content1"),
//                new Article("title2", "content2"),
//                new Article("title3", "content3"),
//        };

        mListView = (ListView) findViewById(R.id.ListView);
        mButton2 = (Button) findViewById(R.id.button2);
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this, R.layout.list_item, sampleArray);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance().getDefaultInstance();
        final RealmResults<Article> articles = realm.where(Article.class).findAll();
        final CustomAdapter adapter = new CustomAdapter(
                this, R.layout.list_row,
//                new ArrayList<Article>(Arrays.asList(articles)));
                articles
        );

        articles.addChangeListener(new RealmChangeListener<RealmResults<Article>>() {
            @Override
            public void onChange(RealmResults<Article> articles) {
                adapter.notifyDataSetChanged();
            }
        });

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (ListActivity.this, MainReviseActivity.class);
//                intent.putExtra("Item", sampleArray[position]);
                intent.putExtra("title", articles.get(position).getTitle());
                intent.putExtra("content", articles.get(position).getContent());

//                if (savedInstanceState == null) {
////            String text = getIntent().getStringExtra("Item");
////            mTextView.setText(text);
//                    intent.putExtra("title", "");
//                    intent.putExtra("content", "");

                    startActivity(intent);
                }
        }
        );

        View.OnClickListener secondOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent2 = new Intent(ListActivity.this, MainActivity.class);
                intent2.putExtra("title", "");
                intent2.putExtra("content", "");

                startActivity(intent2);
            }
        };

        mButton2.setOnClickListener(secondOnClickListener);
    }
}
