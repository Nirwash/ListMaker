package com.nirwashh.android.listmaker.ui.detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.nirwashh.android.listmaker.MainActivity
import com.nirwashh.android.listmaker.MainActivity.Companion.INTENT_LIST_KEY
import com.nirwashh.android.listmaker.R
import com.nirwashh.android.listmaker.TaskList
import com.nirwashh.android.listmaker.databinding.ListDetailActivityBinding
import com.nirwashh.android.listmaker.ui.detail.ui.detail.ListDetailFragment
import com.nirwashh.android.listmaker.ui.detail.ui.detail.ListDetailViewModel
import com.nirwashh.android.listmaker.ui.main.MainViewModel
import com.nirwashh.android.listmaker.ui.main.MainViewModelFactory

class ListDetailActivity : AppCompatActivity() {
    lateinit var b: ListDetailActivityBinding
    lateinit var viewModel: MainViewModel
    lateinit var fragment: ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ListDetailActivityBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnAddTask.setOnClickListener {
            showCreateTaskDialog()
        }
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this)))[MainViewModel::class.java]
        viewModel.list = intent.getParcelableExtra(INTENT_LIST_KEY)!!
        title = viewModel.list.name
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(INTENT_LIST_KEY, viewModel.list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                val task = taskEditText.text.toString()
                viewModel.addTask(task)
                dialog.dismiss()
            }
            .create()
            .show()
    }

}