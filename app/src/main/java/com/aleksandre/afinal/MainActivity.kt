package com.aleksandre.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val db = FirebaseDatabase.getInstance().reference
    val arr = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readFromBase()
        AddBtn.setOnClickListener { addToBase() }
        RemoveBtn.setOnClickListener { removeFromBase() }
    }

    private fun makeText(txt : String)
    {
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
    }

    private fun isEmpty() : Boolean
    {
        val text = enterText.text.toString()
        return text.isEmpty()
    }

    private fun addToBase()
    {
        val text = enterText.text.toString()
        if(!isEmpty())
            db.child("Data").child(text).setValue("")
        else
            makeText("ჯერ ტექსტი შეიყვანე!")
    }

    private fun removeFromBase()
    {
        val text = enterText.text.toString()
        if(!isEmpty())
            db.child("Data").child(text).removeValue()
        else
            makeText("ჯერ ტექსტი შეიყვანე!")
    }

    private fun readFromBase()
    {
        db.child("Data").addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                if(arr.size != 0) arr.clear()

                for(i in p0.children)
                {
                    arr.add(i.key.toString())
                }
                adapt()
            }
        })
    }

    private fun adapt()
    {
        val arrAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr)
        list.adapter = arrAdapter
    }
}
