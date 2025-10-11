package com.smile.mypark

import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.darwin.DISPATCH_TIME_NOW
import platform.darwin.NSEC_PER_SEC
import platform.darwin.dispatch_after
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
actual fun showToast(message: String) {
    val alert = UIAlertController.alertControllerWithTitle(
        title = null,
        message = message,
        preferredStyle = UIAlertControllerStyleAlert
    )
    UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(alert, animated = true, completion = {
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, 1L * NSEC_PER_SEC.toLong() ), dispatch_get_main_queue()) {
            alert.dismissViewControllerAnimated(true, completion = null)
        }
    })
}