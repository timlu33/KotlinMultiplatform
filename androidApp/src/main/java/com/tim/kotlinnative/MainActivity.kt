package com.tim.kotlinnative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tim.kotlinnative.shared.Greeting
import com.tim.kotlinnative.shared.api.UpdateProblem
import com.tim.kotlinnative.shared.model.Member
import com.tim.kotlinnative.shared.presentation.MemberPresenter
import com.tim.kotlinnative.shared.presentation.MembersView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MembersView {


    private val repository by lazy {
        (application as App).dataRepository
    }

    private val presenter by lazy {
        MemberPresenter(this, repository = repository)
    }

    private lateinit var adapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.text = Greeting().greeting()
        setupRecyclerView()
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override var isUpdating = false

    override fun onUpdate(members: List<Member>) {
        adapter.members = members
        runOnUiThread {
            adapter.notifyDataSetChanged()
            Toast.makeText(this, members.size.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun showError(error: Throwable) {
        val errorMessage = when(error) {
            is UpdateProblem -> "Fail to get data"
            else -> "Unknown error"
        }
        runOnUiThread {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter(listOf())
        recyclerView.adapter = adapter
    }




























}
