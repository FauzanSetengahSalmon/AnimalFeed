package org.fauzan0022.assesment1pakanternak

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.fauzan0022.assesment1pakanternak.ui.theme.Assesment1PakanTernakTheme

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assesment1PakanTernakTheme {
        Greeting("Android")
    }
}