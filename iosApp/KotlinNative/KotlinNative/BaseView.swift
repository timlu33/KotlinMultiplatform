//
//  BaseView.swift
//  KotlinNative
//
//  Created by Tim Lu on 2019/5/5.
//  Copyright © 2019 Tim Lu. All rights reserved.
//

import Foundation
import shared
import UIKit

extension UIViewController: BaseView {
    
    public func showError(error: KotlinThrowable) {
        let title: String = "Error"
        var errorMessage: String? = nil
        
        switch error {
        case is UpdateProblem:
            errorMessage = "Fail to get data"
        default:
            errorMessage = "Unknown Error"
        }
        
        if let message = errorMessage {
            self.showError(title: title, message: message)
        }
        
    }
    
    
    func showError(title: String, message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: UIAlertController.Style.alert)
        alertController.addAction(UIAlertAction(title: "Dismiss", style: UIAlertAction.Style.default, handler: nil))
        
        
        self.present(alertController, animated: true, completion: nil)
        
    }
    
}
