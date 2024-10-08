package com.example.navigation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScreenB(navController: NavHostController) {
    Box(){
        Column {
            Image(imageVector = Icons.Rounded.Person, contentDescription =null )
            Text(text = "Person" )
        }

    }
}