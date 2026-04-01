package org.fauzan0022.assesment1pakanternak

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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
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
import org.fauzan0022.assesment1pakanternak.ui.theme.Assesment1PakanTernakTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
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
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.History, contentDescription = stringResource(R.string.history))
                    }
                    IconButton(onClick = { }) {
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
    modifier: Modifier = Modifier,
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
                                null
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
                                null
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
                        onClick = { },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.bagikan))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assesment1PakanTernakTheme {
        MainScreen()
    }
}