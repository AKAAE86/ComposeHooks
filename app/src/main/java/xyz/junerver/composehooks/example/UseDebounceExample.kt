package xyz.junerver.composehooks.example

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import xyz.junerver.compose.hooks.invoke
import xyz.junerver.compose.hooks.useDebounce
import xyz.junerver.compose.hooks.useDebounceFn
import xyz.junerver.compose.hooks.useState
import xyz.junerver.composehooks.ui.component.TButton

/**
 * Description:
 *
 * @author Junerver date: 2024/3/8-14:13 Email: junerver@gmail.com Version:
 *     v1.0
 */
@Composable
fun UseDebounceExample() {
    val (state, setState) = useState(0)
    val debouncedState = useDebounce(value = state)

    val (stateFn, setStateFn) = useState(0)
    val debouncedFn = useDebounceFn(fn = { setStateFn(stateFn + 1) })
    Surface {
        Column {
            Text(text = "current: $state")
            Text(text = "debounced: $debouncedState")
            TButton(text = "+1") {
                setState(state + 1)
            }

            Text(text = "current: $stateFn")
            TButton(text = "debounced +1") {
                /** Manual import：`import xyz.junerver.compose.hooks.invoke` */
                debouncedFn()
            }
        }
    }
}
