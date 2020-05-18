package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPieChartView(chart)
    }

    private fun setupPieChartView(pieChart: PieChart) {
        pieChart.setUsePercentValues(true)
        val legend: Legend = pieChart.legend

        // 円グラフに表示するデータ
        val values: List<Float> = listOf(40f, 30f, 20f, 10f)
        val entries: List<PieEntry> = values.map {
            PieEntry(it)
        }

        val dataSet = PieDataSet(entries, "チャートのラベル")
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        dataSet.setDrawValues(true)
        val labels: List<String> = listOf("A", "B", "C", "D")

        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.WHITE)
        pieChart.data = pieData
    }
}
