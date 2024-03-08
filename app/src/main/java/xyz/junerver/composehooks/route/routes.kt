package xyz.junerver.composehooks.route

import androidx.compose.runtime.Composable
import xyz.junerver.composehooks.HomeScreen
import xyz.junerver.composehooks.example.UseBooleanExample
import xyz.junerver.composehooks.example.UseDebounceExample
import xyz.junerver.composehooks.example.UseIntervalExample
import xyz.junerver.composehooks.example.UseLatestExample
import xyz.junerver.composehooks.example.UseMountExample
import xyz.junerver.composehooks.example.UseNetworkExample
import xyz.junerver.composehooks.example.UseRefExample
import xyz.junerver.composehooks.example.UseUpdateExample

/**
 * Description:
 * @author Junerver
 * date: 2024/3/8-8:50
 * Email: junerver@gmail.com
 * Version: v1.0
 */
val routes = arrayOf<Pair<String, @Composable () -> Unit>>(
    "/" to { HomeScreen() },
    "useNetwork" to { UseNetworkExample() },
    "useRequest" to { TODO() },
    "useBoolean" to { UseBooleanExample() },
    "useContext" to { TODO() },
    "useCreation" to { TODO() },
    "useDebounce" to { UseDebounceExample() },
    "useEffect" to { TODO() },
    "useInterval" to { UseIntervalExample() },
    "useLatest" to { UseLatestExample() },
    "useList" to { TODO() },
    "useMap" to { TODO() },
    "useMount" to { UseMountExample() },
    "useNumber" to { TODO() },
    "usePrevious" to { TODO() },
    "useReducer" to { TODO() },
    "useRef" to { UseRefExample() },
    "useState" to { TODO() },
    "useThrottle" to { TODO() },
    "useTimeout" to { TODO() },
    "useToggle" to { TODO() },
    "useUndo" to { TODO() },
    "useUnmount" to { UseMountExample() },
    "useUpdate" to { UseUpdateExample() },
)
