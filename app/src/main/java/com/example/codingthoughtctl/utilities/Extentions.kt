import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Int.getFormattedDate(): String {
    val sdf = SimpleDateFormat("DD/MM/YY hh:mm a", Locale.getDefault())
    val input = Date(this.toLong())
    return sdf.format(input)
}

fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}