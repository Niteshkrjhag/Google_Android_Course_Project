package com.example.artspaceapp.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.Structure
import com.example.artspaceapp.Temp
import kotlin.math.abs

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Screen(){
    var i by remember {
        mutableIntStateOf(0)
    }


    Column(
        modifier = Modifier
            .statusBarsPadding()
            .wrapContentWidth()
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        i%=5
        when(i){
            0-> detailed(obj = Temp.a, next = {i++}, prev = {i--})
            1,-1-> detailed(obj = Temp.b, next = {i++}, prev = {i--})
            2,-2-> detailed(obj = Temp.c, next = {i++}, prev = {i--})
            3,-3-> detailed(obj = Temp.d, next = {i++}, prev = {i--})
            else -> detailed(obj = Temp.e, next = {i++}, prev = {i--})
        }
    }
}

@Composable
fun detailed(obj:Structure,modifier: Modifier=Modifier,next:()->Unit,prev:()->Unit){
    Card(
        modifier= Modifier
            .wrapContentWidth()
    ) {
        Image(
            painter = painterResource(id = obj.image),
            contentDescription = null,
            modifier = Modifier
                .wrapContentWidth()
                .height(400.dp)
                .padding(20.dp)
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    Card(
        modifier = Modifier
            .height(60.dp)

    ){
        Column(
            modifier = Modifier
                .width(180.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = obj.title,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = obj.author+" " + obj.year,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 5.dp)
                    .align(Alignment.Start)


            )
        }
    }
    Spacer(modifier = Modifier.navigationBarsPadding())
    Row(
        modifier= Modifier
            .padding(top = 60.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
    Button(onClick = prev,
        modifier=Modifier
            .width(150.dp)) {
        Text(text = "Previous")
    }
    Button(onClick = next,
            modifier=Modifier
                .width(150.dp)) {
            Text(text = "Next")
        }
    }
}