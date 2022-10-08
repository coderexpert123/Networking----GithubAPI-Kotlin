package com.singh.covid19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class userdispay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdispay)
        val id:String?=intent.getStringExtra("ID")




        GlobalScope.launch(Dispatchers.Main) {

            val responses: Response<Uuser> =
                withContext(Dispatchers.IO){ Client.api.getUserById(id) }

            if (responses.isSuccessful){
                responses.body()?.let {
                    tv2.text=it.login
                    Picasso.get().load(it.avatarUrl).into(imgvw)
                }
            }
        }


    }
}