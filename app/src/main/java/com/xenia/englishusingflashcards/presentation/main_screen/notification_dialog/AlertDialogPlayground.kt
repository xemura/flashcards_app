package com.xenia.englishusingflashcards.presentation.main_screen.notification_dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xenia.englishusingflashcards.presentation.viewmodels.NotificationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogContent(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    notificationViewModel: NotificationViewModel
) {

    val scope = CoroutineScope(Dispatchers.IO)
    val timeNotification = notificationViewModel.time.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm.invoke()
                    scope.cancel()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp),
                shape = RoundedCornerShape(25.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(202, 240, 248, 255),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "OK",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp)),
        text = {

            val textColor = remember { mutableStateOf(Color.Unspecified) }

            var selectedHour by remember {
                mutableIntStateOf(0) // or use  mutableStateOf(0)
            }

            var selectedMinute by remember {
                mutableIntStateOf(0) // or use  mutableStateOf(0)
            }

            var showDialog by remember {
                mutableStateOf(false)
            }

            val timePickerState = rememberTimePickerState(
                initialHour = selectedHour,
                initialMinute = selectedMinute,
                is24Hour = true
            )

            Column(modifier = Modifier.padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Настройка напоминаний",
                        fontSize = 16.sp, style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.size(170.dp, 40.dp),
                        color = textColor.value
                    )
                    Box(
                        modifier = Modifier.padding(start = 30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(modifier = Modifier
                            .clickable {
                                showDialog = true
                            })
                        {
                            Text(
                                text = timeNotification.value,
                                fontSize = 20.sp
                            )

                            if (showDialog) {
                                AlertDialog(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = MaterialTheme.colorScheme.surface,
                                            shape = RoundedCornerShape(size = 12.dp)
                                        ),
                                    onDismissRequest = { showDialog = false }
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .background(
                                                color = Color.LightGray.copy(alpha = 0.3f)
                                            )
                                            .padding(
                                                top = 28.dp,
                                                start = 20.dp,
                                                end = 20.dp,
                                                bottom = 12.dp
                                            ),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        // time picker
                                        TimePicker(state = timePickerState)

                                        // buttons
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            // dismiss button
                                            TextButton(onClick = { showDialog = false }) {
                                                Text(text = "Dismiss")
                                            }

                                            // confirm button
                                            TextButton(
                                                onClick = {
                                                    scope.launch {
                                                        showDialog = false
                                                        selectedHour = timePickerState.hour
                                                        selectedMinute = timePickerState.minute

                                                        val getTime = if ((selectedHour/10 == 0) and (selectedMinute/10 == 0)) {
                                                            "0$selectedHour:0$selectedMinute"
                                                        } else if (selectedHour/10 == 0) {
                                                            "0$selectedHour:$selectedMinute"
                                                        } else if (selectedMinute/10 == 0) {
                                                            "$selectedHour:0$selectedMinute"
                                                        } else "$selectedHour:$selectedMinute"

                                                        notificationViewModel.saveTimeNotification(getTime)
                                                    }
                                                }
                                            ) {
                                                Text(text = "Confirm")
                                            }
                                        }
                                    }
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

    val notificationViewModel: NotificationViewModel = viewModel(
        factory = NotificationViewModel.Factory
    )

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
            contentColor = Color.Black
        )
    ) {
        Text(
            "Настройка напоминаний",
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }

    if (showAlertDialog.value) {
        AlertDialogContent(
            onDismiss = {
                showAlertDialog.value = false
            },
            onConfirm = {
                showAlertDialog.value = false
            },
            notificationViewModel
        )
    }
}