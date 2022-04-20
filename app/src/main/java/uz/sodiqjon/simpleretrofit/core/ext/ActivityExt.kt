import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by artCore on 28.12.15.
 */
fun AppCompatActivity.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.toggleKeyboard(view: View) {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
    inputManager.toggleSoftInputFromWindow(
        view.applicationWindowToken,
        InputMethodManager.SHOW_FORCED,
        0
    );
}

fun Activity.loadAsync(@LayoutRes res: Int, target: ViewGroup? = null, action: View.() -> Unit) =
    AsyncLayoutInflater(this).inflate(res, target)
    { view, _, parent ->
        with(parent) {
            this?.addView(view)
            action(view)
        }
    }

fun <R> CoroutineScope.executeAsyncTask(
    onPreExecute: () -> Unit,
    doInBackground: () -> R,
    onPostExecute: (R) -> Unit
) = launch {
    onPreExecute() // runs in Main Thread
    val result = withContext(Dispatchers.IO) {
        doInBackground() // runs in background thread without blocking the Main Thread
    }
    onPostExecute(result) // runs in Main Thread
}
