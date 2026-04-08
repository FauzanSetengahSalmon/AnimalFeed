package org.fauzan0022.assesment1pakanternak.Screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.fauzan0022.assesment1pakanternak.R
import org.fauzan0022.assesment1pakanternak.ui.theme.Assesment1PakanTernakTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),

                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Tips.route)}) {
                        Icon(Icons.Outlined.Lightbulb, contentDescription = "Tips")
                    }
                    IconButton(onClick = { navController.navigate(Screen.About.route) }) {
                        Icon(Icons.Outlined.Info, contentDescription = stringResource(R.string.tentang_app))
                    }
                },
                modifier = Modifier.shadow(2.dp)
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { innerPadding ->
        ScreenContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier
) {
    val ternakOptions = listOf("Sapi Potong", "Sapi Perah", "Kambing", "Domba", "Babi")
    val tujuanOptions = listOf("Penggemukan", "Pembibitan")

    var expandedTernak by remember { mutableStateOf(false) }
    var expandedTujuan by remember { mutableStateOf(false) }

    var pilihTernak by rememberSaveable { mutableStateOf("") }
    var tujuan by rememberSaveable { mutableStateOf("") }
    var bobot by rememberSaveable { mutableStateOf("") }
    var umur by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(false) }
    var hasil by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = stringResource(R.string.intro),
            fontSize = 14.sp,
            color = Color.Gray
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text("Input Data", fontWeight = FontWeight.SemiBold)

                // Dropdown ternak
                Box {
                    OutlinedTextField(
                        value = pilihTernak,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.pilih_ternak)) },
                        trailingIcon = {
                            Icon(
                                if (expandedTernak) Icons.Filled.KeyboardArrowUp
                                else Icons.Filled.KeyboardArrowDown,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        Modifier
                            .matchParentSize()
                            .clickable { expandedTernak = true }
                    )

                    DropdownMenu(
                        expanded = expandedTernak,
                        onDismissRequest = { expandedTernak = false }
                    ) {
                        ternakOptions.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    pilihTernak = it
                                    expandedTernak = false
                                }
                            )
                        }
                    }
                }

                // Dropdown tujuan
                Box {
                    OutlinedTextField(
                        value = tujuan,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.tujuan)) },
                        trailingIcon = {
                            Icon(
                                if (expandedTujuan) Icons.Filled.KeyboardArrowUp
                                else Icons.Filled.KeyboardArrowDown,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        Modifier
                            .matchParentSize()
                            .clickable { expandedTujuan = true }
                    )

                    DropdownMenu(
                        expanded = expandedTujuan,
                        onDismissRequest = { expandedTujuan = false }
                    ) {
                        tujuanOptions.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    tujuan = it
                                    expandedTujuan = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = bobot,
                    onValueChange = { bobot = it },
                    label = { Text(stringResource(R.string.bobot_label)) },
                    isError = error,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = umur,
                    onValueChange = { umur = it },
                    label = { Text(stringResource(R.string.umur_label)) },
                    isError = error,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                if (error) {
                    ErrorHint(true)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = {
                            val b = bobot.toFloatOrNull()
                            val u = umur.toIntOrNull()

                            if (b == null || u == null || pilihTernak.isEmpty() || tujuan.isEmpty()) {
                                error = true
                                return@Button
                            }

                            error = false
                            hasil = hitungPakan(pilihTernak, b, u, tujuan)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Hitung")
                    }

                    OutlinedButton(
                        onClick = {
                            bobot = ""
                            umur = ""
                            pilihTernak = ""
                            tujuan = ""
                            hasil = ""
                            error = false
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Reset")
                    }
                }
            }
        }

        if (hasil.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Hasil Perhitungan", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(hasil)

                    Button(
                        onClick = { shareData(context, hasil) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Bagikan")
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text("Input tidak valid", color = MaterialTheme.colorScheme.error)
    }
}

fun hitungPakan(jenis: String, berat: Float, umur: Int, tujuan: String): String {
    val faktorUmur = if (umur < 12) 1.2f else 1f
    val faktorTujuan = if (tujuan == "Penggemukan") 1.3f else 1f

    val total = when (jenis) {
        "Sapi Potong" -> berat * 0.03f
        "Sapi Perah" -> berat * 0.04f
        "Kambing" -> berat * 0.05f
        "Domba" -> berat * 0.04f
        "Babi" -> berat * 0.05f
        else -> 0f
    } * faktorUmur * faktorTujuan

    return "Total pakan: %.2f kg/hari".format(total)
}

private fun shareData(context: Context, message: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    context.startActivity(Intent.createChooser(intent, "Share via"))
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    Assesment1PakanTernakTheme {
        MainScreen(navController = rememberNavController())
    }
}