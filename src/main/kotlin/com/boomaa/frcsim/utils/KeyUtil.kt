package com.boomaa.frcsim.utils

import org.lwjgl.glfw.GLFW
import java.lang.IllegalArgumentException

object KeyUtil {
    fun decodeSpecialChars(key: Int): String {
        return when (key) {
            GLFW.GLFW_KEY_F10           -> "F10"
            GLFW.GLFW_KEY_F11           -> "F11"
            GLFW.GLFW_KEY_F12           -> "F12"
            GLFW.GLFW_KEY_F13           -> "F13"
            GLFW.GLFW_KEY_F14           -> "F14"
            GLFW.GLFW_KEY_F15           -> "F15"
            GLFW.GLFW_KEY_F16           -> "F16"
            GLFW.GLFW_KEY_F17           -> "F17"
            GLFW.GLFW_KEY_F18           -> "F18"
            GLFW.GLFW_KEY_F19           -> "F19"
            GLFW.GLFW_KEY_F20           -> "F20"
            GLFW.GLFW_KEY_F21           -> "F21"
            GLFW.GLFW_KEY_F22           -> "F22"
            GLFW.GLFW_KEY_F23           -> "F23"
            GLFW.GLFW_KEY_F24           -> "F24"
            GLFW.GLFW_KEY_F25           -> "F25"
            GLFW.GLFW_KEY_ESCAPE        -> "Escape"
            GLFW.GLFW_KEY_ENTER         -> "Enter"
            GLFW.GLFW_KEY_TAB           -> "Tab"
            GLFW.GLFW_KEY_BACKSPACE     -> "Backspace"
            GLFW.GLFW_KEY_INSERT        -> "Insert"
            GLFW.GLFW_KEY_DELETE        -> "Delete"
            GLFW.GLFW_KEY_RIGHT         -> "Right"
            GLFW.GLFW_KEY_LEFT          -> "Left"
            GLFW.GLFW_KEY_DOWN          -> "Down"
            GLFW.GLFW_KEY_UP            -> "Up"
            GLFW.GLFW_KEY_PAGE_UP       -> "PageUp"
            GLFW.GLFW_KEY_PAGE_DOWN     -> "PageDown"
            GLFW.GLFW_KEY_HOME          -> "Home"
            GLFW.GLFW_KEY_END           -> "End"
            GLFW.GLFW_KEY_CAPS_LOCK     -> "CapsLock"
            GLFW.GLFW_KEY_SCROLL_LOCK   -> "ScrollLock"
            GLFW.GLFW_KEY_NUM_LOCK      -> "NumLock"
            GLFW.GLFW_KEY_PRINT_SCREEN  -> "PrintScreen"
            GLFW.GLFW_KEY_PAUSE         -> "Pause"
            GLFW.GLFW_KEY_KP_DECIMAL    -> "KPDecimal"
            GLFW.GLFW_KEY_KP_DIVIDE     -> "KPDivide"
            GLFW.GLFW_KEY_KP_MULTIPLY   -> "KPMultiply"
            GLFW.GLFW_KEY_KP_SUBTRACT   -> "KPSubtract"
            GLFW.GLFW_KEY_KP_ADD        -> "KPAdd"
            GLFW.GLFW_KEY_KP_ENTER      -> "KPEnter"
            GLFW.GLFW_KEY_KP_EQUAL      -> "KPEqual"
            GLFW.GLFW_KEY_LEFT_SHIFT    -> "LeftShift"
            GLFW.GLFW_KEY_LEFT_CONTROL  -> "LeftControl"
            GLFW.GLFW_KEY_LEFT_ALT      -> "LeftAlt"
            GLFW.GLFW_KEY_LEFT_SUPER    -> "LeftSuper"
            GLFW.GLFW_KEY_RIGHT_SHIFT   -> "RightShift"
            GLFW.GLFW_KEY_RIGHT_CONTROL -> "RightControl"
            GLFW.GLFW_KEY_RIGHT_ALT     -> "RightAlt"
            GLFW.GLFW_KEY_RIGHT_SUPER   -> "RightSuper"
            GLFW.GLFW_KEY_MENU          -> "Menu"
            else -> throw IllegalArgumentException("Invalid special GLFW character")
        }
    }
}