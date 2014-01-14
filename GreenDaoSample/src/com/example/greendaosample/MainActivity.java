package com.example.greendaosample;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends Activity implements OnClickListener {

	protected CategoryAdapter mAdapter;
	private CategoryDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.category_add_btn).setOnClickListener(this);
		initListView(getDao().loadAll());
	}

	protected CategoryDao getDao() {
		if (null == dao) {
			dao = initDatabase(this);
		}
		return dao;
	}

	public CategoryDao initDatabase(Context context) {
		SQLiteDatabase db = new DaoMaster.DevOpenHelper(context, "Category", null).getWritableDatabase();
		DaoSession daoSesson = new DaoMaster(db).newSession();
		CategoryDao dao = daoSesson.getCategoryDao();
		return dao;
	}

	protected void initListView(List<Category> objects) {
		ListView listView = ((ListView)findViewById(R.id.category_list));
		mAdapter = new CategoryAdapter(this, objects, this);
		listView.setAdapter(mAdapter);
	}

	protected void updateList() {
		if (null != mAdapter) {
			mAdapter.clear();
			mAdapter.addAll(getDao().loadAll());
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (R.id.category_add_btn == id) {
			addCategory();
		} else if (R.id.category_delete_btn == id) {
			deleteCategory((Integer) v.getTag());
		}
		updateList();
	}

	protected void addCategory() {
		EditText et = (EditText) findViewById(R.id.add_category_name);
		String name = et.getText().toString();
		et.setText("");
		if (null != name && 0 < name.length()) {
			insertCategory(name);
		}
	}

	protected void insertCategory(String name) {
		Category category= new Category();
		category.setCategory_name(name);
		getDao().insert(category);
	}

	protected void deleteCategory(int position) {
		Category category = mAdapter.getItem(position);
		getDao().deleteByKey(category.getId());
	}
}
