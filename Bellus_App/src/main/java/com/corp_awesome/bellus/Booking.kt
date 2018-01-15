package com.corp_awesome.bellus

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutCompat
import android.view.Gravity
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 */

/*
    TODO
    Type of appointments - group appointments, one on one, wedding

 */
class Booking : AppCompatActivity(), OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val typeSpinner = findViewById<Spinner>(R.id.appointment_type)
        val sizeSpinner = findViewById<Spinner>(R.id.appointment_size)

        if (parent!! ==typeSpinner ){

            when {
                position == 0 -> {
                    sizeSpinner.setSelection(0)
                    changeSpinnerAccess(false, sizeSpinner)
                }
                position == 1 -> {
                    sizeSpinner.setSelection(1)
                    changeSpinnerAccess(false, sizeSpinner)
                }
                position > 1 -> {
                    if(sizeSpinner.selectedItemPosition <2)
                        sizeSpinner.setSelection(2)
                    changeSpinnerAccess(true, sizeSpinner)
                }

            }
        }
        else{
            when{
                position < 2 && typeSpinner.selectedItemPosition == 2-> sizeSpinner.setSelection(2)
                position == 0 && typeSpinner.selectedItemPosition != 2 -> sizeSpinner.setSelection(1)
            }
        }
    }

    private fun changeSpinnerAccess(access : Boolean, spinner : Spinner){
        spinner.isActivated = access
        spinner.isFocusable = access
        spinner.isEnabled = access

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.booking_activity)

        supportActionBar?.elevation = 0F

        val typeSpinner = findViewById<Spinner>(R.id.appointment_type)
        val sizeSpinner = findViewById<Spinner>(R.id.appointment_size)

        typeSpinner.onItemSelectedListener = this
        sizeSpinner.onItemSelectedListener  = this

        val currentCal = Calendar.getInstance()
        currentCal.timeInMillis = currentCal.timeInMillis + 1209600000
        changeDate(currentCal)


    }


    fun submitRequest(v : View){
        val isValidData = validateInput()

        if (! isValidData)
            Toast.makeText(this, "All required fields must have values", Toast.LENGTH_SHORT).show()
        else
            gatherInfo()

    }

    private fun validateInput() : Boolean{
        val nameET = findViewById<EditText>(R.id.client_name)
        val phoneET = findViewById<EditText>(R.id.client_number)
        val emailET = findViewById<EditText>(R.id.client_email)
        val streetAddressET = findViewById<EditText>(R.id.client_street_address)
        val zipET = findViewById<EditText>(R.id.client_zip)
        val commentET = findViewById<EditText>(R.id.client_comment)

        val typeSpinner = findViewById<Spinner>(R.id.appointment_type)
        val sizeSpinner = findViewById<Spinner>(R.id.appointment_size)

        var isValidData = false

        if (typeSpinner.selectedItemPosition > 0 && sizeSpinner.selectedItemPosition > 0)
            isValidData = true

        return isValidData

    }

    private fun gatherInfo(){
        val nameET = findViewById<EditText>(R.id.client_name)
        val phoneET = findViewById<EditText>(R.id.client_number)
        val emailET = findViewById<EditText>(R.id.client_email)
        val streetAddressET = findViewById<EditText>(R.id.client_street_address)
        val zipET = findViewById<EditText>(R.id.client_zip)
        val commentET = findViewById<EditText>(R.id.client_comment)

        val typeSpinner = findViewById<Spinner>(R.id.appointment_type)
        val sizeSpinner = findViewById<Spinner>(R.id.appointment_size)

        val name = nameET.text
        val phone = phoneET.text
        val email  = emailET.text
        val streetAddress = streetAddressET.text
        val zip = zipET.text
        val comment = commentET.text

        val appointmentType = typeSpinner.selectedItem.toString()
        val appointmentSize = sizeSpinner.selectedItem.toString()

        val time = Calendar.getInstance()
        val hour = time.get(Calendar.HOUR)
        var greeting : String = ""

        when (hour) {
            in 0..11 -> greeting = "Good morning,"
            in 12..17 -> greeting = "Good afternoon,"
            in 18..23 -> greeting = "Good evening,"
        }

        System.out.println(greeting + " My name is " + name)

    }

    fun changeTime (v : View){
        val timePicker = TimePicker(this)
        val dialog = createPickerDialog(timePicker)
        dialog.setPositiveButton("Ok", null)

        dialog.create().show()


    }

    fun changeDate (v : View){
        val datePicker = DatePicker(this)
        val dialog = createPickerDialog(datePicker)

        dialog.setPositiveButton("Ok", { dialogInterface: DialogInterface, i: Int ->
            val cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, datePicker.year)
            cal.set(Calendar.MONTH, datePicker.month)
            cal.set(Calendar.DAY_OF_MONTH, datePicker.dayOfMonth)

            changeDate(cal)

        })

        dialog.create().show()
    }

    private fun changeDate(cal : Calendar){
        val dateTV = findViewById<TextView>(R.id.dateTV)

        dateTV.text = "On: ${SimpleDateFormat("EEE").format(cal.time)}, ${SimpleDateFormat("MMM").format(cal.time)} ${cal.get(Calendar.DAY_OF_MONTH)}, ${cal.get(Calendar.YEAR)}"
    }

    private fun createPickerDialog(v : View) : AlertDialog.Builder{
        val dialog = AlertDialog.Builder(this)

        val parentLL = LinearLayout(this)
        val params = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        parentLL.layoutParams = params
        parentLL.gravity = Gravity.CENTER
        v.layoutParams = params
        parentLL.addView(v)


        dialog.setView(parentLL)
        dialog.setNegativeButton("Dismiss", null)

        return dialog
    }

}


