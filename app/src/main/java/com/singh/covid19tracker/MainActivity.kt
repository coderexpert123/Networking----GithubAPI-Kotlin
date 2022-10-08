package com.singh.covid19tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val adapter=UserAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter.onItemClick={
            val intent= Intent(this,userdispay::class.java)
            intent.putExtra("ID",it)
            startActivity(intent)
        }


        val rcv:RecyclerView=findViewById(R.id.rcv)

        rcv.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=this@MainActivity.adapter
        }

        GlobalScope.launch(Dispatchers.Main) {

            val responses: Response<List<Uuser>> = withContext(Dispatchers.IO){ Client.api.getUsers() }

            if (responses.isSuccessful){
                responses.body()?.let {
                    adapter.swapData(it)
                }
            }
        }


    }
}