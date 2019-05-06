//
//  AppDelegate.swift
//  KotlinNative
//
//  Created by Tim Lu on 2019/5/5.
//  Copyright © 2019 Tim Lu. All rights reserved.
//

import UIKit
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    
    public lazy var dataRepository = { MembersDataRepository(api: GitHubApi()) }
    

    var window: UIWindow?
    
    static var appDeledated : AppDelegate {
        return UIApplication.shared.delegate as! AppDelegate
    }

}

