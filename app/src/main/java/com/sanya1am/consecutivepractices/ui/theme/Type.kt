package com.sanya1am.consecutivepractices.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sanya1am.consecutivepractices.R

val MontserratNormal = FontFamily( Font(R.font.montserrat_wght, FontWeight.Normal) )
val MontserratMedium = FontFamily( Font(R.font.montserrat_medium, FontWeight.Medium) )
val MontserratBold = FontFamily( Font(R.font.montserrat_bold, FontWeight.Bold) )

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = MontserratBold,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 18.sp
    )
)