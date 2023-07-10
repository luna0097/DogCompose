package com.example.proyectofinal.ui.componens

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedText(label: String, modifier: Modifier = Modifier){
    OutlinedTextField(
        value = "",
        label = { Text(text = label) },
        onValueChange = {},
        modifier = modifier
    )
}