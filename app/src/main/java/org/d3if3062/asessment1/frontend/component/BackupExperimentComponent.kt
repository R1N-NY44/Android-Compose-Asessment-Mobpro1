package org.d3if3062.asessment1.frontend.component

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

@Composable
fun backupExperiment(){
    //            val context = LocalContext.current
//            var selectedDate by remember { mutableStateOf<Date?>(null) }
//            var dateString by remember { mutableStateOf("") }
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                TextField(
//                    value = dateString,
//                    onValueChange = { /* Do nothing to prevent user editing */ },
//                    modifier = Modifier.weight(1f),
//                    label = { Text("DD/MM/YYYY") }
//                )
//                Button(
//                    onClick = {
//                        val calendar = Calendar.getInstance()
//                        DatePickerDialog(context, { _, year, month, dayOfMonth ->
//                            val date = GregorianCalendar(year, month, dayOfMonth).time
//                            selectedDate = date
//                            dateString = SimpleDateFormat("dd/MM/yyyy").format(date)
//                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
//                    },
//                    modifier = Modifier.padding(start = 8.dp)
//                ) {
//                    Text("Select Date")
//                }
//            }



    val context = LocalContext.current
    val currentDate = Date()
    var selectedDate by remember { mutableStateOf(currentDate) }
    var dateString by remember { mutableStateOf(SimpleDateFormat("dd/MM/yyyy").format(currentDate)) }

    Button(
        onClick = {
            val calendar = Calendar.getInstance()
            DatePickerDialog(context, { _, year, month, dayOfMonth ->
                val date = GregorianCalendar(year, month, dayOfMonth).time
                selectedDate = date
                dateString = SimpleDateFormat("dd/MM/yyyy").format(date)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    ) {
        Text(dateString)
    }

    var selectedTime by remember { mutableStateOf<Date?>(null) }
    var timeString by remember { mutableStateOf(SimpleDateFormat("HH:mm").format(Date())) }

    Button(
        onClick = {
            val calendar = Calendar.getInstance()
            TimePickerDialog(context, { _, hourOfDay, minute ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val time = calendar.time
                selectedTime = time
                timeString = SimpleDateFormat("HH:mm").format(time)
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
    ) {
        Text(timeString)
    }

    // Label dan Button untuk deadline
//                OutlinedButton(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 8.dp)
//                        .height(56.dp),
//                    shape = RoundedCornerShape(4.dp),
//                    onClick = {
//                        val calendar = Calendar.getInstance()
//                        val datePickerDialog = DatePickerDialog(
//                            context,
//                            { _, year, month, dayOfMonth ->
//                                TimePickerDialog(
//                                    context,
//                                    { _, hourOfDay, minute ->
//                                        calendar.set(year, month, dayOfMonth, hourOfDay, minute)
//                                        val dateTime = calendar.time
//                                        if (dateTime.after(Date())) {
//                                            selectedDateTime = dateTime
//                                            deadLine = SimpleDateFormat("dd/MM/yyyy HH:mm").format(dateTime)
//                                        } else {
//                                            selectedDateTime = Date()
//                                            deadLine = SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date().time)
//                                        }
//                                    },
//                                    calendar.get(Calendar.HOUR_OF_DAY),
//                                    calendar.get(Calendar.MINUTE),
//                                    true
//                                ).show()
//                            },
//                            calendar.get(Calendar.YEAR),
//                            calendar.get(Calendar.MONTH),
//                            calendar.get(Calendar.DAY_OF_MONTH)
//                        )
//                        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
//                        datePickerDialog.show()
//                    }
//                ) {
//                    Row(horizontalArrangement = Arrangement.Start) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.date),
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.onSurface,
//                            modifier = Modifier.padding(start = 8.dp) // Atur padding untuk ikon
//                        )
//                        Text(
//                            text = deadLine,
//                            style = TextStyle(
//                                color = MaterialTheme.colorScheme.onSurface,
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.Normal
//                            ),
//                            modifier = Modifier.padding(start = 8.dp) // Atur padding untuk teks
//                        )
//                    }
//                }


    Text(
        text = " Deadline ",
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    )



}