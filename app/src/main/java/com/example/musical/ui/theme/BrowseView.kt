package com.example.musical.ui.theme

import android.content.ActivityNotFoundException
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.musical.R
import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@Composable
fun Report() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Your All Medical Reports")
        Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
            Column(modifier = Modifier.padding(8.dp)) {
                Column {
                    Text(text = "Blood Report")
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Red Blood Cells")
                        TextButton(onClick = {
                            createAndOpenPdf(context)
                        }) {
                            Row {
                                Text(text = "View Full Report")
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowRight,
                                    contentDescription = "View Full Report"
                                )
                            }
                        }

                    }
                }
                Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                Row(Modifier.padding(vertical = 16.dp)) {
                    Box {
                        Text(text = "Share your Report")
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_share_24),
                            contentDescription = "Share your Report",
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                }
            }

        }
    }
}

fun createAndOpenPdf(context: Context) {
    // Create a new document
    val document = PdfDocument()

    // Create a new page
    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
    val page = document.startPage(pageInfo)

    // Draw something on the page
    val canvas = page.canvas
    val paint = android.graphics.Paint()
    paint.textAlign = android.graphics.Paint.Align.LEFT
    paint.textSize = 16f
    canvas.drawText("daksh dhaka", 80f, 50f, paint)

    // Finish the page
    document.finishPage(page)

    // Save the document to a file
    val file = File(
        context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
        "report.pdf"
    )

    try {
        document.writeTo(FileOutputStream(file))
        document.close()

        // Create an Intent to open the PDF file
        val uri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NO_HISTORY)

        val chooser = Intent.createChooser(intent, "Open PDF")
        try {
            context.startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show()
        }
    } catch (e: IOException) {
        Log.e("Report", "Error writing PDF file", e)
        Toast.makeText(context, "Failed to create PDF", Toast.LENGTH_SHORT).show()
    }
}


