package org.fauzan0022.assesment1pakanternak.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tips Pakan Ternak",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.shadow(1.dp)
            )
        },
        containerColor = Color(0xFFF5F7FA)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            TipsItem("1. Berikan pakan sesuai jenis ternak")
            TipsItem("2. Sesuaikan jumlah pakan dengan berat badan ternak")
            TipsItem("3. Ternak muda butuh nutrisi lebih tinggi")
            TipsItem("4. Gunakan pakan berkualitas (tidak berjamur)")
            TipsItem("5. Tambahkan vitamin atau suplemen jika perlu")
            TipsItem("6. Air minum harus selalu tersedia")
            TipsItem("7. Berikan pakan secara teratur (jadwal tetap)")
            TipsItem("8. Hindari perubahan pakan secara mendadak")
            TipsItem("9. Bersihkan tempat pakan setiap hari")
            TipsItem("10. Perhatikan kondisi kesehatan ternak")
            TipsItem("11. Gunakan kombinasi pakan hijauan & konsentrat")
            TipsItem("12. Simpan pakan di tempat kering")
            TipsItem("13. Jangan beri pakan basi")
            TipsItem("14. Perhatikan tujuan ternak (penggemukan/pembibitan)")
            TipsItem("15. Lakukan pengecekan berat badan secara rutin")
            TipsItem("16. Pisahkan ternak sakit agar tidak menular")
            TipsItem("17. Gunakan pakan tambahan saat musim kemarau")
            TipsItem("18. Pastikan kandang bersih dan nyaman")
            TipsItem("19. Jangan overfeeding (kelebihan pakan)")
            TipsItem("20. Konsultasi dengan peternak berpengalaman")

        }
    }
}

@Composable
fun TipsItem(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(40.dp)
                    .background(Color(0xFF4CAF50), RoundedCornerShape(2.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                color = Color(0xFF333333),
                lineHeight = 20.sp
            )
        }
    }
}