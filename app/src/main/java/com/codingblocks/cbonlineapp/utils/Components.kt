package com.codingblocks.cbonlineapp.utils

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.browser.customtabs.CustomTabsIntent
import com.codingblocks.cbonlineapp.R
import com.codingblocks.cbonlineapp.activities.HomeActivity
import kotlinx.android.synthetic.main.custom_dialog.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.singleTop

object Components {
    fun showconfirmation(context: Context, type: String) {
        val confirmDialog = AlertDialog.Builder(context).create()
        val updateView = context.layoutInflater.inflate(R.layout.custom_dialog, null)
        updateView.okBtn.setOnClickListener {
            when (type) {
                "trial" -> context.startActivity(context.intentFor<HomeActivity>("course" to "mycourses").singleTop())
                "verify" -> {
                    val builder = CustomTabsIntent.Builder()
                            .enableUrlBarHiding()
                            .setToolbarColor(context.resources.getColor(R.color.colorPrimaryDark))
                            .setShowTitle(true)
                            .setSecondaryToolbarColor(context.resources.getColor(R.color.colorPrimary))
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(context, Uri.parse("https://account.codingblocks.com/users/me"))
                }

            }
        }
        updateView.cancelBtn.setOnClickListener {
            confirmDialog.dismiss()
        }
        confirmDialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        confirmDialog.setView(updateView)
        confirmDialog.setCancelable(false)
        confirmDialog.show()
    }
}