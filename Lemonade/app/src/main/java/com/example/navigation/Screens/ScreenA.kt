package com.example.navigation.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavHostController


@Composable
fun ScreenA(navController: NavHostController) {
   Box(
        modifier = Modifier.fillMaxSize(),

    ) {
        Button(
            onClick = {
                      navController.navigate("ScreenB")
            },
            modifier = Modifier
                .align(Alignment.Center)
        ) {

            Text("Goto ScreenB")
        }
    }
}