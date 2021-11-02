package yong.aop.aop.goodrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var catAdapter: CatAdapter
    lateinit var cats : MutableList<CatModel>
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        cats = mutableListOf()
        catAdapter = CatAdapter(this, cats)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recyclerView.adapter = catAdapter
        catAdapter.setItemClickListener(object: CatAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                findViewById<TextView>(R.id.textview).setText(v.findViewById<TextView>(R.id.name_tv).text.toString())
            }
        })
        populateData()
    }

    fun populateData(){

        var catsmallList1 : MutableList<Catsmall> = mutableListOf()
        catsmallList1.add(Catsmall("Bihar","abc"))
        catsmallList1.add(Catsmall("Maharashtra","def"))

        var catsmallList2 : MutableList<Catsmall> = mutableListOf()
        catsmallList2.add(Catsmall("New York", "aaa"))

        var catsmallList3 : MutableList<Catsmall> = mutableListOf()
        catsmallList3.add(Catsmall("New York", "aaa"))

        cats.add(CatModel(CatModel.Maincat, Catbig("India", catsmallList1)))
        cats.add(CatModel(CatModel.Maincat, Catbig("USA", catsmallList2)))
        cats.add(CatModel(CatModel.Maincat, Catbig("USA", catsmallList3)))

        catAdapter.notifyDataSetChanged()
    }
}