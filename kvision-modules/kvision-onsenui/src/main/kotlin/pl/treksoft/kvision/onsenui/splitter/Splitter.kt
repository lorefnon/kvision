/*
 * Copyright (c) 2017-present Robert Jaros
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pl.treksoft.kvision.onsenui.splitter

import com.github.snabbdom.VNode
import org.w3c.dom.HTMLElement
import pl.treksoft.kvision.onsenui.BackButtonEvent
import pl.treksoft.kvision.onsenui.core.Page
import pl.treksoft.kvision.panel.Root
import pl.treksoft.kvision.panel.SimplePanel
import pl.treksoft.kvision.utils.set

/**
 * A splitter component.
 *
 * @constructor Creates a splitter component.
 * @param classes a set of CSS class names
 * @param init an initializer extension function
 */
open class Splitter(
    classes: Set<String> = setOf(),
    init: (Splitter.() -> Unit)? = null
) : SimplePanel(classes) {

    /**
     * A dynamic property returning current left side element.
     */
    @Suppress("UnsafeCastFromDynamic")
    val leftSide: HTMLElement?
        get() = getElement()?.asDynamic()?.left

    /**
     * A dynamic property returning current right side element.
     */
    @Suppress("UnsafeCastFromDynamic")
    val rightSide: HTMLElement?
        get() = getElement()?.asDynamic()?.right

    /**
     * A dynamic property returning current first side element.
     */
    @Suppress("UnsafeCastFromDynamic")
    val side: HTMLElement?
        get() = getElement()?.asDynamic()?.right

    /**
     * A dynamic property returning current content element.
     */
    @Suppress("UnsafeCastFromDynamic")
    val content: HTMLElement?
        get() = getElement()?.asDynamic()?.content

    /**
     * Device back button event listener function.
     */
    protected var onDeviceBackButtonCallback: ((BackButtonEvent) -> Unit)? = null

    init {
        @Suppress("LeakingThis")
        init?.invoke(this)
    }

    override fun render(): VNode {
        return render("ons-splitter", childrenVNodes())
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun afterInsert(node: VNode) {
        if (onDeviceBackButtonCallback != null) {
            getElement()?.asDynamic()?.onDeviceBackButton = onDeviceBackButtonCallback
        }
    }

    /**
     * Sets device back button event listener.
     * @param callback an event listener
     */
    open fun onDeviceBackButton(callback: (event: BackButtonEvent) -> Unit) {
        onDeviceBackButtonCallback = callback
        getElement()?.asDynamic()?.onDeviceBackButton = callback
    }

    /**
     * Clears device back button event listener.
     */
    open fun onDeviceBackButtonClear() {
        onDeviceBackButtonCallback = null
        getElement()?.asDynamic()?.onDeviceBackButton = undefined
    }

}

/**
 * DSL builder extension function.
 *
 * It takes the same parameters as the constructor of the built component.
 */
fun Root.splitter(
    classes: Set<String>? = null,
    className: String? = null,
    init: (Splitter.() -> Unit)? = null
): Splitter {
    val splitter = Splitter(classes ?: className.set, init)
    this.add(splitter)
    return splitter
}

/**
 * DSL builder extension function.
 *
 * It takes the same parameters as the constructor of the built component.
 */
fun Page.splitter(
    classes: Set<String>? = null,
    className: String? = null,
    init: (Splitter.() -> Unit)? = null
): Splitter {
    val splitter = Splitter(classes ?: className.set, init)
    this.add(splitter)
    return splitter
}
