package com.example.orin1.moneyconverterkotlin.mainscreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.orin1.moneyconverterkotlin.R
import com.example.orin1.moneyconverterkotlin.base.BaseActivity
import com.example.orin1.moneyconverterkotlin.model.Currency
import java.util.ArrayList

class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView  {

    private val KEY_MAIN_SPINNER_POZ = "KEY_MAIN_SPINNER_POZ"
    private val KEY_SECOND_SPINNER_POZ = "KEY_SECOND_SPINNER_POZ"

    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null
    private var adapter = SampleAdapter()
    private var handler = Handler()
    private var mainSpinner: Spinner? = null
    private var secondSpinner: Spinner? = null
    private var mainSpinnerAdapter: ArrayAdapter<String>? = null
    private var secondSpinnerAdapter: ArrayAdapter<String>? = null
    private var inputTextValue: EditText? = null
    private var outputTextValue: EditText? = null

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(KEY_MAIN_SPINNER_POZ, mainSpinner!!.selectedItemPosition)
        outState?.putInt(KEY_SECOND_SPINNER_POZ, secondSpinner!!.selectedItemPosition)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainSpinner = findViewById(R.id.main_spinner)
        secondSpinner = findViewById(R.id.second_spinner)
        mainSpinner?.isEnabled = false
        secondSpinner?.isEnabled = false

        inputTextValue = findViewById(R.id.input_textView)
        outputTextValue = findViewById(R.id.output_textView)
        inputTextValue?.isEnabled = false
        outputTextValue?.isEnabled = false

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.currency_recycle_view)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this)

        mainSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                initSecondSpinnerAdapterData(this@MainActivity.getPresenter().getCurrencyNames())

                this@MainActivity.getPresenter().updateRecyclerViewInfo(mainSpinner?.selectedItem.toString())

                if (inputTextValue?.text.toString() != "") {
                    this@MainActivity.getPresenter().convertValue(
                            mainSpinner?.selectedItem.toString(),
                            secondSpinner?.selectedItem.toString(),
                            java.lang.Double.parseDouble(inputTextValue?.text.toString()))
                }
            }
        }

        secondSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (inputTextValue?.text.toString() != "") {
                    this@MainActivity.getPresenter().convertValue(
                            mainSpinner?.selectedItem.toString(),
                            secondSpinner?.selectedItem.toString(),
                            java.lang.Double.parseDouble(inputTextValue?.text.toString()))
                }
            }
        }

        inputTextValue?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                handler.post {
                    if (inputTextValue?.text.toString() != "") {

                        if (savedInstanceState != null && !savedInstanceState.isEmpty) {

                            if (savedInstanceState.getInt(KEY_MAIN_SPINNER_POZ) != -1 || savedInstanceState.getInt(KEY_SECOND_SPINNER_POZ) != -1) {
                                mainSpinner?.setSelection(savedInstanceState.getInt(KEY_MAIN_SPINNER_POZ))
                                secondSpinner?.setSelection(savedInstanceState.getInt(KEY_SECOND_SPINNER_POZ))
                            } else {
                                mainSpinner?.setSelection(0)
                                secondSpinner?.setSelection(0)
                            }

                            /*
                непонятно почему из 0 получается -1 при вводе символа в поле ввода
                 */

                            this@MainActivity.getPresenter().convertValue(
                                    mainSpinner?.selectedItem.toString(),
                                    secondSpinner?.selectedItem.toString(),
                                    java.lang.Double.parseDouble(inputTextValue?.text.toString()))
                            savedInstanceState.clear()

                        } else {
                            this@MainActivity.getPresenter().convertValue(
                                    mainSpinner?.selectedItem.toString(),
                                    secondSpinner?.selectedItem.toString(),
                                    java.lang.Double.parseDouble(inputTextValue?.text.toString()))
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })



    }

    override fun providePresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun showLoadResult(data: List<Currency>) {
        handler.post {
            adapter.setItems(data)
            progressBar?.visibility = View.GONE
            inputTextValue?.isEnabled = true
        }
    }

    override fun initAdapterData(itemNames: List<String>) {
        handler.post { initMainSpinnerAdapterData(itemNames) }
    }

    override fun showConvertResult(convertValue: Double) {
        outputTextValue?.setText(String.format("%.2f", +convertValue).replace(",", "."))

    }

    override fun updateRecyclerViewInfo(currencies: List<Currency>) {
        adapter.setItems(currencies)
    }

    private fun initMainSpinnerAdapterData(items: List<String>) {
        mainSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        mainSpinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mainSpinner?.adapter = mainSpinnerAdapter

        initSecondSpinnerAdapterData(items)
        secondSpinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        secondSpinner?.adapter = secondSpinnerAdapter

        mainSpinner?.isEnabled = true
        secondSpinner?.isEnabled = true

    }

    private fun initSecondSpinnerAdapterData(items: List<String>) {
        val temp = ArrayList(items)
        for (s in temp) {
            if (s == mainSpinner!!.selectedItem.toString()) {
                temp.remove(s)
                break
            }
        }
        if (secondSpinnerAdapter == null) {
            secondSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, temp)
        } else {
            secondSpinnerAdapter?.clear()
            secondSpinnerAdapter?.addAll(temp)
        }
    }

    class VH : RecyclerView.ViewHolder {
        private var moneyNameTextView: TextView? = null
        private var rateTextView: TextView? = null

        constructor(itemView: View) : super(itemView) {
            super.itemView
            moneyNameTextView = itemView.findViewById(R.id.money_id)
            rateTextView = itemView.findViewById(R.id.money_currency)
        }

        fun bind(currency: Currency){
            moneyNameTextView?.text = currency.name
            rateTextView?.text = currency.rate.toString()
        }
    }

    class SampleAdapter : RecyclerView.Adapter<VH>() {
        private var currencyList: List<Currency>? = null

        fun setItems(currencyList: List<Currency>) {
            this.currencyList = currencyList
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(LayoutInflater.from(parent.context).inflate(R.layout.list_item_money, parent, false))
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val currency = currencyList!![position]
            holder.bind(currency)
        }

        override fun getItemCount(): Int {
            return if (currencyList == null) 0 else currencyList!!.size
        }
    }
}
