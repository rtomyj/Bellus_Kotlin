package com.corp_awesome.bellus

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutCompat
import android.view.Gravity
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Displays form a potential client fills out to schedule an appointment
 * Implements OnItemSelectedListener for spinners within the activity that require the functionality
 * @constructor Creates activity context
 */

class Booking : AppCompatActivity(), OnItemSelectedListener {

    private val SINGLE_PRICE = 100f

    private val GROUP_2_PRICE = 120f
    private val GROUP_3_PRICE = 140f
    private val GROUP_4_PRICE = 160f

    private val WEDDING_1_PRICE = 200f
    private val WEDDING_2_PRICE = 220f
    private val WEDDING_3_PRICE = 240f
    private val WEDDING_4_PRICE = 260f
    
    
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val typeSpinner = findViewById<Spinner>(R.id.appointment_type)
        val sizeSpinner = findViewById<Spinner>(R.id.appointment_size)

        if (parent!! == typeSpinner ){

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

        val americanDollarFormatter = NumberFormat.getCurrencyInstance(Locale("ENGLISH", "US"))
        val priceTV : TextView = findViewById(R.id.priceTV)

        when {
            typeSpinner.selectedItemPosition == 0           ->      priceTV.text = americanDollarFormatter.format(0f)
            typeSpinner.selectedItemPosition == 1           ->      priceTV.text = americanDollarFormatter.format(SINGLE_PRICE)
            typeSpinner.selectedItemPosition == 2           ->
            {
                when{
                    sizeSpinner.selectedItemPosition == 2   ->      priceTV.text = americanDollarFormatter.format(GROUP_2_PRICE)
                    sizeSpinner.selectedItemPosition == 3   ->      priceTV.text = americanDollarFormatter.format(GROUP_3_PRICE)
                    sizeSpinner.selectedItemPosition == 4   ->      priceTV.text = americanDollarFormatter.format(GROUP_4_PRICE)
                }
            }
            typeSpinner.selectedItemPosition == 3           ->
            {
                when{
                    sizeSpinner.selectedItemPosition == 1   ->      priceTV.text = americanDollarFormatter.format(WEDDING_1_PRICE)
                    sizeSpinner.selectedItemPosition == 2   ->      priceTV.text = americanDollarFormatter.format(WEDDING_2_PRICE)
                    sizeSpinner.selectedItemPosition == 3   ->      priceTV.text = americanDollarFormatter.format(WEDDING_3_PRICE)
                    sizeSpinner.selectedItemPosition == 4   ->      priceTV.text = americanDollarFormatter.format(WEDDING_4_PRICE)
                }
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
        var isValid = true

        if (! firstRoundValidation()){
            isValid = false
        }
        if (!(isValid && secondRoundValidation()))
            isValid = false
        if (isValid)
            gatherInfo()
    }

    private fun secondRoundValidation() : Boolean{
        return validatePhone() && validateEmail()

    }

    private fun validatePhone() : Boolean{
        val phoneET = findViewById<EditText>(R.id.client_number)

        if (phoneET.text.length >= 10)
            return true
        else{
            Toast.makeText(this, "Phone number too short", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    private fun validateEmail() : Boolean{
        val emailET = findViewById<EditText>(R.id.client_email)

        if (emailET.text.contains("@") && emailET.text.contains(".com"))
            return true
        else{
        Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
        return false
        }
    }

    private fun firstRoundValidation() : Boolean{

        val personalInfoIsValid = validatePersonalInfo(); val  appointmentInfoIsValid = validateAppointmentInfo()

        if (! personalInfoIsValid && appointmentInfoIsValid){
            Toast.makeText(this, "Personal Information is needed", Toast.LENGTH_LONG).show()
            return false
        }
        else if (personalInfoIsValid && ! appointmentInfoIsValid){
            Toast.makeText(this, "Appointment Information is needed", Toast.LENGTH_LONG).show()
            return false
        }
        else if  (! personalInfoIsValid && ! appointmentInfoIsValid){
            Toast.makeText(this, "Fill information on form", Toast.LENGTH_LONG).show()
            return false
        }
        else
            return true


    }

    private fun validateAppointmentInfo() : Boolean{
        val streetAddressET = findViewById<EditText>(R.id.client_street_address)
        val zipET = findViewById<EditText>(R.id.client_zip)
        val commentET = findViewById<EditText>(R.id.client_comment)

        val typeSpinner = findViewById<Spinner>(R.id.appointment_type)

        return (typeSpinner.selectedItemPosition > 0 && streetAddressET.text.isNotEmpty() &&  zipET.text.isNotEmpty() && commentET.text.isNotEmpty() )


    }

    private fun validatePersonalInfo(): Boolean{
        val nameET = findViewById<EditText>(R.id.client_name)
        val phoneET = findViewById<EditText>(R.id.client_number)
        val emailET = findViewById<EditText>(R.id.client_email)

        return (nameET.text.toString().isNotEmpty() && phoneET.text.isNotEmpty() && emailET.text.toString().isNotEmpty())        // if all fields are empty return false

    }

    private fun gatherInfo(){
        val nameET = findViewById<EditText>(R.id.client_name)
        val phoneET = findViewById<EditText>(R.id.client_number)
        val emailET = findViewById<EditText>(R.id.client_email)
        val timeTV = findViewById<TextView>(R.id.timeTV)
        val dateTV = findViewById<TextView>(R.id.dateTV)
        val streetAddressET = findViewById<EditText>(R.id.client_street_address)
        val zipET = findViewById<EditText>(R.id.client_zip)
        val commentET = findViewById<EditText>(R.id.client_comment)

        val typeSpinner = findViewById<Spinner>(R.id.appointment_type)
        val sizeSpinner = findViewById<Spinner>(R.id.appointment_size)

        val name = nameET.text
        val phone = phoneET.text
        val email  = emailET.text
        val time = timeTV.text
        val date = dateTV.text
        val streetAddress = streetAddressET.text
        val zip = zipET.text
        val comment = commentET.text

        val subject = "Looking for an appointment: $date $time"; var greeting : String = ""; var body: String

        val t = Calendar.getInstance()
        t.timeInMillis = System.currentTimeMillis()
        val hour = t.get(Calendar.HOUR)
        when (hour) {
            in 0..11            -> greeting = "Good morning,"
            in 12..17           -> greeting = "Good afternoon,"
            in 18..23           -> greeting = "Good evening,"
        }

        body = "The type of the group is: ${typeSpinner.selectedItem}" +
                "\nThe size is: ${sizeSpinner.selectedItem}" +
                "\n\nThe style I'd like to have is: $comment" +
                "\nThe location of the appointment is: $streetAddress, $zip"
                "\n\n\nMy Information is as follows - " +
                "\nPhone number: $phone" +
                "\nemail: $email" +
                "\n"

        System.out.println("Subject: $subject")
        System.out.println("Header: $greeting My name is $name ")
        System.out.println("Body: $body")


    }

    fun changeTime (v : View){
        val timePicker = TimePicker(this)
        if (android.os.Build.VERSION.SDK_INT < 23){
            timePicker.currentMinute = 0
            timePicker.currentHour += 1
        }
        else{
            timePicker.minute = 0
            timePicker.hour += 1
        }


        val dialog = Popups.customViewAlertDialog(this, createLayoutForTimePickers(timePicker))

        dialog.setNegativeButton("Dismiss", null)
        dialog.setPositiveButton("Ok", {dialogInterface: DialogInterface, i: Int ->

            val cal = Calendar.getInstance()


            if (android.os.Build.VERSION.SDK_INT < 23) {
                cal.set(Calendar.HOUR_OF_DAY, timePicker.currentHour)
                cal.set(Calendar.HOUR, timePicker.currentMinute)
            }
            else {
                cal.set(Calendar.HOUR_OF_DAY, timePicker.hour)
                cal.set(Calendar.MINUTE, timePicker.minute)
            }

            changeTime(cal)

        })

        dialog.create().show()


    }

    private fun changeTime(cal: Calendar ){
        val timeTV = findViewById<TextView>(R.id.timeTV)
        val time : String

        if (cal.get(Calendar.HOUR) < 10)
            time= "At: " + SimpleDateFormat("h:mm aa").format(cal.time)
        else
            time= "At: " + SimpleDateFormat("hh:mm aa").format(cal.time)

        timeTV.text = time
    }

    fun changeDate (v : View){
        val datePicker = DatePicker(this)
        val dialog = Popups.customViewAlertDialog(this, createLayoutForTimePickers(datePicker))

        dialog.setNegativeButton("Dismiss", null)
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


    private fun createLayoutForTimePickers(picker : View) : View{

        val parentLL = LinearLayout(this)
        val params = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        parentLL.layoutParams = params
        parentLL.gravity = Gravity.CENTER
        picker.layoutParams = params
        parentLL.addView(picker)

        return parentLL
    }

}


