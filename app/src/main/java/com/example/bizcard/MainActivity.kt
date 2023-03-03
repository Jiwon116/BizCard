package com.example.bizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonState = remember {
        mutableStateOf(false)
    }

    // Surface : 요소를 감싸는 컨테이너 개념. 화면에 무언가 보여주고 싶을 때 사용함
    // Modifier : Compose의 UI 구성요소(레이아웃 등)들을 꾸미거나 행동(이벤트 리스너)을 추가하기 위한 요소들의 모음
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White,
            elevation =  4.dp) {

            // column : 내부의 모든 요소를 수직으로 나열함
            Column(modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                // profile image
                CreateImageProfile()
                // divider
                Divider()
                // information text
                CreateInfo()
                // portfolio button
                Button(onClick = { buttonState.value = !buttonState.value },
                    modifier = Modifier.padding(5.dp)) {
                    Text(text = "Portfolio",
                        style = MaterialTheme.typography.button)
                }

                // 버튼 클릭했을 때 포트폴리오 내용 보이게 하기
                if(buttonState.value) {
                    Content()
                } else {
                    Box {}
                }
            }
        }
    }
}

@Composable
fun Content() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)){
            Portfolio(data = listOf("Project 1", "Project 2", "Project 3", "Project 4", "Project 5", "Project 6"))
        }
    }

}

@Composable
fun Portfolio(data: List<String>) {
    // LazyColumn : recycler view와 비슷함
    LazyColumn{
        items(data) { item ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
                shape = RectangleShape,
                elevation = 7.dp) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)) {
                    CreateImageProfile(modifier = Modifier.size(100.dp))

                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great Project", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Choi Jiwon",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant)

        Text(modifier = Modifier.padding(3.dp),
            text = "Android Compose Programmer")

        Text(modifier = Modifier
            .align(Alignment.End)
            .padding(3.dp),
            text = "@JetpackCompose",
            style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(modifier = modifier
        .size(150.dp)
        .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardTheme {
        CreateBizCard()
    }
}