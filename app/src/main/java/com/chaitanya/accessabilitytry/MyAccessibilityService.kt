package com.chaitanya.accessabilitytry

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent) {

        val source: AccessibilityNodeInfo = event.source ?: return
        val textViewsText = mutableListOf<String>()
        readTextViews(source, textViewsText)
        source.recycle()

        for (text in textViewsText) {
            Log.d("chece", text)
        }
    }

    override fun onInterrupt() {

    }
    private fun readTextViews(nodeInfo: AccessibilityNodeInfo, textViewsText: MutableList<String>) {
        if (nodeInfo.className == "android.widget.TextView" && nodeInfo.text != null) {
            textViewsText.add(nodeInfo.text.toString())
        }

        for (i in 0 until nodeInfo.childCount) {
            val childNodeInfo = nodeInfo.getChild(i)
            if (childNodeInfo != null) {
                readTextViews(childNodeInfo, textViewsText)
            }
        }
    }


}
