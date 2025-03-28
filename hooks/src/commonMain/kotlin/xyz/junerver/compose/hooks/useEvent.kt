package xyz.junerver.compose.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import xyz.junerver.compose.hooks.utils.HooksEventManager

/*
  Description: More convenient communication between components, just like using EventBus
  Author: Junerver
  Date: 2024/3/13-8:11
  Email: junerver@gmail.com
  Version: v1.0
*/

/**
 * Description: 在Compose中，子组件可以轻易的调用父组件的函数，例如通过
 * props 参数传递、使用 [useContext]钩子暴露等。
 * 但是如果我们希望让父组件调用子组件的方法，举个最简单的例子：父组件中的刷新按钮可以刷新子组件的网络请求， 这时候就有点麻烦了。
 *
 * 我们当然可以传递状态给子组件，当父组件点击刷新按钮时子组件通过[useEffect]监听状态，调用刷新函数， 但是这不够优雅。
 *
 * 现在你可以通过[useEventSubscribe]和[useEventPublish] 这两个钩子来轻松实现组件之间的通讯了，它们非常简单。
 *
 * 在子组件使用[useEventSubscribe]注册一个订阅函数，你无需关注他的生命周期，它会在组件挂载时注册，在卸载时反注册。
 * 在父组件中使用[useEventPublish]获得一个post函数，在适当的时机调用post函数，传递你的事件对象。
 *
 * Description: In Compose, child components can easily call the functions
 * of the parent component, such as passing props parameters, using
 * [useContext] hook exposure, etc.
 *
 * But if we want the parent component to call the child component's
 * method, let's take the simplest example: the refresh button in the
 * parent component can refresh the network request of the child component,
 * which is a bit troublesome at this time.
 *
 * Of course we can pass the state to the child component. When the parent
 * component clicks the refresh button, the child component listens to the
 * state through [useEffect] and calls the refresh function, but this is
 * not elegant enough.
 *
 * Now you can easily implement communication between components through
 * the two hooks [useEventSubscribe] and [useEventPublish].They are very
 * simple.
 *
 * Use [useEventSubscribe] to register a subscription function in the
 * subcomponent. You do not need to pay attention to its life cycle. It
 * will be registered when the component is mounted and deregistered when
 * it is uninstalled.
 *
 * Use [useEventPublish] in the parent component to get a post function.
 * Call the post function at the appropriate time and pass your event
 * object.
 */

/**
 * Register a subscriber. Note that this subscription function will be
 * removed from the subscription list after the component is uninstalled.
 *
 * @param subscriber
 * @param T
 * @receiver
 */
@Composable
inline fun <reified T : Any> useEventSubscribe(noinline subscriber: (T) -> Unit) {
    val latest by useLatestState(subscriber)
    var unSubscribeRef by useRef<(() -> Unit)?>(null)

    useMount {
        unSubscribeRef = HooksEventManager.register(T::class, latest)
    }

    useUnmount {
        unSubscribeRef?.invoke()
    }
}

/**
 * This hook returns a publish function, use that fun to post a event.
 *
 * @param T
 * @return
 */
@Composable
inline fun <reified T : Any> useEventPublish(): (T) -> Unit = remember {
    { event: T -> HooksEventManager.post(event, T::class) }
}

/**
 * 仅在库内部使用的事件订阅
 *
 * @param alias
 * @param subscriber
 * @param T
 * @receiver
 */
@Composable
internal fun <T> useEventSubscribe(alias: String, subscriber: (T?) -> Unit) {
    val latest by useLatestState(subscriber)
    var unSubscribeRef by useRef<(() -> Unit)?>(null)

    useMount {
        unSubscribeRef = HooksEventManager.register(alias, latest)
    }

    useUnmount {
        unSubscribeRef?.invoke()
    }
}

/**
 * 仅在库内部使用的事件发布
 *
 * @param T
 * @return
 */
@Composable
internal fun <T> useEventPublish(alias: String): (T) -> Unit = remember {
    { event: T -> HooksEventManager.post(alias, event) }
}
