package com.xenia.englishusingflashcards.presentation.main_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val timesList = listOf(
    "08:00", "09:00", "10:00",
    "11:00", "12:00", "13:00",
    "14:00", "15:00", "16:00",
    "17:00", "18:00"
)

@Composable
fun AlertDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var timeName : String by remember { mutableStateOf(timesList[0]) }

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm.invoke()
                },
                modifier = Modifier.fillMaxWidth().size(50.dp),
                shape = RoundedCornerShape(25.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(202, 240, 248, 255),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Применить",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier.clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
        text = {
            val checkedState = remember { mutableStateOf(false) }
            val textColor = remember { mutableStateOf(Color.Unspecified) }
            Column(modifier = Modifier.padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Включить уведомления",
                        fontSize = 16.sp, style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.size(150.dp, 50.dp),
                        color = textColor.value
                    )
                    Switch(
                        checked = checkedState.value,
                        modifier = Modifier.padding(start = 50.dp),
                        onCheckedChange = {
                            checkedState.value = it
                            if (checkedState.value) textColor.value = Color(0xff00695C)
                            else textColor.value = Color.Unspecified
                        }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Настройка напоминаний",
                        fontSize = 16.sp, style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.size(150.dp, 50.dp),
                        color = textColor.value
                    )
                    Box(
                        modifier = Modifier.padding(start = 30.dp),
                    ) {
                        Row(modifier = Modifier
                            .clickable {
                                expanded = !expanded
                            }) {
                            Text(
                                modifier = Modifier.padding(top = 7.dp),
                                text = timeName,
                                fontSize = 20.sp
                            )
                            Icon(
                                modifier = Modifier.padding(top = 13.dp),
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = null
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }
                            ) {
                                timesList.forEach { level ->
                                    DropdownMenuItem(
                                        onClick = {
                                            expanded = false
                                            timeName = level
                                        },
                                        text = {
                                            Text(
                                                text = level,
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun AlertDialogPlayground() {
    val showAlertDialog = remember { mutableStateOf(false) }

    Button(
        onClick = {
            showAlertDialog.value = true
        },
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(202, 240, 248, 255),
            contentColor = Color.Black)
    ){
        Text(
            "Настройка напоминаний",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }

    if (showAlertDialog.value) {
        AlertDialog(
            onDismiss = {
                showAlertDialog.value = false },
            onConfirm = {
                showAlertDialog.value = false },
        )
    }
}