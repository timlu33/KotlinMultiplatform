package com.tim.kotlinnative.shared

import platform.UIKit.UIDevice

actual fun platformName(): String = "Kyl ${UIDevice.currentDevice.systemName} ${UIDevice.currentDevice.systemVersion}"