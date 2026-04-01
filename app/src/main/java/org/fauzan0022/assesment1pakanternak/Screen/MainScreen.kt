package org.fauzan0022.assesment1pakanternak.Screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
fun MainScreen(navController: NavHostController, historyList: MutableList<String>) {
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
                    IconButton(onClick = {navController.navigate(Screen.History.route)}) {
                        Icon(Icons.Outlined.History, contentDescription = stringResource(R.string.history))
                    }
                    IconButton(onClick = {navController.navigate(Screen.About.route)}) {
                        Icon(Icons.Outlined.Info, contentDescription = stringResource(R.string.tentang_app))
                    }
                },
                modifier = Modifier.shadow(2.dp)
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { innerPadding ->
        ScreenContent(
            modifier = Modifier.padding(innerPadding),
            historyList =historyList
        )
    }
}
@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    historyList: MutableList<String>
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

                Text(
                    text = "Input Data",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

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
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
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
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = umur,
                    onValueChange = { umur = it },
                    label = { Text(stringResource(R.string.umur_label)) },
                    isError = error,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )

                if (error) {
                    ErrorHint(true)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
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
                            historyList.add(0, hasil)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.hitung))
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
                        Text(stringResource(R.string.reset))
                    }
                }
            }
        }

        if (hasil.isNotEmpty()) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Hasil Perhitungan",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = hasil,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { shareData(context, hasil) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.bagikan))
                    }
                }
            }
        }
    }
}
@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(
            text = stringResource(R.string.input_invalid),
            color = MaterialTheme.colorScheme.error,
            fontSize = 10.sp
        )
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

    val hijauan = total * 0.6f
    val konsentrat = total * 0.4f
    val air = berat * 0.1f

    return "Jenis: $jenis\n" +
            "Bobot: $berat kg\n" +
            "Umur: $umur bulan\n" +
            "Tujuan: $tujuan\n" +
            "\n" +
            "Pakan Harian:\n" +
            "Hijauan: ${"%.2f".format(hijauan)} kg\n" +
            "Konsentrat: ${"%.2f".format(konsentrat)} kg\n" +
            "Air: ${"%.2f".format(air)} liter\n" +
            "\n" +
            "Total: ${"%.2f".format(total)} kg/hari"
}

private fun shareData(context: Context, message: String) {
    val shareInt = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    context.startActivity(Intent.createChooser(shareInt, "Share via"))
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    Assesment1PakanTernakTheme {
        MainScreen(
            navController = rememberNavController(),
            historyList = remember { mutableStateListOf() }
        )
    }
}