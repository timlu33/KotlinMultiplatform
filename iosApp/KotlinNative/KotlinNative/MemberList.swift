//
//  MemberList.swift
//  KotlinNative
//
//  Created by Tim Lu on 2019/5/5.
//  Copyright © 2019 Tim Lu. All rights reserved.
//

import Foundation
import shared

class MemberList {
    var members: [Member] = []
    
    func updateMembers(_ newMembers: [Member]) {
        members.removeAll()
        members.append(contentsOf: newMembers)
    }
    
}
