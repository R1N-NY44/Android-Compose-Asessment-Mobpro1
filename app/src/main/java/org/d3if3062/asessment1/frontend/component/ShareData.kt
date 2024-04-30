package org.d3if3062.asessment1.frontend.component

import android.content.Context
import android.content.Intent

fun shareTodo(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}
fun shareLink(context: Context, url: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "link/plain"
        putExtra(Intent.EXTRA_TEXT, url)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}
