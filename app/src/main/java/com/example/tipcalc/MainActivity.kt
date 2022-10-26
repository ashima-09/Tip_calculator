package com.example.tipcalc

import android.os.Bundle
import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tipcalc.ui.theme.TipCalcTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipApp()
                }
            }
        }
    }
}

@Composable
fun TipApp() {
    var amountInput by remember{mutableStateOf("0")}
    var amount=amountInput.toDoubleOrNull()?:0.0
    var tipAmount=calc(amount,15.0)
    Column() {
        Text(text = "Tip Calculator")

        InputField(amountInput, valueChanged = { amountInput = it })
        Text(
            text = stringResource(R.string.Tip, tipAmount)
        )
    }
}



@Composable
fun InputField(
    amountInput: String,
    valueChanged:(String)->Unit
) {

    TextField(value = amountInput, onValueChange = valueChanged)
}

@Composable
fun calc(
    amount:Double,
    tipPercent:Double=15.0):String{
    val tip=(amount*tipPercent)/100
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview
@Composable
fun TipThemePreview() {
    TipCalcTheme() {

    }
}