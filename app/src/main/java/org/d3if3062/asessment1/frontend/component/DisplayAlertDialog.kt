package org.d3if3062.asessment1.frontend.component

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3062.asessment1.R

@Composable
fun DisplayAlertDialog(
    openDialog: Boolean,
    alertMessage: Int,
    alertConfirmMessage: Int,
    alertDismissMessage: Int,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            text = { Text(text = stringResource(id = alertMessage)) },
            confirmButton = {
                TextButton(
                    onClick = { onConfirmRequest() }
                ) {
                    Text(text = stringResource(id = alertConfirmMessage))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismissRequest() }
                ) {
                    Text(text = stringResource(id = alertDismissMessage))
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogPreview() {
    DisplayAlertDialog(
        openDialog = true,
        alertMessage = R.string.alert_delete,
        alertConfirmMessage = R.string.alert_yes,
        alertDismissMessage = R.string.alert_no,
        onDismissRequest = { },
        onConfirmRequest = { }
    )
}
