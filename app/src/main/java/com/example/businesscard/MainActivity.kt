package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.Footer
import com.example.businesscard.ui.theme.BusinessCardTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    color = Color.Gray
                ){
                    CardInfo(stringResource(R.string.kyle_snyder),
                        stringResource(R.string.junior_dev),
                        stringResource(R.string._2181231234),
                        stringResource(R.string.romedomeyome),
                        stringResource(R.string.snyde_kyl_gmail_com)
                    )
                }
            }
        }
    }
}

@Composable
fun Header(name: String, title: String, modifier: Modifier = Modifier) {
    val logo = painterResource(R.drawable._980884353219830319)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = logo,
                contentDescription = null,
                modifier.size(350.dp),
                alignment = Alignment.Center
            )
            Text(
                text = name,
                fontSize = 30.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
            )
            Text(
                text = title,
                fontSize = 30.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

}

@OptIn(ExperimentalTextApi::class)
@Composable
fun Footer(phone: String, social: String, email: String, modifier: Modifier = Modifier){
    val phoneIcon = painterResource(R.drawable.phone_call_end_11777285)
    val githubIcon = painterResource(R.drawable.github_13171412)
    val emailIcon = painterResource(R.drawable.email_icon)

    val socialAntdd: AnnotatedString = buildAnnotatedString {
        append(social)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ), start = 0, end = social.length
        )
        addUrlAnnotation(
            UrlAnnotation("github.com/RomeDomeYome"),
            start = 0,
            end = social.length
        )
    }
    val uriHandler = LocalUriHandler.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(30.dp)
            .width(250.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .width(200.dp)
        ){
            Image(
                painter = phoneIcon,
                contentDescription = null,
                modifier.size(30.dp)
            )
            Text(
                text = "+1 $phone"
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .width(200.dp)
        ){
            Image(
                painter = emailIcon,
                contentDescription = null,
                modifier.size(30.dp)
            )
            Text(
                text = email
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .width(200.dp)
        ){
            Image(
                painter = githubIcon,
                contentDescription = null,
                modifier.size(30.dp)
            )
            TextWithLinks(
                text = socialAntdd,
                    onClick = { socialAntdd.getUrlAnnotations(it, it)
                    .firstOrNull()?.let { annotation ->
                            uriHandler.openUri(annotation.item.url)
                        }
                    }
            )
        }
    }
}

@Composable
fun CardInfo(name:String, title:String, phone:String, social:String, email:String, modifier:Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Header(
            name,
            title
        )
        Footer(
            phone,
            social,
            email,

        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardInfo("Kyle Snyder", "Junior Dev", "2181231234", "RomeDomeYome", "snyde.kyl@gmail.com")
}