package com.example.advancedprayertimes.logic.util

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

fun createTableLayout(context: Context, gradingData: Array<Array<String>>): TableLayout {

    val rowCount: Int = gradingData.size
    val columnCount: Int = gradingData.first().size

    // 1) Create a tableLayout and its params
    val tableLayoutParams = TableLayout.LayoutParams()
    val tableLayout = TableLayout(context)
    //  tableLayout.setBackgroundColor(Color.BLACK);

    // 2) create tableRow params
    val tableRowParams = TableRow.LayoutParams()
    tableRowParams.weight = 1f

    for (i in 0 until rowCount) {

        // 3) create tableRow
        val tableRow = TableRow(context)

        // tableRow.setBackgroundColor(Color.BLACK);
        for (j in 0 until columnCount) {

            // 4) create textView
            val textView = TextView(context)
            textView.gravity = Gravity.CENTER
            textView.setPadding(10, 10, 10, 10)
            //textView.background = getResources().getDrawable(R.drawable.)
            textView.text = gradingData[i][j]

            if (i == 0) {
                textView.setBackgroundColor(Color.parseColor("#aeaeae"))
            }

            tableRow.addView(textView, tableRowParams)
        }
        tableLayout.addView(tableRow, tableLayoutParams)
    }
    return tableLayout
}